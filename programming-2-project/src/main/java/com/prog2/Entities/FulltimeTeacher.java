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
public class FulltimeTeacher extends Teacher{
    
    public FulltimeTeacher(int id,String sin, String lastName, String fistName, char gender, LocalDate DOB, 
            String specialty, String degree,String dean) {
        super(id,sin, lastName, fistName, gender, DOB, 1,specialty, degree, dean);
    }
}
