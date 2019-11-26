package com.example.boottest.util;

import java.time.Duration;
import java.time.LocalTime;

public class DateUtil {
    public static long getSeconds(LocalTime start, LocalTime end) {
        return Duration.between(end, start).getSeconds();
    }

    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        LocalTime sec = now.plus(Duration.ofSeconds(30));
        System.out.println(getSeconds(sec, now));
    }
}
