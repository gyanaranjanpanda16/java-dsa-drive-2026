package questions.q327; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q327: Find minimum semesters with unlimited parallel courses.
 * Contract: Each course takes one semester; unlimited simultaneous courses; prerequisite edges use valid course IDs; empty course set takes zero semesters.
 * Why: Every course must wait until all predecessor courses finish. Kahn layers schedule it at the earliest possible semester; therefore the number of layers is both a lower bound imposed by prerequisite chains and an attainable schedule.
 * Complexity: O(V+E) time and storage.
 */
public final class Q327MinimumSemesters { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }
    public static int solve(int n,int[][] prerequisites) { // Expose the answer as a reusable method as well as a runnable program.
        List<List<Integer>> g=graph(n,prerequisites,true); // Edge u->v requires u to finish before v starts.
        int[] indegree=new int[n]; for(int[] e:prerequisites) indegree[e[1]]++; // Count unfinished prerequisites.
        Deque<Integer> ready=new ArrayDeque<>(); for(int v=0;v<n;v++) if(indegree[v]==0) ready.add(v); // All initially independent courses can share semester one.
        int semesters=0,completed=0; // Track scheduling layers and cycle-detection progress.
        while(!ready.isEmpty()) { // Each outer iteration schedules exactly one semester.
            int size=ready.size(); semesters++; // Freeze this semester's courses before releasing next-semester dependents.
            for(int i=0;i<size;i++) { // Complete only courses available at the semester's start.
                int v=ready.remove(); completed++; // One course has now satisfied its outgoing prerequisites.
                for(int next:g.get(v)) if(--indegree[next]==0) ready.add(next); // Newly ready courses must wait for the following layer.
            }
        }
        return completed==n?semesters:-1; // A cycle makes a complete course schedule impossible.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(4,new int[][]{{0,2},{1,2},{2,3}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(4,new int[][]{{0,2},{1,2},{2,3}}), 3); // Two initial courses share a semester
        Check.equal(solve(3,new int[][]{}), 1); // All courses independent
        Check.equal(solve(2,new int[][]{{0,1},{1,0}}), -1); // Cyclic prerequisites
    }
}
