import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.TimerTask;
import java.util.Timer;

public class DroneGame extends JPanel {
    private Airplane[] airplanes;
    private int gameTime;
    private Timer timer;
    private Scoreboard scoreboard;
    private Drone drone;
    private JFrame gameWindow;
    private JLabel timeLabel;
    int droneX = 266;
    int droneY = 90;
    BufferedImage img;
    BufferedImage img2;

    /*
    DroneGame
    No-args constructor that creates an instance of a drone game
    Creates an instance of timer, scoreboard, and drone
    Game time is initialize at 90000ms or 1 min 30 sec
     */
    public DroneGame() {
        timer = new Timer();
        gameTime = 90;
        scoreboard = new Scoreboard();
        drone = new Drone();
        gameWindow = new JFrame("Drone Project");
        timeLabel = new JLabel("Time: " + convertTime(gameTime), SwingConstants.CENTER);
        // Creates game window
        gameWindow.setSize(852, 480); // Set to match background resolution. Need to find a way to scale img with frame
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Initialize scoreboard, Drone, Airplanes, MovingPlain, Collisions...
        scoreboard.setTotalScore(5);
        gameWindow.add(scoreboard, BorderLayout.SOUTH);
        gameWindow.add(timeLabel, BorderLayout.NORTH);
        
        try {
            img = ImageIO.read(getClass().getResource("/resources/images/cloudybg.jpg"));
            gameWindow.add(new JLabel(new ImageIcon(img)));
            img2 = ImageIO.read(getClass().getResource("/resources/images/dronepic.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        Starts the game - Spawns airplanes, starts stopwatch
     */
    public void startGame() {
        gameWindow.setVisible(true);

        spawnAirplanes();
        startStopwatch();
        
        // Collision Event

    }

    /*
    Converts gametime from ms to minutes and seconds
     */
    private String convertTime(int time) {
        int minutes = time / 60;
        int seconds = time % 60;
        return minutes + ":" + seconds;
    }

    /*
    Runs when timer ends
     */
    private void nextLevel() {
        scoreboard.setTotalScore(scoreboard.getTotalScore() + 2);
    }

    /*
    Restarts the game by resetting all variables
     */
    private void restartGame() {
        timer.cancel();
        gameTime = 90;
        scoreboard = new Scoreboard();
        drone = new Drone();
    }

    /*
    Runs when player reaches the end of the game
     */
    private void endGame() {
        timer.cancel();
    }

    /*
    User can set configurations including:
        - Difficulty
        - Background
     */
    public void setConfigurations() {
        JFrame gameWindow = new JFrame("Configurations");
        gameWindow.setSize(300, 300);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setVisible(true);
        
        BoxLayout config = new BoxLayout(gameWindow.getContentPane(), BoxLayout.Y_AXIS);
        gameWindow.setLayout(config);
        
        JPanel difficultyPanel = new JPanel();
        JButton easyButton = new JButton("Easy Mode");
        JButton hardButton = new JButton("Hard Mode");
        difficultyPanel.add(easyButton);
        difficultyPanel.add(hardButton);
        
        JPanel exitPanel = new JPanel();
        JButton exitButton = new JButton("Exit");
        exitPanel.add(exitButton);
        
        gameWindow.add(difficultyPanel);
        gameWindow.add(exitPanel);
        
        BoxLayout a = new BoxLayout(gameWindow.getContentPane(), BoxLayout.X_AXIS);
        easyButton.addActionListener(event -> {
            System.out.println("Set to easy mode");
        });
        hardButton.addActionListener(event -> {
            System.out.println("Set to hard mode");
        });
        exitButton.addActionListener(event -> {
            gameWindow.setVisible(false);
            gameWindow.dispose();
        });
    }

    /*
    Starts the game time and increment gameTime every 1000ms or 1 second
     */
    private void startStopwatch() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                    
                Graphics g = img.getGraphics();
                g.drawImage(img2, droneX, droneY, null);
                repaint();
                droneX += 10;
                System.out.println(droneX); 
                
                gameTime--;
                if (gameTime < 1) endGame();
                else timeLabel.setText("Time: " + convertTime(gameTime));
            }
        }, 0, 1000);
    }

    /*
    Creates array of airplane with size of totalScore (number of airplanes)
     */
    private void spawnAirplanes() {
        airplanes = new Airplane[scoreboard.getTotalScore()];
        for (int i = 0; i < scoreboard.getTotalScore(); i++) {
            airplanes[i] = new Airplane();
        }
    }
}
