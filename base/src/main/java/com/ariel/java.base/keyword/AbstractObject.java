package com.ariel.java.base.keyword;

/**
 * 抽象类不能实例化，但可以定义构造器
 */
public abstract class AbstractObject {

    private Integer id;

    public AbstractObject(Integer id) {
        this.id = id;
    }

}
