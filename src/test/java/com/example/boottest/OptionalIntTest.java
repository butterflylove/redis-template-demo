package com.example.boottest;

import org.junit.Test;

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
}
