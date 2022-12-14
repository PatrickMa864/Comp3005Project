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
    public static User currentUser;
    private LookInnaBookModel model;
    public static Basket userBasket;
    public static Basket searchBasket;
    private Basket displayBasket;
    private boolean registered;
    private boolean loggedIn;
    private boolean isManager;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem m1, m2;
    private JLabel[] amountLabel;


    public LookInnaBookFrame(Basket library, ArrayList<User> users, boolean loggedIn, boolean registered, boolean isManager){
        super("LookInnaBook");
        this.library = library;
        this.users = users;
        this.loggedIn = loggedIn;
        this.registered = registered;
        this.isManager = isManager;

        if(loggedIn){
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
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
                    new RegisterFrame(library);
                }
            } else {
                new LookInnaBookFrame(library, users, true, false, true);
            }
        } else if (!registered) {
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
                bookPanel.setPreferredSize(new Dimension(350, 200));
                headerLabel = new JLabel("Title: " + book.getName());
                amountLabel[counter] = new JLabel("Stock: " + book.getNumCopies());
                c.gridwidth = 1;
                c.fill = 0;
                c.ipady = 0;
                c.weighty = 1.0D;
                c.weightx = 0.0D;
                c.anchor = GridBagConstraints.FIRST_LINE_START;
                c.gridy = 0;
                c.gridx = 0;
                bookPanel2.add(headerLabel, c);
                c.anchor = GridBagConstraints.FIRST_LINE_END;
                c.gridx = 2;
                bookPanel2.add(amountLabel[counter], c);
                JButton addButton = new JButton("Add (1) to Cart");
                addButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Book b = book;
                        if (!isManager) {
                            if (b.getNumCopies() > 0) {
                                b.setNumCopies(b.getNumCopies() - 1);
                                if (userBasket.hasBook(b.getName())) {
                                    Book b2 = userBasket.getBook(b.getISBN());
                                    b2.setNumCopies(b2.getNumCopies() + 1);

                                } else {
                                    userBasket.getBooks().add(new Book(b.getISBN(), b.getName(), b.getGenre(), 1, b.getPrice(), b.getNumPages(), b.getVersion(), b.getPublisherRoyalty(), b.getPublishedYear(), b.getPublisherName()));
                                }

                                DataBaseQueries.addBookToBasket(currentUser, book);

                            } else {
                                JOptionPane.showMessageDialog(null, "Book out of stock");
                            }
                        } else {
                            if(b.getNumCopies()>0) {
                                b.setNumCopies(b.getNumCopies() - 1);
                                DataBaseQueries.updateBookAmount(b,true);
                            }
                        }
                        model.updateViews();

                    }
                });
                JButton removeButton = new JButton("Remove (1) from Cart");
                removeButton.addActionListener(controller);
                removeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Book b = book;
                        if (!isManager) {
                            if (userBasket.hasBook(book.getName())) {
                                b.setNumCopies(b.getNumCopies() - 1);
                                book.setNumCopies(book.getNumCopies() + 1);
                                if (b.getNumCopies() == 0) {
                                    userBasket.removeBook(b.getISBN());
                                }
                                DataBaseQueries.deleteBookFromBasket(currentUser, book);
                            } else {
                                JOptionPane.showMessageDialog(null, "Book not found in cart");
                            }
                        } else {
                            b.setNumCopies(b.getNumCopies() + 1);
                            DataBaseQueries.updateBookAmount(b,false);
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

                if(isManager){
                    removeButton.setText("Add (1) stock");
                    addButton.setText("Remove (1) stock");
                }
            }

            JButton viewBasket = new JButton("View Basket");
            if(isManager){
                viewBasket.setText("Add New Book");
            }

            viewBasket.addActionListener(this);

            c.fill = 2;
            c.anchor = 17;
            c.gridx = 1;
            sidePanel.add(viewBasket, c);
            c.fill = 2;
            c.anchor = 12;
            c.gridx = 1;
            JButton salesButton = new JButton("See Sales");

            if(isManager){
                salesButton.addActionListener(this);
                sidePanel.add(salesButton, c);
            }


            JButton checkoutButton = new JButton("Checkout");
            checkoutButton.addActionListener(this);
            c.fill = 2;
            c.anchor = 18;
            c.gridx = 1;

            JButton searchButton = new JButton("Search");

            if(loggedIn && !registered && !isManager) {
                searchButton.setText("Back");
            } else if (!isManager){
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

            if(!currentUser.getUserName().equals("")){
                loggedIn = true;
            }


            this.setVisible(true);
            if(!isManager && !registered && !loggedIn){
                this.dispose();
            }

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
                    new SearchFrame(library, isManager);
                }
                case "Back" -> {
                    this.dispose();
                }
                case "Add New Book" -> {
                    new ManagerAddFrame();
                }
                case "See Sales"->{
                    ArrayList<AddToOrder> ato = DataBaseQueries.getOrdersTiedBooks();
                    String message = "Sales:";
                    int totalForCurrent = 0;
                    int counter = 0;
                    int total = 0;
                    if(ato.size()>0) {
                        for (Order order : DataBaseQueries.getOrders()) {

                            for (AddToOrder addToOrder : ato) {
                                counter++;
                                totalForCurrent += DataBaseQueries.getBookByISBN(addToOrder.getIsbn()).getPrice();
                            }
                            message += "\n" + "ISBN: " + ato.get(counter-1).getIsbn() + "   Total for this book: $" + totalForCurrent;

                            total += order.getPrice();
                            counter = 0;
                        }
                        message += ("\nTotal Revenue: " + total);
                        System.out.println(message);
                        JOptionPane.showMessageDialog(null, message);
                    } else{
                        JOptionPane.showMessageDialog(null, "No Orders Placed");
                    }
                    break;
                }

                default -> System.out.println("Error");
            }
        }else {
            switch (s) {
                case "Search" -> {
                    new SearchFrame(library, isManager);
                }
                case "Checkout" -> new CheckoutFrame(currentUser, userBasket.getTotal(), userBasket, this);
            }
        }
        model.updateViews();

    }

    public static void main(String[] args) {
        new LookInnaBookFrame(new Basket(DataBaseQueries.getAvailableBooks()), DataBaseQueries.makeUserList(), false, false, false);
    }
}
