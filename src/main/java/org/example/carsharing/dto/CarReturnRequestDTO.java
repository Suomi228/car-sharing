package org.example.carsharing.dto;

public class CarReturnRequestDTO {
    private long carId;
    private long bookingId;
    private String carAddress;

    public CarReturnRequestDTO() {
    }

    public CarReturnRequestDTO(long carId, long bookingId, String carAddress) {
        this.carId = carId;
        this.bookingId = bookingId;
        this.carAddress = carAddress;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public String getCarAddress() {
        return carAddress;
    }

    public void setCarAddress(String carAddress) {
        this.carAddress = carAddress;
    }
}
