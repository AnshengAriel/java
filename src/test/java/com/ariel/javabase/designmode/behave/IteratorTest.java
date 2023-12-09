package com.ariel.javabase.designmode.behave;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 迭代器模式
 */
public class IteratorTest {

    @Test
    public void testIterator() {
        CakeHouseMenu menu = new CakeHouseMenu();
        Iterator<MenuItem> iterator = menu.iterator();
        while (iterator.hasNext()) {
            iterator.next().name();
        }
    }


    /**
     * 迭代器
     */
    interface Iterator<T> {
        boolean hasNext();
        T next();
    }

    static class MenuItem {
        String name;
        String description;
        boolean vegetable;
        float price;
        public MenuItem(String name, String description, boolean vegetable, float price) {
            this.name = name; this.description = description; this.vegetable = vegetable; this.price = price;
        }
        public void name() {
            System.out.println(name);
        }
    }

    static class CakeHouseMenu {
        private ArrayList<MenuItem> menuItems;


        public CakeHouseMenu() {
            menuItems = new ArrayList<>();

            addItem("KFC Cake Breakfast","boiled eggs&toast&cabbage",true,3.99f);
            addItem("MDL Cake Breakfast","fried eggs&toast",false,3.59f);
            addItem("Stawberry Cake","fresh stawberry",true,3.29f);
            addItem("Regular Cake Breakfast","toast&sausage",true,2.59f);
        }

        private void addItem(String name, String description, boolean vegetable, float price) {
            MenuItem menuItem = new MenuItem(name, description, vegetable, price);
            menuItems.add(menuItem);
        }

        public Iterator<MenuItem> iterator() {
            return new CakeHouseIterator() ;
        }

        class CakeHouseIterator implements Iterator<MenuItem> {
            private int position;

            @Override
            public boolean hasNext() {
                // TODO Auto-generated method stub
                return position < menuItems.size();
            }

            @Override
            public MenuItem next() {
                return menuItems.get(position ++);
            }
        }
    }
}
