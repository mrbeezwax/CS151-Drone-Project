import javax.swing.*;

public class Scoreboard extends JLabel {
    private int score;
    private int totalScore;

    /*
    No-args constructor that creates an instance of Scoreboard
    Score is initialize at 0
     */
    public Scoreboard() {
        super("Score: 0 out of 5", SwingConstants.CENTER);
        score = 0;
        totalScore = 5;
    }

    /*
    A helper method that adds points to the score
     */
    public void addPoints(int points) {
        score += points;
        setText(score+"");
    }

    public void setTotalScore(int score) {
        totalScore = score;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
