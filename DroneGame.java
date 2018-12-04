import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class DroneGame extends JPanel implements KeyListener {
    private List<Airplane> airplanes;
    private int gameTime;
    private Timer timer;
    private Scoreboard scoreboard;
    private Drone drone;
    private JFrame gameWindow;
    private JLabel timeLabel;
    private int scalar;
    private int planeSpeed;
    private boolean paused;

    /*
    DroneGame
    No-args constructor that creates an instance of a drone game
    Creates an instance of timer, scoreboard, and drone
    Game time is initialize at 90000ms or 1 min 30 sec
     */
    public DroneGame() {
        timer = new Timer();
        gameTime = 90;
        scalar = 1;
        planeSpeed = 1;
        scoreboard = new Scoreboard();
        gameWindow = new JFrame("Drone Project");
        timeLabel = new JLabel("Time: " + convertTime(gameTime), SwingConstants.CENTER);
        timeLabel.setFont(new Font("DialogInput", Font.BOLD, 30));
        // Creates game window
        int width = 1366;
        int height = 768;
        gameWindow.setSize(width, height); // Set to match background resolution. Need to find a way to scale background_img with frame
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setLayout(new BorderLayout());
        gameWindow.setResizable(false);
        gameWindow.addKeyListener(this);
        gameWindow.setFocusable(true);

        // Initialize background image
        try {
            BufferedImage background_img = ImageIO.read(getClass().getResource("/resources/images/background.jpg"));
            gameWindow.setContentPane(new BackgroundImage(background_img, width, height));
        } catch (IOException e) {
            System.out.println("Error reading background image");
        }

        // Initialize scoreboard, Drone, Airplanes, Collisions...
        drone = new Drone();
        airplanes = new ArrayList<>();
        gameWindow.add(scoreboard, BorderLayout.SOUTH);
        gameWindow.add(timeLabel, BorderLayout.NORTH);
        gameWindow.add(drone);
    }

    /*
    User can set configurations including:
        - Difficulty
     */
    public void setConfigurations() {
        JFrame configurationWindow = new JFrame("Configurations");
        configurationWindow.setSize(300, 200);
        configurationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configurationWindow.setVisible(true);
        configurationWindow.setResizable(false);

        BoxLayout config = new BoxLayout(configurationWindow.getContentPane(), BoxLayout.Y_AXIS);
        configurationWindow.setLayout(config);

        JPanel difficultyPanel = new JPanel();
        JButton easyButton = new JButton("Easy Mode");
        JButton hardButton = new JButton("Hard Mode");
        difficultyPanel.add(easyButton);
        difficultyPanel.add(Box.createHorizontalStrut(25));
        difficultyPanel.add(hardButton);

        JLabel difficultyDisplay = new JLabel("Current Difficulty: Easy Mode");
        difficultyDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel exitPanel = new JPanel();
        JButton exitButton = new JButton("Exit");
        exitPanel.add(exitButton);

        configurationWindow.add(difficultyPanel);
        configurationWindow.add(difficultyDisplay);
        configurationWindow.add(exitPanel);

        easyButton.addActionListener(event -> {
            System.out.println("Set to easy mode");
            difficultyDisplay.setText("Current Difficulty: " + "Easy Mode");
            planeSpeed = 1;
            scalar = 1;
        });
        hardButton.addActionListener(event -> {
            System.out.println("Set to hard mode");
            difficultyDisplay.setText("Current Difficulty: " + "Hard Mode");
            planeSpeed = 2;
            scalar = 2;
        });
        exitButton.addActionListener(event -> {
            configurationWindow.setVisible(false);
            configurationWindow.dispose();
        });
    }

    /*
    Starts the game - Spawns airplanes, starts stopwatch
     */
    public void startGame() {
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
        paused = false;

        spawnAirplanes();
        startStopwatch();
        startGameTimer();
    }

    /*
    Converts gametime from ms to minutes and seconds
     */
    private String convertTime(int time) {
        int minutes = time / 60;
        int seconds = time % 60;
        String secondsString;
        if (seconds < 10) secondsString = "0"+seconds;
        else secondsString = ""+seconds;
        return minutes + ":" + secondsString;
    }

    /*
    Runs when player clicks next level at round end
    Initiates the next round with more and slightly faster airplanes. Scales with difficulty
     */
    private void nextRound() {
        timer = new Timer();
        planeSpeed += scalar;
        gameTime = 90;
        System.out.println("New Round");
        startGame();
    }

    /*
    Listens for key press and opens a pause panel in game window
    Two Buttons: Resume and Quit
     */
    private void openPauseMenu() {
        if (paused) return;
        System.out.println("Pause Menu Opened");
        gameWindow.setFocusable(false);
        paused = true;
        timer.cancel();
        JFrame pauseFrame = new JFrame();
        pauseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pauseFrame.setUndecorated(true);
        pauseFrame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        pauseFrame.setVisible(true);
        pauseFrame.setResizable(false);

        JPanel buttonsPanel = new JPanel();
        JButton resume = new JButton("Resume");
        JButton quit = new JButton("Quit");
        buttonsPanel.add(resume);
        buttonsPanel.add(quit);
        resume.addActionListener(event -> {
            System.out.println("Resume");
            pauseFrame.setVisible(false);
            timer = new Timer();
            paused = false;
            gameWindow.setFocusable(true);
            spawnAirplanes();
            startStopwatch();
            startGameTimer();
        });
        quit.addActionListener(event -> {
            System.out.println("Quit");
            pauseFrame.setVisible(false);
            paused = false;
            quitGame();
        });

        pauseFrame.add(buttonsPanel);
        pauseFrame.pack();
        pauseFrame.setLocationRelativeTo(null);
    }

    /*
    Runs when player reaches the end of the round
    Displays score and a button to initiate the next level or to quit
    If continue button clicked, run nextLevel()
    If quit button clicked, run quitGame()
     */
    private void roundEnd() {
        timer.cancel();
        timer.purge();
        // Pop-Up Window
        JFrame roundEndFrame = new JFrame();
        roundEndFrame.setVisible(true);
        roundEndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        roundEndFrame.setResizable(false);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(new JLabel("Total Score: " + scoreboard.getScore()));
        textPanel.add(new JLabel("Would you like to continue?"));

        JPanel buttonPanel = new JPanel();
        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(event -> {
            for (Airplane a : airplanes) gameWindow.remove(a);
            airplanes.clear();
            drone.resetPosition();
            roundEndFrame.setVisible(false);
            nextRound();
        });
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(event -> {
            roundEndFrame.setVisible(false);
            quitGame();
        });

        buttonPanel.add(continueButton);
        buttonPanel.add(exitButton);
        roundEndFrame.add(textPanel, BorderLayout.CENTER);
        roundEndFrame.add(buttonPanel, BorderLayout.SOUTH);
        roundEndFrame.pack();
        roundEndFrame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        DroneGame game = new DroneGame();
        game.openPauseMenu();
    }

    /*
    Runs when user clicks Quit in the pause menu or next round menu
    Brings user back to the menu window
    Should reset game variables
     */
    private void quitGame() {
        gameWindow.setVisible(false);
        DroneGameTester.restartGame();
    }

    /*
    A Timer that controls the movement of the drone and airplanes
     */
    private void startStopwatch() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                checkIfCollision();
                drone.move();
                checkIfOutOfBounds();
                moveAirplanes();
            }
        }, 0, 15);
    }

    /*
    Separate to stopwatch
    Starts the game time and increment gameTime every 1000ms or 1 second
     */
    private void startGameTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
