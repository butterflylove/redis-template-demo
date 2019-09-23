package com.example.boottest;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;
import java.util.OptionalInt;

public class OptionalIntTest {

    @Test
    public void testOptionalInt() {
        OptionalInt op = OptionalInt.of(1);
        if (op.isPresent()) {
            System.out.println(op.getAsInt());
        }
        op.ifPresent(i -> System.out.println("value: " + i));
        System.out.println("======:" + op.orElse(111));
        System.out.println("xxxx: " + op.orElseGet(() -> 333));
        op.orElseThrow(NullPointerException::new);
    }

    @Test
    public void testReduce() {
        List<Integer> list = Lists.newArrayList();
        int sum = list.stream().reduce(Integer::sum).orElse(10);
        System.out.println("====sum:" + sum);

        List<Long> listx = Lists.newArrayList(1L,2L,3L);
        long sumx = listx.stream().reduce(10L, Long::sum);
        System.out.println("====sumx:" + sumx);
    }
}
