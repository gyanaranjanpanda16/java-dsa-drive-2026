package dsa.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Educational least-recently-used cache: hash lookup plus a doubly linked list.
 * get/put take expected O(1) time; O(capacity) space. Not thread-safe; no TTL.
 * Null keys/values are rejected, so get returning null unambiguously means miss.
 */
public final class LruCache<K, V> {
    private final class Node {
        K key;
        V value;
        Node previous;
        Node next;
        Node(K key, V value) { this.key = key; this.value = value; }
    }

    private final int capacity;
    private final Map<K, Node> entries = new HashMap<>();
    private final Node head = new Node(null, null);
    private final Node tail = new Node(null, null);

    public LruCache(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("Capacity must be positive");
        this.capacity = capacity;
        head.next = tail;
        tail.previous = head;
    }

    public V get(K key) {
        Node node = entries.get(Objects.requireNonNull(key, "key"));
        if (node == null) return null;
        unlink(node);
        linkMostRecent(node);
        return node.value;
    }

    public void put(K key, V value) {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(value, "value");
        Node node = entries.get(key);
        if (node != null) {
            node.value = value;
            unlink(node);
        } else {
            node = new Node(key, value);
            entries.put(key, node);
        }
        linkMostRecent(node);
        if (entries.size() > capacity) {
            Node oldest = tail.previous;
            unlink(oldest);
            entries.remove(oldest.key);
        }
    }

    public int size() { return entries.size(); }

    /** Snapshot for teaching/testing; O(size) time and output space. */
    public List<K> keysMostRecentFirst() {
        List<K> result = new ArrayList<>();
        for (Node node = head.next; node != tail; node = node.next) result.add(node.key);
        return result;
    }

    private void unlink(Node node) {
        node.previous.next = node.next;
        node.next.previous = node.previous;
    }

    private void linkMostRecent(Node node) {
        node.next = head.next;
        node.previous = head;
        head.next.previous = node;
        head.next = node;
    }

    public static void main(String[] args) {
        LruCache<String, String> cache = new LruCache<>(2);
        cache.put("user-1", "Ada");
        cache.put("user-2", "Grace");
        cache.get("user-1");
        cache.put("user-3", "Linus");
        System.out.println("Most recent first: " + cache.keysMostRecentFirst());
        System.out.println("Evicted user-2: " + cache.get("user-2"));
    }
}
