package cs4322.project.telemedicineapp.model;

public class Doctor {
    private String username;
    private String fullname;
    private String email;
    private String facility;
    private String credentials;
    private String password;

    public Doctor(){

    }

    public Doctor(String username, String fullname, String email, String facility, String credentials, String password) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.facility = facility;
        this.credentials = credentials;
        this.password = password;
    }

    public Doctor(String fullname, String email, String facility, String credentials, String password) {
        //this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.facility = facility;
        this.credentials = credentials;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getFacility() {
        return facility;
    }

    public String getCredentials() {
        return credentials;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
