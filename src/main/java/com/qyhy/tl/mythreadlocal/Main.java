package com.qyhy.tl.mythreadlocal;

/**
 * @author zhangdj
 * @date 2020/10/16
 */
public class Main {

    static MyThreadLocal<Long> threadLocal = new MyThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }
    };

    public static void main(String[] args) {
        int i = 1;
        System.out.println(~i);
    }
}
