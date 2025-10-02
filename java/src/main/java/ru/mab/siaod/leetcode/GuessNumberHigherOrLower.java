package ru.mab.siaod.leetcode;

/**
 * 374. Guess Number Higher or Lower
 * <p>
 * Forward declaration of guess API.
 *
 * @param num your guess
 * @return -1 if num is higher than the picked number
 * 1 if num is lower than the picked number
 * otherwise return 0
 * int guess(int num);
 */
public class GuessNumberHigherOrLower {
    int val;

    public GuessNumberHigherOrLower(int val) {
        this.val = val;
    }

    public static void main(String[] args) {
//        System.out.println(new GuessNumberHigherOrLower(6).guessNumber(10) == 6);
        System.out.println(new GuessNumberHigherOrLower(1).guessNumber(1) == 1);
        System.out.println(new GuessNumberHigherOrLower(1).guessNumber(2) == 1);
        System.out.println(new GuessNumberHigherOrLower(1).guessNumber(2) == 1);
        System.out.println(new GuessNumberHigherOrLower(2).guessNumber(2) == 2);
    }

    public int guessNumber(int n) {
        int l = 1;
        int r = n;

        while (l < r){
            int mid = r / 2 + l / 2;
            int guess = guess(mid);
            if (guess == 0) {
                return mid;
            } else if (guess < 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    int guess(int num) {
        if (num == val) {
            return 0;
        } else if (num > val) {
            return -1;
        } else {
            return 1;
        }
    }
}
