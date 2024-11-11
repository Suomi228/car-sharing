package org.example.carsharing.dto;


public class DamageReportDTO {
    private long carId;
    private String damageDescription;
    private String discoveryDate;
    private double repairPrice;

    public DamageReportDTO(long carId, String damageDescription, String discoveryDate, double repairPrice) {
        this.carId = carId;
        this.damageDescription = damageDescription;
        this.discoveryDate = discoveryDate;
        this.repairPrice = repairPrice;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getDamageDescription() {
        return damageDescription;
    }

    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }

    public String getDiscoveryDate() {
        return discoveryDate;
    }

    public void setDiscoveryDate(String discoveryDate) {
        this.discoveryDate = discoveryDate;
    }

    public double getRepairPrice() {
        return repairPrice;
    }

    public void setRepairPrice(double repairPrice) {
        this.repairPrice = repairPrice;
    }
}
