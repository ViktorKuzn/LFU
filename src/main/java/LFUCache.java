import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class LFUCache {
    private final LinkedHashMap<Integer, Node> storage;
    private final int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.storage = new LinkedHashMap<>(capacity, 1);
    }

    public int get(Integer key) {
        Node node = storage.get(key);
        if (node == null) {
            return -1;
        }
        return node.incrementFrequency().getValue();
    }

    public void put(Integer key, Integer value) {
        doEvictionIfNeeded(key);
        storage.put(key, new Node(value));
    }

    private void doEvictionIfNeeded(Integer putKey) {
        if (storage.size() < capacity) {
            return;
        }
        long minFrequency = Long.MAX_VALUE;
        Integer keyToRemove = null;
        for (Map.Entry<Integer, Node> entry : storage.entrySet()) {
            if (Objects.equals(entry.getKey(), putKey)) {
                return;
            }
            if (minFrequency >= entry.getValue().getFrequency()) {
                minFrequency = entry.getValue().getFrequency();
                keyToRemove = entry.getKey();
            }
        }
        storage.remove(keyToRemove);
    }

    private class Node {
        private final Integer value;
        private long frequency;

        public Node(Integer value) {
            this.value = value;
            this.frequency = 1;
        }

        public Integer getValue() {
            return value;
        }

        public long getFrequency() {
            return frequency;
        }

        public Node incrementFrequency() {
            ++frequency;
            return this;
        }
    }
}
