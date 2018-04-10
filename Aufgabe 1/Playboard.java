public class Playboard {

    public static final int COLUMNS = 7;
    public static final int ROWS = 6;
    private final String FIELD_SEPARATOR = "|";
    private Token[][] boardMatrix = new Token[COLUMNS][ROWS]; // so boardMatrix[0].length = COLUMNS

    public Token[][] getBoardMatrix(){
        return boardMatrix;
    }

    public Playboard() {
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < ROWS; row++) {
                boardMatrix[col][row] = new Token(col, row);
            }
        }
    }

    public Token addToken(int column, Player player) throws ColumnFullException {
        int row = getNextEmptyRow(column);
        if (row == -1)
            throw new ColumnFullException(column);

        boardMatrix[column][row].setPlayer(player);
        return boardMatrix[column][row];
    }

    public void reset() {
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < ROWS; row++)
                boardMatrix[col][row].setPlayer(null);
        }
    }

    @Override
    public String toString() {
        String newLine = System.lineSeparator();
        String result = "";
        // Column number caption
        for (int i = 1; i <= COLUMNS; i++)
            result += i + " ";
        result += newLine;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                Token iterToken = boardMatrix[col][row];
                result += iterToken + FIELD_SEPARATOR;
            }
            result += newLine;
        }
        return result;
    }

    private int getNextEmptyRow(int col) {
        for (int row = Playboard.ROWS - 1; row >= 0; row--){
            Token iterToken = boardMatrix[col][row];
            if (iterToken.isEmpty())
                return row;
        }
        return -1;
    }
}
