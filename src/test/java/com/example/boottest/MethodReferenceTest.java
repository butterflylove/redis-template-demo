package com.example.boottest;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class MethodReferenceTest {

    @Test
    public void testClassOfStaticMethod() {
        Student student1 = new Student("zhangsan",60);
        Student student2 = new Student("lisi",70);
        Student student3 = new Student("wangwu",80);
        Student student4 = new Student("zhaoliu",90);
        List<Student> students = Arrays.asList(student1,student2,student3,student4);
        students.sort(Student::compareStudentByScore);
        students.forEach(System.out::println);
    }

    @Test
    public void testObjectOfInstanceMehtod() {
        Student student1 = new Student("zhangsan",60);
        Student student2 = new Student("lisi",70);
        Student student3 = new Student("wangwu",80);
        Student student4 = new Student("zhaoliu",90);
        List<Student> students = Arrays.asList(student1,student2,student3,student4);
        StudentComparator studentComparator = new StudentComparator();
        students.sort(studentComparator::compareStudentByScore);
        students.forEach(System.out::println);
    }

    @Test
    public void testClassOfInstanceMethod() {
        Student student1 = new Student("zhangsan",60);
        Student student2 = new Student("lisi",70);
        Student student3 = new Student("wangwu",80);
        Student student4 = new Student("zhaoliu",90);
        List<Student> students = Arrays.asList(student1,student2,student3,student4);
        students.sort(Student::compareByScore);
        students.forEach(System.out::println);
    }

    @Test
    public void testOfConstruct() {
        Supplier<Student> supplier = Student::new;
        Student student = supplier.get();
        System.out.println(student);
    }
}

class Student {
    private String name;
    private int score;

    public Student() {
    }

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Student setScore(int score) {
        this.score = score;
        return this;
    }

    public int compareByScore(Student student) {
        return this.getScore() - student.getScore();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public static int compareStudentByScore(Student student1, Student student2){
        return student1.getScore() - student2.getScore();
    }
}

class StudentComparator {
    public int compareStudentByScore(Student student1,Student student2){
        return student2.getScore() - student1.getScore();
    }
}
