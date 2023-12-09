package com.ariel.javabase.datastructure.algorithm;

import java.util.*;

/**
 * 贪心算法之广播站问题
 * 贪心算法每一步都是最优解，但整体不是
 */
public class Broadcast {

    /**
     * 广播站列表
     */
     private HashMap<String, HashSet<String>> map = new HashMap<>();
    /**
     * 广播区域
     */
    private List<String> areas = new ArrayList<>();

    {
        HashSet<String> set = new HashSet<>();
        set.add("北京");
        set.add("上海");
        set.add("天津");
        map.put("K1", set);

        set = new HashSet<>();
        set.add("广州");
        set.add("北京");
        set.add("深圳");
        map.put("K2", set);

        set = new HashSet<>();
        set.add("成都");
        set.add("上海");
        set.add("杭州");
        map.put("K3", set);

        set = new HashSet<>();
        set.add("上海");
        set.add("天津");
        map.put("K4", set);

        set = new HashSet<>();
        set.add("杭州");
        set.add("大连");
        map.put("K5", set);

        areas.add("北京");
        areas.add("上海");
        areas.add("天津");
        areas.add("广州");
        areas.add("深圳");
        areas.add("成都");
        areas.add("杭州");
        areas.add("大连");

        map.forEach((k, v) -> System.out.printf("%s %s%n", k, v));
        System.out.println(areas);
    }

    /**
     * 使用贪心算法，获取一个广播站列表，使得通过这些广播站能够访问到所有的地区
     * @return 广播站列表
     */
    public List<String> lookup() {
        String k = null;
        List<String> temp = new ArrayList<>(), list = new ArrayList<>();
        int maxSize = 0;
        while (!areas.isEmpty()) {
            for (Map.Entry<String, HashSet<String>> entry : map.entrySet()) {
                temp.clear();
                temp.addAll(entry.getValue());
                temp.retainAll(areas);
                if (temp.size() > maxSize) {
                    maxSize = temp.size();
                    k = entry.getKey();
                }
            }

            if (k != null) {
                list.add(k);
                areas.removeAll(map.get(k));
                k = null;
                maxSize = 0;
            }
        }
        return list;
    }

}
