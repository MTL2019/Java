package com.jw.tree;

/**
 * 线索化二叉树:
 *    1. n 个节点的二叉链表含有 n+1 个空指针（n-1条连接线）,（2n - (n-1)）= n+1;也就是说每个节点都是单线联系
 *    2. 该空指针指向该节点按照某种遍历次序的前驱或后继节点；这种附加指针 --> 线索
 * 1. 某种遍历次序：前序、中序、后序；按照此顺序，节点分为 前驱 / 后继 节点；节点同时还有 左子树 / 右子树 节点，要注意区分
 *
 * 扩展：使用前序、后序线索化二叉树？？
 */
public class ThreadedBinaryTreeTest {
    public static void main(String[] args) {
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();

        HeroNode2 root = new HeroNode2(1, "tom");
        HeroNode2 node2 = new HeroNode2(3, "jack");
        HeroNode2 node3 = new HeroNode2(6, "smith");
        HeroNode2 node4 = new HeroNode2(8, "mary");
        HeroNode2 node5 = new HeroNode2(10, "kind");
        HeroNode2 node6 = new HeroNode2(14, "dim");

        threadedBinaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //threadedBinaryTree.preOrder();//1,3,8,10,6,14
        //threadedBinaryTree.infixOrder();//8,3,10,1,14,6
        //threadedBinaryTree.postOrder();//8,10,3,14,6,1

        //threadedBinaryTree.threadedNodes_pre(root);
        //threadedBinaryTree.threadedNodes_Infix(root);
//
//        System.out.println(node5.getLeft());
//        System.out.println(node5.getRight());
//        System.out.println(node5.getLeftType());
//        System.out.println(node5.getRightType());
//
        threadedBinaryTree.threadedList();

    }
}

class ThreadedBinaryTree{
    private HeroNode2 root;
    private HeroNode2 pre = null;

    public void setRoot(HeroNode2 root) {
        this.root = root;
    }

    //中序遍历次序来线索化节点
    public void threadedNodes_Infix(HeroNode2 node){
        if (node == null) {
            return;
        }

        threadedNodes_Infix(node.getLeft());//线索化左子树

        //处理当前节点的前驱节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);//存储前驱
        }

        //处理前驱节点的后继节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);//让前驱节点的右指针指向当前节点
            pre.setRightType(1);
        }

        pre = node;

        threadedNodes_Infix(node.getRight());//线索化右子树
    }

    //前序遍历次序来线索化节点;  1,3,8,10,6,14
//    public void threadedNodes_pre(HeroNode2 node){
//        if (node == null) {
//            return;
//        }
//
//        //前序：先处理当前节点的前驱节点
//        if (node.getLeft() == null) {
//            node.setLeft(pre);
//            node.setLeftType(1);//存储前驱
//        }
//
//        //处理前驱节点的后继节点
//        if (node.getRight() == null) {
//            pre.setRight(node);//让前驱节点的右指针指向当前节点
//            pre.setRightType(1);
//        }
//
//        pre = node;
//
//        threadedNodes_pre(node.getLeft());//线索化左子树
//        threadedNodes_pre(node.getRight());//线索化右子树
//    }
    /**
     * 线索化二叉树遍历：遍历结果的节点顺序 取决于 线索化时的构造顺序
     *
     */
    public void threadedList(){
        HeroNode2 node = root;
        while (node != null){
            //向左遍历找到getLeftType() == 1的节点， 说明是按照线索化后的有效节点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }

            System.out.println(node);//输出当前节点

            while (node.getRightType() == 1){
                 node = node.getRight();
                System.out.println(node);
            }

            node = node.getRight();
        }
    }

    public void preOrder(){
        if (this.root == null) {
            System.out.println("树为空，无法遍历！");
        }else {
            this.root.preOrder();
        }
    }

    public void infixOrder(){
        if (this.root == null) {
            System.out.println("树为空，无法遍历！");
        }else {
            this.root.infixOrder();
        }
    }

    public void postOrder(){
        if (this.root == null) {
            System.out.println("树为空，无法遍历！");
        }else {
            this.root.postOrder();
        }
    }

    public HeroNode2 preOrderSearch(int no){
        if (root != null) {
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }
    //中序查找
    public HeroNode2 infixOrderSearch(int no){
        if (root != null) {
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }
    //后序查找
    public HeroNode2 postOrderSearch(int no){

        if (root != null) {
            return root.postOrderSearch(no);
        }else{
            return null;
        }
    }
}
class HeroNode2{
    private int id;
    private String name;
    private HeroNode2 left;
    private HeroNode2 right;

    private int leftType;//0:指向左子树 1： 指向前驱
    private int rightType;//0:指向右子树 1： 指向后继

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode2(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode2 getLeft() {
        return left;
    }

    public void setLeft(HeroNode2 left) {
        this.left = left;
    }

    public HeroNode2 getRight() {
        return right;
    }

    public void setRight(HeroNode2 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);

        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
    //中序遍历
    public void infixOrder(){

        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder(){

        if (this.left != null) {
            this.left.postOrder();
        }

        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序查找

    /**
     *
     * @param no 要查找的英雄no
     * @return  找到返回英雄节点，没找到返回null
     */
    public HeroNode2 preOrderSearch(int no){
        if (no == this.id) {
            return this;
        }

        HeroNode2 tempNode = null;
        if (this.left != null) {
            tempNode = this.left.preOrderSearch(no);
        }
        if (tempNode != null) {
            return tempNode;
        }
        if (this.right != null) {
            tempNode = this.right.preOrderSearch(no);
        }
        if (tempNode != null) {
            return tempNode;
        }
        return tempNode;
    }
    //中序查找
    public HeroNode2 infixOrderSearch(int no){

        HeroNode2 tempNode = null;
        if (this.left != null) {
            tempNode = this.left.infixOrderSearch(no);
        }
        if (tempNode != null) {
            return tempNode;
        }

        if (no == this.id) {
            return this;
        }

        if (this.right != null) {
            tempNode = this.right.infixOrderSearch(no);
        }
        if (tempNode != null) {
            return tempNode;
        }
        return tempNode;
    }
    //后序查找
    public HeroNode2 postOrderSearch(int no){

        HeroNode2 tempNode = null;
        if (this.left != null) {
            tempNode = this.left.postOrderSearch(no);
        }
        if (tempNode != null) {
            return tempNode;
        }

        if (this.right != null) {
            tempNode = this.right.postOrderSearch(no);
        }
        if (tempNode != null) {
            return tempNode;
        }

        if (no == this.id) {
            return this;
        }
        return tempNode;
    }
}
