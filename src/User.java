public class User {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private int addressID;

    public User(String userName, String password, String firstName, String lastName, String email, int addressID) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressID = addressID;
    }
    public User(){
        this.userName = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.addressID = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAddress() {
        return addressID;
    }

    public void setAddress(int address) {
        this.addressID = address;
    }

    public String getPassword() {
        return password;
    }
}
