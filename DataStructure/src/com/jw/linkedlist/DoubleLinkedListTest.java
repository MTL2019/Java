package com.jw.linkedlist;

import java.util.Stack;

/**
 * 单链表的常见操作
 * 5道面试题
 *
 */
public class DoubleLinkedListTest {

    public static void main(String[] args) {
        HeroNode2 node1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 node4 = new HeroNode2(4,"林冲","豹子头");
        HeroNode2 node2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 node3 = new HeroNode2(3,"无用","智多星");

        DoubleLinkedList list = new DoubleLinkedList();
        //插入到链表末尾
//        list.add(node1);
//        list.add(node4);
//        list.add(node2);
//        list.add(node3);

        //按no顺序插入
        list.addByOrder(node4);
        list.addByOrder(node2);
        list.addByOrder(node3);
        list.addByOrder(node1);

//        HeroNode2 newHeroNode2 = new HeroNode2(4, "小林", "小包子");
//        list.update(newHeroNode2);
//
//        list.list();//修改前输出
//        list.delete(1);
//        list.delete(4);
//        list.delete(2);
//        list.delete(3);
        list.list();//修改前输出

        System.out.println("=========面试题============");
        System.out.println("1.链表有效节点个数为： " + getLength(list.getHead()));
        System.out.println("2.链表倒数第K个节点为： " + getKthReversedNode(list.getHead(),4));

//        System.out.println("=========反转前============");
//        list.list();
//        System.out.println("=========3.反转后============");
//        getReversedLinkedList(list.getHead());
//        list.list();

        //reversePrint(list.getHead());//4.逆序打印
    }

    ///////////////////////////////////面试题
    //1。 计算链表的有效节点的个数；不统计头节点
    public static int getLength(HeroNode2 head){
        if (head.next == null){
            return 0;
        }
        int length = 0;
        HeroNode2 cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }

