import javax.swing.*;
import java.awt.*;

public class Scoreboard extends JLabel {
    private int score;

    /*
    No-args constructor that creates an instance of Scoreboard
    Score is initialize at 0
     */
    public Scoreboard() {
        super("Score: 0", SwingConstants.CENTER);
        setFont(new Font("DialogInput", Font.BOLD, 30));
        score = 0;
    }

    /*
    A helper method that adds points to the score
     */
    public void addPoints(int points) {
        score += points;
        setText("Score: " + score);
    }
}
