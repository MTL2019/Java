package com.jw.hash;

import java.util.Scanner;

public class HashTable {
    public static void main(String[] args) {

        HashTab hashTab = new HashTab(7);

        Scanner scanner = new Scanner(System.in);
        String key = "";

        while(true){
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("del: 删除雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            int id ;
            switch (key){
                case "add":
                    System.out.println("请输入id:");
                    id = scanner.nextInt();
                    System.out.println("请输入name:");
                    String name = scanner.next();
                    Emp emp = new Emp(id,name);
                    hashTab.add(emp);
                    break;
                case "find":
                    System.out.println("请输入要查找的雇员id:");
                    id = scanner.nextInt();
                    hashTab.findById(id);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "del":
                    System.out.println("请输入要删除的雇员id:");
                    id = scanner.nextInt();
                    hashTab.deleteById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

class HashTab{
    public EmpLinkedList[] empLinkedList;
    private int size;

    public HashTab(int size) {
        this.size = size;
        this.empLinkedList = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            this.empLinkedList[i] = new EmpLinkedList();
        }
    }

    public void findById(int id){
        int position = HashFun(id);
        Emp emp = empLinkedList[position].findById(id);
        if (emp == null) {
            System.out.println("没有找到该雇员");
        }else {
            System.out.printf("在第 %d 条链表找到,该雇员id为: %d \n",position,id);
        }
    }
    public void add(Emp emp){
        int position = HashFun(emp.id);
        empLinkedList[position].add(emp);
    }

    public void deleteById(int id){
        int position = HashFun(id);
        empLinkedList[position].deleteById(id);
    }

    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedList[i].list(i);
        }
    }

    public int HashFun(int id){
        return id % size;
    }
}
class EmpLinkedList{
    private Emp head;

    public void add(Emp emp){
        if (head == null) {
            head = emp;
            return;
        }

        Emp temp = head;
        while (temp.next != null){
            temp = temp.next;
        }

        temp.next = emp;
    }

    public Emp findById(int id){
        if (head == null) {
            return null;
        }
        Emp temp = head;
        while (true){
            if (temp.id == id) {
                return temp;
            }else {
                temp = temp.next;
            }
            if (temp.next == null) {
                break;
            }
        }
        return null;
    }

    /**
     *
     */
    public void deleteById(int id){
        if (head == null) {
            System.out.println("链表为空");
            return ;
        }

        Emp temp = head;
        int flag = 0;
        if (head.id == id) {
            head = temp.next;//删除第一个元素
        }else{
            while (temp.next != null) {
                if (temp.next.id == id) {
                    temp.next = temp.next.next;//删除找到的节点,可删除重复的id
                    flag = 1;
                } else {
                    temp = temp.next;//没找到继续向后查找
                }
            }
            if (flag == 0) {
                System.out.println("没有对应雇员");
            }

        }
    }

    public void list(int no){
        if (head == null) {
            System.out.println("第 "+no+" 条链表为空");
            return;
        }

        System.out.print("第 "+no+" 条链表信息为：");
        Emp temp = head;
        while (true){
            if (temp == null) {
                break;
            }
            System.out.printf("==> id: %d  name: %s ",temp.id,temp.name);
            temp = temp.next;
        }
        System.out.println();
    }
}

class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
