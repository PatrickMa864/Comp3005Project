import java.util.ArrayList;
import java.util.EventObject;

public class LookInnaBookEvent extends EventObject {
    private Basket library;

    public LookInnaBookEvent(LookInnaBookModel model, Basket library) {
        super(model);
        this.library = library;
    }

    public Basket getLibrary() {
        return library;
    }

}