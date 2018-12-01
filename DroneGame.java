import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.TimerTask;
import java.util.Timer;

public class DroneGame {
    private Airplane[] airplanes;
    private int gameTime;
    private Timer timer;
    private Scoreboard scoreboard;
    private Drone drone;
    private JFrame gameWindow;
    private JLabel timeLabel;

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
            BufferedImage img = ImageIO.read(getClass().getResource("/resources/images/cloudybg.jpg"));
            gameWindow.setContentPane(new JLabel(new ImageIcon(img)));
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
        // Add Settings here
    }

    /*
    Starts the game time and increment gameTime every 1000ms or 1 second
     */
    private void startStopwatch() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
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
