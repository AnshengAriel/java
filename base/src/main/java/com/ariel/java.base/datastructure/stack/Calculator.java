package com.ariel.java.base.datastructure.stack;

public class Calculator {

    public Integer calculate(String exp) {
        MyStack numStack = new MyStack(3);
        MyStack operaStack = new MyStack(2);
        // true=上一位也是数字，用以处理多位数
        boolean flag = false;
        for (char c : exp.toCharArray()) {
            // true=字符c是操作符
            if (isOperator(c)) {
                flag = false;
                while (true) {
                    // true=操作符栈为空
                    if (operaStack.isEmpty()) {
                        operaStack.push(((int) c));
                        break;
                    }
                    char last = (char) operaStack.pop().intValue();
                    // true=操作符栈栈顶操作符比当前符号有更高的优先级，需要先运算
                    if (isPriority(last, c)) {
                        Integer second = numStack.pop();
                        Integer first = numStack.pop();
                        int result = compute(first, second, last);
                        numStack.push(result);
                    }else {
                        operaStack.push(((int) last));
                        operaStack.push(((int) c));
                        break;
                    }
                }
            }else {
                String value = String.valueOf(c);
                if (flag) {
                    value = numStack.pop().toString() + value;
                }
                flag = true;
                numStack.push(Integer.valueOf(value));
            }
        }
        return numStack.pop();
    }

    private boolean isOperator(char value) {
        switch (value) {
            case '+' :
            case '-' :
            case '*' :
            case '/' :
            case '=' :
                return true;
            default:
                return false;
        }
    }

    private boolean isPriority(char a, char b) {
        switch (a) {
            case '+' :
            case '-' :
                switch (b) {
                    case '+' :
                    case '-' :
                    case '=' :
                        return true;
                    case '*' :
                    case '/' :
                        return false;
                }
            case '*' :
            case '/' :
                return true;
        }
        return true;
    }

    private int compute(int first, int second, char op) {
        switch (op) {
            case '+' :
                return first + second;
            case '-' :
                return first - second;
            case '*' :
                return first * second;
            case '/' :
                return first / second;
            default:
                return 0;
        }
    }
}
