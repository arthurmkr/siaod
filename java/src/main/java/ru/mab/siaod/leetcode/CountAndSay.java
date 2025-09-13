package ru.mab.siaod.leetcode;

/**
 * 38. Count and Say
 */
public class CountAndSay {
    public static void main(String[] args) {
//        System.out.println(new CountAndSay().countAndSay(1));
//        System.out.println(new CountAndSay().countAndSay(4));
        System.out.println(new CountAndSay().countAndSay(30));
    }

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        short[] arr = new short[4500];
        arr[0] = 1;
        short[] buf = new short[4500];
        short[] temp;

        int m = 1;
        for (int j = 2; j <= n; j++) {
            short last = arr[0];
            short count = 1;
            int k = 0;
            for (int i = 1; i < m; i++) {
                short curChar = arr[i];
                if (curChar == last) {
                    count++;
                } else {
                    buf[k++] = count;
                    buf[k++] = last;

                    count = 1;
                    last = curChar;
                }
            }

            buf[k++] = count;
            buf[k] = last;

            temp = arr;
            arr = buf;
            buf = temp;
            m = k + 1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m - 1; i += 2) {
            sb.append(arr[i]).append(arr[i + 1]);
        }
        return sb.toString();
    }

//
//    private int[] rle(int[] s, int[] buf) {
//        int last = s[0];
//        int count = 1;
//        int k = 0;
//        for (int i = 1; i < s.length; i++) {
//            int curChar = s[i];
//            if (curChar == last) {
//                count++;
//            } else {
//                buf[k++] = count;
//                buf[k++] = last;
//
//                count = 1;
//                last = curChar;
//            }
//        }
//
//        buf[k++] = count;
//        buf[k] = last;
//        return Arrays.copyOf(newS, k+1);
//    }
}
