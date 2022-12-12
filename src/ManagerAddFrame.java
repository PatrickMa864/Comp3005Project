import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;


import static java.lang.String.valueOf;

public class ManagerAddFrame extends JFrame implements ActionListener {
        private String authorName;
        private String publisherName;
        public static Author author;
        public static Publisher publisher;
        public static Address address;

        private Book book;
        private final JButton nextButton = new JButton("Set Author");

        private final JLabel isbnL = new JLabel("ISBN:"),
                titleL = new JLabel("Title:"),
                genreL =  new JLabel("Genre:"),
                numCopiesL =  new JLabel("Number of Copies:"),
                priceL =  new JLabel("Price:"),
                numPagesL =  new JLabel("Number of Pages:"),
                versionL =  new JLabel("Version:"),
                royaltyL =  new JLabel("Royalty:"),
                publishDateL =  new JLabel("Publish Date:"),
                publisherIDL = new JLabel("Publisher ID:"),
                authorIDL = new JLabel("Author ID:");


        private final JTextField isbnTF = new JTextField(10),
                titleTF = new JTextField(20),
                numCopiesTF = new JTextField(4),
                numPagesTF = new JTextField(4),
                priceTF = new JTextField(4),
                versionTF = new JTextField(4),
                royaltyTF = new JTextField(8),
                publishDateTF = new JTextField(8),
                publisherIDTF = new JTextField(4),
                authorIDTF = new JTextField(4);

        final JComboBox<String> genreCB = new JComboBox<>(Book.Genres),
                authorsCB = new JComboBox<>(DataBaseQueries.makeAuthorsList()),
                publisherCB = new JComboBox<>(DataBaseQueries.makePublishersList());

