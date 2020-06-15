package com.example.boottest.entity;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

/**
 * Created by zhangtianlong on 20/6/15.
 */
public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private int level;

    public Person(String name, int age, int level) {
        this.name = name;
        this.age = age;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int compareTo(Person o) {
        return ComparisonChain.start()
                .compare(this.name, o.name, Ordering.natural().nullsFirst())
                .compare(this.age, o.age)
                .compare(this.level, o.level)
                .result();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", level=" + level +
                '}';
    }
}
