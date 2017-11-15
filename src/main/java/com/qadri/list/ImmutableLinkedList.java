package com.qadri.list;

/**
 * Java implementation of the Immutable linked list.
 * @author arslanqadri
 *
 * @param <T>
 */
public class ImmutableLinkedList<T> {
    private final T head;
    private final ImmutableLinkedList<T> tail;

    public ImmutableLinkedList() {
        this(null, null);
    }

    public ImmutableLinkedList(T head) {
        this(head, null);
    }

    public ImmutableLinkedList(T head, ImmutableLinkedList<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    /**
     * Method to return the head element of the list.
     * 
     * @return Head of the list.
     */
    public T head() {
        return head;
    }

    /**
     * Method to return the tail of the list.
     * 
     * @return Tail of the list.
     */
    public ImmutableLinkedList<T> tail() {
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

    private boolean isEmpty() {
        return head == null && tail == null;
    }

    @Override
    public String toString() {
        return "[" + (head != null ? head : "") + (tail != null ? ", " + tail : "") + "]";
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
