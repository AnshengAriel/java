package com.ariel.javabase.reflection.a;

public class E<T> {

    private T t;

    private Class<T> clazz;

    public E(T t) {
        this.t = t;
//        this.clazz = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T hello(T t) {
        return t;
    }

    public Class<T> getClazz() {
        return clazz;
    }
}
