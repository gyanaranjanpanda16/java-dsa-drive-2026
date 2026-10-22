package questions.q336; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Merge accounts connected by shared email addresses.
 * Contract: Each account is a list containing a name followed by at least one email. Merge accounts connected directly or transitively by equal, case-sensitive email strings. Names within a connected component must agree. Return sorted unique emails after each name, with groups sorted lexicographically by their smallest email. Empty input returns an empty list.
 * Why: Comparing every pair of accounts repeatedly is expensive and misses indirect connections unless repeated to convergence. Map each email to its first account and union later owners with it. Connectivity is transitive: if A shares with B and B shares with C, all three belong together even if A and C share nothing directly. Union by size keeps trees shallow; path compression shortens future lookups. Account reconciliation uses this idea, although a real identity system needs stronger verification than an equal email alone.
 * Cost: O(E alpha(A) + E log(E+1) + A log(A+1)) time and O(E+A) space, for A accounts and E email occurrences. Sorting dominates in typical inputs.
 */
public final class Q336MergeAccounts { // Keep the algorithm, demonstration, and boundary checks together.
    private Q336MergeAccounts() { } // Only static entry points are needed for this exercise.
    public static List<List<String>> solve(List<List<String>> accounts) { // Account positions are stable union-find identifiers.
        int n=accounts.size(); int[] parent=new int[n],size=new int[n]; // Each account initially owns one component.
        Map<String,Integer> owner=new HashMap<>(); // Remember one representative account per email.
        for(int i=0;i<n;i++) { // Initialize before processing any unions.
            if(accounts.get(i).size()<2) throw new IllegalArgumentException("name and email required"); // Empty accounts have no identity key.
            parent[i]=i; size[i]=1; // A singleton is its own root.
        }
        for(int i=0;i<n;i++) for(int j=1;j<accounts.get(i).size();j++) { // Names are labels; only emails create connections.
            Integer earlier=owner.putIfAbsent(accounts.get(i).get(j),i); // First sighting records ownership.
            if(earlier!=null) { // A repeated email connects its owners.
                int a=root(parent,i),b=root(parent,earlier); // Union only component roots.
                if(a!=b) { // Avoid counting an already connected component twice.
                    if(size[a]<size[b]) { int swap=a; a=b; b=swap; } // Attach the smaller tree below the larger one.
                    parent[b]=a; size[a]+=size[b]; // Record the merged component size.
                }
            }
        }
        Map<Integer,TreeSet<String>> emails=new HashMap<>(); // A sorted set removes repeated emails within each component.
        for(var entry:owner.entrySet()) emails.computeIfAbsent(root(parent,entry.getValue()),key->new TreeSet<>()).add(entry.getKey()); // Every unique email belongs to exactly one final root.
        for(int i=0;i<n;i++) if(!accounts.get(i).get(0).equals(accounts.get(root(parent,i)).get(0))) throw new IllegalArgumentException("conflicting names"); // Do not silently choose an identity label.
        List<List<String>> result=new ArrayList<>(); // Produce independent output lists.
        for(var entry:emails.entrySet()) { // Emit one group per root that owns emails.
            List<String> group=new ArrayList<>(); group.add(accounts.get(entry.getKey()).get(0)); // The validated component name comes first.
            group.addAll(entry.getValue()); result.add(group); // Append sorted unique emails.
        }
        result.sort(Comparator.comparing(group->group.get(1))); // Deterministic ordering makes examples reproducible.
        return result; // No input account was modified.
    }
    private static int root(int[] parent,int i) { // Follow parents until reaching the representative.
        while(i!=parent[i]) { parent[i]=parent[parent[i]]; i=parent[i]; } // Path halving makes subsequent searches shorter.
        return i; // Only roots point to themselves.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(List.of(List.of("Ada","a@x","b@x"),List.of("Ada","b@x","c@x"),List.of("Lin","z@x"))))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(List.of(List.of("Ada","a@x","b@x"),List.of("Ada","b@x","c@x"),List.of("Lin","z@x"))), List.of(List.of("Ada","a@x","b@x","c@x"),List.of("Lin","z@x"))); // Shared email joins two accounts.
        Check.equal(solve(List.of()), List.of()); // No accounts.
        Check.equal(solve(List.of(List.of("A","x","x"),List.of("A","y"),List.of("A","x","y"))), List.of(List.of("A","x","y"))); // Transitive merging and duplicate emails.
    }
}
