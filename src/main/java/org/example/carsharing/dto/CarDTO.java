package org.example.carsharing.dto;

public class CarDTO {
    private long id;
    private String name;
    private int year;
    private String number;
    private String carClass;
    private double hourPrice;
    private String status;
    public CarDTO() {};

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CarDTO(long id, String name, int year, String number, String carClass, double hourPrice, String status) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.number = number;
        this.carClass = carClass;
        this.hourPrice = hourPrice;
        this.status = status;
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

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public double getHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(double hourPrice) {
        this.hourPrice = hourPrice;
    }
}
