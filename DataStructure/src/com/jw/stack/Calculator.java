package com.jw.stack;

/**
 * 用栈实现综合计算：中缀表达式
 * 1. 通过一个 index  值（索引），来遍历我们的表达式
 * 2. 如果我们发现是一个数字, 就直接入数栈
 * 3. 如果发现扫描到是一个符号,  就分如下情况
 * 3.1 如果发现当前的符号栈为 空，就直接入栈
 * 3.2 如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符， 就需要从数栈中pop出两个数,在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈， 如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
 * 4. 当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行.
 * 5. 最后在数栈只有一个数字，就是表达式的结果
 */
public class Calculator {
    public static void main(String[] args) {

        String expression = "3+800*1-80/2";

        ArrStack2 numStack = new ArrStack2(10);
        ArrStack2 operStack = new ArrStack2(10);

        int num1 = 0;
        int num2 = 0;
        char ch = 0;//扫描到的字符
        int index = 0;//扫描指针
        int oper = 0;
        int res = 0;
        String keepNum = "";


        //先扫描
        while(true){
            ch = expression.substring(index,index+1).charAt(0);

            if (ch == '*' || ch == '/' || ch == '+' || ch == '-') {
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                }else{//如果符号栈不为空
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = operStack.calculate(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else{
                        operStack.push(ch);
                    }
                }
            }else{//如果是数字
                //numStack.push(ch - 48);//只能判断单位数
                keepNum += ch;

                if (index == expression.length() - 1){//最后一个字符
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }

            index++;
            if (index >= expression.length()) {
                break;//扫描完成
            }
        }

        //计算
        while(true){
            if (operStack.isEmpty()) {
                break;//计算完成
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = operStack.calculate(num1,num2,oper);
            numStack.push(res);
        }

        System.out.printf("表达式%s的计算结果为：%d \n",expression,numStack.pop());

    }
}


class ArrStack2 {
    private int maxSize;
    private int[] arrStack;
    private int top = -1;

    public ArrStack2(int maxSize) {
        this.maxSize = maxSize;
        arrStack = new int[this.maxSize];
    }

    public boolean isOper(int ch){

            return (ch == '*' || ch == '/' || ch == '+' || ch == '-') ;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int num) {
        if (isFull()) {
            System.out.println("栈已满！");
            return;
        }
        top++;
        arrStack[top] = num;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空");
        }
        int temp = arrStack[top];
        top--;
        return temp;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空");
            return;
        }

        for (int i = top; i >= 0; i--) {
            System.out.printf("栈值为%d \n", arrStack[i]);
        }
    }

    //返回操作符的优先级
    public int priority(int oper){
        if (oper == '*' || oper == '/') {
            return 1;
        }else if (oper == '+' || oper == '-') {
            return 0;
        }else {
            return -1;
        }
    }

    //返回栈顶值
    public int peek(){
        return arrStack[top];
    }

    public int calculate(int num1,int num2,int oper){
        int res = 0;
        switch (oper){
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;//注意顺序
                break;
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序
                break;
            default:
                break;
        }
        return res;
    }
}
