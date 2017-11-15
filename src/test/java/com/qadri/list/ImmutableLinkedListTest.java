package com.qadri.list;

import org.junit.Assert;
import org.junit.Test;

public class ImmutableLinkedListTest {

    @Test
    public void listCreation1Test() {
        ImmutableLinkedList<String> list = new ImmutableLinkedList<>();
        Assert.assertNotNull(list);
    }

    @Test
    public void listCreation2Test() {
        ImmutableLinkedList<String> list = new ImmutableLinkedList<>("Ashu");
        Assert.assertNotNull(list);
    }

    @Test
    public void listCreation3Test() {
        new ImmutableLinkedList<>("Ashu", null);
    }

    @Test(expected = RuntimeException.class)
    public void listEqualityTest() {
        ImmutableLinkedList<String> list1 = new ImmutableLinkedList<String>().cons("Ashu");
        ImmutableLinkedList<String> list2 = new ImmutableLinkedList<>("Ashu");
        Assert.assertEquals(list1, list2);
    }

    @Test(expected = RuntimeException.class)
    public void listHead1Test() {
        ImmutableLinkedList<String> list = new ImmutableLinkedList<>();
        list.head();
    }

    @Test
    public void listHead2Test() {
        ImmutableLinkedList<String> list = new ImmutableLinkedList<>(null);
        Assert.assertEquals(null, list.head());
    }

    @Test
    public void listHead3Test() {
        ImmutableLinkedList<String> list = new ImmutableLinkedList<>("Ashu");
        Assert.assertEquals("Ashu", list.head());
    }

    @Test(expected = RuntimeException.class)
    public void listTail1Test() {
        ImmutableLinkedList<String> list = new ImmutableLinkedList<>();
        list.tail();
    }

    @Test
    public void listTail2Test() {
        ImmutableLinkedList<String> list = new ImmutableLinkedList<>("Ashu");
        Assert.assertEquals(new ImmutableLinkedList<>(), list.tail());
    }

    @Test
    public void listTail3Test() {
        ImmutableLinkedList<String> list1 = new ImmutableLinkedList<>("Pashu", new ImmutableLinkedList<>("Ashu"));
        ImmutableLinkedList<String> list2 = list1.tail();
        Assert.assertEquals(new ImmutableLinkedList<>("Pashu", new ImmutableLinkedList<>("Ashu")), list1);
        Assert.assertEquals(new ImmutableLinkedList<>("Ashu"), list2);
    }

    @Test
    public void listConsTest() {
        ImmutableLinkedList<String> list1 = new ImmutableLinkedList<>();
        ImmutableLinkedList<String> list2 = list1.cons("Ashu");
        Assert.assertEquals(new ImmutableLinkedList<>(), list1);
        Assert.assertEquals(new ImmutableLinkedList<>("Ashu"), list2);
        Assert.assertEquals(list1, list2.tail());
    }

    @Test(expected = RuntimeException.class)
    public void listDrop1Test() {
        ImmutableLinkedList<String> list = new ImmutableLinkedList<>("Pashu");
        list.drop(-10);
    }

    @Test
    public void listDrop2Test() {
        ImmutableLinkedList<String> list1 = new ImmutableLinkedList<>("Pashu");
        ImmutableLinkedList<String> list2 = list1.drop(0);
        Assert.assertEquals(list1, list2);
    }

    @Test
    public void listDrop3Test() {
        ImmutableLinkedList<String> list1 = new ImmutableLinkedList<>("Pashu", new ImmutableLinkedList<>("Ashu",
                new ImmutableLinkedList<>("Saima", new ImmutableLinkedList<>("Sheeba", new ImmutableLinkedList<>(
                        "Shanu")))));
        ImmutableLinkedList<String> list2 = list1.drop(3);
        Assert.assertEquals(new ImmutableLinkedList<>("Sheeba", new ImmutableLinkedList<>("Shanu")), list2);
    }

    @Test
    public void listReverse1Test() {
        ImmutableLinkedList<String> list1 = new ImmutableLinkedList<>();
        ImmutableLinkedList<String> list2 = list1.reverse();
        Assert.assertEquals(list1, list2);
    }

    @Test
    public void listReverse2Test() {
        ImmutableLinkedList<String> list1 = new ImmutableLinkedList<String>().cons("Shanu").cons("Sheeba")
                .cons("Saima").cons("Ashu").cons("Pashu");
        ImmutableLinkedList<String> list2 = list1.reverse();
        Assert.assertEquals(new ImmutableLinkedList<>("Shanu", new ImmutableLinkedList<>("Sheeba",
                new ImmutableLinkedList<>("Saima",
                        new ImmutableLinkedList<>("Ashu", new ImmutableLinkedList<>("Pashu"))))), list2);
    }

    @Test
    public void listFilter1Test() {
        ImmutableLinkedList<String> list1 = new ImmutableLinkedList<>();
        ImmutableLinkedList<String> list2 = list1.filter(x -> true);
        Assert.assertEquals(list1, list2);
    }

    @Test
    public void listFilter2Test() {
        ImmutableLinkedList<String> list = new ImmutableLinkedList<>(null);
        Assert.assertEquals(new ImmutableLinkedList<>(), list.filter(x -> true));
    }

    @Test
    public void listFilter3Test() {
        ImmutableLinkedList<String> list1 = new ImmutableLinkedList<>("Shanu", new ImmutableLinkedList<>("Sheeba",
                new ImmutableLinkedList<>("Saima",
                        new ImmutableLinkedList<>("Ashu", new ImmutableLinkedList<>("Pashu")))));
        ImmutableLinkedList<String> list2 = list1.filter(x -> x.endsWith("u"));
        Assert.assertEquals(new ImmutableLinkedList<>("Shanu", new ImmutableLinkedList<>("Ashu",
                new ImmutableLinkedList<>("Pashu"))), list2);
    }

    @Test
    public void listMap1Test() {
        ImmutableLinkedList<String> list1 = new ImmutableLinkedList<>();
        ImmutableLinkedList<String> list2 = list1.map(x -> x);
        Assert.assertEquals(list1, list2);
    }

    @Test
    public void listMap2Test() {
        ImmutableLinkedList<String> list = new ImmutableLinkedList<>(null);
        Assert.assertEquals(new ImmutableLinkedList<>(), list.map(x -> x));
    }

    @Test
    public void listMap3Test() {
        ImmutableLinkedList<String> list1 = new ImmutableLinkedList<>("Shanu", new ImmutableLinkedList<>("Sheeba",
                new ImmutableLinkedList<>("Saima")));
        ImmutableLinkedList<String> list2 = list1.map(x -> x.toUpperCase());
        Assert.assertEquals(new ImmutableLinkedList<>("SHANU", new ImmutableLinkedList<>("SHEEBA",
                new ImmutableLinkedList<>("SAIMA"))), list2);
    }

    @Test
    public void listEmptyCheck() {
        ImmutableLinkedList<String> list1 = new ImmutableLinkedList<>();
        Assert.assertEquals(true, list1.isEmpty());
        ImmutableLinkedList<String> list2 = new ImmutableLinkedList<>("Shanu");
        Assert.assertEquals(false, list2.isEmpty());
        ImmutableLinkedList<String> list3 = new ImmutableLinkedList<>("Shanu", new ImmutableLinkedList<>());
        Assert.assertEquals(false, list3.isEmpty());
    }
}
