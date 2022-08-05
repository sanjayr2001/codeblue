package com.example.codeblue;

public class UserProfileCreationHelper {

    String Name, Age, PhoneNumber, Gender;

    public UserProfileCreationHelper(String Name, String Age, String PhoneNumber, String Gender){
        this.Name = Name;
        this.Age = Age;
        this.PhoneNumber = PhoneNumber;
        this.Gender = Gender;
    }
    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) { Gender = gender; }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
