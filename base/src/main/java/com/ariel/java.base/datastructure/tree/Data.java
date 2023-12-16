package com.ariel.java.base.datastructure.tree;

import java.io.Serializable;
import java.util.HashMap;

public class Data implements Serializable {

    public HashMap<Byte, Integer> map;

    public byte[] bytes;

    public Data(HashMap<Byte, Integer> map, byte[] bytes) {
        this.map = map;
        this.bytes = bytes;
    }
}
