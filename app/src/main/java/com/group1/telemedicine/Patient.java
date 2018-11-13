package com.group1.telemedicine;

public class Patient {

    private int ptID;
    private String ptName;
    private String ptEmail; //May be wrong place for this
    private String ptPassword; //May be wrong place for this
    private int ptAge;
    private String ptAddress;
    private String ptGender;

    public int getPtID()
    {
        return ptID;
    }

    public void setPtID(int ptID)
    {
        this.ptID = ptID;
    }

    public String getPtName()
    {
        return ptName;
    }

    public void setPtName(String ptName)
    {
        this.ptName = ptName;
    }

    public String getPtEmail()
    {
        return ptEmail;
    }

    public void setPtEmail(String ptEmail)
    {
        this.ptEmail = ptEmail;
    }

    public String getPtPassword()
    {
        return ptPassword;
    }

    public void setPtPassword(String ptPassword)
    {
        this.ptPassword = ptPassword;
    }

    public int getPtAge()
    {
        return ptAge;
    }

    public void setPtAge(int ptAge)
    {
        this.ptAge = ptAge;
    }

    public String getPtAddress()
    {
        return ptAddress;
    }

    public void setPtAddress(String ptAddress)
    {
        this.ptAddress = ptAddress;
    }

    public String getPtGender()
    {
        return ptGender;
    }

    public void setPtGender(String gender)
    {
        this.ptGender = ptGender;
    }

    public void displayPatient()
    {
        System.out.println("Patient ID: " + ptID);
        System.out.println("Patient Name: " + ptName);
        System.out.println("Patient Age: " + ptAge);
        System.out.println("Patient Email: " + ptEmail);
    }
}
