package com.ariel.java.base.datastructure.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 前缀表达式，运算符在数字之前，也叫做波兰表达式，比如：(3 + 4) * 5 - 6 = - * + 3 4 5 6
 * 后缀表达式，运算符在数字之后，也叫做逆波兰表达式，比如：(3 + 4) * 5 - 6 = 3 4 + 5 * 6 -
 * 中缀表达式，包含大小括号，运算符在两个数字之间，是人类常用的形式，比如：(3 + 4) * 5 - 6
 * 不论何种表达式都在玩消消乐，任意两个相邻的数字加一个字符都能校成一个数字
 */
public class PolandNotation {

    /**
     * 计算后缀表达式
     * @param exp
     * @return
     */
    public Integer calculate(String exp) {
        String[] words = exp.split(" ");
        MyStack stack = new MyStack(words.length);
        for (String str : words) {
            if (str.matches("\\d+")) {
                stack.push(Integer.valueOf(str));
            }else {
                Integer second = stack.pop();
                Integer first = stack.pop();
                int result;
                switch (str) {
                    case "+":
                        result = first + second;
                        break;
                    case "-":
                        result = first - second;
                        break;
                    case "*":
                        result = first * second;
                        break;
                    case "/":
                        result = first / second;
                        break;
                    default:
                        throw new RuntimeException("Unknown Operator");
                }
                stack.push(result);
            }
        }
        return stack.pop();
    }

    /**
     * 将中缀表达式转换为后缀表达式
     * @param midExp
     * @return
     */
    public String convert(String midExp) {
        String[] words = midExp.split(" ");
        MyStack numStack = new MyStack(words.length);
        MyStack operaStack = new MyStack(words.length);
        for (String w : words) {
            if (w.matches("\\d+")) {
                numStack.push(Integer.valueOf(w));
            }else {
                if (operaStack.isEmpty()) {
                    operaStack.push(((int) w.charAt(0)));
                    continue;
                }
                switch (w) {
                    case "(":
                        operaStack.push(((int) w.charAt(0)));
                        break;
                    case ")":
                        while (operaStack.peek() != '(') {
                            numStack.push(operaStack.pop());
                        }
                        operaStack.pop();
                        break;
                    case "+":
                    case "-":
                        int last = operaStack.peek();
                        if (last != '(') {
                            numStack.push(operaStack.pop());
                        }
                        operaStack.push(((int) w.charAt(0)));
                        break;
                    case "*":
                    case "/":
                        last = operaStack.peek();
                        if (last == '*' || last == '/') {
                            numStack.push(operaStack.pop());
                        }
                        operaStack.push(((int) w.charAt(0)));
                        break;
                    default:
                        throw new RuntimeException("Unknown Operator: " + w);
                }
            }
        }

        String lastOpera = Character.valueOf(((char) operaStack.pop().intValue())).toString();
        StringBuilder builder = new StringBuilder(lastOpera);
        while (numStack.peek() != null) {
            builder.append(" ");
            if (operators.contains(numStack.peek())) {
                builder.append(Character.valueOf((char) numStack.pop().intValue()));
            }else {
                builder.append(numStack.pop());
            }
        }
        builder.reverse();
        return builder.toString();
    }

    private final static List<Integer> operators = Arrays.asList(((int) '+'), ((int) '-'), ((int) '*'), ((int) '/'));
}
