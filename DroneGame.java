import javax.swing.*;
import java.util.TimerTask;
import java.util.Timer;

public class DroneGame {
    private int[] airplanes;
    private int gameTime;
    private Timer timer;
    private Scoreboard scoreboard;

    public DroneGame() {
        timer = new Timer();
        gameTime = 90000;
        scoreboard = new Scoreboard();
    }

    // Starts a stopwatch
    // Creates an instance of scoreboard
    // Count points and lives
    // Manage collisions (Keep track of drone location and airplanes locations
    public void startGame() {
        // Creates game window
        // Displays Play Button and Configurations Button
        JFrame gameWindow = new JFrame("Drone Project");
        gameWindow.setSize(800, 400);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Initialize scoreboard, Drone, Airplanes, MovingPlain, Collisions...
        startStopwatch();
        // Collision Event
    }

    private void restartGame() {
        timer.cancel();
        gameTime = 90000;
        scoreboard = new Scoreboard();
    }

    public void setConfigurations() {
        JFrame gameWindow = new JFrame("Configurations");
        gameWindow.setSize(300, 300);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add Settings
    }

    private void startStopwatch() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameTime--;
            }
        }, 0, 1000);
    }
}
