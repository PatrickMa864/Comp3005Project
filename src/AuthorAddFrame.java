import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static java.lang.String.valueOf;

public class AuthorAddFrame extends JFrame implements ActionListener {
    private Basket library;
    private final JButton addAuthorButton = new JButton("Add Author");

    private final JLabel authorFirstNameL = new JLabel("*Author First Name:"),
            authorLastNameL = new JLabel("*Author Last Name:");

    private final JTextField authorFirstNameTF = new JTextField(10),
            authorLastNameTF = new JTextField(10);

    private final JTextField errorLabel = new JTextField("");


    public AuthorAddFrame() {
        JPanel authorPanel = new JPanel();
        JLabel authorLabel = new JLabel("Enter Author Information: ");
        authorPanel.setLayout(new BorderLayout());
        authorLabel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        authorPanel.add(authorLabel, BorderLayout.PAGE_START);

        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setMaximumSize(new Dimension(200, 20));
        errorLabel.setEditable(false);
        errorLabel.setBackground(null);
        errorLabel.setBorder(null);

        Container c = this.getContentPane();
        // Clear GUI in order to reload
        this.setPreferredSize(new Dimension(600, 130));
        this.setMaximumSize(new Dimension(600, 130));
        if (this.getJMenuBar() != null) this.getJMenuBar().setVisible(false);
        c.removeAll();

        final GridBagLayout layout = new GridBagLayout();
        JPanel mainAddPanel = new JPanel(layout);

        addAuthorButton.addActionListener(this);

        GridBagConstraints gbc = new GridBagConstraints();
        Dimension spacer = new Dimension(25, 25);

        gbc.gridy = 0;
        gbc.gridx = 2;
        gbc.gridwidth = 5;
        errorLabel.setForeground(Color.red);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        mainAddPanel.add(errorLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        mainAddPanel.add(authorLabel, gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainAddPanel.add(authorFirstNameL, gbc);
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 2;
        mainAddPanel.add(authorFirstNameTF, gbc);
        gbc.gridx = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        mainAddPanel.add(authorLastNameL, gbc);
        gbc.gridx = 6;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 5;
        mainAddPanel.add(authorLastNameTF, gbc);

        gbc.gridy = 8;
        gbc.gridx = 1;
        mainAddPanel.add(Box.createRigidArea(spacer), gbc);

        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.gridx = 9;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        mainAddPanel.add(addAuthorButton, gbc);


        this.add(mainAddPanel);

        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("LookInnaBook");
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();


        if (o instanceof JButton) {
            switch (((JButton) o).getText()) {

                case "Add Author" : makeAuthor();
                this.dispose();
                break;
                default : System.out.println("Error");
            }
        }
    }

    public void makeAuthor(){
        ManagerAddFrame.author = new Author(authorFirstNameTF.getText(), authorLastNameTF.getText());
        DataBaseQueries.addNewAuthor(ManagerAddFrame.author);
        System.out.println(ManagerAddFrame.author.getFirstName());
    }

    public static void main(String[] args) {
    new AuthorAddFrame();
    }
}
