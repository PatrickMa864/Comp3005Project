public class Publisher {
    private String name;
    private long phone;
    private String email;
    private long bankAccount;

    private int AddressID;

    public int getAddressID() {
        return AddressID;
    }

    public Publisher(String name, long phone, String email, long bankAccount, int AddressID) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.bankAccount = bankAccount;
        this.AddressID = AddressID;
    }

    public String getName() {
        return name;
    }

    public void setName(String lastName) {
        this.name = lastName;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(long bankAccount) {
        this.bankAccount = bankAccount;
    }
}
