import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.TimerTask;
import java.util.Timer;
import java.awt.event.*;
import java.awt.Shape;
import java.awt.geom.Area;

public class DroneGame extends JPanel {
    private Airplane[] airplanes;
    private int gameTime;
    private Timer timer;
    private Scoreboard scoreboard;
    private Drone drone;
    private JFrame gameWindow;
    private JLabel timeLabel;
    private int scalar;

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
        scoreboard = new Scoreboard();
        gameWindow = new JFrame("Drone Project");
        timeLabel = new JLabel("Time: " + convertTime(gameTime), SwingConstants.CENTER);
        // Creates game window
        gameWindow.setSize(852, 500); // Set to match background resolution. Need to find a way to scale background_img with frame
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setLayout(new BorderLayout());
        gameWindow.setResizable(false);

        // Initialize background image
        try {
            BufferedImage background_img = ImageIO.read(getClass().getResource("/resources/images/cloudybg.jpg"));
            gameWindow.setContentPane(new BackgroundImage(background_img));
        } catch (IOException e) {
            System.out.println("Error reading background image");
        }

        // Initialize scoreboard, Drone, Airplanes, Collisions...
        drone = new Drone();
        scoreboard.setTotalScore(5);
        airplanes = new Airplane[1];
        for (int i = 0; i < airplanes.length; i++) airplanes[i] = new Airplane();
        gameWindow.add(scoreboard, BorderLayout.SOUTH);
        gameWindow.add(timeLabel, BorderLayout.NORTH);
        gameWindow.add(drone);
        drone.setFocusable(true);
    }

    /*
        Starts the game - Spawns airplanes, starts stopwatch
     */
    public void startGame() {
        gameWindow.setVisible(true);
        
        spawnAirplanes();
        startStopwatch();
        startGameTimer();
        
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
    Runs when timer ends and initiates the next round with more, slightly faster airplanes. Scales with difficulty
     */
    private void nextLevel() {
        scoreboard.setTotalScore(scoreboard.getTotalScore() + 2);
    }

    /*
    Restarts the game to level 1 by resetting all variables
     */
    private void restartGame() {
        timer.cancel();
        gameTime = 90;
        scoreboard = new Scoreboard();
        drone = new Drone();
    }

    /*
     Restarts the current round
     */
    private void restartRound() {
        //TBD
    }

    /*
    Listens for key press and opens a pause panel in game window
    Three buttons: Resume, Restart Round, and Quit
     */
    private void openPauseMenu() {
        //TBD
    }

    /*
    Runs when player reaches the end of the round
    Displays score and a button to initiate the next level or to quit
    If next level button clicked, run nextLevel()
    If quit button clicked, run quitGame()
     */
    private void roundEnd() {
        timer.cancel();
    }

    /*
    Runs when user clicks Quit in the pause menu or next round menu
    Brings user back to the menu window
    Should run the restartGame method
     */
    private void quitGame() {
        // TBD
    }

    /*
    User can set configurations including:
        - Difficulty
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
            scalar = 1;
        });
        hardButton.addActionListener(event -> {
            System.out.println("Set to hard mode");
            scalar = 5;
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
                checkIfCollision();
                drone.move();
                moveAirplanes();
            }
        }, 0, 15);
    }

    private void startGameTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                checkIfCollision();
                gameTime--;
                if (gameTime < 1) roundEnd();
                else timeLabel.setText("Time: " + convertTime(gameTime));
            }
        }, 0, 1000);
    }

    /*
    Starts the movement of airplanes onto gameWindow
     */
        private void spawnAirplanes() {
            gameWindow.add(airplanes[0]);
        }

        private void moveAirplanes() {
            for (int i = 0; i < airplanes.length; i++) airplanes[i].moveLeft(scalar);
        }
 
    private void checkIfCollision() {
        Rectangle d = new Rectangle(drone.getX() + 25, drone.getY() + 52, 125, 40);
        for (int i = 0; i < airplanes.length; i++){
            Rectangle p = new Rectangle(airplanes[i].getX() + 100, airplanes[i].getY() + 65, 5 , 28);
            if(d.intersects(p)){
                System.out.println("collide");
                System.out.println(drone.getX() + 25 + " to " + (drone.getX() + 125));
                System.out.println((airplanes[i].getX() + 100) + " to " + (airplanes[i].getX() + 105 ));
            }
        }
    }
        
}
