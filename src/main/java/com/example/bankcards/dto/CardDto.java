package com.example.bankcards.dto;

import com.example.bankcards.entity.Status;

import java.time.LocalDate;

public class CardDto {

    private String number;

    private String ownerName;

    private LocalDate expiryDate;

    private int cvv;

    private Status status;

    private int balance;

    public CardDto(String number, String ownerName, LocalDate expiryDate, int cvv, Status status, int balance) {
        this.number = number;
        this.ownerName = ownerName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.status = status;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
