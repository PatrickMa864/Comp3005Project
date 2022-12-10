import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


import static java.lang.String.valueOf;

public class ManagerAddFrame extends JFrame implements ActionListener {
        private Basket library;
        private final JButton addButton = new JButton("Add Book");

        private final JLabel isbnL = new JLabel("ISBN:"),
                titleL = new JLabel("Title:"),
                genreL =  new JLabel("Genre:"),
                numCopiesL =  new JLabel("Number of Copies:"),
                priceL =  new JLabel("Price:"),
                numPagesL =  new JLabel("Number of Pages:"),
                versionL =  new JLabel("Apt No.:"),
                royaltyL =  new JLabel("Street Name:"),
                publishDateL =  new JLabel("Street Num:"),
                publishNameL = new JLabel("City:");


        private final JTextField isbnTF = new JTextField(14),
                titleTF = new JTextField(20),
                numCopiesTF = new JTextField(4),
                numPagesTF = new JTextField(4),
                priceTF = new JTextField(4),
                versionTF = new JTextField(4),
                royaltyTF = new JTextField(8),
                publishDateTF = new JTextField(8),
                publishNameTF = new JTextField(14);

        final JComboBox<String> genreCB = new JComboBox<>(Book.Genres);

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
            this.setPreferredSize(new Dimension(800, 300));
            this.setMaximumSize(new Dimension(800, 300));
            if (this.getJMenuBar() != null) this.getJMenuBar().setVisible(false);
            c.removeAll();

            final GridBagLayout layout = new GridBagLayout();
            JPanel mainAddPanel = new JPanel(layout);

            addButton.addActionListener(this);

            GridBagConstraints gbc = new GridBagConstraints();
            Dimension spacer = new Dimension(25, 25);

            gbc.gridy = 0;
            gbc.gridx = 2;
            gbc.gridwidth = 8;
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

            gbc.gridy = 1;
            gbc.gridx = 1;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainAddPanel.add(isbnL, gbc);
            gbc.gridx = 2;
            gbc.fill = GridBagConstraints.NONE;
            mainAddPanel.add(isbnTF, gbc);
            gbc.gridx = 3;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainAddPanel.add(titleL, gbc);
            gbc.gridx = 4;
            gbc.fill = GridBagConstraints.NONE;
            mainAddPanel.add(titleTF, gbc);
            gbc.gridx = 5;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainAddPanel.add(genreL, gbc);
            gbc.gridx = 6;
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridwidth = 8;
            mainAddPanel.add(genreCB, gbc);

            gbc.gridy = 2;
            gbc.gridx = 1;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainAddPanel.add(numCopiesL, gbc);
            gbc.gridx = 2;
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridwidth = 8;
            mainAddPanel.add(numCopiesTF, gbc);
            gbc.gridx = 5;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainAddPanel.add(priceL, gbc);
            gbc.gridx = 6;
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridwidth = 8;
            mainAddPanel.add(priceTF, gbc);


            gbc.gridy = 5;
            gbc.gridx = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainAddPanel.add(versionL, gbc);
            gbc.gridx = 2;
            gbc.fill = GridBagConstraints.NONE;
            mainAddPanel.add(versionTF, gbc);
            gbc.gridx = 3;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainAddPanel.add(royaltyL, gbc);
            gbc.gridx = 4;
            gbc.fill = GridBagConstraints.NONE;
            mainAddPanel.add(royaltyTF, gbc);
            gbc.gridx = 5;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainAddPanel.add(publishDateL, gbc);
            gbc.gridx = 6;
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridwidth = 8;
            mainAddPanel.add(publishDateTF, gbc);
            gbc.gridx = 7;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainAddPanel.add(publishNameL, gbc);
            gbc.gridx = 8;
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridwidth = 8;
            mainAddPanel.add(publishNameTF, gbc);



            gbc.gridy = 7;
            gbc.gridx = 1;
            mainAddPanel.add(Box.createRigidArea(spacer), gbc);

            gbc.gridy = 8;
            gbc.gridx = 1;
            mainAddPanel.add(Box.createRigidArea(spacer), gbc);

            gbc.gridy = 9;
            gbc.gridwidth = 2;
            gbc.gridx = 3;
            gbc.anchor = GridBagConstraints.LINE_END;
            gbc.fill = GridBagConstraints.NONE;
            mainAddPanel.add(addButton, gbc);


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

                    case "Add Book" -> System.out.println("hi");
                    default -> System.out.println("Error");
                }
            }
        }

//        public void add(){
//            JOptionPane.showMessageDialog(null, "added!" );
//            DataBaseQueries.noOfAddressID+=1;
//            Address address = new Address(DataBaseQueries.noOfAddressID, Integer.parseInt(versionTF.getText()), publishDateTF.getText(), Integer.parseInt(royaltyTF.getText()),
//                    cityTF.getText(), Objects.requireNonNull(provinceCB.getSelectedItem()).toString(), countryTF.getText(), postalCodeTF.getText());
//            DataBaseQueries.addNewAddress(address);
//            DataBaseQueries.noOfUsers+=1;
//            User user = new User(usernameTF.getText(), titleTF.getText(), numCopiesTF.getText(),
//                    priceTF.getText(), genreTF.getText(),DataBaseQueries.noOfAddressID);
//            DataBaseQueries.addNewUser(user);
//            LookInnaBookFrame.currentUser = user;
//            new LookInnaBookFrame(library, LookInnaBookFrame.users, true, true, false);
//            this.dispose();
//        }
        public static void main(String[] args) {
            new ManagerAddFrame();
        }

    }




