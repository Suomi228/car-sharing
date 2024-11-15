package org.example.carsharing.models;

import jakarta.persistence.*;
import org.example.carsharing.constants.BookingStatus;
import org.example.carsharing.constants.CarStatus;

@Entity
@Table(name="booking")
public class BookingEntity extends BaseEntity{

    private CarEntity car;
    private CustomerEntity customer;
    private String startDate;
    private String endDate;
    private BookingStatus status;

    public BookingEntity() {}

    public BookingEntity(CarEntity car, CustomerEntity customer, String startDate, String endDate, BookingStatus status) {
        this.car = car;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id", nullable = false)
    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
    @Column(name = "start_date")
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    @Column(name = "end_date")
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public void changeStatus(BookingStatus bookingStatus) {
        switch (bookingStatus) {
            case CONFIRMED -> {
                this.status = bookingStatus.CONFIRMED;
            }
            case DONE -> {
                this.status = bookingStatus.DONE;
            }
            default -> {
                System.out.println("");
            }
        }
}}
