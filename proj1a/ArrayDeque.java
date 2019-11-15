public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int first;
    private int last;

//    public static void main(String[] args) {
//        ArrayDeque<Integer> d = new ArrayDeque<>();
//        d.addFirst(1);
//        d.addFirst(2);
//        d.addFirst(3);
//        d.addFirst(4);
//        d.addFirst(5);
//        d.addFirst(6);
//        d.addLast(10);
//        d.addLast(11);
//        d.removeFirst();
//        d.removeFirst();
//        d.removeFirst();
//        d.removeFirst();
//        d.removeFirst();
//        d.removeFirst();
//        d.removeFirst();
//        d.removeFirst();
//        d.addFirst(1);
//        d.addFirst(2);
//        d.addFirst(3);
//        d.addFirst(4);
//        d.addFirst(5);
//        d.addFirst(6);
//        d.addFirst(7);
//        d.addFirst(8);
//        int i = d.get(0);

//        d.addLast(0);
//        d.get(0);
//        d.removeFirst();
//        d.addLast(3);
//        d.removeLast();
//        d.addLast(5);
//        d.removeLast();
//        d.addLast(7);
//        d.addFirst(8);
//        d.addFirst(9);
//        d.removeLast();
//        d.addFirst(11);
//        d.removeFirst();
//        d.removeFirst();
//        d.addLast(14);
//        d.removeLast();
//        d.addFirst(16);
//        d.removeFirst();
//        d.get(0);
//
//        d.addLast(0);
//        d.removeFirst();//==> 0
//        d.addLast(2);
//        d.removeFirst(); //==> 2
//        d.addFirst(4);
//        d.addLast(5);
//        d.removeFirst();  //==> 4
//        d.addLast(7);
//        d.addLast(8);
//        d.removeFirst();    //==> 5
//    }

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
            last = 0;
        } else if (first == 0) {
            first = items.length - 1;
        } else {
            first--;
        }
        items[first] = item;
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        if (isEmpty()) {
            last = 0;
            first = 0;
        } else if (last == items.length - 1) {
            last = 0;
        } else {
            last++;
        }
        items[last] = item;
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
        if (size == 1) {
            first = 0;
            last = 0;
        } else if (first == items.length - 1) {
            first = 0;
        } else {
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

        if (size == 1) {
            first = 0;
            last = 0;
        } else if (last == 0) {
            last = items.length - 1;
        } else {
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
     * Returns true if deque is full, false otherwise.
     */
    public boolean isFull() {
        return size == items.length - 1;
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
