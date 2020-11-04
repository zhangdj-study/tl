package com.qyhy.tl.mythreadlocal;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangdj
 * @date 2020/10/16
 * 实现一个自己的ThreadLocal，具有set get 功能
 */
public class MyThreadLocal<T> {

    static HashMap<Thread,HashMap<Integer,Object>> threadLocalMap = new HashMap<>();

    AtomicInteger atomicInteger = new AtomicInteger();

    Integer hash = atomicInteger.addAndGet(0x61c88647);

    synchronized HashMap<Integer,Object> getMap() {
        Thread thread = Thread.currentThread();
        if (!threadLocalMap.containsKey(thread)) {
            threadLocalMap.put(thread,new HashMap<>(16));
        }
        return threadLocalMap.get(thread);
    }

    protected T initialValue() {
        return null;
    }

    void set(T v) {
        HashMap<Integer, Object> map = getMap();
        map.put(this.hash,v);
    }

    T get() {
        HashMap<Integer, Object> map = getMap();
        if (!map.containsKey(this.hash)) {
            map.put(this.hash,initialValue());
        }
        return (T) map.get(this.hash);
    }

}
