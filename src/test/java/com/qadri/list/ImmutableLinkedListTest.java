package com.qadri.list;

import org.junit.Assert;
import org.junit.Test;

public class ImmutableLinkedListTest {

    @Test
    public void listEqualityTest() {
        ImmutableLinkedList<String> list1 = new ImmutableLinkedList<String>().cons("Ashu");
        ImmutableLinkedList<String> list2 = new ImmutableLinkedList<>("Ashu", new ImmutableLinkedList<>());
        Assert.assertEquals(list1, list2);
    }

    @Test
    public void listHeadTest() {
        ImmutableLinkedList<String> list = new ImmutableLinkedList<String>("Ashu");
        Assert.assertEquals("Ashu", list.head());
    }

    @Test
    public void listTailTest() {
        ImmutableLinkedList<String> list1 = new ImmutableLinkedList<>("Pashu", new ImmutableLinkedList<>("Ashu"));
        ImmutableLinkedList<String> list2 = list1.tail();
        Assert.assertEquals(new ImmutableLinkedList<String>("Pashu", new ImmutableLinkedList<String>("Ashu")), list1);
        Assert.assertEquals(new ImmutableLinkedList<>("Ashu"), list2);
    }

    @Test
    public void listConsTest() {
        ImmutableLinkedList<String> list1 = new ImmutableLinkedList<>();
        ImmutableLinkedList<String> list2 = list1.cons("Ashu");
        Assert.assertEquals(new ImmutableLinkedList<>(), list1);
        Assert.assertEquals(new ImmutableLinkedList<>("Ashu", new ImmutableLinkedList<>()), list2);
    }

    @Test
    public void listDropTest() {
        ImmutableLinkedList<String> list1 = new ImmutableLinkedList<>("Pashu", new ImmutableLinkedList<>("Ashu",
                new ImmutableLinkedList<>("Saima", new ImmutableLinkedList<>("Sheeba", new ImmutableLinkedList<>(
                        "Shanu")))));
        ImmutableLinkedList<String> list2 = list1.drop(3);
        Assert.assertEquals(new ImmutableLinkedList<String>("Sheeba", new ImmutableLinkedList<String>("Shanu")), list2);
    }

    @Test
    public void listReverseTest() {
        ImmutableLinkedList<String> list1 = new ImmutableLinkedList<String>().cons("Shanu").cons("Sheeba")
                .cons("Saima").cons("Ashu").cons("Pashu");
        ImmutableLinkedList<String> list2 = list1.reverse();
        Assert.assertEquals(new ImmutableLinkedList<>("Shanu", new ImmutableLinkedList<>("Sheeba",
                new ImmutableLinkedList<>("Saima", new ImmutableLinkedList<>("Ashu", new ImmutableLinkedList<>("Pashu",
                        new ImmutableLinkedList<>()))))), list2);
    }
}
