package com.qyhy.tl.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

/**
 * @author zhangdj
 * @date 2020/10/16
 */
@RestController
public class ThreadLocalTestController {

    /**
     * 用来收集分散在各个线程中的数据
     */
    static HashSet<Val<Integer>> set = new HashSet<>();

    static Integer c = 0;

    synchronized static void addSet(Val<Integer> val) {
        set.add(val);
    }

    static ThreadLocal<Val<Integer>> threadLocal = new ThreadLocal<Val<Integer>>(){
        @Override
        public Val<Integer> initialValue() {
            // 将每个线程中的值放到一个公共的map中
            Val<Integer> val = new Val<>();
            val.set(0);
            addSet(val);
            return val;
        }
    };

    public void _add() throws Exception {
        Thread.sleep(100);
        Val<Integer> val = threadLocal.get();
        val.set(val.get() + 1);
    }

    @GetMapping("add")
    public void add() throws Exception {
        _add();
    }

    @GetMapping("get")
    public Integer get() {
        return set.stream().map(x -> x.get()).reduce((a,x) -> a + x).get();
    }
}
