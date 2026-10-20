package dsa.graphs;

/** Dynamic connectivity under additions. No edge deletion or path reconstruction. */
public final class DisjointSet {
    private final int[] parent;
    private final int[] size;
    private int components;

    public DisjointSet(int count) {
        if (count < 0) throw new IllegalArgumentException("negative element count");
        parent = new int[count];
        size = new int[count];
        components = count;
        for (int i = 0; i < count; i++) { parent[i] = i; size[i] = 1; }
    }

    /** Path compression: amortized O(alpha(n)) over a sequence of operations. */
    public int find(int element) {
        check(element);
        int root = element;
        while (root != parent[root]) root = parent[root];
        while (element != root) {
            int next = parent[element];
            parent[element] = root;
            element = next;
        }
        return root;
    }

    /** Returns false if already connected; union by size keeps trees shallow. */
    public boolean union(int first, int second) {
        int a = find(first);
        int b = find(second);
        if (a == b) return false;
        if (size[a] < size[b]) { int swap = a; a = b; b = swap; }
        parent[b] = a;
        size[a] += size[b];
        components--;
        return true;
    }
    public boolean connected(int first, int second) { return find(first) == find(second); }
    public int componentSize(int element) { return size[find(element)]; }
    public int components() { return components; }
    private void check(int element) {
        if (element < 0 || element >= parent.length) throw new IndexOutOfBoundsException("element: " + element);
    }
    public static void main(String[] args) {
        DisjointSet network = new DisjointSet(5);
        network.union(0, 1); network.union(1, 2); network.union(3, 4);
        System.out.println("0 and 2 connected: " + network.connected(0, 2));
        System.out.println("Networks remaining: " + network.components());
    }
}
