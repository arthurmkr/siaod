package ru.mab.siaod.leetcode;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static ru.mab.siaod.Utils.readArrays;

/**
 * 2300. Successful Pairs of Spells and Potions
 */
public class SuccessfulPairsOfSpellsAndPotions {
    public static void main(String[] args) throws IOException, URISyntaxException {
//        System.out.println(findFirstIndex(new int[]{5, 8, 8}, 6) == 1);
//        System.out.println(findFirstIndex(new int[]{1, 2, 2, 3, 4, 5, 6, 7, 7}, 0) == 0);
//        System.out.println(findFirstIndex(new int[]{1, 2, 2, 3, 4, 5, 6, 7, 7}, 1) == 0);
//        System.out.println(findFirstIndex(new int[]{1, 2, 2, 3, 4, 5, 6, 7, 7}, 2) == 1);
//        System.out.println(findFirstIndex(new int[]{1, 2, 2, 3, 4, 5, 6, 7, 7}, 3) == 3);
//        System.out.println(findFirstIndex(new int[]{1, 2, 2, 3, 4, 5, 6, 7, 7}, 4) == 4);
//        System.out.println(findFirstIndex(new int[]{1, 2, 2, 3, 4, 5, 6, 7, 7}, 5) == 5);
//        System.out.println(findFirstIndex(new int[]{1, 2, 2, 3, 4, 5, 6, 7, 7}, 7) == 7);

//        System.out.println(new File(SuccessfulPairsOfSpellsAndPotions.class.getClassLoader().getResource("1.txt").toURI()));
        List<String> strings = FileUtils.readLines(new File(SuccessfulPairsOfSpellsAndPotions.class.getClassLoader().getResource("1.txt").toURI()), "UTF-8");
//        List<String> strings2 = FileUtils.readLines(new File(SuccessfulPairsOfSpellsAndPotions.class.getClassLoader().getResource("2.txt").toURI()), "UTF-8");
        String[] split1 = strings.get(0).split(",");
        String[] split2 = strings.get(1).split(",");
//
        for(int i = 0; i < split1.length; i++) {
            if(!split1[i].equals(split2[i])) {
                System.out.println(i + ": " + split1[i] + " " + split2[i]);
            }
        }
//
//        String[] split = strings2.get(0).split(",");
//        for (int i = 0; i < split.length; i++) {
//            System.out.println("i = " + i + " : " + split[i]);
//        }
//        int i = 4;
//        int j = 4;
//        int k = i / j;
//        double k2 = (double) i / j;
//        System.out.println((double) (i / j) == (double) i/ j);


        List<int[]> arrs = readArrays("2.txt");
        System.out.println(Arrays.toString(successfulPairs(arrs.get(0), arrs.get(1), 9505642132L)));

    }

    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        int limit = (int) 1e5;
        int[] counts = new int[(int) (limit + 2)];
        int[] nextGreatest = new int[(int) (limit + 2)];
        for (int val : potions) {
            counts[val]++;
        }

        int prevSum = 0;
        int next = -1;
        for (int i = counts.length - 1; i >= 0; i--) {
            if (counts[i] != 0) {
                counts[i] += prevSum;
                prevSum = counts[i];
                next = i;
            }
            nextGreatest[i] = next;
        }

        int[] res = new int[spells.length];
        for (int i = 0; i < spells.length; i++) {
            long ceil = success / spells[i];
            if (ceil != ((double) success) / spells[i]) {
                ceil++;
            }
            res[i] = ceil > limit || nextGreatest[(int) ceil] < 0 ? 0 : counts[nextGreatest[(int) ceil]];
        }
        return res;
    }
}
