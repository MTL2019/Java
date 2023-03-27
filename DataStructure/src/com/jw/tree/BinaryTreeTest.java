package com.jw.tree;

public class BinaryTreeTest {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "无用");
        HeroNode node3 = new HeroNode(3, "陆谨以");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        binaryTree.setRoot(root);

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        System.out.println("前序遍历");
        binaryTree.preOrder();
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//        System.out.println("后序遍历");
//        binaryTree.postOrder();
//        System.out.println("前序查找");
//        System.out.println(binaryTree.preOrderSearch(5));
//        System.out.println("中序查找");
//        System.out.println(binaryTree.infixOrderSearch(5));
//        System.out.println("后序查找");
//        System.out.println(binaryTree.postOrderSearch(5));
    }
}

class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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

    public HeroNode preOrderSearch(int no){
        if (root != null) {
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }
    //中序查找
    public HeroNode infixOrderSearch(int no){
        if (root != null) {
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }
    //后序查找
    public HeroNode postOrderSearch(int no){

        if (root != null) {
            return root.postOrderSearch(no);
        }else{
            return null;
        }
    }
}
class HeroNode{
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int id, String name) {
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
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
    public HeroNode preOrderSearch(int no){
        if (no == this.id) {
            return this;
        }

        HeroNode tempNode = null;
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
    public HeroNode infixOrderSearch(int no){

        HeroNode tempNode = null;
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
    public HeroNode postOrderSearch(int no){

        HeroNode tempNode = null;
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
