package com.jw.avl;

/**
 * 平衡二叉树：任一节点的左右两个子树的高度差的绝对值不超过1；或空树
 * 1. 添加
 *      - 左 、 右 旋转
 *      - 双旋转
 */
public class AVLTreeTest {
    public static void main(String[] args) {

        AVLTree avlTree = new AVLTree();

        //int arr[] = {4,3,6,5,7,8};
        //int arr[] = {10,12,8,9,7,6};
        int arr[] = {10,11,7,6,8,9};
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        System.out.println("height ： " + avlTree.getRoot().height());
        System.out.println("left height ： " + avlTree.getRoot().leftHeight());
        System.out.println("right height ： " + avlTree.getRoot().rightHeight());
        avlTree.infixOrder();
    }
}

class AVLTree{
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
    //返回左子树的高度
    public int leftHeight(){
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight(){
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //返回以当前节点为根的子树的高度
    public int height(){
        return Math.max(left == null ? 0 : left.height(),right == null ? 0 : right.height()) + 1;
    }

    /**
     * rightHeight - leftHeight > 1 需要左旋处理
     *
     */
    public void leftRotate(){
        //1. 以当前节点值创建新节点
        Node newNode = new Node(value);
        //2. 把新节点的左子树设置为当前节点的左子树
        newNode.left = left;
        //3. 把新节点的右子树设置为当前节点的右子树的左子树
        newNode.right = right.left;
        //4. 把当前节点的值换成右子节点的值
        value = right.value;
        //5. 把当前节点的右子树设置为右子树的右子树
        right = right.right;
        //6. 把当前节点的左子树设置为新节点
        left = newNode;
    }

    /**
     * leftHeight - rightHeight > 1 需要 右旋处理
     *
     */
    public void rightRotate(){
        //1. 以当前节点值创建新节点
        Node newNode = new Node(value);
        //2. 把新节点的右子树设置为当前节点的右子树
        newNode.right = right;
        //3. 把新节点的左子树设置为当前节点的左子树的右子树
        newNode.left = left.right;
        //4. 把当前节点的值换成左子节点的值
        value = left.value;
        //5. 把当前节点的左子树设置为左子树的左子树
        left = left.left;
        //6. 把当前节点的左子树设置为新节点
        right = newNode;
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

        //双旋转，防止少数单旋转不能平衡的情况
        if (rightHeight() - leftHeight() > 1) {
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
            }
            leftRotate();
            return;
        }

        if (leftHeight() - rightHeight() > 1) {
            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.leftRotate();
            }
            rightRotate();
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
