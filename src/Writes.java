public class Writes {


    private String first_name; //represent the author _first_name
    private String last_name; //represent the author_lastName
    private long ISBN;  //represents the ISBN for the BOOK


    public Writes(String first_name, String last_name, long ISBN) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.ISBN = ISBN;
    }


    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public long getISBN() {
        return ISBN;
    }
}
