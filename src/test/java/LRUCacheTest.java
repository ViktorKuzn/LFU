
import org.junit.Test;

import static org.junit.Assert.*;

public class LRUCacheTest {

    private LRUCache lruCache;

    public LRUCacheTest() {
        this.lruCache = new LRUCache(2);
    }

    @Test
    public void testCacheEmpty() {
        assertEquals(lruCache.get(1), -1);
    }

    @Test
    public void testSetBelowCapacity() {
        lruCache.set(1, 1);
        assertEquals(lruCache.get(1), 1);
        assertEquals(lruCache.get(2), -1);
        lruCache.set(2, 4);
        assertEquals(lruCache.get(1), 1);
        assertEquals(lruCache.get(2), 4);
    }

    @Test
    public void testCapacityReachedOldestRemoved() {
        lruCache.set(1, 1);
        lruCache.set(2, 5);
        lruCache.set(3, 8);

        assertEquals(lruCache.get(2), 5);
        assertEquals(lruCache.get(3), 8);
        assertEquals(lruCache.get(1), -1);
    }

    @Test
    public void testGetRenewsEntry() {
        lruCache.set(1, 1);
        lruCache.set(2, 4);
        assertEquals(lruCache.get(1), 1);
        lruCache.set(3, 9);
        assertEquals(lruCache.get(1), 1);
        assertEquals(lruCache.get(2), -1);
        assertEquals(lruCache.get(3), 9);
    }

}
