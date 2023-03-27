package com.jw.linkedlist;

/**
 * 单向环形链表: 创建和遍历 需要一个当前指针curBoy辅助
 * josepfu问题：n个小孩围坐一圈，从第k个小孩开始报m个数，第m个小孩出圈，继续报数直到最后一个小孩
 * 求：出圈顺序和最后一个小孩的编号
 */
public class CircleSingleLinkedListTest {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();

        list.addBoy(25);
        list.showBoys();

        list.toGetoutBoys(1,2,25);
    }
}

class CircleSingleLinkedList{
    private Boy first = new Boy(-1);

    public void addBoy(int nums){
        if (nums < 1) {
            System.out.println("输入数字有误\n");
            return;
        }

        Boy curBoy = first;
        for (int i = 1; i <= nums; i++) {
            Boy newBoy = new Boy(i);
            if (i == 1) {
                first = newBoy;
                first.setNext(first);
                curBoy = newBoy;
            }else{
                curBoy.setNext(newBoy);
                newBoy.setNext(first);
                curBoy = newBoy;
            }
        }
    }

    public void showBoys(){

        if (first.getNext() == null) {
            System.out.println("链表为空，没有小孩\n");
        }

        Boy curBoy = first;
        while (true){
            System.out.printf("小孩编号为：%d \n",curBoy.getNo());

            if (curBoy.getNext() == first) {
                return;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * josepfu问题
     * @param startNo 从第startNo个小孩开始报数；
     * @param count ：每次报数，第count个小孩出圈；
     * @param nums： 原始圈的小孩个数
     */
    public void toGetoutBoys(int startNo,int count,int nums){
        //数据校验
        if (startNo < 1 || startNo > nums || nums < 1 ) {
            System.out.println("输入参数有误");
            return;
        }
        if (first == null) {
            System.out.println("链表为空，没有小孩");
        }

        Boy tempBoy = first;
        while (tempBoy.getNext() != first){//设置辅助指针tempBoy指向first前一个
            tempBoy = tempBoy.getNext();
        }
        //从第startNo个小孩开始
        for (int i = 1; i < startNo; i++) {//注意从1开始数count-1个数，因为会自动跳到下一个位置
            first = first.getNext();
            tempBoy = tempBoy.getNext();
        }

        while(true){
            if (tempBoy == first) {
                System.out.printf("最后一个小孩是：%d \n",first.getNo());
                break;
            }

            for (int i = 1; i < count; i++) {//注意从1开始数count-1个数
                first = first.getNext();//退出循环时，first就是要出圈的小孩
                tempBoy = tempBoy.getNext();
            }
            System.out.printf("本次出圈的小孩是：%d \n",first.getNo());
            first = first.getNext();
            tempBoy.setNext(first);
        }
    }
}

//节点
class Boy{
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
