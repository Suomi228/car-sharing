package org.example.carsharing.dto;

public class UnfinishedBookingDTO{
    private long carId;
    private long bookingId;
    private String startDate;
    private String carName;
    private String carAddress;

    public UnfinishedBookingDTO() {
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public UnfinishedBookingDTO(long carId, long bookingId, String startDate, String carName, String carAddress) {
        this.carId = carId;
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.carName = carName;
        this.carAddress = carAddress;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarAddress() {
        return carAddress;
    }

    public void setCarAddress(String carAddress) {
        this.carAddress = carAddress;
    }
}
