package lab5;
import java.util.*;

public class RabinKarpSubstringSearch {
    
    public static List<Integer> rabinKarpSearch(String text, String pattern) {
        List<Integer> indices = new ArrayList<>(); // initialize indices list
        int n = text.length(); // length of String
        int m = pattern.length(); // length of pattern

        int prime = 101; // prime number for hashing
        int pHash = 0; // hash value of pattern
        int tHash = 0; // hash value of current window in text
        int h = 1; // hash multiplier

        // calculate hash value of pattern and first window of text
        for (int i = 0; i < m; i++) {
            pHash = (prime * pHash + pattern.charAt(i)) % prime; //hash value for pattern
            tHash = (prime * tHash + text.charAt(i)) % prime;//hash value for the text that's being compared t the pattern
        }

        // calculate hash multiplier
        for (int i = 0; i < m - 1; i++) {
            h = (h * prime) % prime;
        }

        // iterate through text and compare hash values of windows (the text that's being compared with the pattern)
        for (int i = 0; i <= n - m; i++) {
            // check if hash values match and characters match
        	//first condition checks if the sum matches
        	//second condition compares the substring portion to the pattern to make sure its not a false positive
            if (pHash == tHash && text.substring(i, i + m).equals(pattern)) {
                indices.add(i); // add index to indices list
            }

            // calculate hash value of next window in text
            if (i < n - m) {
                tHash = (prime * (tHash - text.charAt(i) * h) + text.charAt(i + m)) % prime;

                // handle negative hash value
                if (tHash < 0) {
                    tHash += prime;
                }
            }
        }

        return indices; // return indices list
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the text: ");
        String text = scanner.nextLine();
        System.out.print("Enter the pattern: ");
        String pattern = scanner.nextLine();

        List<Integer> indices = rabinKarpSearch(text, pattern); // call rabinKarpSearch method
        if (indices.isEmpty()) { // if pattern not found
            System.out.println("Pattern not found");
        } else { // if pattern found
            System.out.print("Pattern " + pattern + " found at index");
            if (indices.size() == 1) { // if only one index found
                System.out.print(" " + indices.get(0)); // print single index
            } else { // if multiple indices found
                System.out.print("es"); // print plural form of "index"
                for (int i = 0; i < indices.size(); i++) { // iterate through indices
                    System.out.print(" " + indices.get(i)); // print each index
                    if (i < indices.size() - 1) { // if not last index
                        System.out.print(","); // print comma
                    }
                }
            }
            System.out.println(); // print newline
        }
    }
}
