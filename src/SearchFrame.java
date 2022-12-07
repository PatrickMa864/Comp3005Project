import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import static java.lang.String.valueOf;

public class SearchFrame extends JFrame implements ActionListener {
    private final JButton cancelSearch = new JButton("Cancel Search"),
            search = new JButton("Search");

    private final JTextField searchBar = new JTextField(10),
    ISBNsearch = new JTextField(5);
    final JComboBox<String> getGenreList = new JComboBox<>(Book.Genres),
    genreList = new JComboBox<>(Book.Genres);
    private final JTextField errorLabel = new JTextField("");

    private final User user;
    private Basket basket;
    private ArrayList<Book> library;

    public SearchFrame(User user, Basket basket, ArrayList<Book> library){
        this.user = user;
        this.basket = basket;
        this.library = library;
        JPanel searchPanel = new JPanel();
        JLabel searchLabel = new JLabel("Search by Title/Author/ISBN: ");
        JLabel genreLabel = new JLabel("Filter by Genre:");
        searchPanel.setLayout(new BorderLayout());
        searchLabel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        searchPanel.add(searchLabel, BorderLayout.PAGE_START);

        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setMaximumSize(new Dimension(200, 20));
        errorLabel.setEditable(false);
        errorLabel.setBackground(null);
        errorLabel.setBorder(null);

        Container c = this.getContentPane();
        // Clear GUI in order to reload
        this.setPreferredSize(new Dimension(500, 150));
        this.setMaximumSize(new Dimension(500, 150));
        if (this.getJMenuBar() != null) this.getJMenuBar().setVisible(false);
        c.removeAll();

        final GridBagLayout layout = new GridBagLayout();
        JPanel mainSearchPanel = new JPanel(layout);

        cancelSearch.addActionListener(this);
        search.addActionListener(this);

        GridBagConstraints gbc = new GridBagConstraints();
        Dimension spacer = new Dimension(25, 25);
        gbc.gridy = 0;
        gbc.gridx = 2;
        gbc.gridwidth = 8;
        errorLabel.setForeground(Color.red);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        mainSearchPanel.add(errorLabel, gbc);

        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        mainSearchPanel.add(cancelSearch, gbc);


        gbc.gridy = 1;
        mainSearchPanel.add(searchLabel,gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.ipadx = 400;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainSearchPanel.add(searchBar, gbc);

        gbc.ipadx = 0;
        gbc.gridx = 8;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        mainSearchPanel.add(search, gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        mainSearchPanel.add(genreLabel, gbc);
        gbc.gridx = 2;
        mainSearchPanel.add(genreList, gbc);

        searchPanel.add(mainSearchPanel);
        this.add(mainSearchPanel);

        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("LookInnaBook");
        this.setVisible(true);


    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();


        if (o instanceof JButton) {
            switch (((JButton) o).getText()) {
                case "Cancel Search" -> {
                    this.dispose();
                }
                case "Search" -> search();
                default -> System.out.println("Error");
            }
        }
    }

    public void search(){
        String searchWord = searchBar.getText();
        LookInnaBookFrame.searchBasket = new Basket();
        String genre = Objects.requireNonNull(genreList.getSelectedItem()).toString();
        boolean careAboutGenre = false;
        if(!genre.equals("-")){
            careAboutGenre = true;
        }
        for(Book b : library){
            if (careAboutGenre){
                if (b.getGenre().equalsIgnoreCase(genre)) {
                    if (b.getTitle().toUpperCase().contains(searchWord.toUpperCase())) {
                        LookInnaBookFrame.searchBasket.addBook(b);
                    } else if (valueOf(b.getISBN()).equals(searchWord)) {
                        LookInnaBookFrame.searchBasket.addBook(b);
                    } else if (b.getAuthor().getFirstName().toUpperCase().contains(searchWord.toUpperCase()) || b.getAuthor().getLastName().toUpperCase().contains(searchWord.toUpperCase())) {
                        LookInnaBookFrame.searchBasket.addBook(b);
                    }
                }
            }else{
                if (b.getTitle().toUpperCase().contains(searchWord.toUpperCase())) {
                    LookInnaBookFrame.searchBasket.addBook(b);
                } else if (valueOf(b.getISBN()).equals(searchWord)) {
                    LookInnaBookFrame.searchBasket.addBook(b);
                } else if (b.getAuthor().getFirstName().toUpperCase().contains(searchWord.toUpperCase()) || b.getAuthor().getLastName().toUpperCase().contains(searchWord.toUpperCase())) {
                    LookInnaBookFrame.searchBasket.addBook(b);
                }
            }
        }
        System.out.println(LookInnaBookFrame.searchBasket.printBasket());
        this.dispose();

    }
}

