package com.ariel.java.base.designmode.behave;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 访问者模式模式
 */
public class VisitorTest {

    @Test
    public void testVisitor() {
        ObjectStructure os = new ObjectStructure();
        os.add(new ConcreteElementA());
        os.add(new ConcreteElementB());
        Visitor visitor = new ConcreteVisitorA();
        os.accept(visitor);
        System.out.println("------------------------");
        visitor = new ConcreteVisitorB();
        os.accept(visitor);

    }



    //抽象访问者
    interface Visitor {
        void visit(ConcreteElementA element);

        void visit(ConcreteElementB element);
    }


    //具体访问者A类
    static class ConcreteVisitorA implements Visitor {
        public void visit(ConcreteElementA element) {
            System.out.println("具体访问者A访问-->" + element.operationA());
        }

        public void visit(ConcreteElementB element) {
            System.out.println("具体访问者A访问-->" + element.operationB());
        }
    }


    //具体访问者B类
    static class ConcreteVisitorB implements Visitor {
        public void visit(ConcreteElementA element) {
            System.out.println("具体访问者B访问-->" + element.operationA());
        }

        public void visit(ConcreteElementB element) {
            System.out.println("具体访问者B访问-->" + element.operationB());
        }
    }


    //抽象元素类
    interface Element {
        void accept(Visitor visitor);
    }


    //具体元素A类
    static class ConcreteElementA implements Element {
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        public String operationA() {
            return "具体元素A的操作。";
        }
    }


    //具体元素B类
    static class ConcreteElementB implements Element {
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        public String operationB() {
            return "具体元素B的操作。";
        }
    }


    //对象结构角色
    static class ObjectStructure {
        private List<Element> list = new ArrayList<>();

        public void accept(Visitor visitor) {
            Iterator<Element> i = list.iterator();
            while (i.hasNext()) {
                i.next().accept(visitor);
            }
        }

        public void add(Element element) {
            list.add(element);
        }

        public void remove(Element element) {
            list.remove(element);
        }
    }

}
