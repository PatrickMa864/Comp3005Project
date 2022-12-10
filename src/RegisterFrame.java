import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static java.lang.String.valueOf;

public class RegisterFrame extends JFrame implements ActionListener {
    private Basket library;
    private final JButton register = new JButton("Register");

    private final JLabel usernameL = new JLabel("Username:"),
            passwordL = new JLabel("Password:"),
            emailL =  new JLabel("Email:"),
            firstNameL =  new JLabel("First Name:"),
            lastNameL =  new JLabel("Last Name:"),
            addressIDL =  new JLabel("Address:"),
            apartmentNumL =  new JLabel("Apt No.:"),
            streetNameL =  new JLabel("Street Name:"),
            streetNumL =  new JLabel("Street Num:"),
            cityL = new JLabel("City:"),
            provinceL = new JLabel("Province:"),
            countryL =  new JLabel("Country:"),
            postalCodeL = new JLabel("Postal Code"),
            addressL = new JLabel("Address:");

    private final JTextField usernameTF = new JTextField(8),
            passwordTF = new JTextField(8),
            emailTF = new JTextField(20),
            firstNameTF = new JTextField(14),
            lastNameTF = new JTextField(14),

            addressIDTF = new JTextField(8),
            apartmentNumTF = new JTextField(4),
            streetNameTF = new JTextField(20),
            streetNumTF = new JTextField(4),
            cityTF = new JTextField(8),
            countryTF = new JTextField(8),
            postalCodeTF = new JTextField(6);
    final JComboBox<String> provinceCB = new JComboBox<>(Address.Provinces);

    private final JTextField errorLabel = new JTextField("");


    public RegisterFrame(Basket library){
        this.library=library;
        JPanel registerPanel = new JPanel();
        JLabel registerLabel = new JLabel("Register: ");
        registerPanel.setLayout(new BorderLayout());
        registerLabel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        registerPanel.add(registerLabel, BorderLayout.PAGE_START);

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
        JPanel mainRegisterPanel = new JPanel(layout);

        register.addActionListener(this);

        GridBagConstraints gbc = new GridBagConstraints();
        Dimension spacer = new Dimension(25, 25);

        gbc.gridy = 0;
        gbc.gridx = 2;
        gbc.gridwidth = 8;
        errorLabel.setForeground(Color.red);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        mainRegisterPanel.add(errorLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        mainRegisterPanel.add(registerLabel,gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainRegisterPanel.add(usernameL, gbc);
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        mainRegisterPanel.add(usernameTF, gbc);
        gbc.gridx = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainRegisterPanel.add(passwordL, gbc);
        gbc.gridx = 4;
        gbc.fill = GridBagConstraints.NONE;
        mainRegisterPanel.add(passwordTF, gbc);
        gbc.gridx = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainRegisterPanel.add(emailL, gbc);
        gbc.gridx = 6;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 8;
        mainRegisterPanel.add(emailTF, gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainRegisterPanel.add(firstNameL, gbc);
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 8;
        mainRegisterPanel.add(firstNameTF, gbc);
        gbc.gridx = 5;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainRegisterPanel.add(lastNameL, gbc);
        gbc.gridx = 6;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 8;
        mainRegisterPanel.add(lastNameTF, gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        mainRegisterPanel.add(Box.createRigidArea(spacer), gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        mainRegisterPanel.add(addressL,gbc);

        gbc.gridy = 5;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainRegisterPanel.add(apartmentNumL, gbc);
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        mainRegisterPanel.add(apartmentNumTF, gbc);
        gbc.gridx = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainRegisterPanel.add(streetNumL, gbc);
        gbc.gridx = 4;
        gbc.fill = GridBagConstraints.NONE;
        mainRegisterPanel.add(streetNumTF, gbc);
        gbc.gridx = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainRegisterPanel.add(streetNameL, gbc);
        gbc.gridx = 6;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 8;
        mainRegisterPanel.add(streetNameTF, gbc);

        gbc.gridy = 6;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainRegisterPanel.add(cityL, gbc);
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        mainRegisterPanel.add(cityTF, gbc);
        gbc.gridx = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainRegisterPanel.add(provinceL, gbc);
        gbc.gridx = 4;
        gbc.fill = GridBagConstraints.NONE;
        mainRegisterPanel.add(provinceCB, gbc);
        gbc.gridx = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainRegisterPanel.add(countryL, gbc);
        gbc.gridx = 6;
        gbc.fill = GridBagConstraints.NONE;
        mainRegisterPanel.add(countryTF, gbc);
        gbc.gridx = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainRegisterPanel.add(postalCodeL, gbc);
        gbc.gridx = 8;
        gbc.fill = GridBagConstraints.NONE;
        mainRegisterPanel.add(postalCodeTF, gbc);

        gbc.gridy = 7;
        gbc.gridx = 1;
        mainRegisterPanel.add(Box.createRigidArea(spacer), gbc);

        gbc.gridy = 8;
        gbc.gridx = 1;
        mainRegisterPanel.add(Box.createRigidArea(spacer), gbc);

        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        mainRegisterPanel.add(register, gbc);


        this.add(mainRegisterPanel);

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

                case "Register" -> register();
                default -> System.out.println("Error");
            }
        }
    }

    public void register(){
        JOptionPane.showMessageDialog(null, "Registered!" );
        DataBaseQueries.noOfAddressID+=1;
        Address address = new Address(DataBaseQueries.noOfAddressID, Integer.parseInt(apartmentNumTF.getText()), streetNameTF.getText(), Integer.parseInt(streetNumTF.getText()),
                cityTF.getText(), Objects.requireNonNull(provinceCB.getSelectedItem()).toString(), countryTF.getText(), postalCodeTF.getText());
        DataBaseQueries.addNewAddress(address);
        DataBaseQueries.noOfUsers+=1;
        User user = new User(usernameTF.getText(), passwordTF.getText(), firstNameTF.getText(),
                lastNameTF.getText(), emailTF.getText(),DataBaseQueries.noOfAddressID);
        DataBaseQueries.addNewUser(user);
        LookInnaBookFrame.currentUser = user;
        new LookInnaBookFrame(library, LookInnaBookFrame.users, true, true);
        this.dispose();
    }

}

