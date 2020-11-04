package com.qyhy.tl.hello;

/**
 * @author zhangdj
 * @date 2020/10/16
 */
public class Val<T> {

    T v;

    public void set(T v) {
        this.v = v;
    }

    public T get() {
        return v;
    }
}
