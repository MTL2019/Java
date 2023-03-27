/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog2.Entities;

import java.time.LocalDate;

/**
 *
 * @author Wei Jin
 */
public abstract class Person {
    
    protected int id;
    protected String sin;
    protected String lastName;
    protected String firstName;
    protected char gender; //f: female; m : male
    private LocalDate DOB;
    private int category; // 1: Futlltime teacher 2: Parttime Teacher 3: Staff

    public Person(int id,String sin, String lastName,String firstName,char gender, LocalDate dateOfBirth,int category){
        setId(id);
        setSin(sin);
        setLastName(lastName);
        setFirstName(firstName);
        setGender(gender);
        setDOB(dateOfBirth);
        setCategory(category);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
    
    public String getSin() {
        return sin;
    }
    
    public void setSin(String sin) {
        this.sin = sin;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public char getGender() {
        return gender;
    }
    
    public String getGenderString() {
        if(gender == 'f'){
            return "Female";
        }else
            return "Male";
    }

    public LocalDate getDOB() {
        return DOB;
    }
    
    
}
