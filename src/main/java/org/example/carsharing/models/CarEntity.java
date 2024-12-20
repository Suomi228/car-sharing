package org.example.carsharing.models;


import jakarta.persistence.*;
import org.example.carsharing.constants.CarClass;
import org.example.carsharing.constants.CarStatus;

import java.time.LocalDateTime;

@Entity
@Table(name="car")
public class CarEntity extends BaseEntity{
    private String name;
    private int year;
    private String number;
    private CarClass carClass;
    private double hourPrice;
    private CarStatus status;
    private String adress;
    private boolean isDeleted;

    @Column(name = "is_deleted")
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    protected CarEntity() {}

    protected CarEntity(double hourPrice, CarClass carClass, String number, int year, String name, CarStatus status, String adress, boolean isDeleted) {
        this.hourPrice = hourPrice;
        this.carClass = carClass;
        this.number = number;
        this.year = year;
        this.name = name;
        this.status=status;
        this.adress = adress;
        this.isDeleted = false;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "car_class")
    public CarClass getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }
    @Column(name = "hour_price")
    public double getHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(double hourPrice) {
        this.hourPrice = hourPrice;
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public void changeStatus(CarStatus carStatus) {
            switch (carStatus) {
                case USED -> {
                    this.status = carStatus.USED;
                }
                case FREE -> {
                    this.status = carStatus.FREE;
                }
                case BROKEN -> {
                    this.status = carStatus.BROKEN;
                }
                case IN_REPAIR -> {
                    this.status = carStatus.IN_REPAIR;
                }
                default -> {
                    System.out.println("");
                }
            }
        }
}
