package com.becooni.todoapp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StringTest {

    @Test
    public void replace() {
        String literal = "string";

        String replaced = literal.replaceAll("", "");

        assertThat(literal.equals(replaced), is(true));
    }

    @Test
    public void replaceEquals() {
        String literal = "string";

        String replaced = literal.replaceAll("", "");

        assertThat(literal == replaced, is(false));
    }

    @Test
    public void toUpperCase() {
        String literal = "string";

        String upper = literal.toUpperCase();

        assertThat(literal == upper, is(false));
    }

    @Test
    public void equalsTest() {
        String s1 = "abc";
        String s2 = new String("abc");

        boolean result = s1 == s2;
        assertThat(result, is(false));

        result = s1.equals(s2);
        assertThat(result, is(true));

        s2.intern();
        result = s1 == s2;
        assertThat(result, is(false));

        List<String> a = new ArrayList();

        a.subList(0, 0);
    }
}
