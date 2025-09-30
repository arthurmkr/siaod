package ru.mab.siaod.leetcode;

/**
 * 9. Palindrome Number
 */
public class PalindromeNumber {
    public static void main(String[] args) {
        PalindromeNumber alg = new PalindromeNumber();
        System.out.println(alg.isPalindrome(121) == true);
        System.out.println(alg.isPalindrome(-121) == false);
        System.out.println(alg.isPalindrome(Integer.MAX_VALUE) == false);
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int tmp = x;
        int reverse = 0;
        while (tmp > 0) {
            reverse *= 10;
            reverse += tmp % 10;
            tmp /= 10;
        }

        return reverse == x;
    }
}
