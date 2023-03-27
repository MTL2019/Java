package com.jw.tree;

import java.io.*;
import java.util.*;

/**
 * 利用赫夫曼编码，实现对字符串的压缩和解压
 * 利用赫夫曼编码，实现对 文件 的压缩和解压； 对重复数据不多或已压缩的文件，压缩效果不明显
 */
public class HuffmanCodeTest {

    static Map<Byte,String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {

        //压缩字符串
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();//获得对应的byte字符串
//
//        byte[] huffmanZipBytes = huffmanZip(contentBytes);
//
//        System.out.println("赫夫曼编码后的byte字符串为： "+Arrays.toString(huffmanZipBytes));
//        System.out.println("赫夫曼编码后的byte字符串长度为： "+huffmanZipBytes.length);//压缩后的长度为17
//
//        byte[] sourceBytes = decode(huffmanCodes,huffmanZipBytes);
//        System.out.println("赫夫曼编码后的byte字符串为： "+ new String(sourceBytes));

        //////////////////测试压缩文件
        String srcFile = "/Users/davin/Documents/1.jpeg";
        String dstFile = "/Users/davin/Documents/1.zip";
        String dstFileUnzip = "/Users/davin/Documents/2.jpeg";

        zipFile(srcFile,dstFile);//压缩二进制文件
        unZipFile(dstFile,dstFileUnzip);//解压二进制文件

    }
    //解压二进制文件
    public static void unZipFile(String zipFile,String dstFile){
        //创建输入输出流
        InputStream is = null;
        ObjectInputStream ois = null;
        FileOutputStream os = null;

        try{
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);

            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte,String> huffmanCodes = (Map<Byte,String>) ois.readObject();

            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            os = new FileOutputStream(dstFile);
            os.write(bytes);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try{
                os.close();
                ois.close();
                is.close();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    //压缩二进制文件
    public static void zipFile(String srcFile,String dstFile){
        //创建输入输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        FileInputStream is = null;

        try{
            is = new FileInputStream(srcFile);
            byte[] b = new byte[is.available()];
            is.read(b);

            byte[] huffmanBytes = huffmanZip(b);
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCodes);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try{
                is.close();
                oos.close();
                os.close();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    //////////////////////////////////解码字符串
    /**
     * 解码
     * @param huffmanCodes 赫夫曼编码
     * @param huffmanBytes 待解码的赫夫曼编码的字符串
     * @return 解码后的赫夫曼编码的字符串
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        StringBuilder stringBuilder = new StringBuilder();

        //获得二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);//判断是否是最后一位
            stringBuilder.append(byteToBitString(!flag,b));//将byte转换为 二进制字符串，存入StringBuilder
        }
        
        //将二进制字符串 按指定huffmanCodes 解码
        //先反转huffmanCodes，要根据原来的huffmanCodes的value反向查询key
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte,String> entry:huffmanCodes.entrySet()){
             map.put(entry.getValue(), entry.getKey());
        }

        //遍历二进制字符串， 匹配huffmanCodes，完成解码
        List<Byte> list = new ArrayList<>();//接收解码后的byte
        for (int i = 0; i < stringBuilder.length(); ) {//i由count实现递增
            int count = 1;//匹配每个code的指针
            boolean flag = true;
            Byte b = null;
            while (flag){
                String key = stringBuilder.substring(i,i+count);//i不动，count移动
                b = map.get(key);

                if (b == null) {
                    count++;
                }else {
                    flag = false;//匹配到code后，退出while进入下一个i循环
                }
            }
            list.add(b);//匹配到就加入到list
            i += count;
        }

        //将list装入byte[]
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }
    /**
     * 将byte 转换为二进制的字符串
     * @param flag 是否需要补位；最后一个字节不足8位时，不需要补高位
     * @param b
     * @return b 对应的二进制的字符串，按补码返回
     */
    private static String byteToBitString(boolean flag,byte b){
        int temp = b;//转换为int

        if (flag) {//当b为最后一位时，不需补齐高位，flag = false
            temp |= 256;//按位与；1 0000 0000 ｜ 0000 0001 = 1 0000 0001； ｜：如果相对应位都是 0，则结果为 0，否则为 1
        }

        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);//补位后，只取最后8位
        }else {
            return str;//需要补位时，不截取
        }
    }
    /////////////////////////////////////////////////压缩 字符串
    /**
     * 使用Huffman编码 压缩 字符串
     * @param bytes 待压缩的bytes字符串
     * @return 压缩后的bytes字符串
     */
    public  static byte[] huffmanZip(byte[] bytes){
        //获得byte字符节点list
        List<CodeNode> list = getNodes(bytes);
        //创建HuffmanTree
        CodeNode huffmanTreeRoot = createHuffmanTree(list);
        //获得Huffman编码
        huffmanCodes = getCodes(huffmanTreeRoot);
        //根据Huffman编码 压缩bytes
        byte[] huffmanCodesBytes = zip(bytes, huffmanCodes);
        return huffmanCodesBytes;
    }

    /**
     *
     * @param bytes 要转换的字符集
     * @param huffmanCodes 赫夫曼编码
     * @return 转换后的字符集
     *
     * byte存的是原值的补码，即先获得原值的反码（每位取反），然后加1 获得补码
     */
    private static byte[] zip(byte[] bytes, Map<Byte,String> huffmanCodes){

        StringBuilder stringBuilder = new StringBuilder();
        for (byte b:bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        //将stringBuilder 转换为 byte[]
        int len;
        //len = (stringBuilder.length() + 7 )/8;//和下面等价
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        }else {
            len = stringBuilder.length() / 8 + 1;
        }

        byte[] huffmanCodesBytes = new byte[len];//存储压缩后的byte[]
        int index = 0;//记录放了几个byte
        String str;
        for (int i = 0; i < stringBuilder.length(); i +=8) {//每8位读一次

            if(i + 8 > stringBuilder.length()){
                str = stringBuilder.substring(i);//最后不足8位，就取到最后即可
            }else{
                str = stringBuilder.substring(i,i+8);
            }

            //将str转换为byte，放入byte[]
            huffmanCodesBytes[index] = (byte) Integer.parseInt(str,2);//
            index++;
        }
        return huffmanCodesBytes;
    }

    //重载，方便主程序调用
    public static Map<Byte,String> getCodes(CodeNode node){
        if (node == null) {
            return null;
        }
        getCodes(node.left,"0", stringBuilder);
        getCodes(node.right,"1", stringBuilder);

        return huffmanCodes;
    }
    /**
     * 获得叶子节点的路径字符串，即赫夫曼编码，存放在 huffmanCodes 中
     * @param node 从该节点开始递归
     * @param code 从左、右两个路径递归 左 ： 0  ； 右 ： 1
     * @param stringBuilder 拼接路径的字符串
     */
    public static void getCodes(CodeNode node,String code , StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);

        if (node != null) {//空节点不处理

            if (node.data == null) {//data = null，说明是非叶子节点
                //递归处理
                getCodes(node.left,"0",stringBuilder2);
                getCodes(node.right,"1",stringBuilder2);
            }else {//叶子节点
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
    }
    /**
     * 生成赫夫曼树
     * @param list
     * @return
     */
    public static CodeNode createHuffmanTree(List<CodeNode> list){

        CodeNode leftNode;
        CodeNode rightNode;

        while(list.size() > 1){

            Collections.sort(list);//先排序
            leftNode = list.get(0);
            rightNode = list.get(1);

            CodeNode node = new CodeNode(null,leftNode.weight + rightNode.weight);
            node.left = leftNode;
            node.right = rightNode;

            list.remove(leftNode);
            list.remove(rightNode);
            list.add(node);
        }

        return list.get(0);
    }

    public static void preOrder(CodeNode root){
        if (root == null) {
            System.out.println("树为空，不能遍历！");
        }else {
            root.preOrder();
        }
    }
    /**
     * 获得包含 byte字符及其出现频率的键值对 的节点的list
     * @param bytes byte数组，依次存放字符对应的ascii码
     * @return 存放包含byte字符及其出现频率的键值对的节点的list
     */
    private static List<CodeNode> getNodes(byte[] bytes){
        ArrayList<CodeNode> codeNodes = new ArrayList<>();

        //遍历bytes，统计每个b的出现次数，放入countsMap
        Map<Byte, Integer> countsMap = new HashMap<>();
        for (byte b:bytes) {
            Integer count = countsMap.get(b);
            if (count == null) {
                countsMap.put(b,1);
            }else {
                countsMap.put(b,count+1);
            }
        }

        //把每个键值对转为codeNode，并加入codeNodes
        for (Map.Entry<Byte, Integer> entry:countsMap.entrySet()) {
            codeNodes.add(new CodeNode(entry.getKey(),entry.getValue()));
        }

        return codeNodes;
    }
}

//实现Comparable接口，便于使用Collections的排序方法
class CodeNode implements Comparable<CodeNode>{
    public int weight;
    public Byte data;//存放字符本身
    public CodeNode left;
    public CodeNode right;

    public CodeNode(Byte data, int weight) {
        this.weight = weight;
        this.data = data;
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
        return "CodeNode{" +
                "weight=" + weight +
                " data=" + data +
                '}';
    }

    @Override
    public int compareTo(CodeNode o) {
        return this.weight - o.weight;//以支持Collections的sort功能，从小到大排序；逆序排序则加负号
    }
}
