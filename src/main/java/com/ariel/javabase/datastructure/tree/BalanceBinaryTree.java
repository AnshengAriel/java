package com.ariel.javabase.datastructure.tree;

/**
 * 平衡二叉树，也叫avl树，是基于二叉排序树（bst）的一种优化
 * 任意一个节点的左右子树的高度相差不超过1
 */
public class BalanceBinaryTree {

    private Node root;

    public void add(int value) {
        add(new Node(value));
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        }else {
            root.add(node);
            rotate();
        }
    }

    public void rotate() {
        if (root != null) {
            root = root.rotate();
        }
    }

    public void printPretty() {
        if (root != null) {
            int height = root.height();
            int width = 1 << height;
            int[][] ints = new int[height][width];
            root.order(ints, 0, width >> 1, width >> 1);

            boolean flag;
            for (int i = 0; i < ints[0].length; i++) {
                flag = false;
                for (int j = 0; j < ints.length; j++) {
                    if (ints[j][i] != 0) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    for (int j = 0; j < ints.length; j++) {
                        ints[j][i] = -1;
                    }
                }
            }

            for (int i = 0; i < ints.length; i++) {
                System.out.print(i + "\t|");
                for (int j = 0; j < ints[i].length; j++) {
                    if (ints[i][j] == -1) {
                        continue;
                    }
                    System.out.print("\t");
                    if (ints[i][j] != 0) {
                        System.out.print(ints[i][j]);
                    }
                }
                System.out.println();
            }
        }
    }

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        public void add(Node node) {
            if (node.value < value) {
                if (left == null) {
                    left = node;
                }else {
                    left.add(node);
                }
            }else {
                if (right == null) {
                    right = node;
                }else {
                    right.add(node);
                }
            }
        }

        public int height() {
            return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
        }

        public Node rotate() {
            if (left != null || right != null) {
                int lHeight = 0;
                int rHeight = 0;
                if (left != null) {
                    left = left.rotate();
                    lHeight = left.height();
                }
                if (right != null) {
                    right = right.rotate();
                    rHeight = right.height();
                }
                // true=左右子树高度相差太大，需要调整
                if (Math.abs(lHeight - rHeight) > 1) {
                    return lHeight < rHeight ? leftRotate() : rightRotate();
                }
            }
            return this;
        }

        public Node leftRotate() {
            // 为防止过度左旋，先判断右子树的左子树是否过高
            int lHeight = right.left != null ? right.left.height() : 0;
            int rHeight = right.right != null ? right.right.height() : 0;
            if (lHeight > rHeight) {
                right = right.rightRotate();
            }
            // 右子树高，节点左旋。
            Node temp;
            Node parent = this;
            temp = parent.right.left;
            parent.right.left = parent;
            parent = parent.right;
            parent.left.right = temp;
            return parent;
        }

        public Node rightRotate() {
            // 为防止过度右旋，先判断左子树的右子树是否过高
            int lHeight = left.left != null ? left.left.height() : 0;
            int rHeight = left.right != null ? left.right.height() : 0;
            if (lHeight < rHeight) {
                left = left.leftRotate();
            }
            // 左子树高，节点右旋
            Node temp;
            Node parent = this;
            temp = parent.left.right;
            parent.left.right = parent;
            parent = parent.left;
            parent.right.left = temp;
            return parent;
        }


        public void order(int[][] ints, int i, int j, int k) {
            ints[i][j] = value;
            i++;
            int l = k >> 1;
            if (left != null) {
                left.order(ints, i, j - l, l);
            }
            if (right != null) {
                right.order(ints, i, j + l, l);
            }
        }


    }

}
