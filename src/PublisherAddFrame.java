import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


import static java.lang.String.valueOf;

public class PublisherAddFrame extends JFrame implements ActionListener {
    private final JButton addPublisherButton = new JButton("Add Publisher");

    private final JLabel publisherNameL = new JLabel("*Publisher Name:"),
            publisherEmailL = new JLabel("*Email:"),
            publisherPhoneL = new JLabel("*Phone Number:"),
            publisherBankingL = new JLabel("*Bank Account:"),
            addressLabel = new JLabel("Address:"),
            streetNumLabel = new JLabel("*Street Number: "),
            streetNameLabel = new JLabel("*Street Name: ", JLabel.RIGHT),
            apartmentLabel = new JLabel("*Apartment: ", JLabel.RIGHT),
            cityLabel = new JLabel("*City: "),
            provinceLabel = new JLabel("*Province: ", JLabel.RIGHT),
            countryLabel = new JLabel("*Country: "),
            postalCodeLabel = new JLabel("*Postal Code: ", JLabel.RIGHT);


    private final JTextField publisherNameTF = new JTextField(10),
            publisherEmailTF = new JTextField(10),
            publisherPhoneTF = new JTextField(10),
            publisherBankingTF = new JTextField(10),
            streetNumTF = new JTextField(5),
            streetNameTF = new JTextField(10),
            apartmentTF = new JTextField(5),
            cityTF = new JTextField(10),
            countryTF = new JTextField(10),
            postalCodeTF = new JTextField(10);


    private final JTextField errorLabel = new JTextField("");
    final JComboBox<String> provinceCB = new JComboBox<>(Address.Provinces);


    public PublisherAddFrame() {
        JPanel publisherPanel = new JPanel();
        JLabel publisherLabel = new JLabel("Enter Publisher Information: ");
        publisherPanel.setLayout(new BorderLayout());
        publisherLabel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        publisherPanel.add(publisherLabel, BorderLayout.PAGE_START);

        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setMaximumSize(new Dimension(200, 20));
        errorLabel.setEditable(false);
        errorLabel.setBackground(null);
        errorLabel.setBorder(null);

        Container c = this.getContentPane();
        // Clear GUI in order to reload
        this.setPreferredSize(new Dimension(800, 270));
        this.setMaximumSize(new Dimension(800, 270));
        if (this.getJMenuBar() != null) this.getJMenuBar().setVisible(false);
        c.removeAll();

        final GridBagLayout layout = new GridBagLayout();
        JPanel mainAddPanel = new JPanel(layout);

        addPublisherButton.addActionListener(this);

        GridBagConstraints gbc = new GridBagConstraints();
        Dimension spacer = new Dimension(25, 25);

        gbc.gridy = 0;
        gbc.gridx = 2;
        gbc.gridwidth = 10;
        errorLabel.setForeground(Color.red);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        mainAddPanel.add(errorLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        mainAddPanel.add(publisherLabel, gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainAddPanel.add(publisherNameL, gbc);
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 2;
        mainAddPanel.add(publisherNameTF, gbc);
        gbc.gridx = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        mainAddPanel.add(publisherEmailL, gbc);
        gbc.gridx = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 2;
        mainAddPanel.add(publisherEmailTF, gbc);


        gbc.gridy = 3;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainAddPanel.add(publisherBankingL, gbc);
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 2;
        mainAddPanel.add(publisherBankingTF, gbc);
        gbc.gridx = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        mainAddPanel.add(publisherPhoneL, gbc);
        gbc.gridx = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 2;
        mainAddPanel.add(publisherPhoneTF, gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;
        mainAddPanel.add(Box.createRigidArea(spacer), gbc);

        gbc.gridy = 5;
        gbc.gridx = 1;
        gbc.gridwidth = 4;
        addressLabel.setFont(addressLabel.getFont().deriveFont(Font.BOLD));
        mainAddPanel.add(addressLabel, gbc);

        gbc.gridy = 6;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        mainAddPanel.add(streetNumLabel, gbc);
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        mainAddPanel.add(streetNumTF, gbc);
        gbc.gridx = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainAddPanel.add(streetNameLabel, gbc);
        gbc.gridx = 4;
        gbc.fill = GridBagConstraints.NONE;
        mainAddPanel.add(streetNameTF, gbc);
        gbc.gridx = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainAddPanel.add(apartmentLabel, gbc);
        gbc.gridx = 6;
        gbc.fill = GridBagConstraints.NONE;
        mainAddPanel.add(apartmentTF, gbc);

        gbc.gridy = 7;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainAddPanel.add(cityLabel, gbc);
        gbc.gridx = 4;
        mainAddPanel.add(provinceLabel, gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        mainAddPanel.add(cityTF, gbc);
        gbc.gridx = 5;
        gbc.gridwidth = 1;
        mainAddPanel.add(provinceCB, gbc);

        gbc.gridy = 8;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainAddPanel.add(countryLabel, gbc);
        gbc.gridx = 4;
        mainAddPanel.add(postalCodeLabel, gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        mainAddPanel.add(countryTF, gbc);
        gbc.gridx = 5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        mainAddPanel.add(postalCodeTF, gbc);

        gbc.gridy = 9;
        gbc.gridx = 1;
        mainAddPanel.add(Box.createRigidArea(spacer), gbc);

        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.gridx = 6;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        mainAddPanel.add(addPublisherButton, gbc);


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

                case "Add Publisher" :
                    makeAddress();
                    makePublisher();
                    this.dispose();
                    break;
                default :
                    System.out.println("Error");
            }
        }
    }
    public void makeAddress(){
        DataBaseQueries.updateAddressCount();
        DataBaseQueries.noOfAddressID += 1;
        ManagerAddFrame.address = new Address(DataBaseQueries.noOfAddressID, Integer.parseInt(apartmentTF.getText()),
                streetNameTF.getText(), Integer.parseInt(streetNumTF.getText()), cityTF.getText(),
                Objects.requireNonNull(provinceCB.getSelectedItem()).toString(), countryTF.getText(), postalCodeTF.getText());
        DataBaseQueries.addNewAddress(ManagerAddFrame.address);
    }

    public void makePublisher(){
        ManagerAddFrame.publisher = new Publisher(publisherNameTF.getText(),Long.parseLong(publisherPhoneTF.getText()), publisherEmailTF.getText(),
                Long.parseLong(publisherBankingTF.getText()), ManagerAddFrame.address.getAddress_id());
        DataBaseQueries.addNewPublisher(ManagerAddFrame.publisher);

    }

    public static void main(String[] args) {
        new PublisherAddFrame();
    }
}
