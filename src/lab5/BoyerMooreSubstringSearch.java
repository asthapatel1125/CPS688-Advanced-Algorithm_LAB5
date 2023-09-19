package lab5;

import java.util.*;

public class BoyerMooreSubstringSearch {

    // Boyer-Moore substring search method using the "bad character" rule
    public static List<Integer> search(String text, String pattern) {

        List<Integer> result = new ArrayList<>();
        int n = text.length(); // length of text
        int m = pattern.length(); // length of pattern
        
        // Initialize a map to store the last occurrence of each character in the pattern
        Map<Character, Integer> lastOccurrence = new HashMap<>();
        
        // For each character in the pattern, store its last occurrence position
        for (int i = 0; i < m; i++) {
            lastOccurrence.put(pattern.charAt(i), i);
        }

        int i = 0;
        while (i <= n - m) {
            int j = m - 1;

            // Compare characters from the end of the pattern to the beginning
            while (j >= 0 && text.charAt(i + j) == pattern.charAt(j)) {
                j--;
            }
            
            if (j == -1) {
                // Pattern found
                result.add(i);
                i += m;
            } else {
                // Get the character in the text that does not match with the pattern
                char badChar = text.charAt(i + j);
                
                // Calculate the shift distance using the "bad character" rule
                int shift = Math.max(1, j - lastOccurrence.getOrDefault(badChar, -1));
                i += shift;
            }
        } 

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTestCases;

        System.out.print("Enter the number of test cases: ");
        numTestCases = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 1; i <= numTestCases; i++) {
            System.out.println("Test Case #" + i);
            System.out.print("Enter the text: ");
            String text = scanner.nextLine();
            System.out.print("Enter the pattern: ");
            String pattern = scanner.nextLine();
            List<Integer> result = search(text, pattern);
            if (result.isEmpty()) {
                System.out.println("Pattern not found.");
            } else {
                System.out.print("Pattern " + pattern + " found at index ");
                for (int index : result) {
                    System.out.print(index + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        scanner.close();
    }
}
