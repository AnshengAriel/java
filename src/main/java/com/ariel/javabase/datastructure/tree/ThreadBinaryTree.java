package com.ariel.javabase.datastructure.tree;

/**
 * 线索二叉树
 * 普通二叉树中序遍历转成数组后，如果数组中某元素节点的左右指针为空，将其左指针指向前一个元素，右指针指向后一个元素
 * 详细操作流程：
 * 1.检查并填充left节点
 * 2.检查并填充pre的right节点
 * 3.将pre指向当前节点
 * 4.回溯pre给下一个节点
 */
public class ThreadBinaryTree extends BinaryTree {

    public void preThread() {
        if (root != null) {
            root.preThread();
        }
    }

    public void midThread() {
        if (root != null) {
            root.midThread();
        }
    }

    public void postThread() {
        if (root != null) {
            root.postThread();
        }
    }

    public void preThreadLookup() {
        Node temp = root;
        while (temp != null) {
            // 输出根节点
            System.out.println(temp);
            while (temp.leftChild) {
                temp = temp.left;
                // 输出左子树
                System.out.println(temp);
            }
            if (!temp.rightChild) {
                temp = temp.right;
                if (temp == null) {
                    return;
                }
            }
        }
    }

    public void midThreadLookup() {
        Node temp = root;
        while (temp != null) {
            while (temp.leftChild) {
                temp = temp.left;
            }
            // 输出左子树
            System.out.println(temp);
            while (!temp.rightChild) {
                temp = temp.right;
                if (temp == null) {
                    return;
                }
            }
            // 输出根节点
            System.out.println(temp);
            // 切换右子树
            temp = temp.right;
        }
    }

    public void postThreadLookup() {
        Node pot = root;
        Node pre = null;
        while (pot != null) {
            while (pot.leftChild) {
                pot = pot.getLeft();
            }
            while (pot != null && !pot.rightChild) {
                System.out.println(pot);
                pre = pot;
                pot = pot.getRight();
            }
            //若pot结点为根节点，则遍历完成
            if (pot == root) {
                System.out.println(pot);
                return;
            }
            // 若pot.getRight() == pre则说明以pot为根结点的子树遍历完成，应遍历pot兄弟结点。
            // 先获取pot结点的父节点，再获取pot结点的兄弟结点。
            // 若pot结点无兄弟结点，则继续向上寻找。
            while (pot != null && pot.getRight() == pre) {
                System.out.println(pot);
                pre = pot;
//                pot = pot.getParent();
            }
            if (pot != null && pot.rightChild) {
                pot = pot.getRight();
            }
        }

    }


}
