package questions.q388;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q388: Phone Keypad Combinations.
 * Contract: Non-null string of digits 2..9, short enough to enumerate; empty input returns an empty list.
 * Why it works: Each answer selects exactly one mapped letter at each digit position. The Cartesian product recursion produces every valid selection once.
 * Cost: O(d*4^d) worst-case time/output and O(d) stack.
 */
public final class Q388PhoneKeypadCombinations { // Keep this question independently runnable and testable.
    private Q388PhoneKeypadCombinations() { } // No object state is needed by this standalone exercise.
    public static List<String> letters(String digits) { // Expose the operation for an independent caller and the executable checks below.
        String[] keys={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"}; // Encode the actual telephone digit-to-letter mapping.
        for(char c:digits.toCharArray()) if(c<'2'||c>'9') throw new IllegalArgumentException("Digits 2..9 only"); // Every selected digit must have letters.
        List<String> out=new ArrayList<>(); // Accumulate one word per choice combination.
        if(!digits.isEmpty()) expand(digits,0,new StringBuilder(),keys,out); // Empty input deliberately returns no suggestions.
        return out; // Letter order follows the keypad mapping.
    }
    private static void expand(String d,int i,StringBuilder p,String[] keys,List<String> out) { // Keep recursive state or a repeated sub-operation inside this solution.
        if(i==d.length()) { out.add(p.toString()); return; } // All digit positions have a chosen letter.
        for(char c:keys[d.charAt(i)-'0'].toCharArray()) { // Explore the letters belonging to this digit only.
            p.append(c); expand(d,i+1,p,keys,out); p.deleteCharAt(p.length()-1); // Choose, explore and restore the prefix.
        }
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(letters("23")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(letters("").isEmpty(), "Q388 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(letters("2").equals(List.of("a","b","c")), "Q388 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(letters("79").size()==16, "Q388 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
