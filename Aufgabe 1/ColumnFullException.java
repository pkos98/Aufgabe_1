public class ColumnFullException extends Exception {

    public  ColumnFullException(){
        super("The chosen column is already full!");
    }

    public  ColumnFullException(int column) {
        super("The column #" + (column + 1) + " is already full!");
    }
}
