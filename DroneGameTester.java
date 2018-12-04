import javax.swing.*;
import java.awt.*;

/*
Main Tester class
Use the main method to run the game/project
 */
public class DroneGameTester {
    private static JFrame menuWindow;
    private static DroneGame game;

    public static void main(String[] args) {
        game = new DroneGame();
        menuWindow = new JFrame();
        menuWindow.setSize(500, 300);
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuWindow.setLayout(new BorderLayout());
        JLabel title = new JLabel("Drone Project", SwingConstants.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton playButton = new JButton("Play Game");
        playButton.addActionListener(event -> {
            menuWindow.setVisible(false);
            game.startGame();
        });
        JButton configurationButton = new JButton("Configure");
        configurationButton.addActionListener(event -> {
            game.setConfigurations();
        });
        buttonPanel.add(playButton);
        buttonPanel.add(Box.createHorizontalStrut(100));
        buttonPanel.add(configurationButton);
        menuWindow.add(title);
        menuWindow.add(buttonPanel, BorderLayout.SOUTH);
        menuWindow.setLocationRelativeTo(null);
        menuWindow.setVisible(true);
    }

    public static void restartGame() {
        menuWindow.setVisible(true);
        game = new DroneGame();
    }
}
