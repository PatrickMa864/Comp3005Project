import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class LookInnaBookFrame extends JFrame implements LookInnaBookView, ActionListener {
    private ArrayList<Book> library;
    private ArrayList<Book> userBasket;

    public LookInnaBookFrame(ArrayList<Book> library){
        super("LookInnaBook");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userBasket = new ArrayList<Book>();

        String[] options = {"User", "Manager"};
        int loginOption = JOptionPane.showOptionDialog(null, "Login as User or Manager?",
                "Select an Option",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[1]);


        LookInnaBookModel model = new LookInnaBookModel(library);


        LookInnaBookController controller = new LookInnaBookController(model);

        this.setTitle("LookInnaBook Store");
        this.setSize(new Dimension(885, 300));
        GridBagConstraints c = new GridBagConstraints();
        JPanel mainPanel = new JPanel(new BorderLayout());
        final JLabel actionLabel = new JLabel("Books available in Store:");
        JPanel headerPanel = new JPanel(new BorderLayout());
        JPanel bodyPanel = new JPanel();
        JScrollPane bodyScroll = new JScrollPane(bodyPanel, 21, 32);
        JPanel sidePanel = new JPanel(new GridBagLayout());
        int numBooks = library.size();
        JPanel[] bookPanels = new JPanel[numBooks];
        int counter = 0;

        JLabel headerLabel;
        for(Iterator var12 = library.iterator(); var12.hasNext(); ++counter) {
            final Book book = (Book) var12.next();
            JPanel bookPanel = new JPanel(new GridLayout());
            JPanel bookPanel2 = new JPanel(new GridBagLayout());
            bookPanel2.setBackground(new Color(228, 201, 149));
            bookPanel.setPreferredSize(new Dimension(330, 200));
            headerLabel = new JLabel("Title: " + book.getTitle());
            final JLabel amountLabel = new JLabel("Stock: " + book.getNumCopies());
            c.gridwidth = 1;
            c.fill = 0;
            c.ipady = 0;
            c.weighty = 1.0D;
            c.weightx = 0.0D;
            c.anchor = 23;
            c.gridy = 0;
            c.gridx = 0;
            bookPanel2.add(headerLabel, c);
            c.gridx = 2;
            bookPanel2.add(amountLabel, c);
            JButton addButton = new JButton("Add (1) to Cart");
            addButton.addActionListener(controller);
            JButton removeButton = new JButton("Remove (1) from Cart");
            removeButton.addActionListener(controller);
            JButton infoButton = new JButton("?");
            infoButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane infoMessage = new JOptionPane();
                    JOptionPane.showMessageDialog(null, book.getInfo());
                }
            });
            c.fill = 2;
            c.anchor = 20;
            c.gridy = 2;
            c.gridx = 0;
            bookPanel2.add(addButton, c);
            c.gridx = 1;
            bookPanel2.add(removeButton, c);
            c.gridx = 2;
            bookPanel2.add(infoButton, c);
            bookPanel.add(bookPanel2);
            bookPanels[counter] = bookPanel;
            bodyPanel.add(bookPanels[counter]);


        }

        JButton viewBasket = new JButton("View Basket");
        viewBasket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane BasketView = new JOptionPane();
                JFrame basket = new JFrame("Basket");
                JOptionPane.showMessageDialog(basket, "VIEWING BASKET:"  + "\n" + printBasket(userBasket) + "\nTOTAL: $" + getTotal(userBasket));
            }
        });
        c.fill = 2;
        c.anchor = 19;
        c.gridx = 0;
        sidePanel.add(viewBasket, c);


        headerLabel = new JLabel("WELCOME TO THE LOOK-INNA-BOOK STORE");
        headerPanel.add(headerLabel, "North");
        headerPanel.add(actionLabel, "South");
        mainPanel.add(headerPanel, "First");
        mainPanel.add(bodyScroll, "Center");
        mainPanel.add(sidePanel, "East");
        this.add(mainPanel);


        this.setVisible(true);
    }

    public String printBasket(ArrayList<Book> basket){
        String output = "";
        for ( Book b:basket){
            output += "\""+b.getTitle() +"\" by"+ b.getAuthor().getFirstName()+ " " + b.getAuthor().getLastName()+ "   Quantity:" + b.getNumCopies()+ "\n";
        }
        return output;
    }

    public double getTotal(ArrayList<Book> basket){
        int output = 0;
        for ( Book b:basket){
            output += b.getNumCopies() * b.getPrice();
        }
        return output;
    }

    @Override
    public void update(LookInnaBookEvent event) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        ArrayList<Book> library = new ArrayList<>();
        Author a1 = new Author(1,"Charles", "Dickens", "cd@g.co");
        Publisher p1 = new Publisher("a","b", "c", "d");
        Book b1 = new Book("the book", a1, 1, p1, 3.00, 100, Genre.ADVENTURE,4,1,1.00 );
        Book b2 = new Book("the book2", a1, 2, p1, 3.00, 100, Genre.ADVENTURE, 3,2,1.00 );
        Book b3 = new Book("the book3", a1, 3, p1, 3.00, 100, Genre.ADVENTURE, 2,3,1.00 );
        library.add(b1);
        library.add(b2);
        library.add(b3);
        new LookInnaBookFrame(library);
    }
}
