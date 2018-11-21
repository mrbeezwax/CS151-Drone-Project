public class Scoreboard {
    int score;

    /*
    No-args constructor that creates an instance of Scoreboard
    Score is initialize at 0
     */
    public Scoreboard() {
        score = 0;
    }

    /*
    A helper method that adds points to the score
     */
    public void addPoints(int points) {
        score = points;
    }
}
