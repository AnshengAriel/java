package com.ariel.javabase.datastructure.tree;

public class BinaryTree {

    Node root;

    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (root == null) {
            root = node;
        }else {
            root.add(node);
        }
    }

    public Node get(int id) {
        if (root == null) {
            return null;
        }
        return root.get(id);
    }

    public void delete(int id) {
        if (root != null) {
            if (root.getId() == id) {
                if (root.getLeft() == null) {
                    root = root.getRight();
                }else if (root.getRight() == null) {
                    root = root.getLeft();
                }else {
                    // 找到右子树中的最小节点，替代要删除的左子节点
                    Node temp = root.searchFarLeftAndDelete(root.right);
                    temp.left = root.left;
                    temp.right = root.right;
                    root = temp;
                }
            }else {
                root.delete(id);
            }
        }
    }

    public void printPretty() {
        if (root != null) {
            int depth = depth();
            int width = 1 << depth;
            int[][] ints = new int[depth][width];
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

    int depth() {
        if (root != null) {
            return root.depth();
        }
        return 0;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (root != null) {
            root.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void midOrder() {
        if (root != null) {
            root.midOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (root != null) {
            root.postOrder();
        }
    }

    /**
     * 前序查找
     */
    public Node preLookup(int id) {
        if (root != null) {
            return root.preLookup(id);
        }
        return null;
    }

    /**
     * 中序查找
     */
    public Node midLookup(int id) {
        if (root != null) {
            return root.midLookup(id);
        }
        return null;
    }

    /**
     * 后序查找
     */
    public Node postLookup(int id) {
        if (root != null) {
            return root.postLookup(id);
        }
        return null;
    }

    static class Node {
        int id;
        String name;
        Node left;
        Node right;
        static Node pre;
        boolean leftChild;
        boolean rightChild;
        public Node(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void add(Node node) {
            if (node == null) {
                return;
            }
            if (node.id < this.id) {
                if (left == null) {
                    left = node;
                }else {
                    left.add(node);
                }
                leftChild = true;
            }else {
                if (right == null) {
                    right = node;
                }else {
                    right.add(node);
                }
                rightChild = true;
            }
        }

        public Node get(int id) {
            if (id < this.id) {
                if (left == null) {
                    return null;
                }
                return left.get(id);
            }else if (id > this.id){
                if (right == null) {
                    return null;
                }
                return right.get(id);
            }else {
                return this;
            }
        }

        /**
         * 前序遍历删除
         */
        public boolean delete(int id) {
            if (left != null) {
                // 删左节点前先处理其子节点
                if (left.id == id) {
                    if (left.left == null) {
                        left = left.right;
                    }else if (left.right == null) {
                        left = left.left;
                    }else {
                        // 找到右子树中的最小节点，替代要删除的左子节点
                        Node temp = searchFarLeftAndDelete(left.right);
                        temp.left = left.left;
                        temp.right = left.right;
                        left = temp;
                    }
                    return true;
                }else if (left.delete(id)) {
                    return true;
                }
            }
            if (right != null) {
                // 删右节点前先处理其子节点
                if (right.id == id) {
                    if (right.left == null) {
                        right = right.right;
                    }else if (right.right == null) {
                        right = right.left;
                    }else {
                        // 找到右子树中的最小节点，替代要删除的左子节点
                        Node temp = searchFarLeftAndDelete(right.right);
                        temp.left = right.left;
                        temp.right = right.right;
                        right = temp;
                    }
                    return true;
                }else if (right.delete(id)) {
                    return true;
                }
            }

            return false;
        }

        public Node searchFarLeftAndDelete(Node parent) {
            // 寻找右子树最小节点，然后删除
            Node temp = parent;
            while (temp.left != null) {
                temp = temp.left;
            }
            parent.delete(temp.id);
            return temp;
        }

        public void order(int[][] ints, int i, int j, int k) {
            ints[i][j] = id;
            i++;
            int l = k >> 1;
            if (left != null) {
                left.order(ints, i, j - l, l);
            }
            if (right != null) {
                right.order(ints, i, j + l, l);
            }
        }

        /**
         * 前序遍历
         */
        public void preOrder() {
            System.out.println(this);
            if (leftChild) {
                left.preOrder();
            }
            if (rightChild) {
                right.preOrder();
            }
        }

        /**
         * 中序遍历
         */
        public void midOrder() {
            if (leftChild) {
                left.midOrder();
            }
            System.out.println(this);
            if (rightChild) {
                right.midOrder();
            }
        }

        /**
         * 后序遍历
         */
        public void postOrder() {
            if (leftChild) {
                left.postOrder();
            }
            if (rightChild) {
                right.postOrder();
            }
            System.out.println(this);
        }

//    @Override
//    public String toString() {
//        return "Node{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }


        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", left=" + (left != null ? left.id : null) +
                    ", right=" + (right != null ? right.id : null) +
                    '}';
        }

        public int depth() {
            int l = 1, r = 1;
            if (left != null) {
                l = left.depth() + 1;
            }
            if (right != null) {
                r = right.depth() + 1;
            }
            return Math.max(l, r);
        }

        /**
         * 前序查找
         */
        public Node preLookup(int id) {
            if (this.id == id) {
                return this;
            }
            Node node;
            if (left != null && (node = left.preLookup(id)) != null) {
                return node;
            }
            if (right != null && (node = right.preLookup(id)) != null) {
                return node;
            }
            return null;
        }

        /**
         * 中序查找
         */
        public Node midLookup(int id) {
            Node node;
            if (left != null && (node = left.preLookup(id)) != null) {
                return node;
            }
            if (this.id == id) {
                return this;
            }
            if (right != null && (node = right.preLookup(id)) != null) {
                return node;
            }
            return null;
        }

        /**
         * 后序查找
         */
        public Node postLookup(int id) {
            Node node;
            if (left != null && (node = left.preLookup(id)) != null) {
                return node;
            }
            if (right != null && (node = right.preLookup(id)) != null) {
                return node;
            }
            if (this.id == id) {
                return this;
            }
            return null;
        }

        /**
         * 中序线索化
         */
        public void midThread() {
            if (leftChild) {
                left.midThread();
            }

            if (left == null) {
                left = pre;
            }
            if (pre != null && !pre.rightChild) {
                pre.right = this;
            }
            pre = this;

            if (rightChild) {
                right.midThread();
            }
        }

        /**
         * 前序线索化 4213657
         */
        public void preThread() {
            if (left == null) {
                left = pre;
            }
            if (pre != null && !pre.rightChild) {
                pre.right = this;
            }
            pre = this;

            if (leftChild) {
                left.preThread();
            }

            if (rightChild) {
                right.preThread();
            }
        }

        /**
         * 后序线索化 1325764
         */
        public void postThread() {
            if (leftChild) {
                left.postThread();
            }

            if (rightChild) {
                right.postThread();
            }

            if (left == null) {
                left = pre;
            }
            if (pre != null && !pre.rightChild) {
                pre.right = this;
            }
            pre = this;
        }

    }

}


