public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    public class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    /**
     * Creates an empty linked list deque.
     */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     * no loop or recursion
     */
    public void addFirst(T item) {
        Node node = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * no loop or recursion
     */
    public void addLast(T item) {
        Node node = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
        size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return sentinel.next == sentinel && sentinel.prev == sentinel;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return this.size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        Node itr = sentinel.next;
        while (itr != sentinel) {
            System.out.print(itr.item + " ");
            itr = itr.next;
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (!isEmpty()) {
            Node p = sentinel.next;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size--;
            return p.item;
        } else {
            return null;
        }
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (!isEmpty()) {
            Node p = sentinel.prev;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size--;
            return p.item;
        } else {
            return null;
        }
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item
     * exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;

    }

    /**
     * Same as get, but uses recursion.
     */
    public T getRecursive(int index) {
        return getRecursive(sentinel.next, index);
    }

    /**
     * a helper method for recursive get
     */
    private T getRecursive(Node itr, int index) {
        if (index >= size) {
            return null;
        }
        Node p = itr;

        if (index == 0) {
            return p.item;
        }
        if (index > 0) {
            p = p.next;
        }

        return getRecursive(p, index - 1);
    }

}
