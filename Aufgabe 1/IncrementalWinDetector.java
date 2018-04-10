import java.util.LinkedList;
import java.util.List;

public class IncrementalWinDetector implements IWinDetector {

    private enum WinCondition {
        Horizontal,
        Vertical,
        Diagonal
    }

    private Token[][] boardMatrix;
    private List<Token> connecting4 = new LinkedList<>();

    public  IncrementalWinDetector(Token[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
    }

    @Override
    public List<Token> getConnecting4() {
        return connecting4;
    }

    public boolean AddToken(Token newToken) {
        if (newToken.isEmpty())
            return false;
        return (checkWin(WinCondition.Horizontal, newToken) || checkWin(WinCondition.Vertical, newToken));
    }

    private boolean checkWin(WinCondition condition, Token newToken) {
        if (condition == WinCondition.Diagonal)
            return checkDiagonalWin(newToken);
        // if condition == horizontal => Row is fixed, iterate through columns
        // if condition == vertical   => column is fixed, iterate through rows
        // always check the 3 neighbors (vertical or horizontal) of the newToken
        int limit = condition == WinCondition.Horizontal ?
                                 Playboard.COLUMNS : Playboard.ROWS;
        for (int i = 0; i < limit; i++) {
            for (int j = i; j < limit; j++) {
                Token iterToken;
                if (condition == WinCondition.Horizontal) // iterate through columns
                    iterToken = boardMatrix[j][newToken.getRow()];
                else                                      // iterate through rows
                    iterToken = boardMatrix[newToken.getColumn()][j];

                if (iterToken.getPlayer() == newToken.getPlayer())
                    connecting4.add(iterToken);
                else {
                    connecting4.clear();
                    break;
                }

                if (connecting4.size() == 4)
                    return true;
            }
            connecting4.clear();
        }
        return false;
    }

    private boolean checkDiagonalWin(Token newToken) {
        if (true)
            return false;
        else if (false)
            return false;
        int col = newToken.getColumn();
        int row = newToken.getRow();
        Player player = newToken.getPlayer();
        Token[] previousDiagonal = new Token[3];
        Token[] nextDiagonal = new Token[3];
        List<Token> diagonal = new LinkedList<>();
        for (int i = 1; i <= 3; i++) {
            int iCol = col - i;
            int iRow = row - i;
            if (iCol >= 0  && iRow >= 0 && boardMatrix[iCol][iRow].getPlayer() == player)
                previousDiagonal[i-1] = boardMatrix[iCol][iRow];

            iCol = col + i;
            iRow = row + i;
            //if (iCol < Playboard.COLUMNS && iRow < Playboard.ROWS && boardMatrix[iCol][iRow] == player)
                nextDiagonal[i-1] = boardMatrix[iCol][iRow];
        }
        return false;
    }

}
