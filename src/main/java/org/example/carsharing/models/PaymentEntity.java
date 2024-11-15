package org.example.carsharing.models;

import jakarta.persistence.*;
import org.example.carsharing.constants.PaymentStatus;

@Entity
@Table(name="payment")
public class PaymentEntity extends BaseEntity{
    private BookingEntity booking;
    private double totalPrice;
    private String paymentDate;
    private PaymentStatus paymentStatus;

    public PaymentEntity() {}

    public PaymentEntity(BookingEntity booking, double totalPrice, String paymentDate, PaymentStatus paymentStatus) {
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
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
