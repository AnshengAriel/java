package com.ariel.javabase.datastructure.string;

/**
 * KMP算法
 * 在一个文本串中查找子串，kmp利用之前判断的信息减少比对的次数
 */
public class Kmp {

    public int indexOf(String text, String key) {
        int[] next = next(key);
        for (int i = 0, j = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != key.charAt(j)) {
                j = next[j - 1];
            }
            if (text.charAt(i) == key.charAt(j)) {
                j++;
            }
            if (j == key.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    public int[] next(String key) {
        int[] next = new int[key.length()];
        for (int i = 1, j = 0; i < key.length(); i++) {
            while (j > 0 && key.charAt(i) != key.charAt(j)) {
                System.out.print(j);
                j = next[j-1];
                System.out.println(" " + j);
            }
            if (key.charAt(i) == key.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
