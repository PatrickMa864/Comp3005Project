import java.util.ArrayList;
import java.util.EventObject;

public class LookInnaBookEvent extends EventObject {
    private ArrayList<Book> library;

    public LookInnaBookEvent(LookInnaBookModel model, ArrayList<Book> library) {
        super(model);
        this.library = library;
    }

    public ArrayList<Book> getLibrary() {
        return library;
    }

}