    //2. 返回倒数第index个节点；思路：总长度-index就可以定位
    public static HeroNode2 getKthReversedNode(HeroNode2 head,int index){
        HeroNode2 temp = head.next;

        //输入数据校验
        if (index <= 0 ||  getLength(head) < index ){
            System.out.println("输入的索引不符合要求！");
            return null;
        }
//        int count = 0;
//        while (true){
//            if(temp == null){
//                break;
//            }
//            if (count == getLength(head)-index) {
//                return temp;
//            }
//            temp = temp.next;
//            count++;
//        }
//        return null;
        //用for循环
        if (temp == null) {
            return null;//如果链表为空，找不到
        }
        for (int i = 0; i < getLength(head)-index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 3. 反转链表
     * @param head 要反转的链表头
     * @return 反转后的链表
     * 思路：1。 新建一个反转的头；
     *      2。 每遍历一个，将其放到反转头的后面，即插到新链表的最前面
     *      3。 将原始头放在反转头的后面即可
     */
    public static void getReversedLinkedList(HeroNode2 head){
        HeroNode2 reversedListHead = new HeroNode2(0,"","");
        if (head.next == null || head.next.next == null) {
            System.out.println("链表为空或只有一个元素");
            return ;
        }

        HeroNode2 cur = head.next;//遍历的节点
        HeroNode2 next;//遍历的节点的下一个节点
        while (cur != null ){

            next = cur.next;//把原列表暂存起来
            //reversedListHead.next 新表的第一个元素
            cur.next = reversedListHead.next;
            reversedListHead.next = cur;
            cur = next;
        }

        head.next = reversedListHead.next;
    }
    /*
    /**
     * 4。 逆序打印单链表： 利用栈实现
     * @param head
     //
    public static void reversePrint(HeroNode head){

        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;

        while (temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        while(stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    /**
     * 5. 合并两个链表，合并后依然有序
     * @param head 把head1合并到head
     * @param head1
     //
    public static void mergeLinkedList(HeroNode head,HeroNode head1){
        if (head1.next == null) {
            return;
        }

        HeroNode cur = head1.next;
        HeroNode next;
        while(cur != null){
            next = cur.next;
            insertByOrder(head,cur);
            cur = next;
        }
    }

    public static void insertByOrder(HeroNode head,HeroNode node){
        HeroNode temp = head;//要插入的对象指向temp的下一个

        boolean flag = false;
        while (true){
            if (temp.next == null) {//到链表尾部了
                break;
            }
            if (temp.next.no == node.no) {
                flag = true;//说明插入的节点序号已存在
                break;
            }else if (temp.next.no > node.no) {
                break;//找到位置了，需要跳出循环
            }

            temp = temp.next;
        }

        if (flag == true) {
            System.out.println("插入的英雄序号已存在: \n" + temp.next.no);
        }else {//插入
            node.next = temp.next;
            temp.next = node;
        }
    }
    */
}

class DoubleLinkedList{
    private HeroNode2 head ;

    public HeroNode2 getHead() {
        return head;
    }

    public DoubleLinkedList(){
        head = new HeroNode2(0,"","") ;
    }

    public void delete(int no){
        boolean flag = false;
        HeroNode2 temp = head.next;

        while (true){
            if(temp == null){
               break;//说明链表为空
            }
            if (temp.no == no) {
                flag = true;//说明找到了要删除的节点
                break;
            }
            temp = temp.next;
        }

        if (flag == true){
            temp.pre.next = temp.next;
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.printf("要删除的节点no: %d不存在\n",no);
        }
    }

    public void update(HeroNode2 newNode){
        boolean flag = false;
        HeroNode2 temp = head.next;//要修改的对象指向temp

        while (true){
            if(temp == null){
                break;
            }
            if (temp.no == newNode.no) {
                flag = true;//说明找到对应的节点
                break;
            }
            temp = temp.next;
        }

        if (flag == true){
            temp.name = newNode.name;
            temp.nickName = newNode.nickName;
        }else{
            System.out.printf("要修改的英雄no：%d 不存在\n",newNode.no);
        }
    }

    //直接插到连接结尾，不考虑顺序
    //先判断位置，再插入
    public void add(HeroNode2 node){
        HeroNode2 temp = head;//

        while (temp.next != null){
            temp = temp.next;//找到链表末尾
        }

        temp.next = node;
        node.pre = temp;
    }

    //按no顺序插入
    //思路：1. 先while循环找出插入位置，while循环中不符合需要接着循环的条件，一律break；然后调整指针
    //2. 根据循环结果，做插入赋值等操作
    public void addByOrder(HeroNode2 node){
        HeroNode2 temp = head;//要插入的对象指向temp的下一个

        boolean flag = false;
        while (true){
            if (temp.next == null) {//到链表尾部了
                break;
            }
            if (temp.next.no == node.no) {
                flag = true;//说明插入的节点序号已存在
                break;
            }else if (temp.next.no > node.no) {
                break;//找到位置了，需要跳出循环
            }

            temp = temp.next;
        }

        if (flag == true) {
            System.out.println("插入的英雄序号已存在: \n" + temp.next.no);
        }else {//插入

            if (temp.next != null){//注意：非空判断要在使用temp.next之前
            node.next = temp.next;
            temp.next.pre = node;
            }
            node.pre = temp;
            temp.next = node;//先使用temp.next，再给他赋值，否则出错
        }
    }

    public void list(){
        HeroNode2 temp = head;

        if (head.next == null) {
            System.out.println("空链表，没有元素");
            return;
        }

        while (true){
            if (temp.next == null) {
                return;
            }
            System.out.println(temp.next);
            temp = temp.next;
        }
    }
}

class HeroNode2{
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no,String name,String nickName){
        this.no = no;
        this.name = name;
        this.nickName = nickName;
        this.next = null;
        this.pre = null;
    }

    @Override
    public String toString() {
        return "No: " + this.no + " Name: " + this.name +" NickName: " + this.nickName;
    }
}