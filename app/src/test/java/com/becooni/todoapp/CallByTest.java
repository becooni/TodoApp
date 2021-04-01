package com.becooni.todoapp;

import androidx.annotation.NonNull;

import org.junit.Test;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CallByTest {

    @Test
    public void primitive() {
        int a = 1;
        int b = 2;

        String result = a + " " + b;
        assertThat(result, is("1 2"));

        swap(a, b);
        result = a + " " + b;
        assertThat(result, is("1 2"));
    }

    private void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    @Test
    public void reference() {
        Person person = new Person("jake");

        String result = person.getName();
        assertThat(result, is("jake"));

        callByValue(person);
        result = person.getName();
        assertThat(result, is("dave"));

        callByReference(person);
        result = person.getName();
        assertThat(result, is("dave"));
    }

    private void callByValue(Person person) {
        person.setName("dave");
    }

    private void callByReference(Person person) {
        person = new Person("ref");
    }

    @Test
    public void referenceCopy() {
        Person p1 = new Person("p1");
        Person p2 = new Person("p2");

        String result = p1.getName() + " " + p2.getName();
        assertThat(result, is("p1 p2"));

        swap(p1, p2);
        result = p1.getName() + " " + p2.getName();
        assertThat(result, is("p1 p2"));

        swapField(p1, p2);
        result = p1.getName() + " " + p2.getName();
        assertThat(result, is("p2 p1"));

//        p1.equals(p2);
//        p1.hashCode();
//        p1.toString();
//        p1.getClass();
//
//        try {
//            p1.wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        p1.notify();
//        p1.notifyAll();
    }

    private void swap(Person p1, Person p2) {
        Person temp = p1;
        p1 = p2;
        p2 = temp;
    }

    private void swapField(Person p1, Person p2) {
        String temp = p1.getName();
        p1.setName(p2.getName());
        p2.setName(temp);
    }
}

class Person {

    private String name;

    private Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
