import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.String.valueOf;

public class LookInnaBookFrame extends JFrame implements LookInnaBookView, ActionListener {
    private ArrayList<Book> library;
    private LookInnaBookModel model;
    private Basket userBasket;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem m1, m2;

    private JLabel[] amountLabel;

    public LookInnaBookFrame(ArrayList<Book> library){
        super("LookInnaBook");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userBasket = new Basket();

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        m1 = new JMenuItem("Search");
        m2 = new JMenuItem("Checkout");
        m1.addActionListener(this);
        m2.addActionListener(this);
        menu.add(m1);
        menu.add(m2);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        String[] options = {"User", "Manager"};
        int loginOption = JOptionPane.showOptionDialog(null, "Login as User or Manager?",
                "Select an Option",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[1]);


        model = new LookInnaBookModel(library);

        model.addLookInnaBookView(this);


        LookInnaBookController controller = new LookInnaBookController(model);

        this.setTitle("LookInnaBook Store");
        this.setSize(new Dimension(885, 330));
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

        amountLabel = new JLabel[numBooks];
        JLabel headerLabel;
        for(Iterator var12 = library.iterator(); var12.hasNext(); ++counter) {
            final Book book = (Book) var12.next();
            JPanel bookPanel = new JPanel(new GridLayout());
            JPanel bookPanel2 = new JPanel(new GridBagLayout());
            bookPanel2.setBackground(new Color(228, 201, 149));
            bookPanel.setPreferredSize(new Dimension(330, 200));
            headerLabel = new JLabel("Title: " + book.getTitle());
            amountLabel[counter] = new JLabel("Stock: " + book.getNumCopies());
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
            bookPanel2.add(amountLabel[counter], c);
            JButton addButton = new JButton("Add (1) to Cart");
            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Book b = book;
                    if(b.getNumCopies()>0) {
                        b.setNumCopies(b.getNumCopies() - 1);
                        if(userBasket.hasBook(b.getTitle())){
                            Book b2 = userBasket.getBook(b.getISBN());
                            b2.setNumCopies(b2.getNumCopies()+1);

                        }else{
                            userBasket.getBooks().add(new Book(b.getTitle(),b.getAuthor(),b.getISBN(),b.getPublisher(),b.getPrice(), b.getNumPages(), b.getGenre(), 1,b.getVersion(),b.getPublisherRoyalty()));
                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "Book out of stock");
                    }
                    model.updateViews();

                }
            });
            JButton removeButton = new JButton("Remove (1) from Cart");
            removeButton.addActionListener(controller);
            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Book b = userBasket.getBook(book.getISBN());
                    if(userBasket.hasBook(book.getTitle())) {
                        b.setNumCopies(b.getNumCopies() - 1);
                        book.setNumCopies(book.getNumCopies()+1);
                        if(b.getNumCopies()==0){
                            userBasket.removeBook(b.getISBN());
                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "Book not found in cart");
                    }
                    model.updateViews();
                }
            });

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
                JOptionPane.showMessageDialog(basket, "VIEWING BASKET:"  + "\n" + userBasket.printBasket() + "\nTOTAL: $" + userBasket.getTotal());
            }
        });
        c.fill = 2;
        c.anchor = 17;
        c.gridx = 1;
        sidePanel.add(viewBasket, c);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                JOptionPane  = new JOptionPane();
//                JFrame basket = new JFrame("Basket");
//                JOptionPane.showMessageDialog(basket, "VIEWING BASKET:"  + "\n" + printBasket(userBasket) + "\nTOTAL: $" + getTotal(userBasket));
            }
        });
        c.anchor = 19;
        c.gridy = 0;
        JTextField searchText = new JTextField();
        sidePanel.add(searchText,c);
        c.gridx = 2;
        sidePanel.add(searchButton, c);


        headerLabel = new JLabel("WELCOME TO THE LOOK-INNA-BOOK STORE");
        headerPanel.add(headerLabel, "North");
        headerPanel.add(actionLabel, "South");
        mainPanel.add(headerPanel, "First");
        mainPanel.add(bodyScroll, "Center");
        mainPanel.add(sidePanel, "East");
        this.add(mainPanel);


        this.setVisible(true);
    }






    public void update(LookInnaBookEvent event) {
        System.out.println("made it");
        library = event.getLibrary();
        for(int i = 0; i<library.size(); i++){
            amountLabel[i].setText("Stock: " + valueOf(library.get(i).getNumCopies()));
        }

    }

    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        switch(s){
            case "Search":
                String searchKey = JOptionPane.showInputDialog(null, "Search for:");
                model.search(searchKey);
                break;
            case "Checkout:":

        }

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
