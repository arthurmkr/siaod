package ru.mab.siaod.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1431. Kids With the Greatest Number of Candies
 */
public class KidsWithTheGreatestNumberOfCandies {
    public static void main(String[] args) {
        KidsWithTheGreatestNumberOfCandies alg = new KidsWithTheGreatestNumberOfCandies();
        System.out.println(alg.kidsWithCandies(new int[]{2,3,5,1,3}, 3));
        System.out.println(alg.kidsWithCandies(new int[]{4,2,1,1,2}, 1));
        System.out.println(alg.kidsWithCandies(new int[]{12,1,12}, 10));
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max =-1;
        for(int cur : candies) {
            if(max < cur) {
                max = cur;
            }
        }

        List<Boolean> res = new ArrayList<>();
        for(int cur : candies) {
            res.add(max <= cur + extraCandies);
        }

        return res;
    }
}
