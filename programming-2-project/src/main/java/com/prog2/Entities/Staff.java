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
public class Staff extends Person implements PayRoll{
    
    private String duty  ;
    private double workload ;//weekly working hours; must be <=40

    public  Staff(int id,String sin,String lastName,String firstName,char gender, LocalDate dateOfBirth,
            String duty,double workload){
        
        super(id,sin, lastName,firstName,gender, dateOfBirth,3);
        
        setDuty(duty);
        setWorkload(workload);
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public void setWorkload(double workload) {
        this.workload = workload;
    }

    public String getDuty() {
        return duty;
    }

    public double getWorkload() {
        return workload;
    }

    @Override
    public String toString() {
        return "Staff{" + "id=" + this.sin + ", lastName=" + this.lastName + ", firstName=" + this.firstName +
                ", sex=" + this.gender + "duty=" + duty + ", workload=" + workload + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.sin);
        hash = 71 * hash + Objects.hashCode(this.lastName);
        hash = 71 * hash + Objects.hashCode(this.firstName);
        hash = 79 * hash + Objects.hashCode(this.duty);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.workload) ^ (Double.doubleToLongBits(this.workload) >>> 32));
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
        final Staff other = (Staff) obj;
        
        return Objects.equals(this.sin, other.getSin());
    }
      
    @Override
    public double computePayRoll() { 
       
        return workload * 32*2*0.75;
    }

}
