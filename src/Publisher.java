public class Publisher {
    private String name;
    private String phone;
    private String email;
    private String bankAccount;

    private Address address;

    public Publisher(String name, String phone, String email, String bankAccount, Address address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.bankAccount = bankAccount;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String lastName) {
        this.name = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
