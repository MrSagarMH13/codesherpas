package com.codesherpas.entity;

import com.codesherpas.enums.DispenserStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Dispenser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dispenserId;

    private double flowVolume;

    private DispenserStatus dispenserStatus;

    private double totalRevenue;

    public Long getDispenserId() {
        return dispenserId;
    }

    public void setDispenserId(Long dispenserId) {
        this.dispenserId = dispenserId;
    }

    public double getFlowVolume() {
        return flowVolume;
    }

    public void setFlowVolume(double flowVolume) {
        this.flowVolume = flowVolume;
    }

    public DispenserStatus getDispenserStatus() {
        return dispenserStatus;
    }

    public void setDispenserStatus(DispenserStatus dispenserStatus) {
        this.dispenserStatus = dispenserStatus;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
