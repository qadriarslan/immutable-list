package com.qadri.list;

import org.junit.Assert;
import org.junit.Test;

public class ImmutableListTest {

    @Test
    public void listheadTest() {
        ImmutableList<String> list = new ImmutableList<String>().cons("Ashu");
        System.out.println("Head: " + list);
        Assert.assertEquals("Ashu", list.head());
        System.out.println("Head: " + list);
    }

    @Test
    public void listTailTest() {
        ImmutableList<String> list1 = new ImmutableList<String>().cons("Ashu").cons("Pashu");
        ImmutableList<String> list2 = list1.tail();
        System.out.println("Tail: " + list1);
        System.out.println("Tail: " + list2);
        Assert.assertNotSame(list1, list2);
        Assert.assertTrue(list1 != list2);
        Assert.assertTrue(list2 != null);
        Assert.assertEquals("Pashu", list1.head());
        Assert.assertEquals("Ashu", list2.head());
    }

    @Test
    public void listConsTest() {
        ImmutableList<String> list1 = new ImmutableList<>();
        ImmutableList<String> list2 = list1.cons("Arslan");
        System.out.println("Cons: " + list1);
        System.out.println("Cons: " + list2);
        Assert.assertNotSame(list1, list2);
        Assert.assertTrue(list1 != list2);
        Assert.assertTrue(list2 != null);
        Assert.assertEquals("Arslan", list2.head());
    }

    @Test
    public void listDropTest() {
        ImmutableList<String> list1 = new ImmutableList<String>().cons("Shanu").cons("Sheeba").cons("Saima")
                .cons("Ashu").cons("Pashu");
        ImmutableList<String> list2 = list1.drop(3);
        System.out.println("Drop: " + list1);
        System.out.println("Drop: " + list2);
        Assert.assertNotSame(list1, list2);
        Assert.assertTrue(list1 != list2);
        Assert.assertTrue(list2 != null);
        Assert.assertEquals("Pashu", list1.head());
        Assert.assertEquals("Sheeba", list2.head());
    }

    @Test
    public void listReverseTest() {
        ImmutableList<String> list1 = new ImmutableList<String>().cons("Shanu").cons("Sheeba").cons("Saima")
                .cons("Ashu").cons("Pashu");
        ImmutableList<String> list2 = list1.reverse();
        System.out.println("Reverse: " + list1);
        System.out.println("Reverse: " + list2);
        Assert.assertNotSame(list1, list2);
        Assert.assertTrue(list1 != list2);
        Assert.assertTrue(list2 != null);
        Assert.assertEquals("Pashu", list1.head());
        Assert.assertEquals("Shanu", list2.head());
    }
}
