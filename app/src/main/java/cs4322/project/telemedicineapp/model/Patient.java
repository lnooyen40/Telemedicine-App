package cs4322.project.telemedicineapp.model;


import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Patient {
    private String fullname;
    private String username;
    private String email;
    private String phoneNumber;
    private String birthDate;
    private String gender;
    private String password;

    public Patient(){

    }

    public Patient(String fullname, String username, String email, String phoneNumber, String birthDate, String gender, String password) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.gender = gender;
        this.password = password;
    }

    public Patient(String fullname,String email, String phoneNumber, String birthDate, String gender, String password) {
        this.fullname = fullname;
        //this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.gender = gender;
        this.password = password;
    }

    //Accessors
    public String getfullname() {
        return fullname;
    }

    public String getusername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    //Mutators
    public void setfullname(String fullname) {
        this.fullname = fullname;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        String value = String.format("Email: %s; First name: %s\n", email, fullname);
        return value;
    }
}
