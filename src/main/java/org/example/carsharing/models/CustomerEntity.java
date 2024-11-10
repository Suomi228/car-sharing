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
    private String driverLicense;

    protected CustomerEntity() {}

    protected CustomerEntity(String firstName, String lastName, String number, String driverLicense) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.driverLicense = driverLicense;
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
    @Column(name = "driver_license")
    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }
}
