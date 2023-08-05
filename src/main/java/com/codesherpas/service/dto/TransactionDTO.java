package com.codesherpas.service.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TransactionDTO implements Serializable {

    private Long transactionId;

    private Long dispenserId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private double amount;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getDispenserId() {
        return dispenserId;
    }

    public void setDispenserId(Long dispenserId) {
        this.dispenserId = dispenserId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
