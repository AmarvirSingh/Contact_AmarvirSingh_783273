package com.example.contact_amarvirsingh_783273;

public class ContactModelClass {
    int id ;
    String firstName, LastName , address, EmailId, PhoneNumber;

    public ContactModelClass(int id, String firstName, String lastName, String address, String emailId, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        LastName = lastName;
        this.address = address;
        EmailId = emailId;
        PhoneNumber = phoneNumber;
    }

    public ContactModelClass(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
