import java.util.LinkedList;
import java.util.List;

public class IncrementalWinDetector implements IWinDetector {

    private enum WinCondition {
        Horizontal,
        Vertical,
        Diagonal
    }

    private TokenPlace[][] boardMatrix;
    private List<TokenPlace> connecting4 = new LinkedList<>();

    public  IncrementalWinDetector(TokenPlace[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
    }

    @Override
    public List<TokenPlace> getConnecting4() {
        return connecting4;
    }

    public boolean AddToken(TokenPlace newTokenPlace) {
        if (newTokenPlace.isEmpty())
            return false;
        return (checkWin(WinCondition.Diagonal, newTokenPlace));
    }

    private boolean checkWin(WinCondition condition, TokenPlace newTokenPlace) {
        if (condition == WinCondition.Diagonal)
            return checkDiagonalWin(newTokenPlace);
        // if condition == horizontal => Row is fixed, iterate through columns
        // if condition == vertical   => column is fixed, iterate through rows
        // always check the 3 neighbors (vertical or horizontal) of the newTokenPlace
        int limit = condition == WinCondition.Horizontal ?
                                 Playboard.COLUMNS : Playboard.ROWS;
        for (int i = 0; i < limit; i++) {
            for (int j = i; j < limit; j++) {
                TokenPlace iterTokenPlace;
                if (condition == WinCondition.Horizontal) // iterate through columns
                    iterTokenPlace = boardMatrix[j][newTokenPlace.getRow()];
                else                                      // iterate through rows
                    iterTokenPlace = boardMatrix[newTokenPlace.getColumn()][j];

                if (iterTokenPlace.getPlayer() == newTokenPlace.getPlayer())
                    connecting4.add(iterTokenPlace);
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

    private boolean checkDiagonalWin(TokenPlace newTokenPlace) {
        for (int iCol = 0; iCol < Playboard.COLUMNS; iCol++) {
            for (int iRow = 0; iRow < Playboard.ROWS; iRow++) {
                TokenPlace iterToken = boardMatrix[iCol][iRow];
                if (iterToken.getPlayer() == newTokenPlace.getPlayer())
                    connecting4.add(iterToken);
                else
                    connecting4.clear();

                if (connecting4.size() == 4)
                    return true;
            }
        }
        for (int iCol = Playboard.COLUMNS - 1; iCol >= 0 ; iCol--) {
            for (int iRow = Playboard.ROWS - 1; iRow >= 0 ; iRow--) {
                TokenPlace iterToken = boardMatrix[iCol][iRow];
                if (iterToken.getPlayer() == newTokenPlace.getPlayer())
                    connecting4.add(iterToken);
                else
                    connecting4.clear();

                if (connecting4.size() == 4)
                    return true;
            }
        }
        for (int iCol = Playboard.COLUMNS -1; iCol >= 0; iCol--) {
            for (int iRow = 0; iRow < Playboard.ROWS; iRow++) {
                TokenPlace iterToken = boardMatrix[iCol][iRow];
                if (iterToken.getPlayer() == newTokenPlace.getPlayer())
                    connecting4.add(iterToken);
                else
                    connecting4.clear();

                if (connecting4.size() == 4)
                    return true;
            }
        }
        for (int iCol = 0; iCol < Playboard.COLUMNS ; iCol--) {
            for (int iRow = Playboard.ROWS - 1; iRow >= 0 ; iRow--) {
                TokenPlace iterToken = boardMatrix[iCol][iRow];
                if (iterToken.getPlayer() == newTokenPlace.getPlayer())
                    connecting4.add(iterToken);
                else
                    connecting4.clear();

                if (connecting4.size() == 4)
                    return true;
            }
        }
        return false;
    }

}
