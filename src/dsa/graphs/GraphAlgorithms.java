package dsa.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

/** Directed adjacency lists; add both directions to represent an undirected edge. */
public final class GraphAlgorithms {
    private GraphAlgorithms() { }

    /** Nonnegative travel cost; Dijkstra does not accept negative edge weights. */
    public record Edge(int to, int weight) {
        public Edge {
            if (weight < 0) throw new IllegalArgumentException("weight must be nonnegative");
        }
    }
    private record State(int vertex, long distance) { }

    /** O(V + E) time, O(V) extra space. Unreachable vertices have distance -1. */
    public static int[] bfsDistances(int[][] graph, int source) {
        validate(graph);
        checkVertex(source, graph.length);
        int[] distance = new int[graph.length];
        Arrays.fill(distance, -1);
        Deque<Integer> queue = new ArrayDeque<>();
        distance[source] = 0;
        queue.addLast(source);
        while (!queue.isEmpty()) {
            int vertex = queue.removeFirst();
            for (int next : graph[vertex]) {
                if (distance[next] == -1) {
                    // FIFO processes every distance-d vertex before distance d+1.
                    distance[next] = distance[vertex] + 1;
                    queue.addLast(next); // Mark on enqueue, so each vertex enters once.
                }
            }
        }
        return distance;
    }

    /** Iterative DFS of reachable vertices. O(V + E) time, O(V) extra space. */
    public static List<Integer> dfs(int[][] graph, int source) {
        validate(graph);
        checkVertex(source, graph.length);
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];
        // A frame remembers the next neighbor, just like a recursive call would.
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{source, 0});
        visited[source] = true;
        order.add(source);
        while (!stack.isEmpty()) {
            int[] frame = stack.peek();
            int vertex = frame[0];
            if (frame[1] == graph[vertex].length) { stack.pop(); continue; }
            int next = graph[vertex][frame[1]++];
            if (!visited[next]) {
                visited[next] = true;
                order.add(next);
                stack.push(new int[]{next, 0});
            }
        }
        return order;
    }

    /** Kahn's algorithm; throws for a directed cycle. O(V + E) time, O(V) space. */
    public static List<Integer> topologicalOrder(int[][] graph) {
        validate(graph);
        int[] indegree = new int[graph.length];
        for (int[] neighbors : graph) for (int next : neighbors) indegree[next]++;
        Deque<Integer> ready = new ArrayDeque<>();
        for (int v = 0; v < graph.length; v++) if (indegree[v] == 0) ready.addLast(v);
        List<Integer> order = new ArrayList<>();
        while (!ready.isEmpty()) {
            int vertex = ready.removeFirst();
            order.add(vertex);
            for (int next : graph[vertex]) {
                // Remove the outgoing dependency; zero means all prerequisites done.
                if (--indegree[next] == 0) ready.addLast(next);
            }
        }
        if (order.size() != graph.length) throw new IllegalArgumentException("directed cycle");
        return order;
    }

    /**
     * Nonnegative weighted shortest paths. Long.MAX_VALUE means unreachable.
     * Lazy heap: O(V + E log(E + 1)) time, O(V + E) extra space, including validation.
     * Edge weights are int, while path sums use long to avoid int overflow.
     */
    public static long[] dijkstra(List<List<Edge>> graph, int source) {
        Objects.requireNonNull(graph, "graph");
        // Accept any List implementation while keeping indexed vertex lookup O(1).
        // This copies the outer container only; neighbor lists are traversed in order.
        graph = new ArrayList<>(graph);
        for (List<Edge> edges : graph) {
            Objects.requireNonNull(edges, "neighbor list");
            for (Edge edge : edges) {
                Objects.requireNonNull(edge, "edge");
                checkVertex(edge.to(), graph.size());
            }
        }
        checkVertex(source, graph.size());
        long[] distance = new long[graph.size()];
        Arrays.fill(distance, Long.MAX_VALUE);
        PriorityQueue<State> heap = new PriorityQueue<>(Comparator.comparingLong(State::distance));
        distance[source] = 0;
        heap.offer(new State(source, 0));
        while (!heap.isEmpty()) {
            State current = heap.poll();
            if (current.distance() != distance[current.vertex()]) continue; // Stale entry.
            for (Edge edge : graph.get(current.vertex())) {
                long candidate = current.distance() + edge.weight();
                if (candidate < distance[edge.to()]) {
                    distance[edge.to()] = candidate;
                    heap.offer(new State(edge.to(), candidate));
                }
            }
        }
        return distance;
    }

    private static void validate(int[][] graph) {
        Objects.requireNonNull(graph, "graph");
        for (int[] neighbors : graph) {
            Objects.requireNonNull(neighbors, "neighbor array");
            for (int vertex : neighbors) checkVertex(vertex, graph.length);
        }
    }
    private static void checkVertex(int vertex, int size) {
        if (vertex < 0 || vertex >= size) throw new IndexOutOfBoundsException("vertex: " + vertex);
    }

    public static void main(String[] args) {
        int[][] dependencies = {{1, 2}, {3}, {3}, {}, {}};
        System.out.println("Hops from 0: " + Arrays.toString(bfsDistances(dependencies, 0)));
        System.out.println("DFS: " + dfs(dependencies, 0));
        System.out.println("Build order: " + topologicalOrder(dependencies));
        List<List<Edge>> roads = List.of(List.of(new Edge(1, 8), new Edge(2, 2)),
                List.of(new Edge(3, 1)), List.of(new Edge(1, 1)), List.of());
        System.out.println("Travel costs: " + Arrays.toString(dijkstra(roads, 0)));
    }
}
