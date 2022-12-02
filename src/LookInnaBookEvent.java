import java.util.EventObject;

public class LookInnaBookEvent extends EventObject {
    private char[][] board;
    private String outCome;

    public LookInnaBookEvent(LookInnaBookModel model, char[][] board, String outCome) {
        super(model);
        this.board = board;
        this.outCome = outCome;
    }

    public String getOutCome() {
        return outCome;
    }

    public char[][] getBoard(){
        return board;
    }
}