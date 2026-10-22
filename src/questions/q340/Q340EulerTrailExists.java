package questions.q340; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q340: Decide whether all undirected edges can be traversed once.
 * Contract: Undirected multigraph; loops and repeated edges allowed; isolated vertices ignored; empty edge set has an empty Euler trail.
 * Why: Every interior visit uses one entering and one leaving edge, so only zero or two vertices may have odd degree. With connected non-isolated vertices, these parity conditions are also sufficient by successively splicing edge cycles into a trail.
 * Complexity: O(V+E α(V)) amortized time and O(V) space.
 */
public final class Q340EulerTrailExists { // One class contains this question, its answer, and reproducible checks.
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
    public static boolean solve(int n,int[][] edges) { // Expose the answer as a reusable method as well as a runnable program.
        Dsu dsu=new Dsu(n); int[] degree=new int[n]; // Connectivity and endpoint parity jointly determine Euler feasibility.
        for(int[] edge:edges) { dsu.union(edge[0],edge[1]); degree[edge[0]]++; degree[edge[1]]++; } // A loop contributes two incident ends and does not change connectivity.
        int representative=-1,odd=0; // Isolated vertices do not need to belong to the edge-containing component.
        for(int v=0;v<n;v++) if(degree[v]>0) { // Only vertices touched by an edge matter for an edge-covering trail.
            if(representative==-1) representative=dsu.find(v); // Remember the first nontrivial component.
            else if(dsu.find(v)!=representative) return false; // A trail cannot jump between disconnected edge-containing components.
            if((degree[v]&1)==1) odd++; // An odd degree can occur only at a trail endpoint.
        }
        return odd==0||odd==2; // Zero odd vertices gives a circuit; two gives an open trail.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(3,new int[][]{{0,1},{1,2}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(3,new int[][]{{0,1},{1,2}}), true); // Two odd endpoints
        Check.equal(solve(4,new int[][]{{0,1},{0,2},{0,3}}), false); // Four odd vertices
        Check.equal(solve(3,new int[][]{}), true); // Empty trail uses every edge vacuously
    }
}
