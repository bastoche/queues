import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> head;
    private Node<Item> tail;

    private int size;

    public Deque() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        checkNotNull(item);
        Node<Item> newItem = new Node<>(item);
        if (isEmpty()) {
            head = newItem;
            tail = newItem;
        } else {
            Node<Item> oldHead = head;
            head = newItem;
            Node.connect(head, oldHead);
        }
        size += 1;
    }

    public void addLast(Item item) {
        checkNotNull(item);
        Node<Item> newItem = new Node<>(item);
        if (isEmpty()) {
            head = newItem;
            tail = newItem;
        } else {
            Node<Item> oldTail = tail;
            tail = newItem;
            Node.connect(oldTail, tail);
        }
        size += 1;
    }

    private void checkNotNull(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    public Item removeFirst() {
        checkIsEmpty();
        size -= 1;
        Node<Item> oldHead = head;
        head = head.next;
        if (isEmpty()) {
            tail = null;
        } else {
            head.previous = null;
        }
        return oldHead.item;

    }

    public Item removeLast() {
        checkIsEmpty();
        size -= 1;
        Node<Item> oldTail = tail;
        tail = tail.previous;
        if (isEmpty()) {
            head = null;
        } else {
            tail.next = null;
        }
        return oldTail.item;
    }

    private void checkIsEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private Node<Item> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Item next() {
                if (node == null) {
                    throw new NoSuchElementException();
                }
                Item item = node.item;
                node = node.next;
                return item;
            }
        };
    }

    private static class Node<Item> {
        final Item item;

        Node<Item> previous;
        Node<Item> next;


        private Node(Item item) {
            this.item = item;
        }

        static <T> void connect(Node<T> from, Node<T> to) {
            from.next = to;
            to.previous = from;
        }
    }
}