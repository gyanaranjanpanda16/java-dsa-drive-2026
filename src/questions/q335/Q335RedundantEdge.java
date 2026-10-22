package questions.q335; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q335: Return the last edge that closes an undirected cycle.
 * Contract: Undirected edge pairs with valid vertices; multiple cycle-closing edges are allowed; return the last one in input order.
 * Why: Before inserting an edge, its endpoints are connected exactly when some earlier path joins them. Adding the new edge then creates a cycle; if the endpoints are separate, joining them preserves a forest between components.
 * Complexity: O(V+E α(V)) amortized time and O(V) space.
 */
public final class Q335RedundantEdge { // One class contains this question, its answer, and reproducible checks.
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
    public static String solve(int n,int[][] edges) { // Expose the answer as a reusable method as well as a runnable program.
        Dsu dsu=new Dsu(n); int[] redundant=null; // Start with no previously detected cycle-closing edge.
        for(int[] edge:edges) { // Edges are inserted in the supplied order.
            if(!dsu.union(edge[0],edge[1])) redundant=edge; // Already-connected endpoints have an existing path, so this edge closes a cycle.
        }
        return redundant==null?"[]":Arrays.toString(redundant); // Retain the last rejected edge, including a loop or parallel repeat.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(3,new int[][]{{0,1},{1,2},{2,0}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(3,new int[][]{{0,1},{1,2},{2,0}}), "[2, 0]"); // Triangle closing edge
        Check.equal(solve(3,new int[][]{{0,1},{1,2}}), "[]"); // A forest has no redundant edge
        Check.equal(solve(2,new int[][]{{0,1},{0,1}}), "[0, 1]"); // Parallel edge closes a multigraph cycle
    }
}
