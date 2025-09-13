package ru.mab.siaod.leetcode;

/**
 * 2179. Count Good Triplets in an Array
 */
public class CountGoodTripletsInArray {
    public static void main(String[] args) {
        long l;
        long count;
//        l = System.currentTimeMillis();
//        count = goodTriplets(new int[]{2, 0, 1, 3}, new int[]{0, 1, 2, 3});
//        System.out.println("count =" + count + ", time: " + (System.currentTimeMillis() - l));
////
//        l = System.currentTimeMillis();
//        count = goodTriplets(new int[]{4, 0, 1, 3, 2}, new int[]{4, 1, 0, 2, 3});
//        System.out.println("count =" + count + ", time: " + (System.currentTimeMillis() - l));

        l = System.currentTimeMillis();
        count = goodTriplets(
                new int[]{13,14,10,2,12,3,9,11,15,8,4,7,0,6,5,1},
                new int[]{8,7,9,5,6,14,15,10,2,11,4,13,3,12,1,0});
        System.out.println("count =" + count + ", time: " + (System.currentTimeMillis() - l));
    }

    public static long goodTriplets(int[] nums1, int[] nums2) {
        int[] indexes = makeIndexes(nums2);


        int count = 0;
        for (int i = 0; i < nums1.length - 2; i++) {
            int indexX2 = indexes[nums1[i]];
            for (int k = i + 1; k < nums1.length - 1; k++) {
                int indexY2 = indexes[nums1[k]];

                if (indexX2 < indexY2) {
                    for (int j = k + 1; j < nums1.length; j++) {
                        int indexZ2 = indexes[nums1[j]];

                        if (indexY2 < indexZ2) {
                            count += Math.min(nums1.length - indexes[nums1[j]], nums1.length - j);
                            break;
                        }
                    }
                }
            }
        }
        return count;
    }

    private static int[] makeIndexes(int[] arr) {
        int[] indexes = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            indexes[arr[i]] = i;
        }

        return indexes;
    }
}
