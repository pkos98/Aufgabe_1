import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class Connect4 {
    private int PLAYERS_NUM = 2;
    private Player[] players;
    private Playboard playboard;
    private IWinDetector winDetector;
    private InputController inputController;

    public static final boolean IS_DEBUG =
            java.lang.management.ManagementFactory.getRuntimeMXBean().
                    getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;

    public static void main(String[] args) throws IOException {
        Connect4 game = new Connect4();
        game.start();
    }

    public Connect4() throws IOException {
        playboard = new Playboard();
        winDetector = new IncrementalWinDetector(playboard.getBoardMatrix());
        inputController = new InputController(playboard);
        players = createPlayers();
    }

    public void start() throws IOException {
        System.out.println(playboard);
        for (int i = 0; true; i++)  {
            if (i == PLAYERS_NUM)
                i = 0;
            Player currentPlayer = players[i];
            int columnInput = -1;
            TokenPlace newTokenPlace;
            while(true){

                // Get valid input, if there is no valid input
                if (columnInput == -1)
                    columnInput = inputController.getColumnInput(currentPlayer) - 1;

                try {
                    newTokenPlace = playboard.addToken(columnInput, currentPlayer);
                    break;
                } catch (ColumnFullException e) {
                    System.out.println(e.getMessage());
                    columnInput = inputController.getColumnInput(currentPlayer) - 1; // Convert to 0 based
                    continue;
                }
            }
            System.out.print(playboard);
            if (winDetector.AddToken(newTokenPlace))
                GameFinishedEvent(currentPlayer);
        }
    }

    private Player[] createPlayers() throws IOException {
        Player[] result = new Player[PLAYERS_NUM];
        if (IS_DEBUG) {
            PLAYERS_NUM = 2;
            result = new Player[PLAYERS_NUM];
            result[0] = new Player("first", 'x');
            result[1] = new Player("second", 'o');
            return result;
        }
        TreeMap<String, Character> playerInfos = inputController.getPlayers(PLAYERS_NUM);
        ArrayList<String> playerNames = new ArrayList<>(playerInfos.descendingKeySet());
        for (int i = 0; i < PLAYERS_NUM; i++) {
            String currentPlayerName = playerNames.get(i);
            Character currentPlayerChar = playerInfos.get(currentPlayerName);
            result[i] = new Player(currentPlayerName, currentPlayerChar);
        }
        return result;
    }

    public void GameFinishedEvent(Player winner) {
        winner.winGame();

        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Congrats " + winner.getName() + ", you won!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        System.out.println("Current stats:");
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i]);
        }
        System.out.println("NEW GAME" + System.lineSeparator());

        playboard.reset();
        winDetector = new IncrementalWinDetector(playboard.getBoardMatrix());
        try {
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
