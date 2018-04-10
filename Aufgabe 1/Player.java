public class Player {

    private char tokenChar;
    private String name;
    private int highScore;

    public char getTokenChar() {
        return tokenChar;
    }
    public String getName() {
        return name;
    }
    public int getHighScore() {
        return highScore;
    }

    public Player(String playerName, char playerChar) {
        name = playerName;
        tokenChar = playerChar;
    }

    public void winGame() {
        highScore++;
    }

    @Override
    public String toString() {
        String newLine = System.lineSeparator();
        return "Name: " + getName() + newLine +
               "Char: " + getTokenChar() + newLine +
               "Highscore: " + getHighScore() + newLine;
    }

}
