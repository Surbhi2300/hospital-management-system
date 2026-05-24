package com.example.hospitalmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private Long id;

    private String number;
    private String wardName;

    public Patient(){}

    public Long getId(){
        return id;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String number){
        this.number=number;
    }

    public String getWardName(){
        return wardName;
    }

    public void setWardName(String wardName){
        this.wardName=wardName;
    }
}