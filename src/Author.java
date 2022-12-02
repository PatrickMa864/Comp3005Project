public class Author {
    private int author_id;
    private String firstName;
    private String lastName;
    private String email;

    public Author(int author_id, String firstName, String lastName, String email) {
        this.author_id = author_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
