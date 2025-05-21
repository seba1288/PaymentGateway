package pl.swrobel.ecommerce.sales;

public class AcceptOfferComand {
    String fname;
    String lname;
    String email;

    public String getFname() {
        return fname;
    }

    public AcceptOfferComand setFname(String fname) {
        this.fname = fname;
        return this;
    }

    public String getLname() {
        return lname;
    }

    public AcceptOfferComand setLname(String lname) {
        this.lname = lname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AcceptOfferComand setEmail(String email) {
        this.email = email;
        return this;
    }
}
