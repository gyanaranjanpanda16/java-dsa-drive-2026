package questions.q462; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Maximize capital with at most k affordable projects.
 * Contract: Given equally sized arrays of nonnegative int required capital and profits, choose at most k distinct projects. A project is affordable when current capital meets its requirement; its profit is added after completion and the requirement is not spent. Start with nonnegative long capital. Return maximum final capital; reject an overflowing long result.
 * Why: Trying every project order is exponential. Sort projects by requirement, then maintain a max heap of profits for all currently affordable, unchosen projects. Choosing the largest affordable nonnegative profit gives at least as much next-step capital as any alternative, so it cannot reduce future options. Repeat at most k times. This is a simplified project-unlocking model, not a realistic investment recommendation: costs, durations, and risks would change the model.
 * Cost: O(n log n + min(k,n) log n) time and O(n) extra space. Each project enters the heap once and is selected at most once.
 */
public final class Q462MaximizeCapital { // Keep the algorithm, demonstration, and boundary checks together.
    private Q462MaximizeCapital() { } // Only static entry points are needed for this exercise.
    public static long solve(int[] required,int[] profits,int k,long capital) { // Capital requirements unlock projects without being deducted.
        if(required.length!=profits.length||k<0||capital<0) throw new IllegalArgumentException("invalid sizes or bounds"); // Keep parallel arrays aligned and budgets nonnegative.
        int[][] projects=new int[required.length][2]; // Copy requirements and profits into sortable records.
        for(int i=0;i<required.length;i++) { if(required[i]<0||profits[i]<0) throw new IllegalArgumentException("nonnegative projects required"); projects[i]=new int[]{required[i],profits[i]}; } // Negative profits would invalidate the greedy premise.
        Arrays.sort(projects,Comparator.comparingInt(project->project[0])); // Reveal projects in increasing affordability order.
        PriorityQueue<Integer> available=new PriorityQueue<>(Comparator.reverseOrder()); int next=0; // Keep the most profitable affordable project at the head.
        for(int chosen=0;chosen<k;chosen++) { // At most k distinct projects may be completed.
            while(next<projects.length&&projects[next][0]<=capital) available.add(projects[next++][1]); // Newly affordable projects become candidates exactly once.
            if(available.isEmpty()) break; // No remaining project can increase capital or unlock another one.
            capital=Math.addExact(capital,available.remove()); // Add profit with explicit overflow detection.
        }
        return capital; // Stopping before k selections is permitted.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{0,1,1},new int[]{1,2,3},2,0))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{0,1,1},new int[]{1,2,3},2,0), 4L); // An initial project unlocks better choices.
        Check.equal(solve(new int[]{2},new int[]{9},1,0), 0L); // No affordable project.
        Check.equal(solve(new int[]{0},new int[]{5},0,7), 7L); // No selections allowed.
        Check.equal(solve(new int[]{0},new int[]{5},3,0), 5L); // A project cannot be selected twice.
    }
}
