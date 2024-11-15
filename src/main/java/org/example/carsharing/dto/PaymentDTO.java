package org.example.carsharing.dto;

import org.example.carsharing.constants.PaymentStatus;

public class PaymentDTO {
    private long bookingId;
    private double totalPrice;
    private String paymentDate;
    private PaymentStatus paymentStatus;

    public PaymentDTO() {};

    public PaymentDTO(long bookingId, double totalPrice, String paymentDate, PaymentStatus paymentStatus) {
        this.bookingId = bookingId;
        this.totalPrice = totalPrice;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
