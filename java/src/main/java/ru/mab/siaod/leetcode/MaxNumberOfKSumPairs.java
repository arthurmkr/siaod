package ru.mab.siaod.leetcode;

import java.util.Arrays;

/**
 * 1679. Max Number of K-Sum Pairs
 */
public class MaxNumberOfKSumPairs {
    public static void main(String[] args) {
        System.out.println(maxOperations(new int[]{1, 2, 3, 4}, 5) == 2);
        System.out.println(maxOperations(new int[]{3, 1, 3, 4, 3}, 6) == 1);
        System.out.println(maxOperations(new int[]{2, 5, 4, 4, 1, 3, 4, 4, 1, 4, 4, 1, 2, 1, 2, 2, 3, 2, 4, 2}, 3) == 4);
    }

    public static int maxOperations(int[] nums, int k) {
        int[] tmp = new int[nums.length];
        for (int m = 0, n = 0; m < nums.length; m++) {
            if (nums[m] < k) {
                tmp[n++] = nums[m];
            }
        }
        Arrays.sort(tmp);

        int cnt = 0;
        int i = 0;
        int j = tmp.length - 1;

        while (i < j) {
            int sum = tmp[i] + tmp[j];
            if (sum < k) {
                i++;
            } else if (sum > k) {
                j--;
            } else {
                cnt++;
                i++;
                j--;
            }
        }
        return cnt;
    }
//
//    public static int maxOperations(int[] nums, int k) {
//        Map<Integer, Integer> map = new HashMap<>();
//
//        int cnt = 0;
//        for (int num : nums) {
//            if (num >= k) {
//                continue;
//            }
//
//            int additionCount = map.getOrDefault(k - num, 0);
//
//            if (additionCount > 0) {
//                cnt++;
//                map.put(k - num, additionCount - 1);
//            } else {
//                map.put(num, map.getOrDefault(num, 0) + 1);
//            }
//        }
//        return cnt;
//    }

//    public static int maxOperations(int[] nums, int k) {
//        Map<Integer, Integer> map = new HashMap<>();
//
//        int cnt = 0;
//        for (int num : nums) {
//            if (num >= k) {
//                continue;
//            }
//
//            map.put(num, map.getOrDefault(num, 0) + 1);
//        }
//
//        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<Integer, Integer> entry = iterator.next();
//
//            int addition = k - entry.getKey();
//            if (addition == entry.getKey()) {
//                cnt += entry.getValue() / 2;
//            } else {
//                cnt += Math.min(entry.getValue(), map.getOrDefault(addition, 0));
//            }
//            iterator.remove();
//        }
//
//        return cnt;
//    }


}
