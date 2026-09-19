package string.class_problems;

import java.util.Scanner;

public class PalindromeChecker {

    static boolean iterativeCheck(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    static boolean recursiveCheck(String str, int left, int right) {
        if (left >= right) {
            return true;
        }

        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }

        return recursiveCheck(str, left + 1, right - 1);
    }

    static boolean charArrayCheck(String str) {
        char[] arr = str.toCharArray();

        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] != arr[arr.length - 1 - i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String str = sc.nextLine();

        System.out.println("\nIterative: " +
                (iterativeCheck(str) ? "Palindrome" : "Not Palindrome"));

        System.out.println("Recursive: " +
                (recursiveCheck(str, 0, str.length() - 1)
                        ? "Palindrome" : "Not Palindrome"));

        System.out.println("Character Array: " +
                (charArrayCheck(str) ? "Palindrome" : "Not Palindrome"));

        sc.close();
    }
}