import java.util.EventObject;

public class LookInnaBookEvent extends EventObject {
    private String outCome;

    public LookInnaBookEvent(LookInnaBookModel model, String outCome) {
        super(model);
        this.outCome = outCome;
    }

    public String getOutCome() {
        return outCome;
    }

}