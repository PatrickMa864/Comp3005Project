import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public SearchFrame(User user, Basket basket){
        this.user = user;
        this.basket = basket;
        JPanel searchPanel = new JPanel();
        JLabel searchLabel = new JLabel("Search ");
        searchPanel.setLayout(new BorderLayout());
        searchLabel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        searchPanel.add(searchLabel, BorderLayout.PAGE_START);

        JPanel search = new JPanel(new GridLayout(1, 1));
        JScrollPane currentSearch = new JScrollPane(search,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        currentSearch.setMaximumSize(new Dimension(198, 500));
        searchPanel.add(currentSearch, BorderLayout.CENTER);

        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setMaximumSize(new Dimension(200, 20));
        errorLabel.setEditable(false);
        errorLabel.setBackground(null);
        errorLabel.setBorder(null);

        Address address = user.getAddress();

        Container c = this.getContentPane();
        // Clear GUI in order to reload
        this.setPreferredSize(new Dimension(1150, 550));
        this.setMaximumSize(new Dimension(1100, 500));
        if (this.getJMenuBar() != null) this.getJMenuBar().setVisible(false);
        c.removeAll();

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

    }
}

