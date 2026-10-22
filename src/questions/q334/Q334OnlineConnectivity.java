package questions.q334; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q334: Process union and connectivity queries online.
 * Contract: Operation rows [0,u,v] add connectivity and [1,u,v] query it; valid vertex IDs; deletions are not supported.
 * Why: Union-find maintains a partition whose blocks are exactly the connected components after every insertion. Union merges the required blocks, and representative equality answers whether two vertices belong to the same block.
 * Complexity: O(V+Q α(V)) amortized time; O(V) DSU plus query output.
 */
public final class Q334OnlineConnectivity { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }    static final class Dsu { // Parent trees represent disjoint connected components.
        final int[] parent,size; // Size guides merging so trees remain shallow.
        Dsu(int n) { // Initially every vertex is its own component.
            parent=new int[n]; size=new int[n]; // Allocate one parent and size entry per vertex.
            for(int i=0;i<n;i++) { parent[i]=i; size[i]=1; } // Singletons point to themselves and contain one member.
        }
        int find(int x) { // Return a canonical representative of x's component.
            while(parent[x]!=x) { parent[x]=parent[parent[x]]; x=parent[x]; } // Path halving shortens future searches without changing component membership.
            return x; // A self-parent is the component root.
        }
        boolean union(int a,int b) { // Return whether this edge merges two previously separate components.
            a=find(a); b=find(b); // Only component roots may be reparented safely.
            if(a==b) return false; // An already-connected pair requires no structural change.
            if(size[a]<size[b]) { int t=a; a=b; b=t; } // Attach the smaller tree to the larger to limit depth growth.
            parent[b]=a; size[a]+=size[b]; // Merge both membership and root-size information.
            return true; // Exactly one component was eliminated.
        }
    }
    public static String solve(int n,int[][] operations) { // Expose the answer as a reusable method as well as a runnable program.
        Dsu dsu=new Dsu(n); // Every vertex begins disconnected from every other vertex.
        List<Boolean> answers=new ArrayList<>(); // Only query operations produce output.
        for(int[] operation:operations) { // Process updates in the given chronological order.
            if(operation[0]==0) dsu.union(operation[1],operation[2]); // A union permanently connects the two current components.
            else if(operation[0]==1) answers.add(dsu.find(operation[1])==dsu.find(operation[2])); // Equal roots exactly represent current connectivity.
            else throw new IllegalArgumentException("unknown operation"); // Reject malformed operation codes instead of guessing their meaning.
        }
        return answers.toString(); // Results reflect state at each query, not the final graph alone.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(3,new int[][]{{1,0,2},{0,0,1},{0,1,2},{1,0,2}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(3,new int[][]{{1,0,2},{0,0,1},{0,1,2},{1,0,2}}), "[false, true]"); // Updates change later query results
        Check.equal(solve(1,new int[][]{{1,0,0}}), "[true]"); // A vertex connects to itself
        Check.equal(solve(0,new int[][]{}), "[]"); // No operations
    }
}
