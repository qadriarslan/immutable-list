package com.qadri.list;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Java implementation of the Immutable linked list.
 * 
 * @author arslanqadri
 *
 * @param <T>
 */
public class ImmutableLinkedList<T> {
    private final Optional<T> head;
    private final ImmutableLinkedList<T> tail;

    public ImmutableLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public ImmutableLinkedList(final T value) {
        this.head = Optional.ofNullable(value);
        this.tail = new ImmutableLinkedList<>();
    }

    public ImmutableLinkedList(final T value, final ImmutableLinkedList<T> tail) {
        if (tail == null) {
            throw new RuntimeException("Tail can't be null. Use another constructor.");
        }
        this.head = Optional.ofNullable(value);
        this.tail = tail;
    }

    /**
     * Method to return the head element of the list.
     * 
     * @return Head of the list.
     */
    public T head() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty.");
        }
        return head.orElse(null);
    }

    /**
     * Method to return the tail of the list.
     * 
     * @return Tail of the list.
     */
    public ImmutableLinkedList<T> tail() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty.");
        }
        return tail;
    }

    /**
     * Method to return a new list with the given value added in the beginning.
     * 
     * @param value
     *            Value to be added in the list
     * @return Another list with the given value added at the head.
     */
    public ImmutableLinkedList<T> cons(T value) {
        return new ImmutableLinkedList<>(value, this);
    }

    /**
     * Method to return another list without first n elements of the original
     * list.
     * 
     * @param n
     *            Number of elements not needed from the original list.
     * @return Another list without first 'n' elements of the original list.
     */
    public ImmutableLinkedList<T> drop(int n) {
        if (n < 0) {
            throw new RuntimeException("Invalid param for drop.");
        } else if (n == 0) {
            return this;
        }
        return tail().drop(n - 1);
    }

    /**
     * Method to return a new list having the elements in the reversed order.
     * 
     * @return Another list containing the elements in the reversed order than
     *         the original one.
     */
    public ImmutableLinkedList<T> reverse() {
        return reverse(new ImmutableLinkedList<>());
    }

    private ImmutableLinkedList<T> reverse(ImmutableLinkedList<T> stagingList) {
        if (isEmpty()) {
            return stagingList;
        }
        return tail().reverse(stagingList.cons(head()));
    }

    /**
     * Method to filter the elements of the list by the given filter.
     * 
     * @param filterBy
     *            Filter to apply on the list.
     * @return A new list containing only the elements that satisfy the filterBy
     *         criteria.
     */
    public ImmutableLinkedList<T> filter(Predicate<T> filterBy) {
        ImmutableLinkedList<T> filteredList;
        if (isEmpty()) {
            filteredList = this;
        }
        else if (head.isPresent() && filterBy.test(head.get())) {
            filteredList = new ImmutableLinkedList<T>(head.get(), tail.filter(filterBy));
        } else {
            filteredList = tail.filter(filterBy);
        }
        return filteredList;
    }

    /**
     * Method to apply a given mapping function on the list elements.
     * 
     * @param mapBy
     *            Mapping function to be applied on the list elements.
     * @return A new list containing the elements obtained by applying the
     *         mapping function.
     */
    public <R> ImmutableLinkedList<R> map(Function<T, R> mapBy) {
        ImmutableLinkedList<R> mappedList;
        if (isEmpty()) {
            mappedList = new ImmutableLinkedList<R>();
        } else if (head.isPresent()) {
            mappedList = new ImmutableLinkedList<R>(mapBy.apply(head.get()), tail.map(mapBy));
        } else {
            mappedList = tail.map(mapBy);
        }
        return mappedList;
    }

    /**
     * Method to check if the list is empty or not.
     * 
     * @return <code>true</code> if the list is empty, <code>false</code>
     *         otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((head == null) ? 0 : head.hashCode());
        result = prime * result + ((tail == null) ? 0 : tail.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        @SuppressWarnings("unchecked")
        ImmutableLinkedList<T> other = (ImmutableLinkedList<T>) obj;
        if (head == null) {
            if (other.head != null)
                return false;
        } else if (!head.equals(other.head))
            return false;
        if (tail == null) {
            if (other.tail != null)
                return false;
        } else if (!tail.equals(other.tail))
            return false;
        return true;
    }
}
