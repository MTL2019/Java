/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog2.Entities;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Wei Jin
 */
public class Teacher extends Person implements PayRoll {

    private String specialty;
    private String degree; 
    private String dean;//Yes : is Dean; No : not Dean

    public Teacher(int id,String sin, String lastName, String fistName, char gender, LocalDate DOB,int category,
            String specialty, String degree,String dean) {

        super(id,sin, lastName, fistName, gender, DOB,category);

        setSpecialty(specialty);
        setDegree(degree);
        setDean( dean);
    }

    public void setDean(String dean) {
        this.dean = dean;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    /**
     *
     * @param degree
     */
    public void setDegree(String degree) {
        
        this.degree = degree.toUpperCase();
    }

    public String getDegree() {
        return degree;
    }

    public String getDean(){
        return dean;
    }
    
    @Override
    public String toString() {
        return "Teacher{" + "id=" + this.sin + ", lastName=" + this.lastName + ", firstName=" + this.firstName
                + ", gender=" + this.gender + ", specialty=" + specialty + ", degree=" + degree +  '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.sin);
        hash = 71 * hash + Objects.hashCode(this.lastName);
        hash = 71 * hash + Objects.hashCode(this.firstName);
        hash = 71 * hash + Objects.hashCode(this.specialty);
        hash = 71 * hash + Objects.hashCode(this.degree);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Teacher other = (Teacher) obj;

        return Objects.equals(this.sin, other.getSin());
    }
    
    public int getDegreeRate() {
        return switch (this.getDegree()) {
            case "PHD" ->
                112;
            case "MASTER" ->
                82;
            case "BACHELOR" ->
                82;
            default ->
                -1;//Error --
        };
    }
    
    @Override
    public double computePayRoll() { 
       
//        if(isFullTime()){
//            return (32 * this.getDegreeRate() * 2) * 0.85;
//        }else{
//            return (this.getHours() * this.getDegreeRate() * 2) * 0.76;
//        }   
    return 0.0;
    }
}
