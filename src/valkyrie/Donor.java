package valkyrie;

import java.io.InputStream;
import java.util.Date;

public class Donor {
    private int id, age;
    private String firstName, lastName, fathersName, gender, weight, bloodType, idType, idNo, mobileNo, medical, landlineNo, eMail, address;
    private Date lastDonation;
    private InputStream picture;
    
    Donor() {
    }
    
    Donor(int age, String firstName, String lastName, String fathersName, String gender, String weight, String bloodType,
            String idType, String idNo, String mobileNo, String medical, String landlineNo, String eMail,
            String address, Date lastDonation, InputStream picture) {
        setAge(age);
        setFirstName(firstName);
        setLastName(lastName);
        setFathersName(fathersName);
        setGender(gender);
        setWeight(weight);
        setBloodType(bloodType);
        setIdentificationType(idType);
        setIDNo(idNo);
        setMobileNo(mobileNo);
        setMedical(medical);
        setLandlineNo(landlineNo);
        setEMail(eMail);
        setAddress(address);
        setLastDonation(lastDonation);
        setPicture(picture);
        
    }
    
    Donor(int id, String firstName, String lastName, String gender, int age, String weight, String bloodType,
          String mobileNo, String landlineNo, String eMail, String address) {
        setID(id);
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        setAge(age);
        setWeight(weight);
        setBloodType(bloodType);
        setMobileNo(mobileNo);
        setLandlineNo(landlineNo);
        setEMail(eMail);
        setAddress(address);
        
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }   

    public void setWeight(String weight) {
        this.weight = weight;
    }
    
    public void setIdentificationType(String idType) {
        this.idType = idType;
    }
    
    public void setIDNo(String idNo) {
        this.idNo = idNo;
    }
    
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    
    public void setMedical(String medical) {
        this.medical = medical;
    }
    
    public void setLandlineNo(String landlineNo) {
        this.landlineNo = landlineNo;
    }
    
    public void setEMail(String eMail) {
        this.eMail = eMail;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setLastDonation(Date lastDonation) {
        if(lastDonation != null)
            this.lastDonation = lastDonation;
        else
            this.lastDonation = null;
    }
    
    public void setLastDonation(Object lastDonation) {
        if(lastDonation != null)
            this.lastDonation = java.sql.Date.valueOf(lastDonation.toString());
        else
            this.lastDonation = null;
    }
    
    public  void setPicture(InputStream picture) {
        this.picture = picture;
    }
    
    public int getID() {
        return id;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getFathersName() {
        return fathersName;
    }
    
    public String getGender() {
        return  gender;
    }
    
    public  String getWeight() {
        return weight;
    }
    
    public String getBloodType() {
        return bloodType;
    }
    
    public String getIdentificationType() {
        return idType;
    }
    
    public String getIDNo() {
        return idNo;
    }
    
    public String getMobileNo() {
        return mobileNo;
    }
    
    public String getMedical() {
        return medical;
    }
    
    public String getLandlineNo() {
        return landlineNo;
    }
    
    public String getEMail() {
        return eMail;
    }
    
    public String getAddress() {
        return address;
    }
    
    public Date getLastDonation() {
        return lastDonation;
    }
    
    public InputStream getPicture() {
        return picture;
    }
    
}
