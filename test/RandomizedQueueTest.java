import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class RandomizedQueueTest {
    @Test
    void isEmpty_empty() {
        RandomizedQueue queue = new RandomizedQueue();
        assertTrue(queue.isEmpty());
    }

    @Test
    void isEmpty_notEmpty() {
        RandomizedQueue<String> queue = new RandomizedQueue();
        queue.enqueue("test");
        assertFalse(queue.isEmpty());
    }

    @Test
    void size_empty() {
        RandomizedQueue queue = new RandomizedQueue();
        assertEquals(0, queue.size());
    }

    @Test
    void size_notEmpty() {
        RandomizedQueue<String> queue = new RandomizedQueue();
        queue.enqueue("test1");
        queue.enqueue("test2");
        assertEquals(2, queue.size());
    }

    @Test
    void enqueue_null() {
        RandomizedQueue<String> queue = new RandomizedQueue();
        assertThrows(IllegalArgumentException.class, () -> {
            queue.enqueue(null);
        });
    }

    @Test
    void dequeue_empty() {
        RandomizedQueue<String> queue = new RandomizedQueue();
        assertThrows(NoSuchElementException.class, () -> {
            queue.dequeue();
        });
    }

    @Test
    void dequeue_notEmpty() {
        RandomizedQueue<String> queue = new RandomizedQueue();
        queue.enqueue("test");
        assertEquals("test", queue.dequeue());
    }

    @Test
    void sample_empty() {
        RandomizedQueue<String> queue = new RandomizedQueue();
        assertThrows(NoSuchElementException.class, () -> {
            queue.sample();
        });
    }

    @Test
    void sample_notEmpty() {
        RandomizedQueue<String> queue = new RandomizedQueue();
        queue.enqueue("sample");
        assertEquals("sample", queue.sample());
    }

    @Test
    void iterator_next_empty() {
        RandomizedQueue<String> queue = new RandomizedQueue();
        Iterator<String> iterator = queue.iterator();
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }

    @Test
    void iterator_next_noMoreElements() {
        RandomizedQueue<String> queue = new RandomizedQueue();
        queue.enqueue("test");
        Iterator<String> iterator = queue.iterator();
        assertEquals("test", iterator.next());
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }

    @Test
    void iterator_remove_noMoreElements() {
        RandomizedQueue<String> queue = new RandomizedQueue();
        Iterator<String> iterator = queue.iterator();
        assertThrows(UnsupportedOperationException.class, () -> {
            iterator.remove();
        });
    }

}