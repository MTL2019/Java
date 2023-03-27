package com.jw.queue;

import java.util.Scanner;

/**
 * 用数组实现队列：先进先出
 * 问题： 该数组不能复用
 * 原因：取出元素后，头指针往后移动，不能复位
 */
public class ArrayQueueTest {
    public static void main(String[] args) {

        System.out.println("========测试用数组模拟队列==========");
        Scanner scanner = new Scanner(System.in);
        ArrayQueue queue = new ArrayQueue(3);
        boolean flag = true;
        char key;
        while (flag){
            System.out.println("请选择：a: 添加元素到队列");
            System.out.println("请选择：g: 从队列取出元素");
            System.out.println("请选择：h: 查看队列头部元素");
            System.out.println("请选择：s: 查看队列所有元素");
            System.out.println("请选择：e: 退出");

            key = scanner.next().charAt(0);
            switch (key){
                case 'a':
                    System.out.println("请输入要添加的元素值：");
                    int value = scanner.nextInt();
                    try {
                        queue.addNum(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int num = queue.getNum();
                        System.out.println(num);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 'h':
                    queue.showHead();
                    break;
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    System.out.println("退出");
                    scanner.close();
                    flag = false;
                    break;
                default :
                    break;
            }
        }
        System.out.println("退出测试！");
    }
}

class ArrayQueue {

    private int maxSize;//队列最大容量
    private int head;
    private int rear;
    private int[] arrQueue;

    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arrQueue = new int[maxSize];
        head = -1;//头指针指向队列头的上一个元素
        rear = -1;//尾指针指向队列末尾元素
    }

    public boolean isFull(){
        return rear == maxSize - 1;
    }

    public boolean isEmpty(){
        return head == rear;
    }

    public int getNum(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        head++;
        return arrQueue[head];
    }

    public void addNum(int n){
        if (isFull()){
            throw new RuntimeException("队列满了，不能添加数据");
        }
        rear++;
        arrQueue[rear] = n;

    }

    public void showHead(){
        if (isEmpty()){
            System.out.println("队列为空，没有数据");
            return ;
        }
        System.out.printf("arrQueue[%d]: %d \n" , head+1, arrQueue[head+1]);
    }

    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空，没有数据");
            return ;
        }
        for (int i = head+1; i <= rear; i++) {
            System.out.printf("arrQueue[%d] = %d \n" , i, arrQueue[i]);
        }
    }
}
