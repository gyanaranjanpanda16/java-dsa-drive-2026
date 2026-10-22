package questions.q333; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q333: Find a minimum spanning-tree weight using sorted edges.
 * Contract: Undirected [u,v,int weight] edges with valid vertices; parallel edges and self-loops allowed; returns DISCONNECTED if no spanning tree exists.
 * Why: Each accepted edge is a minimum remaining edge crossing between two forest components. The cut property permits adding it to some optimal spanning tree, while union-find rejects precisely the edges that would close a cycle.
 * Complexity: O(E log(E+1)+E α(V)) time and O(V+E) space.
 */
public final class Q333KruskalMst { // One class contains this question, its answer, and reproducible checks.
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
        int[][] sorted=edges.clone(); Arrays.sort(sorted,Comparator.comparingInt(e->e[2])); // Consider cheaper edges before more expensive alternatives without changing input order.
        Dsu dsu=new Dsu(n); int selected=0; long total=0; // Components represent the forest built so far.
        for(int[] e:sorted) if(dsu.union(e[0],e[1])) { // Accept only edges joining different trees of the forest.
            total+=e[2]; selected++; // Every accepted edge reduces the number of components by one.
        }
        return n==0||selected==n-1?Long.toString(total):"DISCONNECTED"; // A connected forest on n vertices has exactly n-1 edges.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(3,new int[][]{{0,1,4},{1,2,1},{0,2,2}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(3,new int[][]{{0,1,4},{1,2,1},{0,2,2}}), "3"); // Cycle edge is rejected
        Check.equal(solve(3,new int[][]{{0,1,2}}), "DISCONNECTED"); // Isolated vertex
        Check.equal(solve(2,new int[][]{{0,1,-3}}), "-3"); // Negative weights are valid in an MST
    }
}
