package com.ariel.javabase.datastructure.recursion;

/**
 * 汉诺塔问题
 * 5 0 0
 * a b c
 * a-b a-c b-c
 * a-b c-a c-a c-b a-b
 */
public class HanoiTower {

    private int count = 1;

    /**
     * 将i个盘从a挪到c
     */
    public void recurse(int i, char a, char b, char c) {
        if (i == 1) {
            System.out.printf("第%d步，把第%d个盘，从%s->%s%n", count++, i, a, c);
            return;
        }
        // 将i-1个盘从a挪到b
        recurse(i-1, a, c, b);
        // 将第i个盘从a到c
        System.out.printf("第%d步，把第%d个盘，从%s->%s%n", count++, i, a, c);
        // 将i-1个盘从b挪到c
        recurse(i-1, b, a, c);
    }
}