//                checkIfCollision();
                gameTime--;
                if (gameTime < 1) roundEnd();
                else timeLabel.setText("Time: " + convertTime(gameTime));
            }
        }, 0, 1000);
    }

    /*
    Starts the movement of airplanes onto gameWindow
    Every 2000ms or 2 sec, spawn a new airplane at a random y location
    x should always be the same
     */
    private void spawnAirplanes() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Airplane newPlane = new Airplane();
                airplanes.add(newPlane);
                gameWindow.add(newPlane);
            }
        }, 0, 2000 / planeSpeed);
    }

    private void moveAirplanes() {
        for(Airplane a : airplanes) {
            a.moveLeft(planeSpeed);
        }
    }

    private void checkIfOutOfBounds() {
//        for (Airplane a : airplanes) {
//            if (a.getX() < 0) remove(a);
//        }
        if (airplanes.get(0).getX() < -75) {
            remove(airplanes.get(0));
            airplanes.remove(0);
            scoreboard.addPoints(100 * scalar);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() != 80) drone.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() != 80) drone.keyReleased(e);
        else openPauseMenu();
    }

    private void checkIfCollision() {
        Rectangle d = new Rectangle(drone.getX() + 25, drone.getY() + 52, 125, 40);
        for (int i = 0; i < airplanes.size(); i++){
            Rectangle p = new Rectangle(airplanes.get(i).getX() + 100, airplanes.get(i).getY() + 65, 1 , 10);
            if(d.intersects(p)){
                remove(airplanes.get(i));
                airplanes.remove(i);
                //repaint();
            }
        }
    }
}
