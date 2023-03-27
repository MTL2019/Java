package com.jw.stack;

public class ArrStackTest {
    public static void main(String[] args) {
        ArrStack arrStack = new ArrStack(5);

        for (int i = 0; i < 5; i++) {
            arrStack.put(i);
        }
        arrStack.list();

        System.out.println(arrStack.pop());
        arrStack.list();
    }
}

class ArrStack{
    private int maxSize;
    private int[] arrStack;
    private int top = -1;

    public ArrStack(int maxSize) {
        this.maxSize = maxSize;
        arrStack = new int[this.maxSize];
    }

    public boolean isFull(){
        return top == maxSize - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public void put(int num){
        if(isFull())
        {
            System.out.println("栈已满！");
            return;
        }
        top++;
        arrStack[top] = num;
    }

    public int pop(){
        if (isEmpty()) {
            throw new RuntimeException("栈已空");
        }
        int temp = arrStack[top];
        top--;
        return temp;
    }
    public void list(){
        if (isEmpty()) {
            System.out.println("栈为空");
            return;
        }

        for (int i = top;i>=0;i--) {
            System.out.printf("栈值为%d \n",arrStack[i]);
        }
    }
}
