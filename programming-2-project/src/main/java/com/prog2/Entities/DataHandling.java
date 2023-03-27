/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog2.Entities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Wei Jin
 */
public class DataHandling {
    
    private static ArrayList<Department> departmentList = new ArrayList<>();
    
    public DataHandling(){
        
        departmentList = new ArrayList<>();
    }
    
    public static ArrayList<Department> getDepartmentList() {
        return departmentList;
    }
    
    public static int getDepartmentIdByName(String name){
        int departmentId = -1;
        
        if(departmentList != null){
            for (Department department : departmentList) {
                if (name.equals(department.getDepName())) {
                    departmentId = department.getDepNo();
                }
            }
        }
        return departmentId;
    }
    public static int getNextID(){
        int maxId = 0;
        
        if(departmentList != null){
            for (Department department : departmentList) {
                for (Teacher listTeacher : department.getListTeachers()) {
                    if(listTeacher.getId() > maxId)
                        maxId = listTeacher.getId();
                }
                for (Staff listStaff : department.getListStaff()) {
                    if(listStaff.getId() > maxId)
                        maxId = listStaff.getId();
                }
            }
        }
        return maxId + 1;
    }
    
     public static boolean Load() {

        readDataFromTeacherFile();
        readDataFromStaffFile();

        return true;
    }
     
