package com.jw.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树：所有叶子节点的带权路径之和最小
 * 1。 数组所有元素都可以看作一个节点的二叉树
 * 2。 先对节点从小到大排序
 * 3。 将最小的两个节点的权值相加，作为其父节点的权值，组成新的二叉树
 * 4。 删除两个小的使用过的节点，将新二叉树的根节点加入数组重新参与排序
 * 5。 重复3。4， 直到数组中只有一个root为止
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13,7,8,3,29,6,1};

        Node root = createHuffmanTree(arr);

        root.preOrder();
    }

    public static void preOrder(Node root){
        if (root == null) {
            System.out.println("树为空，不能遍历！");
        }else {
            root.preOrder();
        }
    }

    public static Node createHuffmanTree(int[] arr){

        List<Node> nodes = new ArrayList<>();
        for (int i :arr) {
            nodes.add(new Node(i));//将数组的值存放到ArrayList中，便于排序
        }

        Node leftNode;
        Node rightNode;

        while(nodes.size() > 1){

            Collections.sort(nodes);//先排序
            leftNode = nodes.get(0);
            rightNode = nodes.get(1);

            Node node = new Node(leftNode.value + rightNode.value);
            node.left = leftNode;
            node.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(node);
        }

        return nodes.get(0);
    }
}

//实现Comparable接口，便于使用Collections的排序方法
class Node implements Comparable<Node>{
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
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

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;//以支持Collections的sort功能，从小到大排序；逆序排序则加负号
    }
}
