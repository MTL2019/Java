package Examples;

import java.util.Scanner;
import java.util.Stack;

public class EvaluateExpressionChapter20 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter an Expression: ( 0-9,+,-,*,/,(,) )");
        String s = input.nextLine();

        System.out.println(evaluateExpression(s));

    }

    /** Evaluate an expression */
    public static int evaluateExpression(String expression) {

        Stack<Integer> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        expression = insertBlanks(expression);
        String[] tokens = expression.split(" ");

        //Scan tokens
        for (String token: tokens ) {
            if (token.length() == 0) {
                continue;
            } else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
                //process all operator above the stack
                while (!operatorStack.isEmpty() && (
                            operatorStack.peek() == '+' ||
                            operatorStack.peek() == '-' ||
                            operatorStack.peek() == '*' ||
                            operatorStack.peek() == '/' )
                        ){
                    processAnOperator(operandStack,operatorStack);
                }

                operatorStack.push(token.charAt(0));//push + / -
            }
            else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
                while (!operatorStack.isEmpty() && (
                                operatorStack.peek() == '*' ||
                                operatorStack.peek() == '/' )
                ){
                    processAnOperator(operandStack,operatorStack);
                }

                operatorStack.push(token.charAt(0));//push + / -
            }
            else if(token.trim().charAt(0) =='(') {
                operatorStack.push('(');
            }
            else if (token.trim().charAt(0) ==')'){
                while (operatorStack.peek() != '('){
                    processAnOperator(operandStack, operatorStack);
                }

                operandStack.pop();
            }else {
                operandStack.push(Integer.valueOf(token));
            }
        }

        //Process remaining operators
        while (!operatorStack.isEmpty()) {
            processAnOperator(operandStack, operatorStack);
        }

        return operandStack.pop();
    }

    public static void processAnOperator(Stack<Integer> operandStack, Stack<Character> operatorStack){

        char op = operatorStack.pop();

        int op1 = operandStack.pop();
        int op2 = operandStack.pop();

        if (op == '+') {
            operandStack.push(op2 + op1);
        } else if (op == '-') {
            operandStack.push(op2 - op1);
        }else if (op == '*') {
            operandStack.push(op2 * op1);
        }else if (op == '/') {
            operandStack.push(op2 / op1);
        }
    }
    public static String insertBlanks(String s) {

        String result="";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')' ||
                    s.charAt(i) == '+' || s.charAt(i) == '-' ||
                    s.charAt(i) == '*' || s.charAt(i) == '/') {
                result += " " + s.charAt(i) + " ";
            }else
                result += s.charAt(i) ;
        }

        return result;
    }
}
