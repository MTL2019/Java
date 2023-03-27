package com.jw.binarySortTree;

/**
 * 二叉排序树：任何非叶子节点，其左子节点、当前节点、右子节点值相等或递增；查找和插入速度较快
 * 1. 添加
 * 2. 遍历
 * 3. 删除：3种情况：叶子节点无子树、1个子树、2个子树
 */
public class BinarySortTreeTest {
    public static void main(String[] args) {
        int arr[] = {7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree = new BinarySortTree();

        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        //binarySortTree.infixOrder();

        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(1);
        binarySortTree.delNode(10);
        binarySortTree.delNode(12);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(2);

        System.out.println();
        System.out.println("root为： " + binarySortTree.getRoot());
        binarySortTree.infixOrder();
    }
}

class BinarySortTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void add(Node node){
        if (root == null) {
            root = node;
        }else {
            root.add(node);
        }
    }

    public void infixOrder(){
        if (root == null) {
            System.out.println("二叉排序树为空，不能遍历");
        }else {
            root.infixOrder();
        }

    }

    public Node search(int value){
        if (root == null) {
            return null;
        }else {
            return root.search(value);
        }
    }

    //找父节点
    public Node searchParent(int value){
        if (root == null) {
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    public void delNode(int value){
        if (root == null) {
            return;
        }else {
            Node target = search(value);
            if (target == null) {
                return;
            }

            if (root.left == null && root.right == null) {//如果找到的是唯一的根节点，则直接删除
                root = null;
                return;
            }

            Node parent = searchParent(value);
            //第1种情况：如果要删除的是叶子节点
            if (target.left == null && target.right == null) {
                if (parent.left != null && parent.left.value == value) {//判断是左子节点
                    parent.left = null;
                }else if (parent.right != null && parent.right.value == value) {//判断是左子节点
                    parent.right = null;
                }
            }else if (target.left != null && target.right != null) {//第3种情况：有左、右子树的情况

                //把要删除节点target的右子树的最小值节点提到target位置，然后删除右子树的最小值节点
                //或者：把要删除节点target的左子树的最大值节点提到target位置，然后删除左子树的最大值节点
                //目的：删除后，保证该子树依然是二叉排序树，即从左、当前、右节点值递增
                int minVal = delRightTreeMin(target.right);
                target.value = minVal;

                //或者从target节点下的左子树找最大的，然后替换

            }else {//第2种情况：有左 或者 右子树的情况
                if (target.left != null) {//target有左子节点
                    if (parent != null) {
                        if (parent.left.value == value) {//target是parent的左子节点
                            parent.left = target.left;//删除target
                        }else {
                            parent.right = target.left;//target是parent的右子节点
                        }
                    }else {
                        root = target.left;//target为根节点时
                    }

                }else  {//target有右子节点
                    if (parent != null) {
                        if (parent.left.value == value) {//target是parent的左子节点
                            parent.left = target.right;//删除target
                        } else {
                            parent.right = target.right;//target是parent的右子节点
                        }
                    }else {
                        root = target.right;//target为根节点时
                    }
                }
            }
        }
    }

    /**
     * 返回node节点下最小的节点的值，并删除该节点
     * @param node
     * @return
     */
    public int delRightTreeMin(Node node){
        Node target = node;

        while (target.left != null){
            target = target.left;//最小的节点在node下的最左侧
        }

        delNode(target.value);
        return target.value;
    }
}
class Node{
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void add(Node node){
        if (node == null) {
            return;
        }

        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            }else {
                this.left.add(node);
            }
        }else {
            if (this.right == null) {
                this.right = node;
            }else {
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.print(this.value+" ");

        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public Node search(int value){
        if (this.value == value) {
            return this;
        }else if (value < this.value) {
            if ( this.left == null) {
                return null;
            }
            return this.left.search(value);
        }else {
            if ( this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //找父节点
    public Node searchParent(int value){
        if (this.left != null && this.left.value == value ||
                this.right != null && this.right.value == value) {
            return this;
        }else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            }else if (value >= this.value && this.right != null) {//使用有值相等的情况
                return this.right.searchParent(value);
            }else {
                return null;
            }
        }
    }
}
