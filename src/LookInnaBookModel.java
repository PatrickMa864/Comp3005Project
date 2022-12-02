import java.util.ArrayList;
import java.util.List;

public class LookInnaBookModel {
    private ArrayList<Book> library;
    private List<LookInnaBookView> views;

    public LookInnaBookModel(ArrayList<Book> library){
        this.library = library;
        views = new ArrayList<>();
    }

    public void addLookInnaBookView(LookInnaBookFrame frame) {
        views.add(frame);
    }

    public void updateViews(){
        for (LookInnaBookView view: views){
            view.update(new LookInnaBookEvent(this, library));
        }
    }



    public void addLookInnaBookView(LookInnaBookView v){
        views.add(v);
    }

    public void search(String search){
        System.out.println("test");
    }
}
