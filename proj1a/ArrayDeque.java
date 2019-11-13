public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirstIndex;
    private int nextLastIndex;

    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(7);
        deque.addFirst(8);
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();

        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        deque.addLast(9);
        deque.addLast(10);
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();

        deque.printDeque();
    }

    /**
     * Creates an empty array deque
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirstIndex = 3;
        nextLastIndex = 4;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        items[nextFirstIndex] = item;
        if (nextFirstIndex > 0) {
            nextFirstIndex--;
        } else {
            nextFirstIndex = items.length - 1;
        }
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        items[nextLastIndex] = item;
        if (nextLastIndex < items.length - 1) {
            nextLastIndex++;
        } else {
            nextLastIndex = 0;
        }
        size++;
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (nextFirstIndex < items.length - 1) {
            nextFirstIndex++;
        } else {
            nextFirstIndex = 0;
        }

        T item = this.get(nextFirstIndex);
        items[nextFirstIndex] = null;

        if (size > 0) {
            size--;
        }
        return item;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (nextLastIndex > 0) {
            nextLastIndex--;
        } else {
            nextLastIndex = items.length - 1;
        }

        T item = this.get(nextLastIndex);
        items[nextLastIndex] = null;

        if (size > 0) {
            size--;
        }
        return item;
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

    private void resize(int capacity) {
    }

    /**
     * get the load factor of an deque
     */
    private double getLoadFactor() {
        return ((double) size) / items.length;
    }

}
