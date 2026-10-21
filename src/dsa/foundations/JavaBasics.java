package dsa.foundations;

import java.util.Arrays;
import java.util.Objects;

/** Small programs for learning variables, conditions, loops, methods, and references. */
public final class JavaBasics {
    private JavaBasics() { }

    /** Time O(n), extra space O(1). The count describes the prefix already visited. */
    public static int countEven(int[] values) {
        Objects.requireNonNull(values, "values");
        int count = 0;
        for (int value : values) {
            if (value % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    /** n!; accepts 0..20 because 21! does not fit in a signed long. Time O(n). */
    public static long factorial(int n) {
        if (n < 0 || n > 20) {
            throw new IllegalArgumentException("n must be between 0 and 20");
        }
        long result = 1; // The empty product makes 0! equal to 1.
        for (int factor = 2; factor <= n; factor++) {
            result *= factor;
        }
        return result;
    }

    /** Time O(sqrt(n)), extra space O(1). Every composite has a factor <= sqrt(n). */
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        // n / divisor avoids overflow from computing divisor * divisor.
        for (int divisor = 2; divisor <= n / divisor; divisor++) {
            if (n % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] original = {1, 2, 3, 4};
        int[] alias = original;      // Copies the reference, not the array.
        int[] independent = original.clone();
        alias[0] = 10;
        System.out.println("Original after changing alias: " + Arrays.toString(original));
        System.out.println("Independent copy: " + Arrays.toString(independent));
        System.out.println("Even values: " + countEven(original));
        System.out.println("5! = " + factorial(5));
        System.out.println("29 is prime: " + isPrime(29));
    }
}
