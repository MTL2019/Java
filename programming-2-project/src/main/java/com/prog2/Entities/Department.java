/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog2.Entities;

import java.util.ArrayList;

/**
 *
 * @author Wei Jin
 */
public class Department {
    
    private int deptNo;
    private String deptName;
    private ArrayList<Teacher> listTeachers;
    private ArrayList<Staff> listStaff;
    
    public Department(int depNo,String depName){
        
        setDepNo(depNo);
        setDepName(depName);
        listTeachers = new  ArrayList<>();
        listStaff = new  ArrayList<>();
    }

    public void setDepNo(int depNo) {
        this.deptNo = depNo;
    }

    public int getDepNo() {
        return deptNo;
    }

    public void setDepName(String depName) {
        this.deptName = depName;
    }
    
    public ArrayList<Teacher> getListTeachers() {
        return listTeachers;
    }

    public ArrayList<Staff> getListStaff() {
        return listStaff;
    }

    public String getDepName() {
        return deptName;
    }
    
}
