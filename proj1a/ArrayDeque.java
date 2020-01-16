public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public static void main(String[] args) {
        ArrayDeque<Integer> d = new ArrayDeque<>();
        d.addFirst(0);
        d.removeLast(); //==> 0
        d.addLast(2);
        d.removeLast(); //==> 2
        d.addLast(4);
        d.removeLast(); //==> 4
        d.addFirst(6);
        d.removeFirst(); //==> 6
        d.addLast(8);
        d.addFirst(9);
        d.addLast(10);
        d.get(2);//==> 10
        d.addLast(12);
        d.addFirst(13);
        d.addFirst(14);
        d.addLast(15);
        d.removeLast();   //==> 15
        d.addFirst(17);
        d.addFirst(18);
        d.addLast(19);
        d.get(6); //==> 10
        d.removeFirst();  //==> 18
    }

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
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        items[nextFirst] = item;

        size++;
        nextFirst--;
        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        }
        resize();
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
        resize();
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

        if (size > 0) {
            size--;
        }
        nextFirst++;
        if (nextFirst == items.length) {
            nextFirst = 0;
        }
        resize();
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

        if (size > 0) {
            size--;
        }
        nextLast--;
        if (nextLast == -1) {
            nextLast = items.length - 1;
        }
        resize();
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

    /**
     * resize the array
     **/
    private void resize() {
        if (getLoadFactor() > 0.75) {
            T[] arr = (T[]) new Object[items.length * 2];
        }
        if (items.length >= 16) {
            if (getLoadFactor() < 0.25) {
                T[] arr = (T[]) new Object[items.length / 2];
            }
        }
    }

    /**
     * expand the array
     **/
    private void resizeUp() {
        T[] big = (T[]) new Object[items.length * 2];
        if (items[0] == null && items[items.length - 1] == null) {
            System.arraycopy(items, nextFirst + 1, big, 0, size);
        } else {
            System.arraycopy(items, nextFirst + 1, big, 0, items.length - nextFirst - 1);
            int len = 0;
            for (int i = 0; i < big.length; i++) {
                if (big[i] != null) {
                    len++;
                }
            }
            System.arraycopy(items, 0, big, len, nextLast);
        }
        items = big;

        nextFirst = big.length - 1;
        nextLast = size;
    }

    /**
     * shrink the array
     **/
    private void resizeDown() {
        T[] small = (T[]) new Object[items.length / 2];
        if (items[0] == null && items[items.length - 1] == null) {
            System.arraycopy(items, nextFirst + 1, small, 0, size);
        } else {
            System.arraycopy(items, nextFirst + 1, small, 0, items.length - nextFirst - 1);
            int len = 0;
            for (int i = 0; i < small.length; i++) {
                if (small[i] != null) {
                    len++;
                }
            }
            System.arraycopy(items, 0, small, len, nextLast);
        }
        items = small;

        nextFirst = small.length - 1;
        nextLast = size;
    }
}
