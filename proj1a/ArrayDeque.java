public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /**
     * Creates an empty array deque
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    /**
     * Creates a deep copy of other
     **/
    public ArrayDeque(ArrayDeque other) {

    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        items[nextFirst] = item;

        size++;
        nextFirst--;
        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        }
        //resize
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        items[nextLast] = item;

        size++;
        nextLast++;
        if (nextLast == items.length) {
            nextLast = 0;
        }
        //resize
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        T toRemove = get(0);
        if (nextFirst == items.length - 1) {
            items[0] = null;
        } else {
            items[nextFirst + 1] = null;
        }

        size--;
        nextFirst++;
        if (nextFirst == items.length) {
            nextFirst = 0;
        }
        return toRemove;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        T toRemove = get(size - 1);
        if (nextLast == 0) {
            items[items.length - 1] = null;
        } else {
            items[nextLast - 1] = null;
        }

        size--;
        nextLast--;
        if (nextLast == -1) {
            nextFirst = items.length - 1;
        }
        return toRemove;
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
        if (index >= size) {
            return null;
        }
        int mod = (nextFirst + 1 + index) % items.length;
        return items[mod];
    }

    /**
     * get the load factor of an deque
     */
    private double getLoadFactor() {
        return ((double) size) / items.length;
    }

}
