package com.jw.queue;

import java.util.Scanner;

/**
 * 用数组模拟环形队列：
 * 1.  front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
 * front 的初始值 = 0
 * 2.  rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
 * rear 的初始值 = 0
 * 3. 当队列满时，条件是  (rear  + 1) % maxSize == front 【满】
 * 4. 对队列为空的条件， rear == front 空
 * 5. 当我们这样分析， 队列中有效的数据的个数   (rear + maxSize - front) % maxSize   // rear = 1 front = 0
 */
public class CircleArrayQueueTest {
    public static void main(String[] args) {

        System.out.println("==========测试用数组模拟环形队列============");

        Scanner scanner = new Scanner(System.in);
        CircleArrayQueue queue = new CircleArrayQueue(4);//实际容量是3，空一个位置
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
                    scanner.close();
                    flag = false;
                    break;
                default :
                    break;
            }
        }
        System.out.println("退出环形队列测试！");
    }
}

class CircleArrayQueue {

    private int maxSize;//队列最大容量
    private int head;
    private int rear;
    private int[] arrQueue;

    public CircleArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arrQueue = new int[maxSize];
        head = 0;//头指针指向队列头的第一个元素,初始值设为0 可以省略
        rear = 0;//尾指针指向队列末尾元素的下一个位置,初始值设为0 可以省略
    }

    public boolean isFull(){
        //理解：当head=0,rear= maxSize - 1;即判定为满，maxSize - 1位置为空，预留为约定
        //取模 可以理解为 归0 操作
        return (rear + 1 ) % maxSize == head;
    }

    public boolean isEmpty(){
        return head == rear;
    }

    public int size(){
        return (rear - head + maxSize) % maxSize;//返回元素个数
    }

    public int getNum(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        int temp = arrQueue[head];
        head = (head + 1)% maxSize;
        return temp;
    }

    public void addNum(int n){
        if (isFull()){
            throw new RuntimeException("队列满了，不能添加数据");
        }

        arrQueue[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public void showHead(){
        if (isEmpty()){
            System.out.println("队列为空，没有数据");
            return ;
        }
        System.out.printf("arrQueue[%d]: %d \n" , head, arrQueue[head]);
    }

    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空，没有数据");
            return ;
        }
        for (int i = head; i < head + size(); i++) {
            System.out.printf("arrQueue[%d] = %d \n" , (i+maxSize)%maxSize, arrQueue[(i+maxSize)%maxSize]);
        }
    }
}
