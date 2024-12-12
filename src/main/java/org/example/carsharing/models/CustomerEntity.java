package org.example.carsharing.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="customer")
public class CustomerEntity extends BaseEntity{
    private String firstName;
    private String lastName;
    private String number;
    private String password;
    private boolean isAdmin;

    public CustomerEntity() {}

    public CustomerEntity(String firstName, String lastName, String number, String password, boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "is_admin")
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

}
