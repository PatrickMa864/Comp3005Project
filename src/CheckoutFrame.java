import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class CheckoutFrame extends JFrame implements ActionListener{

    private final JButton cancelOrder = new JButton("Cancel Checkout"),
            submitOrder = new JButton("Confirm Order");

    private final JTextField shippingStreetNumTF = new JTextField(5),
            shippingStreetNameTF = new JTextField(10),
            shippingApartmentTF = new JTextField(5),
            shippingCityTF = new JTextField(10),
            shippingCountryTF = new JTextField(10),
            shippingPostalCodeTF = new JTextField(10),
            billingStreetNumTF = new JTextField(5),
            billingStreetNameTF = new JTextField(10),
            billingApartmentTF = new JTextField(5),
            billingCityTF = new JTextField(10),
            billingCountryTF = new JTextField(10),
            billingPostalCodeTF = new JTextField(10),
            creditCardNumTF = new JTextField(10),
            creditCardExpTF = new JTextField(5),
            creditCardCVVTF = new JTextField(5);


    final JComboBox<String> shippingProvinceCB = new JComboBox<>(Address.Provinces),
            billingProvinceCB = new JComboBox<>(Address.Provinces);

    private final JCheckBox billingSameAsShipping = new JCheckBox("Billing Address is the same as Shipping Address");

    private final JTextField errorLabel = new JTextField("");


    private final User user;
    private final double totalCost;

    public CheckoutFrame(User user, double totalCost, Basket basket) {
        // Setup a cart view

        System.out.println("hihi");
        JPanel cartPanel = new JPanel();
        JLabel cartLabel = new JLabel("Cart: ");
        cartPanel.setLayout(new BorderLayout());
        cartLabel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        cartPanel.add(cartLabel, BorderLayout.PAGE_START);
        // JPanels
        JPanel cart = new JPanel(new GridLayout(1, 1));
        JScrollPane currentCart = new JScrollPane(cart,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        currentCart.setMaximumSize(new Dimension(198, 500));
        cartPanel.add(currentCart, BorderLayout.CENTER);
        //cartPanel.setPreferredSize(cartDimension);

        // Allow the order number to be highlighted and copied
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setMaximumSize(new Dimension(200, 20));
        errorLabel.setEditable(false);
        errorLabel.setBackground(null);
        errorLabel.setBorder(null);

        shippingProvinceCB.setBackground(Color.WHITE);
        billingProvinceCB.setBackground(Color.WHITE);
        this.user= user;
        this.totalCost = totalCost;
        Address address = user.getAddress();
        //ArrayList<Object> userInfo = DatabaseQueries.lookForaUser(username);
        Container c = this.getContentPane();
        // Clear GUI in order to reload
        this.setPreferredSize(new Dimension(1150, 550));
        this.setMaximumSize(new Dimension(1100, 500));
        if (this.getJMenuBar() != null) this.getJMenuBar().setVisible(false);
        c.removeAll();

        // Setup Components

        /* ToolTips */
        billingStreetNumTF.setToolTipText("Enter the street number for your billing address");
        billingStreetNameTF.setToolTipText("Enter the street name for your billing address");
        billingApartmentTF.setToolTipText("Enter the apartment number for your billing address (Optional)");
        billingCityTF.setToolTipText("Enter the city name for your billing address");
        billingProvinceCB.setToolTipText("Select the province name for your billing address");
        billingCountryTF.setToolTipText("Enter the country name for your billing address");
        billingPostalCodeTF.setToolTipText("Enter the postal code for your billing address (Format: X1X1X1");
        shippingStreetNumTF.setToolTipText("Enter the street number for your shipping address");
        shippingStreetNameTF.setToolTipText("Enter the street name for your shipping address");
        shippingApartmentTF.setToolTipText("Enter the apartment number for your shipping address (Optional)");
        shippingCityTF.setToolTipText("Enter the city name for your shipping address");
        shippingProvinceCB.setToolTipText("Select the province name for your shipping address");
        shippingCountryTF.setToolTipText("Enter the country name for your shipping address");
        shippingPostalCodeTF.setToolTipText("Enter the postal code for your shipping address (Format: X1X1X1");
        billingSameAsShipping.setToolTipText("Select this if your shipping address is the same as your billing address");
        creditCardNumTF.setToolTipText("Enter your 16-digit credit card number, without spaces");
        creditCardExpTF.setToolTipText("Enter your credit card's expiry date (Format: MMYY)");
        creditCardCVVTF.setToolTipText("Enter your credit card's 3 digit CVV");
        cancelOrder.setToolTipText("Return to the store page");
        submitOrder.setToolTipText("Submit your order to the store");

        /* Panel */
        final GridBagLayout layout = new GridBagLayout();
        JPanel checkoutPanel = new JPanel(layout);

        /* JLabels */
        JLabel totalPriceLabel = new JLabel("Total Price: ", JLabel.RIGHT),
                userLabel = new JLabel("<html><u>User</u>: " + user.getUserName() + "</html>"),
                nameLabel = new JLabel("<html><u>Name</u>: " + user.getFirstName() + " " + user.getLastName() + "</html>"),
                emailLabel = new JLabel("<html><u>Email will be sent to</u>: " + user.getEmail() + "</html>"),
                // checkout shipping address info
                shippingLabel = new JLabel("Confirm Shipping Address"),
                streetNumLabel = new JLabel("*Street Number: "),
                streetNameLabel = new JLabel("*Street Name: ", JLabel.RIGHT),
                apartmentLabel = new JLabel("Apartment: ", JLabel.RIGHT),
                cityLabel = new JLabel("*City: "),
                provinceLabel = new JLabel("*Province: ", JLabel.RIGHT),
                countryLabel = new JLabel("*Country: "),
                postalCodeLabel = new JLabel("*Postal Code: ", JLabel.RIGHT),
                //  checkout Billing info
                billingAddressLabel = new JLabel("Billing Info"),
                creditCardNumLabel = new JLabel("Credit Card #: "),
                creditCardExpLabel = new JLabel("EXP: "),
                creditCardCVV = new JLabel("CVV: ", JLabel.RIGHT),
                billingStreetNumLabel = new JLabel("Street Number: "),
                billingStreetNameLabel = new JLabel("Street Name: ", JLabel.RIGHT),
                billingApartmentLabel = new JLabel("Apartment: ", JLabel.RIGHT),
                billingCityLabel = new JLabel("City: "),
                billingProvinceLabel = new JLabel("Province: ", JLabel.RIGHT),
                billingCountryLabel = new JLabel("Country: "),
                billingPostalCodeLabel = new JLabel("Postal Code: ", JLabel.RIGHT);

        /* Setup checkoutPanel */
        // Get the addresses of the user.
        //ArrayList<Object> addresses = DatabaseQueries.lookForanAddressWithID(username);
        //boolean sameAddress = (user.getAddress().getStreet().equals(addresses.get(8).toString()) && Objects.requireNonNull(addresses).get(6).toString().equals(addresses.get(14).toString()));
        boolean sameAddress = true;
        /* ActionListeners */
        cancelOrder.addActionListener(this);
        submitOrder.addActionListener(this);
        billingSameAsShipping.addActionListener(e -> {
            boolean sameAsBilling = !billingSameAsShipping.isSelected();
            billingStreetNumTF.setEnabled(sameAsBilling);
            billingStreetNameTF.setEnabled(sameAsBilling);
            billingApartmentTF.setEnabled(sameAsBilling);
            billingCityTF.setEnabled(sameAsBilling);
            billingProvinceCB.setEnabled(sameAsBilling);
            billingCountryTF.setEnabled(sameAsBilling);
            billingPostalCodeTF.setEnabled(sameAsBilling);
        });

        billingSameAsShipping.setSelected(sameAddress);
        // Populate the fields with values where appropriate.
        if (!sameAddress) {
            billingStreetNumTF.setText(valueOf(address.getStreetNum()));
            billingStreetNameTF.setText(address.getStreet());
            billingApartmentTF.setText(valueOf(address.getApartmentNo()));
            billingCityTF.setText(address.getCity());
            billingProvinceCB.setSelectedItem(address.getProvince());
            billingCountryTF.setText(address.getCountry());
            billingPostalCodeTF.setText(address.getPostalCode());
        }
        shippingStreetNumTF.setText(valueOf(address.getStreetNum()));
        shippingStreetNameTF.setText(address.getStreet());
        shippingApartmentTF.setText(valueOf(address.getApartmentNo()));
        shippingCityTF.setText(address.getCity());
        shippingProvinceCB.setSelectedItem(address.getProvince());
        shippingCountryTF.setText(address.getCountry());
        shippingPostalCodeTF.setText(address.getPostalCode());

        // Only enable the billing fields if the user has a different billing and shipping address.
        billingStreetNumTF.setEnabled(!sameAddress);
        billingStreetNameTF.setEnabled(!sameAddress);
        billingApartmentTF.setEnabled(!sameAddress);
        billingCityTF.setEnabled(!sameAddress);
        billingProvinceCB.setEnabled(!sameAddress);
        billingCountryTF.setEnabled(!sameAddress);
        billingPostalCodeTF.setEnabled(!sameAddress);

        GridBagConstraints con = new GridBagConstraints();
        Dimension spacer = new Dimension(25, 25);
        con.gridy = 0;
        con.gridx = 2;
        con.gridwidth = 8;
        errorLabel.setForeground(Color.red);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.anchor = GridBagConstraints.CENTER;
        checkoutPanel.add(errorLabel, con);
        con.gridx = 0;
        con.anchor = GridBagConstraints.LINE_START;
        con.fill = GridBagConstraints.NONE;
        checkoutPanel.add(cancelOrder, con);

        con.gridy = 1;
        con.gridx = 1;
        con.weightx = 1.0;
        con.gridwidth = 4;
        con.anchor = GridBagConstraints.LINE_START;
        userLabel.setFont(userLabel.getFont().deriveFont(Font.BOLD));
        checkoutPanel.add(userLabel, con);

        con.gridy = 2;
        con.gridx = 1;
        con.gridwidth = 4;
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));
        checkoutPanel.add(nameLabel, con);

        con.gridy = 3;
        con.gridx = 1;
        con.gridwidth = 4;
        emailLabel.setFont(emailLabel.getFont().deriveFont(Font.BOLD));
        checkoutPanel.add(emailLabel, con);

        con.gridy = 4;
        con.gridx = 1;
        checkoutPanel.add(Box.createRigidArea(spacer), con);

        con.gridy = 5;
        con.gridx = 1;
        con.gridwidth = 4;
        shippingLabel.setFont(shippingLabel.getFont().deriveFont(Font.BOLD));
        checkoutPanel.add(shippingLabel, con);

        con.gridy = 6;
        con.gridx = 1;
        con.gridwidth = 1;
        checkoutPanel.add(streetNumLabel, con);
        con.gridx = 2;
        con.fill = GridBagConstraints.NONE;
        checkoutPanel.add(shippingStreetNumTF, con);
        con.gridx = 3;
        con.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(streetNameLabel, con);
        con.gridx = 4;
        con.fill = GridBagConstraints.NONE;
        checkoutPanel.add(shippingStreetNameTF, con);
        con.gridx = 5;
        con.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(apartmentLabel, con);
        con.gridx = 6;
        con.fill = GridBagConstraints.NONE;
        checkoutPanel.add(shippingApartmentTF, con);

        con.gridy = 7;
        con.gridx = 1;
        con.gridwidth = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(cityLabel, con);
        con.gridx = 4;
        checkoutPanel.add(provinceLabel, con);
        con.gridx = 2;
        con.gridwidth = 2;
        con.fill = GridBagConstraints.NONE;
        checkoutPanel.add(shippingCityTF, con);
        con.gridx = 5;
        con.gridwidth = 1;
        checkoutPanel.add(shippingProvinceCB, con);

        con.gridy = 8;
        con.gridx = 1;
        con.gridwidth = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(countryLabel, con);
        con.gridx = 4;
        checkoutPanel.add(postalCodeLabel, con);
        con.gridx = 2;
        con.gridwidth = 2;
        con.fill = GridBagConstraints.NONE;
        checkoutPanel.add(shippingCountryTF, con);
        con.gridx = 5;
        con.gridwidth = 1;
        con.anchor = GridBagConstraints.LINE_START;
        checkoutPanel.add(shippingPostalCodeTF, con);

        con.gridy = 9;
        con.gridx = 1;
        checkoutPanel.add(Box.createRigidArea(spacer), con);

        con.gridy = 10;
        con.gridx = 1;
        con.gridwidth = 3;
        con.fill = GridBagConstraints.HORIZONTAL;
        billingAddressLabel.setFont(billingAddressLabel.getFont().deriveFont(Font.BOLD));
        checkoutPanel.add(billingAddressLabel, con);
        con.gridx = 3;
        checkoutPanel.add(billingSameAsShipping, con);

        con.gridy = 11;
        con.gridx = 1;
        con.gridwidth = 1;
        checkoutPanel.add(creditCardNumLabel, con);
        con.gridx = 2;
        con.gridwidth = 3;
        //  checkout Billing info
        checkoutPanel.add(creditCardNumTF, con);

        con.gridy = 12;
        con.gridx = 1;
        con.gridwidth = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(creditCardExpLabel, con);
        con.gridx = 2;
        con.fill = GridBagConstraints.NONE;
        checkoutPanel.add(creditCardExpTF, con);
        con.gridx = 3;
        con.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(creditCardCVV, con);
        con.gridx = 4;
        con.fill = GridBagConstraints.NONE;
        checkoutPanel.add(creditCardCVVTF, con);

        con.gridy = 13;
        con.gridx = 1;
        checkoutPanel.add(Box.createRigidArea(spacer), con);

        con.gridy = 14;
        con.gridx = 1;
        con.gridwidth = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(billingStreetNumLabel, con);
        con.gridx = 2;
        con.fill = GridBagConstraints.NONE;
        checkoutPanel.add(billingStreetNumTF, con);
        con.gridx = 3;
        con.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(billingStreetNameLabel, con);
        con.gridx = 4;
        con.fill = GridBagConstraints.NONE;
        checkoutPanel.add(billingStreetNameTF, con);
        con.gridx = 5;
        con.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(billingApartmentLabel, con);
        con.gridx = 6;
        con.fill = GridBagConstraints.NONE;
        checkoutPanel.add(billingApartmentTF, con);

        con.gridy = 15;
        con.gridx = 1;
        con.gridwidth = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(billingCityLabel, con);
        con.gridx = 4;
        checkoutPanel.add(billingProvinceLabel, con);
        con.gridx = 2;
        con.gridwidth = 2;
        con.fill = GridBagConstraints.NONE;
        checkoutPanel.add(billingCityTF, con);
        con.gridx = 5;
        con.gridwidth = 1;
        checkoutPanel.add(billingProvinceCB, con);

        con.gridy = 16;
        con.gridx = 1;
        con.gridwidth = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(billingCountryLabel, con);
        con.gridx = 4;
        checkoutPanel.add(billingPostalCodeLabel, con);
        con.gridx = 2;
        con.gridwidth = 2;
        con.fill = GridBagConstraints.NONE;
        checkoutPanel.add(billingCountryTF, con);
        con.gridx = 5;
        con.gridwidth = 1;
        con.anchor = GridBagConstraints.LINE_START;
        checkoutPanel.add(billingPostalCodeTF, con);

        con.gridy = 17;
        con.gridx = 1;
        checkoutPanel.add(Box.createRigidArea(spacer), con);

        con.gridy = 18;
        con.gridx = 4;
        con.gridwidth = 1;
        con.anchor = GridBagConstraints.LINE_END;
        con.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(totalPriceLabel, con);
        con.gridx = 5;
        con.anchor = GridBagConstraints.CENTER;
        // JLabels
        JLabel checkoutTotalPriceValueLabel = new JLabel(valueOf(totalCost), JLabel.CENTER);
        checkoutPanel.add(checkoutTotalPriceValueLabel, con);
        con.gridwidth = 2;
        con.gridx = 6;
        con.anchor = GridBagConstraints.LINE_END;
        con.fill = GridBagConstraints.NONE;
        checkoutPanel.add(submitOrder, con);

        con.gridy = 19;
        con.gridx = 5;
        con.gridwidth = 2;
        con.anchor = GridBagConstraints.LINE_END;
        JLabel checkoutSuccessLabel = new JLabel("", JLabel.CENTER);
        checkoutPanel.add(checkoutSuccessLabel, con);

        con.gridy = 20; // shift everything to the top and center
        con.gridx = 0;
        con.weighty = 1.0;
        con.weightx = 1.0;
        con.gridwidth = 1;
        con.anchor = GridBagConstraints.CENTER;
        con.fill = GridBagConstraints.BOTH;
        checkoutPanel.add(Box.createGlue(), con);
        con.gridx = 6;
        checkoutPanel.add(Box.createGlue(), con);

        checkoutPanel.setBorder(new EmptyBorder(10, 10, 10, 10));


        JSplitPane checkoutAndCart = new JSplitPane();
        checkoutAndCart.setEnabled(false);
        checkoutPanel.setMinimumSize(new Dimension(825, 500));
        checkoutAndCart.setLeftComponent(checkoutPanel);
        cart.setMaximumSize(new Dimension(198, 500));
        cartPanel.setMaximumSize(new Dimension(198, 500));

        for (Book b : basket.getBooks()) {
            JTextArea area = new JTextArea();
            area.setMinimumSize(new Dimension(198, 100));
            area.setMaximumSize(new Dimension(198, 100));
            area.setText(b.getSimpleInfo());
            if (basket.getBooks().size() > 6) {
                cart.setLayout(new GridLayout(basket.getBooks().size(), 1));
            } else {
                cart.setLayout(new GridLayout(7, 1));
            }
            cart.add(area);
        }

        checkoutAndCart.setRightComponent(cartPanel);

        //DatabaseQueries.lookForaUser(username);

        c.add(checkoutAndCart);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("LookInnaBook");
        this.setVisible(true);

    }


    /**
     * Implements ActionListeners for GUI components
     *
     * @param e The ActionEvent that was triggered via a JButton.
     */
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o instanceof JButton) {
            switch (((JButton) o).getText()) {
                case "Cancel Checkout", "Return to Bookstore" -> {
                    this.dispose();
                }
                case "Confirm Order" -> System.out.println("ordered"); // checkoutScreen
                default -> System.out.println("Error");
            }
        }
    }

}