        private final JTextField errorLabel = new JTextField("");
        public ManagerAddFrame(){
            //this.library=library;
            JPanel addPanel = new JPanel();
            JLabel addLabel = new JLabel("Enter Book Information: ");
            addPanel.setLayout(new BorderLayout());
            addLabel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
            addPanel.add(addLabel, BorderLayout.PAGE_START);

            errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
            errorLabel.setMaximumSize(new Dimension(200, 20));
            errorLabel.setEditable(false);
            errorLabel.setBackground(null);
            errorLabel.setBorder(null);

            Container c = this.getContentPane();
            // Clear GUI in order to reload
            this.setPreferredSize(new Dimension(1000, 200));
            this.setMaximumSize(new Dimension(1000, 200));
            if (this.getJMenuBar() != null) this.getJMenuBar().setVisible(false);
            c.removeAll();

            final GridBagLayout layout = new GridBagLayout();
            JPanel mainAddPanel = new JPanel(layout);

            nextButton.addActionListener(this);

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
            mainAddPanel.add(addLabel,gbc);

            gbc.gridy = 2;
            gbc.gridx = 1;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainAddPanel.add(isbnL, gbc);
            gbc.gridx = 2;
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridwidth = 2;
            mainAddPanel.add(isbnTF, gbc);
            gbc.gridx = 4;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridwidth = 1;
            mainAddPanel.add(titleL, gbc);
            gbc.gridx = 5;
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridwidth = 2;
            mainAddPanel.add(titleTF, gbc);
            gbc.gridx = 7;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridwidth = 1;
            mainAddPanel.add(genreL, gbc);
            gbc.gridx = 8;
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridwidth = 8;
            mainAddPanel.add(genreCB, gbc);

            gbc.gridy = 3;
            gbc.gridx = 1;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainAddPanel.add(numCopiesL, gbc);
            gbc.gridx = 2;
            gbc.fill = GridBagConstraints.NONE;
            mainAddPanel.add(numCopiesTF, gbc);
            gbc.gridx = 3;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainAddPanel.add(priceL, gbc);
            gbc.gridx = 4;
            gbc.fill = GridBagConstraints.NONE;
            mainAddPanel.add(priceTF, gbc);
            gbc.gridx = 5;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainAddPanel.add(numPagesL, gbc);
            gbc.gridx = 6;
            gbc.fill = GridBagConstraints.NONE;
            mainAddPanel.add(numPagesTF, gbc);
            gbc.gridx = 7;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainAddPanel.add(versionL, gbc);
            gbc.gridx = 8;
            gbc.fill = GridBagConstraints.NONE;
            mainAddPanel.add(versionTF, gbc);



            gbc.gridy = 5;
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainAddPanel.add(royaltyL, gbc);
            gbc.gridx = 2;
            gbc.fill = GridBagConstraints.NONE;
            mainAddPanel.add(royaltyTF, gbc);
            gbc.gridx = 3;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainAddPanel.add(publishDateL, gbc);
            gbc.gridx = 4;
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridwidth = 8;
            mainAddPanel.add(publishDateTF, gbc);


            gbc.gridy = 7;
            gbc.gridx = 1;
            mainAddPanel.add(Box.createRigidArea(spacer), gbc);

            gbc.gridy = 8;
            gbc.gridx = 1;
            mainAddPanel.add(Box.createRigidArea(spacer), gbc);

            gbc.gridy = 9;
            gbc.gridwidth = 2;
            gbc.gridx = 9;
            gbc.anchor = GridBagConstraints.LINE_END;
            gbc.fill = GridBagConstraints.NONE;
            mainAddPanel.add(nextButton, gbc);


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

                    case "Set Author" :
                        askAuthor();
                        nextButton.setText("Set Publisher");
                        break;
                    case "Set Publisher":
                        askPublisher();
                        nextButton.setText("Add Book");
                        break;
                    case "Add Book":
                        try {
                            addBook();
                        } catch (ParseException ex) {
                            throw new RuntimeException(ex);
                        }
                        if (author== null){
                            DataBaseQueries.updateWrites(authorName,book);
                        }else if (authorName == null){
                            DataBaseQueries.updateWrites(author.getFullName(),book);
                        }
                        JOptionPane.showMessageDialog(null, "Book Added! \nPlease restart the software\nto see newly added books." );
                        this.dispose();
                        break;

                    default : System.out.println("Error");
                }
            }
        }

        public void askAuthor(){
            String[] options = {"Use Existing Author", "Choose New Author"};
            int authorOption = JOptionPane.showOptionDialog(null, "Use an existing author or choose a new author?",
                    "Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if(authorOption==0){
                authorsCB.setEditable(true);
                JOptionPane.showMessageDialog(null, authorsCB, "Choose Author", JOptionPane.QUESTION_MESSAGE);
                authorName = authorsCB.getSelectedItem().toString();
                System.out.println(authorName);

            }else{
                new AuthorAddFrame();
            }

        }

        public void askPublisher(){
            String[] options = {"Use Existing Publisher", "Choose New Publisher"};
            int authorOption = JOptionPane.showOptionDialog(null, "Use an existing publisher or choose a new publisher?",
                    "Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if(authorOption==0){
                publisherCB.setEditable(true);
                JOptionPane.showMessageDialog(null, publisherCB, "Choose Publisher", JOptionPane.QUESTION_MESSAGE);
                publisherName =  publisherCB.getSelectedItem().toString();
                System.out.println(publisherName);
            }else{
               new PublisherAddFrame();
            }
        }

        public void addBook() throws ParseException {
            if (publisher == null) {
                book = new Book(Long.parseLong(isbnTF.getText()), titleTF.getText(), Objects.requireNonNull(genreCB.getSelectedItem()).toString(),
                        Integer.parseInt(numCopiesTF.getText()), Double.parseDouble(priceTF.getText()), Integer.parseInt(numPagesTF.getText()),
                        Integer.parseInt(versionTF.getText()), Double.parseDouble(royaltyTF.getText()), new SimpleDateFormat("yyyy-MM-DD").parse(publishDateTF.getText()), publisherName);

            } else if (publisherName == null) {
                book = new Book(Long.parseLong(isbnTF.getText()), titleTF.getText(), Objects.requireNonNull(genreCB.getSelectedItem()).toString(),
                        Integer.parseInt(numCopiesTF.getText()), Double.parseDouble(priceTF.getText()), Integer.parseInt(numPagesTF.getText()),
                        Integer.parseInt(versionTF.getText()), Double.parseDouble(royaltyTF.getText()), new SimpleDateFormat("yyyy-MM-DD").parse(publishDateTF.getText()), publisher.getName());
            }
            DataBaseQueries.addNewBook(book);
        }
        public static void main(String[] args) {
            new ManagerAddFrame();
        }

    }




