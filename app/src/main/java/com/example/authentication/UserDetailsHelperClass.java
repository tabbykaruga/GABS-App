package com.example.authentication;

public class UserDetailsHelperClass {

    String location,phone,group,name;

    public UserDetailsHelperClass() {

    }

    public UserDetailsHelperClass(String name,String location, String phone, String group) {
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
