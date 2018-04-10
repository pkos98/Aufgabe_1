public class Token {
    private int column;
    private int row;
    private Player player;

    public int getColumn() {
        return column;
    }
    public int getRow() {
        return row;
    }
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Token(int column, int row){
        this.column = column;
        this.row = row;
    }

    public boolean isEmpty() {
        return getPlayer() == null;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return " ";
        else
            return Character.toString(player.getTokenChar());
    }

}
