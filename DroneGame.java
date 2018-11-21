import javax.swing.*;
import java.util.TimerTask;
import java.util.Timer;

public class DroneGame {
    private Airplane[] airplanes;
    private int gameTime;
    private Timer timer;
    private Scoreboard scoreboard;
    private Drone drone;

    /*
    DroneGame
    No-args constructor that creates an instance of a drone game
    Creates an instance of timer, scoreboard, and drone
    Game time is initialize at 90000ms or 1 min 30 sec
     */
    public DroneGame() {
        timer = new Timer();
        gameTime = 90000;
        scoreboard = new Scoreboard();
        drone = new Drone();
    }

    /*
        Starts the game
        Creates the game window with drone at starting position, a game time, and score
        Stopwatch starts
     */
    public void startGame() {
        // Creates game window
        // Displays Play Button and Configurations Button
        JFrame gameWindow = new JFrame("Drone Project");
        gameWindow.setSize(800, 400);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel gameTimePanel = new JPanel();
        gameTimePanel.add(new JLabel("Time: " + convertTime(gameTime)));
        // Initialize scoreboard, Drone, Airplanes, MovingPlain, Collisions...
        startStopwatch();
        // Collision Event
    }

    /*
    Converts gametime from ms to minutes and seconds
     */
    private String convertTime(int time) {
        int convertedToSeconds = time / 1000;
        int minutes = convertedToSeconds / 60;
        int seconds = convertedToSeconds % 60;
        return minutes + ":" + seconds;
    }

    /*
    Restarts the game by resetting all variables
     */
    private void restartGame() {
        timer.cancel();
        gameTime = 90000;
        scoreboard = new Scoreboard();
        drone = new Drone();
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
            }
        }, 0, 1000);
    }
}
