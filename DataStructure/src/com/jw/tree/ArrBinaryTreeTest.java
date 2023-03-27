package com.jw.tree;

/**
 * 顺序存储二叉树的特点：
 * 通常只考虑完全二叉树
 * 第 n 个元素的左子节点为 2*n +1
 * 第 n 个元素的右子节点为 2*n +2
 * 第 n 个元素的父节点为 n-1 /2
 * n 从0开始，表示从第几个元素开始
 *
 * 应用： 堆排序
 */
public class ArrBinaryTreeTest {
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7};

        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        //arrBinaryTree.preOrder();
        //arrBinaryTree.preOrder();
        arrBinaryTree.postOrder();
    }
}

//顺序存储二叉树遍历
class ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载
    public void preOrder() {
        this.preOrder(0);
    }

    public void infixOrder() {
        this.infixOrder(0);
    }

    public void postOrder() {
        this.postOrder(0);
    }

    public void preOrder(int index){
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }

        System.out.println(arr[index]);

        //向左递归
        if ((2*index +1) < arr.length) {
            preOrder(2*index +1);
        }

        //向右递归
        if ((2*index +2) < arr.length) {
            preOrder(2*index +2);
        }
    }

    public void infixOrder(int index){
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }

        //向左递归
        if ((2*index +1) < arr.length) {
            infixOrder(2*index +1);
        }

        System.out.println(arr[index]);

        //向右递归
        if ((2*index +2) < arr.length) {
            infixOrder(2*index +2);
        }
    }

    public void postOrder(int index){
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }

        //向左递归
        if ((2*index +1) < arr.length) {
            postOrder(2*index +1);
        }

        //向右递归
        if ((2*index +2) < arr.length) {
            postOrder(2*index +2);
        }

        System.out.println(arr[index]);
    }
}
