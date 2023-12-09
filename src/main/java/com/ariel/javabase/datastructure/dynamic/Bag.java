package com.ariel.javabase.datastructure.dynamic;

import java.util.LinkedList;

/**
 * 动态规划之背包问题
 */
public class Bag {

    public int recurse(int capacity) {
        LinkedList<Item> items = new LinkedList<>();
        int maxPrice = recurse(capacity, 0, 0, items);
        for (Item item : items) {
            item.quantity++;
        }
        return maxPrice;
    }

    public int recurse(int capacity, int totalWeight, int totalPrice, LinkedList<Item> items) {
        if (capacity <= totalWeight) {
            return capacity == totalWeight ? totalPrice : 0;
        }
        int maxPrice = totalPrice, tempPrice;
        LinkedList<Item> lastItems = new LinkedList<>(), tempItems;
        for (Item item : Item.values()) {
            // 背包物品不重复
            if (items.contains(item)) {
                continue;
            }
            tempItems = new LinkedList<>();
            tempItems.addLast(item);
            tempPrice = recurse(capacity, totalWeight + item.weight, totalPrice + item.price, tempItems);
            if (tempPrice > maxPrice) {
                maxPrice = tempPrice;
                lastItems = tempItems;
            }
        }
        // 添加暂时正确的商品，后序可能被替换
        items.addAll(lastItems);
        return maxPrice;
    }

    public void loop() {
        // 重量
        int[] weight = {1, 3, 4};
        int[] price = {1500, 2000, 3000};
        int[][] dp = new int[3][5];

        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = price[0];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                // true=背包容量小于当前物品，所以背包不变
                if (j < weight[i]) {
                    dp[i][j] = dp[i-1][j];
                }else {
                    // max左=背包不变 max右=尝试清空包裹，直到空间足够，然后添加当前物品
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]] + price[i]);
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }

        for (int i = 0; i < weight.length; i++) {
            System.out.printf("%d %d%n", weight[i], price[i]);
        }

    }

    public void printItem() {
        for (Item item : Item.values()) {
            System.out.printf("物品[%s] 重量[%s] 单价[%s] 数量[%s]%n", item, item.weight, item.price, item.quantity);
        }
    }

    public enum Item {
        // 吉他
        GUITAR(1, 1500, 0),
        // 音响
        AUDIO(4, 3000, 0),
        // 电脑
        COMPUTER(3, 2000, 0),
        ;
        public final int weight;
        public final int price;
        public int quantity;
        Item(int weight, int price, int quantity) {
            this.weight = weight;
            this.price = price;
            this.quantity = quantity;
        }

    }
}
