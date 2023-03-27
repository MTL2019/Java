package com.jw.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 使用逆波兰表达式计算
 * 中缀表达式： 普通的计算表达式
 * 后缀表达式：运算后在数字后面
 * 前缀表达式：运算后在数字前面
 */
public class RePorlandExpression {
    public static void main(String[] args) {

        String s = "1+((2+3)*4)-5";
        List<String> InfixList = toInfixExpressionList(s);
        System.out.println(InfixList);

        ArrayList<String> suffixExpression = toSuffixExpressionList(InfixList);
        System.out.println("后缀表达式为： "+suffixExpression);
        //计算逆波兰表达式，中间用空格分割
//        String suffixExpresison = "3 4 + 5 * 6 -";
//        String suffixExpresison1 = "30 4 + 5 * 6 -";
//        String suffixExpresison2 = "4 5 * 8 - 60 + 8 2 / +";
//
//        List<String> list = getListString(suffixExpresison2);
//        System.out.println(list);
        System.out.printf("后缀表达式的值为：%d \n ",cal(suffixExpression));

    }

    /**
     * 将中缀表达式转换为后缀表达式
     * 1) 初始化两个栈：运算符栈s1和储存中间结果的栈s2 -- 此处用ArrayList代替；
     * 2) 从左至右扫描中缀表达式；
     * 3) 遇到操作数时，将其压s2；
     * 4) 遇到运算符时，比较其与s1栈顶运算符的优先级：
     * 1.如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     * 2.否则，若优先级比栈顶运算符的高，也将运算符压入s1；
     * 3.否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较；
     * 5) 遇到括号时：
     *    (1) 如果是左括号“(”，则直接压入s1
     *    (2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6) 重复步骤2至5，直到表达式的最右边
     * 7) 将s1中剩余的运算符依次弹出并压入s2
     * 8)  依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     * @param ls
     * @return
     */
    public static ArrayList<String> toSuffixExpressionList(List<String> ls){

        //"1+((2+3)*4)-5" = 16
        Stack<String> stack = new Stack<>();
        ArrayList<String> list = new ArrayList<>();

        //遍历中缀表达式
        for (String item:ls) {
            if (item.matches("\\d+")) {
                list.add(item);
            }else if (item.equals("(")) {
                stack.add(item);
            }else if (item.equals(")")) {
                    while (!(stack.peek().equals("(")))
                    {
                        list.add(stack.pop());
                    }
                    stack.pop();//弹出左括号，丢弃

            }else{

                while(stack.size() != 0 && (getPriority(item) <= getPriority(stack.peek()))){
                        list.add(stack.pop());
                }

                stack.push(item);
            }
        }

        while (stack.size() > 0){
            list.add(stack.pop());
        }

        return list;
    }
    /**
     * 将中缀表达式存储在list中
     * @param s
     * @return
     */
    public static List<String> toInfixExpressionList(String s){
        int index = 0;
        String keepStr = "";
        ArrayList<String> list = new ArrayList<>();
        char c = 0;//扫描到的字符

        do {
            c = s.charAt(index);//开始扫描字符

            //如果c是非数字 0(48) - 9(57)，添加到list
            if (c < 48 || c > 57) {
                list.add("" + c);//转换为String
                index++;
            }else {
                keepStr = "";

                while((index < s.length()) && ((c = s.charAt(index)) >= 48) && ((c = s.charAt(index)) <= 57)){
                    keepStr += c;
                    index++;
                }
                list.add(keepStr);
            }
        }while(index < s.length());

        return list;
    }
    /**
     * 用arrayList来存储表达式
     * @param se
     * @return
     */
    public static List<String> getListString(String se){
        String[] strings= se.split(" ");

        ArrayList<String> list = new ArrayList<>();

        for (String item:strings) {
            list.add(item);
        }
        return list;
    }

    /**
     * 使用逆波兰表达式计算
     * 从左至右扫描后缀表达式
     * 1。 如果是数字，则压入栈
     * 2。 如果是符号，则弹出两个值进行计算，并将计算结果压入栈
     * 3。 循环至计算完毕
     * @param list 后缀表达式：逆波兰表达式
     * @return 返回计算结果
     */
    public static int cal(List<String> list){

        Stack<String> stack = new Stack<>();
        int res = 0;

        for (String item: list) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());

                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                }else{
                    throw new RuntimeException("输入运算符有误！");
                }

                stack.push(String.valueOf(res));
            }
        }
        return res;
    }

    public static int getPriority(String oper){
        if (oper.equals("+") || oper.equals("-")) {
            return 1;
        } else if (oper.equals("*") || oper.equals("/")) {
            return 2;
        }else{
            System.out.println("不是运算符");
            return 0;
        }
    }
}
