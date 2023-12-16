package com.ariel.java.base.designmode.behave;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 解释器模式
 */
public class ComplainTest {

    @Test
    public void testExplain() {
        //创建环境对象
        ExplainContext context = new ExplainContext();

        //创建多个变量对象
        Variable a = new Variable("a");
        Variable b = new Variable("b");
        Variable c = new Variable("c");
        Variable d = new Variable("d");

        //将变量存储到环境变量中
        context.assign(a,1);
        context.assign(b,2);
        context.assign(c,3);
        context.assign(d,4);

        //获取抽象语法树
        AbsExpression expression = new Minus(a,new Plus(new Minus(b,c),d));

        int result = expression.interpret(context);

        System.out.println(expression + " = " + result);
    }


    abstract static class AbsExpression {
        public abstract int interpret(ExplainContext context);
    }

    static class Variable extends AbsExpression{

        //声明存储变量名的成员变量
        private String name;

        public Variable(String name) {
            this.name = name;
        }

        @Override
        public int interpret(ExplainContext context) {
            //直接返回变量的值
            return context.getValue(this);
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static class Plus extends AbsExpression{
        // +号 左边的表达式
        private AbsExpression left;

        //+号 右边的表达式
        private AbsExpression right;

        public Plus(AbsExpression left, AbsExpression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int interpret(ExplainContext context) {
            //将 左边表达式结果 与 右边表达式的结果 相加

            return left.interpret(context) + right.interpret(context);
        }

        @Override
        public String toString() {
            return "( " + left.toString() + " + " + right.toString() + " )";
        }
    }

    static class Minus extends AbsExpression{
        // -号 左边的表达式
        private AbsExpression left;

        // -号 右边的表达式
        private AbsExpression right;

        public Minus(AbsExpression left, AbsExpression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int interpret(ExplainContext context) {
            //将 左边表达式结果 与 右边表达式的结果 相加

            return left.interpret(context) - right.interpret(context);
        }

        @Override
        public String toString() {
            return "( " + left.toString() + " - " + right.toString() + " )";
        }
    }

    static class ExplainContext {

        //定义一个map集合,用来存储变量及对应的值
        private Map<Variable,Integer> map  = new HashMap<>();

        //添加变量
        public void assign(Variable var,Integer value){
            map.put(var,value);
        }

        //根据变量获取对应的值
        public int getValue(Variable var){
            return map.get(var);
        }
    }


}

