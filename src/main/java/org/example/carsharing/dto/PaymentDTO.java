package org.example.carsharing.dto;

public class PaymentDTO {
    private long bookingId;
    private double totalPrice;
    private String paymentDate;
    private String paymentStatus;

    public PaymentDTO() {};

    public PaymentDTO(long bookingId, double totalPrice, String paymentDate, String paymentStatus) {
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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
