package org.example.carsharing.models;

import jakarta.persistence.*;

@Entity
@Table(name="payment")
public class PaymentEntity extends BaseEntity{
    private BookingEntity booking;
    private double totalPrice;
    private String paymentDate;
    private String paymentStatus;

    protected PaymentEntity() {}

    public PaymentEntity(BookingEntity booking, double totalPrice, String paymentDate, String paymentStatus) {
        this.booking = booking;
        this.totalPrice = totalPrice;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }
    @ManyToOne
    @JoinColumn(name = "booking_id", referencedColumnName = "id", nullable = false)
    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
    }
    @Column(name = "total_price")
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    @Column(name = "payment_date")
    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
    @Column(name = "payment_status")
    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
