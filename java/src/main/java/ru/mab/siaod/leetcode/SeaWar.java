package ru.mab.siaod.leetcode;

public class SeaWar {
    public static void main(String[] args) {
        int sea[][] = {
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 1, 1, 1, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 1, 1},
        };

        int count = count(sea);
        System.out.println(count);
    }

    static int count(int[][] sea) {
        int counter = 0;
        for (int i = 0; i < sea.length; i++) {
            for (int j = 0; j < sea.length; j++) {
                if (sea[i][j] == 1) {
                    if (i == 0 || sea[i - 1][j] != 1) {
                        counter++;
                    }

                    while (j < sea.length && sea[i][j] == 1) {
                        j++;
                    }
                    j--;
                }
            }
        }

        return counter;
    }
}
