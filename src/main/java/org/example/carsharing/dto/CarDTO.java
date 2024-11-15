package org.example.carsharing.dto;

import org.example.carsharing.constants.CarClass;
import org.example.carsharing.constants.CarStatus;

public class CarDTO {
    private long id;
    private String name;
    private int year;
    private String number;
    private CarClass carClass;
    private double hourPrice;
    private CarStatus status;
    private String adress;
    public CarDTO() {};

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public CarDTO(long id, String name, int year, String number, CarClass carClass, double hourPrice, CarStatus status, String adress) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.number = number;
        this.carClass = carClass;
        this.hourPrice = hourPrice;
        this.status = status;
        this.adress = adress;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }

    public double getHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(double hourPrice) {
        this.hourPrice = hourPrice;
    }
}
