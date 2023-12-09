package com.ariel.javabase.datastructure.tree;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 赫夫曼树
 * 节点的带权路径长度 = 根节点到节点的距离 * 结点的权重
 * 树的带权路径长度 = 所有叶子节点的带权路径长度之和
 * 赫夫曼数就是树权（wpl）最低的二叉树
 */
public class HuffmanTree implements Serializable {

    private HNode root;

    private HNode end = new HNode(true);

    private HashMap<Byte, Integer> map = new HashMap<>();

    public void build(int[] ints) {
        switch (ints.length) {
            case 0 : return;
            case 1 : root = new HNode(ints[0]); return;
            case 2 : root = new HNode(new HNode(ints[0]), new HNode(ints[1])); return;
        }
        ArrayList<HNode> nodes = new ArrayList<>(ints.length);
        for (int anInt : ints) {
            nodes.add(new HNode(anInt));
        }

        HNode parent;
        while (true) {
            nodes.sort(HNode::compareTo);
            parent = new HNode(nodes.remove(0), nodes.remove(0));
            if (nodes.isEmpty()) {
                break;
            }
            nodes.add(parent);
        }
        root = parent;
    }

    public byte[] encode(String msg) {
        return encode(msg.getBytes(StandardCharsets.UTF_8));
    }

    public byte[] encode(byte[] bytes) {
        // 获取节点统计列表
        for (byte b : bytes) {
            if (map.containsKey(b)) {
                map.put(b, map.get(b) + 1);
            }else {
                map.put(b, 1);
            }
        }

        HNode[] nodes = createRoot();
//        printPretty();
//        for (HNode node : nodes) {
//            System.out.println(node);
//        }

        // 根据编码表，逐个翻译字节
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            for (int i = nodes.length - 1; i >= 0; i--) {
                if (nodes[i].getData() == b) {
                    builder.append(nodes[i].getPath());
                    break;
                }
            }
        }

        // 对最后一位不足8位的情况做处理
        int latest = builder.length() % 8;
        if (latest > 0) {
            for (int i = latest; i < 8;) {
                if (i + end.getPath().length() > 8) {
                    builder.append(end.getPath(), 0, 8-i);
                    break;
                }else {
                    builder.append(end.getPath());
                    i+=end.getPath().length();
                }
            }
        }

        String binaryStr = builder.toString();

        // 将哈夫曼编码串拆分成普通字节数组
        byte[] codecByte = new byte[binaryStr.length() / 8];
        for (int i = 0; i < codecByte.length; i++) {
            String binary = binaryStr.substring(i * 8, i * 8 + 8);
            codecByte[i] = Integer.valueOf(binary, 2).byteValue();
        }
        return codecByte;
    }

    private HNode[] createRoot() {
        // 根据字符统计表，构建哈夫曼树
        List<HNode> nodes = new ArrayList<>();
        map.forEach((k, v) -> nodes.add(new HNode(k, v)));
        nodes.add(end);
        nodes.sort(HNode::compareTo);
        HNode[] array = nodes.toArray(new HNode[0]);

        HNode parent;
        while (true) {
            nodes.sort(HNode::compareTo);
            parent = new HNode(nodes.remove(0), nodes.remove(0));
            if (nodes.isEmpty()) {
                break;
            }
            nodes.add(parent);
        }

        // 生成字符编码，左0右1
        root = parent;
        root.prePath(new StringBuilder());
        return array;
    }

    public byte[] decode(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            String s = Integer.toBinaryString(b + 256);
            if (b < 0) {
                builder.append(s);
            } else {
                builder.append(s, 1, 9);
            }
        }
        String binaryStr = builder.toString();

        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < binaryStr.length();) {
            i = root.lookup4Decode(list, binaryStr, i);
        }

        byte[] rbs = new byte[list.size()];
        for (int i = 0; i < rbs.length; i++) {
            rbs[i] = list.get(i);
        }
        return rbs;
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

    public void zip(String source, String target) {
        try (
                FileInputStream in = new FileInputStream(source);
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(target));
        ) {
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            out.writeObject(new Data(map, encode(bytes)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void upzip(String source, String target) {
        try (
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(source));
                FileOutputStream out = new FileOutputStream(target);
        ) {
            Data data = (Data) in.readObject();
            map = data.map;
            byte[] bytes = data.bytes;

            createRoot();
            out.write(decode(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public class HNode implements Comparable<HNode>, Serializable {

        private int value;

        private byte data;

        private String path;

        private HNode left;

        private HNode right;

        private boolean end;

        public HNode(int value) {
            this.value = value;
        }

        public HNode(boolean end) {
            this.end = end;
        }

        public HNode(byte data) {
            this.data = data;
        }

        public HNode(byte data, int value) {
            this.data = data;
            this.value = value;
        }

        public HNode(HNode left, HNode right) {
            this.value = left.value + right.value;
            this.left = left;
            this.right = right;
        }

        public byte getData() {
            return data;
        }

        public String getPath() {
            return path;
        }

        @Override
        public int compareTo(HNode o) {
            return this.value - o.value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HNode node = (HNode) o;
            return data == node.data;
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }

        @Override
        public String toString() {
            return "HNode{" +
                    "value=" + value +
                    ", data=" + data +
                    ", path=" + path +
                    ", left=" + (left != null ? left.value : null) +
                    ", right=" + (right != null ? right.value : null) +
                    '}';
        }

        public void prePrint() {
            System.out.println(this);
            if (left != null) {
                left.prePrint();
            }
            if (right != null) {
                right.prePrint();
            }
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

        public void addValue() {
            value++;
        }

        public void prePath(StringBuilder builder) {
            path = builder.toString();
            if (left != null) {
                builder.append('0');
                left.prePath(builder);
                builder.deleteCharAt(builder.length() - 1);
            }
            if (right != null) {
                builder.append('1');
                right.prePath(builder);
                builder.deleteCharAt(builder.length() - 1);
            }
        }

        public int lookup4Decode(List<Byte> list, String binaryStr, int i) {
            if (end || i == binaryStr.length()) {
                return i;
            }
            if (left == null && right == null) {
                list.add(getData());
                return i;
            }
            char c = binaryStr.charAt(i);
            if (c == '0') {
                i = left.lookup4Decode(list, binaryStr, i+1);
            }else {
                i = right.lookup4Decode(list, binaryStr, i+1);
            }
            return i;
        }
    }

}
