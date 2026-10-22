package questions.q492; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Compute a huge-index Fibonacci residue with matrix powers.
 * Contract: Return F(n) modulo a positive int modulus for nonnegative long n, with F(0)=0 and F(1)=1.
 * Why: The transition matrix[[1,1],[1,0]] maps consecutive Fibonacci states to the next state. Its nth power contains F(n) in the upper-right position. Binary exponentiation composes many transitions in logarithmically many matrix multiplications. Useful for fixed-size linear recurrences.
 * Cost: O(log(n+1)) fixed2x2 matrix operations and O(1) live matrix space.
 */
public final class Q492MatrixFibonacci { // Keep the algorithm, demonstration, and boundary checks together.
    private Q492MatrixFibonacci() { } // Only static entry points are needed for this exercise.
    private static long[][] multiply(long[][] a,long[][] b,int modulus) { // Multiply two recurrence transitions modulo the bounded int modulus.
        long[][] result=new long[2][2]; // Allocate a fresh result so inputs remain available during composition.
        for(int i=0;i<2;i++) for(int j=0;j<2;j++) result[i][j]=(a[i][0]*b[0][j]+a[i][1]*b[1][j])%modulus; // Two residue products still fit long for an int modulus.
        return result; // Matrix multiplication composes the represented state transformations.
    }
    public static long solve(long n,int modulus) { // Jump across recurrence steps using the binary expansion of n.
        if(n<0||modulus<=0) throw new IllegalArgumentException("invalid index or modulus"); // Define a nonnegative sequence index and valid reduction base.
        long one=1L%modulus; long[][] result={{one,0},{0,one}},power={{one,one},{one,0}}; // Begin with the identity and one Fibonacci transition.
        while(n>0) { // Consume one exponent bit at a time.
            if((n&1)!=0) result=multiply(result,power,modulus); // Apply this block of transitions when the bit is selected.
            power=multiply(power,power,modulus); n>>>=1; // Double the block length and move to the next bit.
        }
        return result[0][1]; // The upper-right entry equals F(original n) modulo modulus.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(10,1000))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(10,1000), 55L); // A familiar Fibonacci value.
        Check.equal(solve(0,7), 0L); // Identity matrix contains F(0)=0.
        Check.equal(solve(50,1000000007), 586268941L); // A large Fibonacci value is reduced exactly.
    }
}
