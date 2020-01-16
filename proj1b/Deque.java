public interface Deque<Item> {
    public void addFirst(Item x);

    public void addLast(Item x);

    public boolean isEmpty();

    public int size();

    public void printDeque();

    public Item removeFirst();

    public Item removeLast();

    public Item get(int index);
}
