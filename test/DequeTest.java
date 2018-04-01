import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {
    @Test
    void isEmpty_empty() {
        Deque deque = new Deque();
        assertTrue(deque.isEmpty());
    }

    @Test
    void isEmpty_notEmpty() {
        Deque<Integer> deque = new Deque();
        deque.addFirst(1);
        assertFalse(deque.isEmpty());
    }

    @Test
    void size_empty() {
        Deque deque = new Deque();
        assertEquals(0, deque.size());
    }

    @Test
    void size_notEmpty() {
        Deque<Integer> deque = new Deque();
        deque.addFirst(1);
        assertEquals(1, deque.size());
    }

    @Test
    void addFirst_null() {
        Deque<Integer> deque = new Deque();
        assertThrows(IllegalArgumentException.class, () -> {
            deque.addFirst(null);
        });
    }

    @Test
    void addLast_null() {
        Deque<Integer> deque = new Deque();
        assertThrows(IllegalArgumentException.class, () -> {
            deque.addLast(null);
        });
    }

    @Test
    void removeFirst_empty() {
        Deque<Integer> deque = new Deque();
        assertThrows(NoSuchElementException.class, deque::removeFirst);
    }

    @Test
    void removeFirst_notEmpty() {
        Deque<String> deque = new Deque();
        deque.addFirst("first");
        assertEquals("first", deque.removeFirst());
        assertTrue(deque.isEmpty());
    }

    @Test
    void removeLast_empty() {
        Deque<Integer> deque = new Deque();
        assertThrows(NoSuchElementException.class, deque::removeLast);
    }

    @Test
    void removeLast_notEmpty() {
        Deque<String> deque = new Deque();
        deque.addLast("last");
        assertEquals("last", deque.removeLast());
        assertTrue(deque.isEmpty());
    }

    @Test
    void iterator() {
        Deque<String> deque = new Deque();
        deque.addFirst("test1");
        deque.addFirst("test2");
        deque.addFirst("test3");
        Iterator<String> iterator = deque.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        assertEquals(3, count);
    }

    @Test
    void iterator_remove() {
        Deque<Integer> deque = new Deque();
        Iterator<Integer> iterator = deque.iterator();
        assertThrows(UnsupportedOperationException.class, iterator::remove);
    }

    @Test
    void iterator_hasNext_empty() {
        Deque<Integer> deque = new Deque();
        Iterator<Integer> iterator = deque.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    void iterator_next_empty() {
        Deque<Integer> deque = new Deque();
        Iterator<Integer> iterator = deque.iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void iterator_next_noMoreElements() {
        Deque<String> deque = new Deque();
        deque.addFirst("test");
        Iterator<String> iterator = deque.iterator();
        assertEquals("test", iterator.next());
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}