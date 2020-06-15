package com.example.boottest;

import com.example.boottest.entity.Person;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created by zhangtianlong on 20/6/15.
 */
public class StudentTest {

    @Test
    public void testComapre() {
        Person a = new Person("aa", 11, 10);
        Person b = new Person("aa", 11, 14);
        List<Person> list = Lists.newArrayList(b, a);
        list.sort(Person::compareTo);
        list.forEach(System.out::println);
    }
}
