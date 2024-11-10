package org.example.carsharing.models;

import jakarta.persistence.*;

@Entity
@Table(name="damage_report")
public class DamageReportEntity extends BaseEntity{
    private CarEntity car;
    private String damageDescription;
    private String discoveryDate;
    private double repairPrice;

    protected DamageReportEntity() {}

    public DamageReportEntity(CarEntity car, String damageDescription, String discoveryDate, double repairPrice) {
        this.car = car;
        this.damageDescription = damageDescription;
        this.discoveryDate = discoveryDate;
        this.repairPrice = repairPrice;
    }
    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id", nullable = false)
    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }
    @Column(name = "damage_discription")
    public String getDamageDescription() {
        return damageDescription;
    }

    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }
    @Column(name = "discovery_date")
    public String getDiscoveryDate() {
        return discoveryDate;
    }

    public void setDiscoveryDate(String discoveryDate) {
        this.discoveryDate = discoveryDate;
    }
    @Column(name = "repair_price")
    public double getRepairPrice() {
        return repairPrice;
    }

    public void setRepairPrice(double repairPrice) {
        this.repairPrice = repairPrice;
    }
}
