import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LFUCacheTest {
    @Test
    public void test() {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1,1);
        lfuCache.put(2,2);
        lfuCache.put(3,3);
        lfuCache.put(4,4);

        assertEquals(lfuCache.get(1), 1);
        assertEquals(lfuCache.get(2), -1);
        assertEquals(lfuCache.get(4), 4);
    }
}
