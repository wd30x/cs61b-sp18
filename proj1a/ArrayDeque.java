import java.util.Arrays;

public class ArrayDeque<T> {
    T[] items;
    int size;
    int nextFirstIndex;
    int nextLastIndex;

    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.printDeque();
        System.out.println();
        System.out.println(deque.size);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        deque.addLast(9);
        System.out.println(deque.nextFirstIndex);
        //deque.removeLast();
        deque.removeFirst();
        deque.printDeque();
    }

    /**
     * Creates an empty array deque
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirstIndex = 0;
        nextLastIndex = 1;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if (getLoadFactor() >= 0.5) {
            resize(items.length * 2);
            nextFirstIndex += items.length / 2;
        }
        items[nextFirstIndex] = item;
        nextFirstIndex--;
        if (nextFirstIndex < 0) {
            nextFirstIndex = items.length - 1;
        }
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        if (getLoadFactor() >= 0.5) {
            resize(items.length * 2);
            nextFirstIndex += items.length / 2;
        }
        items[nextLastIndex] = item;
        nextLastIndex++;
        if (nextLastIndex > items.length - 1) {
            nextLastIndex = 0;
        }
        size++;
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
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (nextFirstIndex == items.length - 1) {
            nextFirstIndex = -1;
        }
        T item = this.get(nextFirstIndex + 1);
        items[nextFirstIndex + 1] = null;
        size--;
        nextFirstIndex++;
        return item;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (nextLastIndex == 0) {
            nextLastIndex = items.length;
        }
        T item = this.get(nextLastIndex - 1);
        items[nextLastIndex - 1] = null;
        size--;
        nextLastIndex--;
        return item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists
     * , returns null. Must not alter the deque!
     */
    public T get(int index) {
        return items[index];
    }

    private void resize(int capacity) {
        T[] new1 = (T[]) new Object[capacity / 2];
        T[] new2 = (T[]) new Object[capacity / 2];
        T[] old1 = Arrays.copyOfRange(items, 0, items.length / 2);
        T[] old2 = Arrays.copyOfRange(items, items.length / 2, items.length);
        System.arraycopy(old1, 0, new1, 0, old1.length);
        System.arraycopy(old2, 0, new2, new2.length / 2, old2.length);
        items = concat(new1, new2);
    }

    /**
     * concatenate two arrays
     * source: https://stackoverflow.com/questions/80476/how-can-i-concatenate-two-arrays-in-java
     */
    private static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    /**
     * get the load factor of an deque
     */
    private double getLoadFactor() {
        return ((double) size) / items.length;
    }


}
