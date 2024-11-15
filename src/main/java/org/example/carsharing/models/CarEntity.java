package org.example.carsharing.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="car")
public class CarEntity extends BaseEntity{
    private String name;
    private int year;
    private String number;
    private String carClass;
    private double hourPrice;
    private String status;
    private String adress;

    protected CarEntity() {}

    protected CarEntity(double hourPrice, String carClass, String number, int year, String name, String status, String adress) {
        this.hourPrice = hourPrice;
        this.carClass = carClass;
        this.number = number;
        this.year = year;
        this.name = name;
        this.status=status;
        this.adress = adress;
    }
    @Column(name = "adress")
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    @Column(name = "car_class")
    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }
    @Column(name = "hour_price")
    public double getHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(double hourPrice) {
        this.hourPrice = hourPrice;
    }
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
