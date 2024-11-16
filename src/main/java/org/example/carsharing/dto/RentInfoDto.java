package org.example.carsharing.dto;

public class RentInfoDto {
    private String carName;
    private String startDate;
    private String endDate;
    private double totalPrice;

    public RentInfoDto() {
    }

    public RentInfoDto(String carName, String startDate, String endDate, double totalPrice) {
        this.carName = carName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
