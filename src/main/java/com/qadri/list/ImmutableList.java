package com.qadri.list;

public class ImmutableList<T> {
    private Link head;

    // head - Returns the first element of a list.
    // tail - Returns a new list with all elements of the original list except
    // the first.
    // cons - Takes an argument and prepends it to the list.
    // drop - Takes an integer ‘n’ as argument and returns a new list after
    // removing first n elements from the list.
    // reverse - Returns the reverse of a list

    /**
     * Method to return the head element of the list.
     * 
     * @return
     */
    public T head() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty.");
        }
        return head.value;
    }

    /**
     * Method to return a new list with all elements except the head of the
     * original list.
     * 
     * @return
     */
    public ImmutableList<T> tail() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty.");
        }
        ImmutableList<T> newList = new ImmutableList<>();
        newList.head = clone(head.next);
        return newList;
    }

    /**
     * Method to return a new list with the given value added in the beginning.
     * 
     * @param value
     *            Value to be added in the list
     * @return Another list with the given value added at the head.
     */
    public ImmutableList<T> cons(T value) {
        ImmutableList<T> newList = new ImmutableList<>();
        newList.head = clone(head);
        newList.add(value);
        return newList;
    }

    /**
     * Method to return another list without first n elements of the original
     * list.
     * 
     * @param n
     *            Number of elements not needed from the original list.
     * @return Another list without first 'n' elements of the original list.
     */
    public ImmutableList<T> drop(int n) {
        if (n < 0) {
            throw new RuntimeException("Invalid param for drop.");
        }
        if (n > 0) {
            return tail().drop(n - 1);
        }
        ImmutableList<T> newList = new ImmutableList<>();
        newList.head = clone(head);
        return newList;
    }

    /**
     * Method to return a new list having the elements in the reversed order.
     * 
     * @return Another list containing the elements in the reversed order than
     *         the original one.
     */
    public ImmutableList<T> reverse() {
        return reverse(new ImmutableList<>());
    }

    private boolean isEmpty() {
        return head == null;
    }

    private Link clone(Link node) {
        if (node == null) {
            return null;
        }
        Link clone = new Link(node.value);
        clone.next = clone(node.next);
        return clone;
    }

    private void add(T value) {
        Link node = new Link(value);
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    private ImmutableList<T> reverse(ImmutableList<T> newList) {
        if (isEmpty()) {
            return newList;
        }
        newList.add(head());
        return tail().reverse(newList);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((head == null) ? 0 : head.hashCode());
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
        ImmutableList<T> other = (ImmutableList<T>) obj;
        if (head == null) {
            if (other.head != null)
                return false;
        } else if (!head.equals(other.head))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ImmutableList [" + (head != null ? head : "") + "]";
    }

    class Link {
        T value;
        Link next;

        Link(T value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((next == null) ? 0 : next.hashCode());
            result = prime * result + ((value == null) ? 0 : value.hashCode());
            return result;
        }

        @Override
        public String toString() {
            return "Link [" + (value != null ? value : "") + (next != null ? ", " + next : "") + "]";
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
            Link other = (Link) obj;
            if (next == null) {
                if (other.next != null)
                    return false;
            } else if (!next.equals(other.next))
                return false;
            if (value == null) {
                if (other.value != null)
                    return false;
            } else if (!value.equals(other.value))
                return false;
            return true;
        }
    }
}
