public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int first;
    private int last;

    /**
     * Creates an empty array deque
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        first = 0;
        last = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if (isEmpty()) {
            first = 0;
            items[first] = item;
            size++;
            return;
        }
        if (first != 0) {
            first--;
            items[first] = item;
        } else {
            first = items.length - 1;
            items[first] = item;
        }
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        if (isEmpty()) {
            last = 0;
            items[last] = item;
            size++;
            return;
        }
        if (last != items.length - 1) {
            last++;
            items[last] = item;
        } else {
            last = 0;
            items[last] = item;
        }
        size++;
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T rem = items[first];
        items[first] = null;
        if (first == items.length - 1) {
            first = 0;
        } else if (first != 0) {
            first++;
        }
        size--;
        return rem;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T rem = items[last];
        items[last] = null;
        if (last == 0 && getLoadFactor() > 0.8) {
            last = items.length - 1;
        } else if (last != 0) {
            last--;
        }
        size--;
        return rem;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        for (int i = 0; i < items.length; i++) {
            System.out.print(items[i] + " ");
        }
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        return items[index];
    }

    /**
     * Double the size of the deque if it is almost full(80%)
     */
    private void resizeUp(int capacity) {
        if (getLoadFactor() > 0.8) {

        }
    }

    /**
     * Halve the size of the deque if it is almost empty(25%)
     */
    private void resizeDown(int capacity) {
        if (getLoadFactor() < 0.25) {

        }
    }

    /**
     * get the load factor of an deque
     */
    private double getLoadFactor() {
        return ((double) size) / items.length;
    }

}
