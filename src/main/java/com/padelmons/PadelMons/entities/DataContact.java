package com.padelmons.PadelMons.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class DataContact {

    private String email;
    private String phone;

    public DataContact() {}
    public DataContact(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
