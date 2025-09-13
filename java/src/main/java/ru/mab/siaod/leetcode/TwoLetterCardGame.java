package ru.mab.siaod.leetcode;

public class TwoLetterCardGame {
    public static void main(String[] args) {
        TwoLetterCardGame game = new TwoLetterCardGame();
        System.out.println(game.score(new String[]{"aa", "ab", "ba", "ac"}, 'a'));
        System.out.println(game.score(new String[]{"aa", "ab", "ba"}, 'a'));
        System.out.println(game.score(new String[]{"aa", "ab", "ba", "ac"}, 'b'));
        System.out.println(game.score(new String[]{"ba", "ba"}, 'b'));
        System.out.println(game.score(new String[]{"ab", "aa", "ab", "bc", "cc", "bc", "bb", "ac", "bc", "bc", "aa",
                "aa", "ba", "bc", "cb", "ba", "ac", "bb", "cb", "ac", "cb", "cb", "ba", "bc", "ca", "ba", "bb", "cc",
                "cc", "ca", "ab", "bb", "bc", "ba", "ac", "bc", "ac", "ac", "bc", "bb", "bc", "ac", "bc", "aa", "ba",
                "cc", "ac", "bb", "ba", "bb"}, 'b'));
    }

    public int score(String[] cards, char x) {
        int[] counts1 = new int[20];
        int[] counts2 = new int[10];
        int xxCounter = 0;

        for (String card : cards) {
            char char1 = card.charAt(0);
            char char2 = card.charAt(1);

            boolean charMatch1 = char1 == x;
            boolean charMatch2 = char2 == x;

            if (charMatch1 && charMatch2) {
                xxCounter++;
            } else if (charMatch1) {
                counts2[char2 - 'a']++;
            } else if (char2 == x) {
                counts1[char1 - 'a']++;
            }
        }

        int totalCount = 0;
        for (int i = xxCounter; i > 0; i--) {
            int maxIndex1 = maxIndex(counts1);
            int maxIndex2 = maxIndex(counts2);

            int maxCount1 = maxIndex1 == -1 ? 0 : counts1[maxIndex1];
            int maxCount2 = maxIndex2 == -1 ? 0 : counts2[maxIndex2];

            if(maxCount1 == 0 &&  maxCount2 == 0) {
                break;
            } else if(counts1[maxIndex1] >= counts2[maxIndex2]) {
                totalCount++;
                counts1[maxIndex1]--;
            } else if(counts2[maxIndex2] > counts1[maxIndex1]) {
                totalCount++;
                counts2[maxIndex2]--;
            }
        }

        totalCount += sum(counts1)/ 2;
        totalCount += sum(counts2)/ 2;

        return totalCount;
    }

    private int sum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    private int maxIndex(int[] arr) {
        int maxIndex = -1;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max && arr[i] != 0) {
                max = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