    public static void readDataFromTeacherFile() {
        
        String projectPath = System.getProperty("user.dir");
        //Read data from teachers data text
        String path = projectPath + "\\src\\main\\java\\resources\\teacher_data.txt";
        File file = new File(path);

        try (Scanner input = new Scanner(file)) {		// try with resource

            while (input.hasNext()) {
                String row = input.nextLine();			// read the entire row
                String[] strs = row.split(",");			// extract each part
                for (int i = 0; i < strs.length; i++) {
                    strs[i]= strs[i].trim();   
                }
                
                int     dept_number = Integer.parseInt(strs[0]);
                String  dept_name = strs[1];
                Teacher teacher = null;
                    
                //Check  DepNo already existed 
                boolean flag = false;
                for (Department dept : departmentList) {
                    if (dept.getDepNo() == dept_number) {
                        flag = true;
                        break;
                    }
                }
                
                //save data directly if departmentList is empty or DepNo does not exist
                if (departmentList.isEmpty() || flag == false) {
                    
                    if(Integer.parseInt(strs[8]) == 1 ){//Full-time
                         teacher = new FulltimeTeacher(
                                 Integer.parseInt(strs[2]),
                                strs[3],
                                strs[4],
                                strs[5],
                                strs[6].charAt(0),
                                LocalDate.parse(strs[7]),
                                strs[9],
                                strs[10],
                                strs[11]
                                );
                    }else{
                        //Part time
                         teacher = new ParttimeTeacher(
                                 Integer.parseInt(strs[2]),
                                strs[3],
                                strs[4],
                                strs[5],
                                strs[6].charAt(0),
                                LocalDate.parse(strs[7]),
                                
                                strs[9],
                                 strs[10],
                                Double.parseDouble(strs[11]),
                                strs[12]
                                );
                    }
                        //Save teacher
                        Department department = new Department(dept_number,dept_name);
                        department.getListTeachers().add(teacher);
                        departmentList.add(department);
                } else {
                    //if deptNo exists, data should be saved to department with the same DepNo
                    for (Department dept : departmentList) {
                        if (dept.getDepNo() == dept_number) {
                            
                            if(Integer.parseInt(strs[8]) == 1 ){//Full-time
                                teacher = new FulltimeTeacher(
                                Integer.parseInt(strs[2]),
                                strs[3],
                                strs[4],
                                strs[5],
                                strs[6].charAt(0),
                                LocalDate.parse(strs[7]),
                                        
                                strs[9],
                                strs[10],
                                strs[11]
                                );
                        }else{
                                //Part time
                                teacher = new ParttimeTeacher(
                                Integer.parseInt(strs[2]),
                                strs[3],
                                strs[4],
                                strs[5],
                                strs[6].charAt(0),
                                LocalDate.parse(strs[7]),
                                
                                strs[9],
                                 strs[10],
                                Double.parseDouble(strs[11]),
                                strs[12]
                                );
                        }

                                //Save teacher
                                dept.getListTeachers().add(teacher);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(String.format("File %s does not exist", path));
        }
    }
    
    public static void readDataFromStaffFile() {
        
        String projectPath = System.getProperty("user.dir");
        //Read data from staff data text
        String path = projectPath + "\\src\\main\\java\\resources\\staff_data.txt";

        File file1 = new File(path);

        try (Scanner input1 = new Scanner(file1)) {		// try with resource

            while (input1.hasNext()) {
                String row = input1.nextLine();			// read the entire row
                String[] strs = row.split(",");			// extract each part
                for (int i = 0; i < strs.length; i++) {
                    strs[i]= strs[i].trim();   
                }
                
                int dept_number = Integer.parseInt(strs[0]);
                String dept_name = strs[1];
                Staff staff = null;

                //Check  DepNo already existed 
                boolean flag = false;
                for (Department dept : departmentList) {
                    if (dept.getDepNo() == dept_number) {
                        flag = true;
                        break;
                    }
                }
                
                //save data directly if departmentList is empty or DepNo does not exist
                if (departmentList.isEmpty() || flag == false) {
                        //staff
                         staff = new Staff(
                                 Integer.parseInt(strs[2]),
                                 strs[3],
                                strs[4],
                                strs[5],
                                strs[6].charAt(0),
                                LocalDate.parse(strs[7]),
                                strs[8],
                                Double.parseDouble(strs[9]));

                        //Save teacher
                        Department department = new Department(dept_number,dept_name);
                        department.getListStaff().add(staff);
                        departmentList.add(department);
                } else {
                    //if deptNo exists, data should be saved to department with the same DepNo
                    for (Department dept : departmentList) {
                        if (dept.getDepNo() == dept_number) {
                                //staff
                                staff = new Staff(
                                Integer.parseInt(strs[2]),
                                 strs[3],
                                strs[4],
                                strs[5],
                                strs[6].charAt(0),
                                LocalDate.parse(strs[7]),
                                strs[8],
                                Double.parseDouble(strs[9]));

                                //Save teacher
                                dept.getListStaff().add(staff);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(String.format("File %s does not exist", path));
        }
    }

    public static void deleteEmpById(int id){
        
        if (departmentList.isEmpty()) {
            System.out.println("There is no more employee in the databse!");
            
        }else
        {
            int flag = 0;
            Teacher temp = null;
            Staff temp1 = null;
            for (Department dept : departmentList) {
                for (Teacher listTeacher : dept.getListTeachers()) {
                    if (listTeacher.getId() == id) {
                        temp = listTeacher;
                        
                        flag = 1;
                    }
                }
                
                for (Staff listStaff : dept.getListStaff()) {
                    if (listStaff.getId() == id) {
                        temp1 = listStaff;
                        
                        flag = 2;
                    }
                }
                
                if (flag == 1) {
                    dept.getListTeachers().remove(temp);
                    JOptionPane.showMessageDialog(null, "Remove employee successfully!");
                    
                    break;
                } else if (flag == 2) {
                    dept.getListStaff().remove(temp1);
                    JOptionPane.showMessageDialog(null, "Remove employee successfully!");
                    
                    break;
                }
            }
            
            
        }
    }
            
    public static boolean Save(int deptNo,String deptName,Person person) {
        
        if(person == null){
            return false;
        }
        
        if (departmentList.isEmpty()) {

            Department department = null;
            if (person.getCategory() == 1 || person.getCategory() == 2) {//Full time teacher
                Teacher teacher = (Teacher)person;
                 department = new Department(deptNo, deptName);
                department.getListTeachers().add(teacher);
            }else{
                //Staff
                Staff staff = (Staff)person;
                 department = new Department(deptNo, deptName);
                department.getListStaff().add(staff);
            }
            
            //Save
            departmentList.add(department);
        } else {
            //if deptNo exists, data should be saved to department with the same DepNo
            for (Department dept : departmentList) {
                if (dept.getDepNo() == deptNo) {
                    if (person.getCategory() == 1 || person.getCategory() == 2) {//Full time teacher
                        Teacher teacher = (Teacher)person;
                        dept.getListTeachers().add(teacher);
                    }else{
                        //Staff
                        Staff staff = (Staff)person;
                        dept.getListStaff().add(staff);
                    }                   
                } else {
                    System.out.println("???throw exception");/////////////////////////////////////Exception
                }
            }
        }

        return true;
    }
}
