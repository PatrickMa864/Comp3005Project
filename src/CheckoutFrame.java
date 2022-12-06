import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

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
    private Basket basket;

    private LookInnaBookFrame lookInnaBookFrame;

    private int orderNum = 0;

    public CheckoutFrame(User user, double totalCost, Basket basket, LookInnaBookFrame lookInnaBookFrame) {

        JPanel cartPanel = new JPanel();
        JLabel cartLabel = new JLabel("Cart: ");
        cartPanel.setLayout(new BorderLayout());
        cartLabel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        cartPanel.add(cartLabel, BorderLayout.PAGE_START);

        JPanel cart = new JPanel(new GridLayout(1, 1));
        JScrollPane currentCart = new JScrollPane(cart,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        currentCart.setMaximumSize(new Dimension(198, 500));
        cartPanel.add(currentCart, BorderLayout.CENTER);

        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setMaximumSize(new Dimension(200, 20));
        errorLabel.setEditable(false);
        errorLabel.setBackground(null);
        errorLabel.setBorder(null);

        shippingProvinceCB.setBackground(Color.WHITE);
        billingProvinceCB.setBackground(Color.WHITE);
        this.user= user;
        this.totalCost = totalCost;
        this.basket = basket;
        this.lookInnaBookFrame = lookInnaBookFrame;
        Address address = user.getAddress();
        //ArrayList<Object> userInfo = DatabaseQueries.lookForaUser(username);
        Container c = this.getContentPane();
        // Clear GUI in order to reload
        this.setPreferredSize(new Dimension(1150, 550));
        this.setMaximumSize(new Dimension(1100, 500));
        if (this.getJMenuBar() != null) this.getJMenuBar().setVisible(false);
        c.removeAll();


        final GridBagLayout layout = new GridBagLayout();
        JPanel checkoutPanel = new JPanel(layout);

        JLabel totalPriceLabel = new JLabel("Total Price: ", JLabel.RIGHT),
                userLabel = new JLabel("User: " + user.getUserName() ),
                nameLabel = new JLabel("Name: " + user.getFirstName() + " " + user.getLastName()),
                emailLabel = new JLabel("Email: " + user.getEmail() ),
                shippingLabel = new JLabel("Confirm Shipping Address"),
                streetNumLabel = new JLabel("*Street Number: "),
                streetNameLabel = new JLabel("*Street Name: ", JLabel.RIGHT),
                apartmentLabel = new JLabel("Apartment: ", JLabel.RIGHT),
                cityLabel = new JLabel("*City: "),
                provinceLabel = new JLabel("*Province: ", JLabel.RIGHT),
                countryLabel = new JLabel("*Country: "),
                postalCodeLabel = new JLabel("*Postal Code: ", JLabel.RIGHT),
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


        boolean sameAddress = true;

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
        billingStreetNumTF.setEnabled(!sameAddress);
        billingStreetNameTF.setEnabled(!sameAddress);
        billingApartmentTF.setEnabled(!sameAddress);
        billingCityTF.setEnabled(!sameAddress);
        billingProvinceCB.setEnabled(!sameAddress);
        billingCountryTF.setEnabled(!sameAddress);
        billingPostalCodeTF.setEnabled(!sameAddress);

        GridBagConstraints gbc = new GridBagConstraints();
        Dimension spacer = new Dimension(25, 25);
        gbc.gridy = 0;
        gbc.gridx = 2;
        gbc.gridwidth = 8;
        errorLabel.setForeground(Color.red);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        checkoutPanel.add(errorLabel, gbc);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        checkoutPanel.add(cancelOrder, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        userLabel.setFont(userLabel.getFont().deriveFont(Font.BOLD));
        checkoutPanel.add(userLabel, gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.gridwidth = 4;
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));
        checkoutPanel.add(nameLabel, gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        gbc.gridwidth = 4;
        emailLabel.setFont(emailLabel.getFont().deriveFont(Font.BOLD));
        checkoutPanel.add(emailLabel, gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;
        checkoutPanel.add(Box.createRigidArea(spacer), gbc);

        gbc.gridy = 5;
        gbc.gridx = 1;
        gbc.gridwidth = 4;
        shippingLabel.setFont(shippingLabel.getFont().deriveFont(Font.BOLD));
        checkoutPanel.add(shippingLabel, gbc);

        gbc.gridy = 6;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        checkoutPanel.add(streetNumLabel, gbc);
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        checkoutPanel.add(shippingStreetNumTF, gbc);
        gbc.gridx = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(streetNameLabel, gbc);
        gbc.gridx = 4;
        gbc.fill = GridBagConstraints.NONE;
        checkoutPanel.add(shippingStreetNameTF, gbc);
        gbc.gridx = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(apartmentLabel, gbc);
        gbc.gridx = 6;
        gbc.fill = GridBagConstraints.NONE;
        checkoutPanel.add(shippingApartmentTF, gbc);

        gbc.gridy = 7;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(cityLabel, gbc);
        gbc.gridx = 4;
        checkoutPanel.add(provinceLabel, gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        checkoutPanel.add(shippingCityTF, gbc);
        gbc.gridx = 5;
        gbc.gridwidth = 1;
        checkoutPanel.add(shippingProvinceCB, gbc);

        gbc.gridy = 8;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(countryLabel, gbc);
        gbc.gridx = 4;
        checkoutPanel.add(postalCodeLabel, gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        checkoutPanel.add(shippingCountryTF, gbc);
        gbc.gridx = 5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        checkoutPanel.add(shippingPostalCodeTF, gbc);

        gbc.gridy = 9;
        gbc.gridx = 1;
        checkoutPanel.add(Box.createRigidArea(spacer), gbc);

        gbc.gridy = 10;
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        billingAddressLabel.setFont(billingAddressLabel.getFont().deriveFont(Font.BOLD));
        checkoutPanel.add(billingAddressLabel, gbc);
        gbc.gridx = 3;
        checkoutPanel.add(billingSameAsShipping, gbc);

        gbc.gridy = 11;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        checkoutPanel.add(creditCardNumLabel, gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 3;
        //  checkout Billing info
        checkoutPanel.add(creditCardNumTF, gbc);

        gbc.gridy = 12;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(creditCardExpLabel, gbc);
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        checkoutPanel.add(creditCardExpTF, gbc);
        gbc.gridx = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(creditCardCVV, gbc);
        gbc.gridx = 4;
        gbc.fill = GridBagConstraints.NONE;
        checkoutPanel.add(creditCardCVVTF, gbc);

        gbc.gridy = 13;
        gbc.gridx = 1;
        checkoutPanel.add(Box.createRigidArea(spacer), gbc);

        gbc.gridy = 14;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(billingStreetNumLabel, gbc);
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        checkoutPanel.add(billingStreetNumTF, gbc);
        gbc.gridx = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(billingStreetNameLabel, gbc);
        gbc.gridx = 4;
        gbc.fill = GridBagConstraints.NONE;
        checkoutPanel.add(billingStreetNameTF, gbc);
        gbc.gridx = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(billingApartmentLabel, gbc);
        gbc.gridx = 6;
        gbc.fill = GridBagConstraints.NONE;
        checkoutPanel.add(billingApartmentTF, gbc);

        gbc.gridy = 15;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(billingCityLabel, gbc);
        gbc.gridx = 4;
        checkoutPanel.add(billingProvinceLabel, gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        checkoutPanel.add(billingCityTF, gbc);
        gbc.gridx = 5;
        gbc.gridwidth = 1;
        checkoutPanel.add(billingProvinceCB, gbc);

        gbc.gridy = 16;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(billingCountryLabel, gbc);
        gbc.gridx = 4;
        checkoutPanel.add(billingPostalCodeLabel, gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        checkoutPanel.add(billingCountryTF, gbc);
        gbc.gridx = 5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        checkoutPanel.add(billingPostalCodeTF, gbc);

        gbc.gridy = 17;
        gbc.gridx = 1;
        checkoutPanel.add(Box.createRigidArea(spacer), gbc);

        gbc.gridy = 18;
        gbc.gridx = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        checkoutPanel.add(totalPriceLabel, gbc);
        gbc.gridx = 5;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel checkoutTotalPriceValueLabel = new JLabel(valueOf(totalCost), JLabel.CENTER);
        checkoutPanel.add(checkoutTotalPriceValueLabel, gbc);
        gbc.gridwidth = 2;
        gbc.gridx = 6;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        checkoutPanel.add(submitOrder, gbc);

        gbc.gridy = 19;
        gbc.gridx = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel checkoutSuccessLabel = new JLabel("", JLabel.CENTER);
        checkoutPanel.add(checkoutSuccessLabel, gbc);

        gbc.gridy = 20;
        gbc.gridx = 0;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        checkoutPanel.add(Box.createGlue(), gbc);
        gbc.gridx = 6;
        checkoutPanel.add(Box.createGlue(), gbc);

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
                case "Confirm Order" -> checkout();
                default -> System.out.println("Error");
            }
        }
    }

    public void checkout(){
        JOptionPane.showMessageDialog(null, "Order Confirmed!" );
        orderNum+=1;
        System.out.println(orderNum);
        Order order = new Order(orderNum++, new Date(System.currentTimeMillis()), basket.getTotal(),new Date(System.currentTimeMillis()+5000000), Order.Status.PROCESSED );
        LookInnaBookFrame.userBasket = new Basket();
        this.dispose();
    }

}
