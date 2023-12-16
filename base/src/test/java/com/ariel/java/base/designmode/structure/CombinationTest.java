package com.ariel.java.base.designmode.structure;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 组合模式
 */
public class CombinationTest {

    @Test
    public void testPreSingleTon() {

    }

    interface Component {
        void add(Component c);
        void remove(Component c);
        Component getChild(int i);
        void operation();
    }


    static class Leaf implements Component{

        private String name;


        public Leaf(String name) {
            this.name = name;
        }

        @Override
        public void add(Component c) {}

        @Override
        public void remove(Component c) {}

        @Override
        public Component getChild(int i) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void operation() {
            // TODO Auto-generated method stub
            System.out.println("树叶"+name+"：被访问！");
        }

    }

    static class Composite implements Component {

        private ArrayList<Component> children = new ArrayList<Component>();

        public void add(Component c) {
            children.add(c);
        }

        public void remove(Component c) {
            children.remove(c);
        }

        public Component getChild(int i) {
            return children.get(i);
        }

        public void operation() {
            for (Component obj : children) {
                obj.operation();
            }
        }
    }
}