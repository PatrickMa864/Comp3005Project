public class Author {
    private String firstName;
    private String lastName;

    public Author(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author(String fullName) {
        String[] splited = fullName.split(" ");
        this.firstName = splited[0];
        this.lastName = splited[1];
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

    public String getFullName(){
        return firstName+" "+lastName;
    }

}
