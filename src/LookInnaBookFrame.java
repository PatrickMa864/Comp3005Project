import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import static java.lang.String.valueOf;

public class LookInnaBookFrame extends JFrame implements LookInnaBookView, ActionListener {
    private Basket library;
    static ArrayList<User> users;
    private User currentUser;
    private LookInnaBookModel model;
    public static Basket userBasket;
    public static Basket searchBasket;
    private Basket displayBasket;
    private boolean loggedIn;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem m1, m2;
    private JLabel[] amountLabel;


    public LookInnaBookFrame(Basket library, ArrayList<User> users, boolean loggedIn){
        super("LookInnaBook");
        this.library = library;
        this.users = users;
        this.loggedIn = loggedIn;
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
        if (!loggedIn) {
            String[] options = {"User", "Manager"};
            int loginOption = JOptionPane.showOptionDialog(null, "Login as User or Manager?",
                    "Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (loginOption == 0) {
                String[] options2 = {"Login", "Register"};
                int loginOption2 = JOptionPane.showOptionDialog(null, "Login or Register as new User?",
                        "Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[1]);
                if (loginOption2 == 0) {
                    String userName = "";
                    usernamefield:
                    while (userName != null) {
                        userName = JOptionPane.showInputDialog(null, "Please enter your Username:");
                        for (User u : users) {
                            if (u.getUserName().equalsIgnoreCase(userName)) {
                                String password = JOptionPane.showInputDialog(null, "Please enter your password");
                                if (u.getPassword().equals(password)) {
                                    currentUser = u;
                                    System.out.println("welcome");
                                    break usernamefield;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Password Failed");
                                    currentUser = null;
                                    break;
                                }
                            }
                        }
                        if (userName != null) {
                            JOptionPane.showMessageDialog(null, "Username does not exist");
                        }
                    }
                } else {
                    String userName = "";
                    usernamefield:
                    while (userName != null) {
                        String newUsername = JOptionPane.showInputDialog(null, "Please enter a Username:");
                        for (User u : users) {
                            if (u.getUserName().equals(newUsername)) {
                                JOptionPane.showMessageDialog(null, "Username alreadt exist");
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            currentUser = new User();

        }

        if(currentUser!=null) {
            displayBasket = this.library;
            model = new LookInnaBookModel(displayBasket);

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
            int numBooks = displayBasket.getBooks().size();
            JPanel[] bookPanels = new JPanel[numBooks];
            int counter = 0;

            amountLabel = new JLabel[numBooks];
            JLabel headerLabel;
            for (Iterator var12 = displayBasket.getBooks().iterator(); var12.hasNext(); ++counter) {
                final Book book = (Book) var12.next();
                JPanel bookPanel = new JPanel(new GridLayout());
                JPanel bookPanel2 = new JPanel(new GridBagLayout());
                bookPanel2.setBackground(new Color(75, 179, 101));
                bookPanel.setPreferredSize(new Dimension(330, 200));
                headerLabel = new JLabel("Title: " + book.getName());
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
                        if (b.getNumCopies() > 0) {
                            b.setNumCopies(b.getNumCopies() - 1);
                            if (userBasket.hasBook(b.getName())) {
                                Book b2 = userBasket.getBook(b.getISBN());
                                b2.setNumCopies(b2.getNumCopies() + 1);

                            } else {
                                userBasket.getBooks().add(new Book(b.getISBN(), b.getName(), b.getGenre(), b.getNumCopies(), b.getPrice(), b.getNumPages(), b.getVersion(), b.getPublisherRoyalty(), b.getPublishedYear(), b.getPublisherName()));
                            }

                            DataBaseQueries.addBookToBasket(currentUser, book);

                        } else {
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
                        if (userBasket.hasBook(book.getName())) {
                            b.setNumCopies(b.getNumCopies() - 1);
                            book.setNumCopies(book.getNumCopies() + 1);
                            if (b.getNumCopies() == 0) {
                                userBasket.removeBook(b.getISBN());
                            }
                            DataBaseQueries.deleteBookFromBasket(currentUser,book);
                        } else {
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
            viewBasket.addActionListener(this);


            c.fill = 2;
            c.anchor = 17;
            c.gridx = 1;
            sidePanel.add(viewBasket, c);
            JButton checkoutButton = new JButton("Checkout");
            checkoutButton.addActionListener(this);
            c.fill = 2;
            c.anchor = 18;
            c.gridx = 1;

            JButton searchButton = new JButton("Search");

            if(loggedIn) {
                searchButton.setText("Back");
            } else{
                sidePanel.add(checkoutButton, c);
            }
            searchButton.addActionListener(this);
            c.anchor = 19;
            c.gridy = 0;
            c.gridx = 1;
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
        DataBaseQueries.updateAddressCount();
        DataBaseQueries.updateOrderCount();
        DataBaseQueries.updateUserCount();
    }


    public void update(LookInnaBookEvent event) {
        library = event.getLibrary();
        for(int i = 0; i<library.getBooks().size(); i++){
            amountLabel[i].setText("Stock: " + valueOf(library.getBooks().get(i).getNumCopies()));
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        Object o = e.getSource();

        if (o instanceof JButton) {
            switch (((JButton) o).getText()) {
                case "Checkout" ->{
                       new CheckoutFrame(currentUser, userBasket.getTotal(), userBasket, this);
                }
                case "View Basket" -> {
                        JFrame basket = new JFrame("Basket");
                        JOptionPane.showMessageDialog(basket, "VIEWING BASKET:" + "\n" + userBasket.printBasket() + "\nTOTAL: $" + userBasket.getTotal());
                }
                case "Search" -> {
                    new SearchFrame(library);
                }
                case "Back" -> {
                    this.dispose();
                }

                default -> System.out.println("Error");
            }
        }else {
            switch (s) {
                case "Search" -> {
                    new SearchFrame(library);
                }
                case "Checkout" -> new CheckoutFrame(currentUser, userBasket.getTotal(), userBasket, this);
            }
        }
        model.updateViews();

    }

    public static void main(String[] args) {
        new LookInnaBookFrame(new Basket(DataBaseQueries.getAvailableBooks()), DataBaseQueries.makeUserList(),false);
    }
}
