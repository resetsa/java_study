package com.resetsa.homeworks.model;

public class Customer {

    private Integer id;
    private String name;
    private String lastname;
    private String phone;
    private String mail;
    
    public Customer(Integer id, String name, String lastname, String phone, String mail) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.mail = mail;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", lastname=" + lastname + ", phone=" + phone + ", mail="
                + mail + "]";
    }

}
