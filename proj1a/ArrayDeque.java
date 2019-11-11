public class ArrayDeque<T> {
    T[] items;
    int size;

    /**
     * Creates an empty array deque
     */
    public ArrayDeque() {
        items = (T[]) new Object();
        size = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {

    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {

    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return true;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return 0;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {

    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        return null;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        return null;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists
     * , returns null. Must not alter the deque!
     */
    public T get(int index) {
        return null;
    }
}
