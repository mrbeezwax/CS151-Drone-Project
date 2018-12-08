import javax.swing.*;
import java.awt.*;

/*
Main Tester class
Use the main method to run the game/project
 */
class DroneGameTester {
    private static JFrame menuWindow;
    private static DroneGame game;

    public static void main(String[] args) {
        game = new DroneGame();
        menuWindow = new JFrame();
        menuWindow.setSize(300, 300);
        menuWindow.setResizable(false);
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuWindow.setLayout(new BorderLayout());
        menuWindow.setContentPane(new TitleBackgroundImage(300,300));
        JPanel buttonPanel = new JPanel();
        JButton playButton = new JButton("Play Game");
        playButton.addActionListener(event -> {
            menuWindow.setVisible(false);
            game.startGame();
        });
        JButton configurationButton = new JButton("Configure");
        configurationButton.addActionListener(event -> game.setConfigurations());
        buttonPanel.add(playButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(configurationButton);
        menuWindow.add(buttonPanel, BorderLayout.SOUTH);
        menuWindow.setLocationRelativeTo(null);
        menuWindow.setVisible(true);
    }

    public static void restartGame() {
        menuWindow.setVisible(true);
        game = new DroneGame();
    }
}
