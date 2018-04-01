import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;

    private Item[] array;

    public RandomizedQueue() {
        resize(1);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == array.length) {
            resize(array.length * 2);
        }
        array[size++] = item;
    }

    public Item dequeue() {
        checkNotEmpty();
        if (size <= array.length / 4) {
            resize(array.length / 2);
        }
        int index = getRandomIndex();
        Item result = array[index];
        array[index] = array[--size];
        array[size] = null;
        return result;
    }

    public Item sample() {
        checkNotEmpty();
        return array[getRandomIndex()];
    }

    private int getRandomIndex() {
        return StdRandom.uniform(size);
    }

    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    private void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        copy(array, newArray);
        array = newArray;
    }

    private void copy(Item[] from, Item[] to) {
        if (from != null) {
            for (int i = 0; i < from.length && i < to.length; i++) {
                to[i] = from[i];
            }
        }
    }

    public Iterator<Item> iterator() {
        Item[] randomizedArray = (Item[]) new Object[size];
        copy(array, randomizedArray);
        StdRandom.shuffle(randomizedArray);

        return new Iterator<Item>() {
            private Item[] array = randomizedArray;
            private int index;

            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[index++];
            }
        };
    }
}