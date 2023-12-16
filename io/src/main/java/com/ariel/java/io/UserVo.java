package com.ariel.java.io;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class UserVo implements Serializable {
    private static final long serialVersionUID = 34555643212456L;

    private UserVo() {
        System.out.println("UserVo");
    }

    private Integer id;

    private String name;

    /**
     * 反序列化拦截器，见{@linkplain java.io.ObjectStreamClass 字段readResolveMethod}
     * 还有多种拦截器可以设置
     * @return 反序列化对象，需要手动赋值
     */
    private Object readResolve() {
        System.out.println("readResolve");
        return new UserVo();
    }

    public static UserVo valueOf(Integer id, String name) {
        System.out.println("valueOf");
        UserVo vo = new UserVo();
        vo.id = id;
        vo.name = name;
        return vo;
    }
}
