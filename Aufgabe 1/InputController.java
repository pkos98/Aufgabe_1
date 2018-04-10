import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class InputController {

    private Playboard playboard;
    private BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    private int validationCounter;

    public InputController(Playboard playboard) throws IOException {
        this.playboard = playboard;
    }

    public TreeMap<String, Character> getPlayers(int numberOfPlayers) throws IOException {
        TreeMap<String, Character> players = new TreeMap<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.format("Name of player %d#: ", (i+1));
            String name = validatePlayerName(readLine());
            Character character = (char)('a' + i);
            players.put(name, character);
        }
        return players;
    }

    public int getColumnInput(Player player) throws IOException {
        System.out.print(player.getName() + "`s turn: ");
        String input = readLine();

        int numberInput = validateColumnInput(input);
        if (numberInput == -1) {
            System.out.println("Invalid input. Please try again.");
            numberInput = getColumnInput(player);
        }
        return numberInput;
    }

    private String validatePlayerName(String input) {
        if (input.equals(""))
            return "Player #" + ++validationCounter;
        else
            return input;
    }

    private int validateColumnInput(String input) {
        int inputToNumber;
        try {
            inputToNumber = Integer.parseInt(input);
        }
        catch (Exception ex) {
            return -1;
        }

        if (inputToNumber < 1 || inputToNumber > Playboard.COLUMNS)
            return -1;
        else
            return inputToNumber;
    }

    private String readLine() throws IOException {
        String newLine = System.lineSeparator();
        String line = buffer.readLine();
        if (line.equals(newLine))
            return "";
        return line;
    }

}
