package ru.mab.siaod.leetcode;

/**
 * 2289. Steps to Make Array Non-decreasing
 */
public class StepsToMakeArrayNonDecreasing {
    public static void main(String[] args) {
        StepsToMakeArrayNonDecreasing alg = new StepsToMakeArrayNonDecreasing();

//        System.out.println(alg.totalSteps(new int[]{5, 3, 4, 4, 7, 3, 6, 11, 8, 5, 11}) == 3);
//        System.out.println(alg.totalSteps(new int[]{4, 5, 7, 7, 13}) == 0);
//        System.out.println(alg.totalSteps(new int[]{10, 1, 2, 3, 4, 5, 6, 1, 2, 3}) == 6);
//        System.out.println(alg.totalSteps(new int[]{10, 1, 2, 3, 4, 5, 6, 1, 2, 3, 11}) == 6);
//        System.out.println(alg.totalSteps(new int[]{5, 11, 7, 8, 11}) == 2);
//        System.out.println(alg.totalSteps(new int[]{7, 14, 4, 14, 13, 2, 6, 13}) == 3);
//        System.out.println(alg.totalSteps(new int[]{20, 1, 5, 10, 1, 1, 1, 1, 1, 30}) == 3);
        System.out.println(alg.totalSteps(new int[]{7, 15, 5, 5, 6, 10, 7, 13}) == 5);
    }

    //    public int totalSteps(int[] nums) {
//        List<Integer> maxs = new ArrayList<>();
//
//        maxs.add(nums[0]);
//        int prev = nums[0];
//        int count = 0;
//        int maxCount = 0;
//        boolean found = false;
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] < prev) {
//                if (!found) {
//                    maxCount = Math.max(count, maxCount);
//                    count = 1;
//
//                    found = true;
//                    maxs.add(nums[i]);
//                } else {
//                    maxs.set(maxs.size()-1, nums[i]);
//                }
//            } else {
//                found = false;
//                for (int j = 0; j < maxs.size(); j++) {
//                    if (nums[i] >= maxs.get(j)) {
//                        maxs.set(j, nums[i]);
//                        if (j != 0) {
//                            count++;
//                        } else {
//                                   maxs.clear();
//                            maxs.add(nums[i]);
//                            break;
//                        }
//                    }
//                }
//            }
//
//            prev = nums[i];
//        }
//
//        return Math.max(count, maxCount);
//    }
    public int totalSteps(int[] nums) {
        int max = nums[0];
        int betweenMax = 0;
        int cnt = 0;
        int maxCnt = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                maxCnt = Math.max(maxCnt, cnt);
                cnt = 0;
                max = nums[i];
            } else if (nums[i] < max) {
                if (nums[i] >= betweenMax) {
                    betweenMax = nums[i];
                    cnt++;
                } else {

                }
            }
        }
        return 0;
    }
}
