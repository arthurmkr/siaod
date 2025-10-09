package ru.mab.siaod.leetcode;

/**
 * 151. Reverse Words in a String
 */
public class ReverseWordsInString {
    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue").equals("blue is sky the"));
//        System.out.println(reverseWords("  hello world  "));
        System.out.println(reverseWords("  hello world  ").equals("world hello"));
//        System.out.println(reverseWords("a good   example"));
        System.out.println(reverseWords("a good   example").equals("example good a"));
        System.out.println(reverseWords("example").equals("example"));
    }

    public static String reverseWords(String s) {
        char[] arr = s.toCharArray();
        char[] res = new char[s.length()];

        int i = 0;
        int k = res.length;
        int len = 0;

        while (i < arr.length) {
            while (i < arr.length && arr[i] == ' ') i++;
            while (i < arr.length && arr[i] != ' ') {
                i++;
                len++;
            }

            if (len > 0) {
                for (int j = len, m = k - len; j > 0; j--, m++) {
                    res[m] = arr[i - j];
                }

                if (i >= arr.length) {
                    k -= len;
                    break;
                }
                k = k - len - 1;
                res[k] = ' ';
                len = 0;
            }
        }

        int start = res[k] == ' ' ? k + 1 : k;
        int totalLength = res[k] == ' ' ? res.length - k - 1 : res.length - k ;

        return new String(res, start, totalLength);
    }

//    public static String reverseWords(String s) {
//        String[] split = s.split("\\s+");
//        StringBuilder res = new StringBuilder(s.length());
//
//        res.append(split[split.length - 1]);
//        for (int i = split.length - 2; i >= 0; i--) {
//            if(split[i].length() > 0) {
//                res.append(' ')
//                        .append(split[i]);
//            }
//        }
//
//        return res.toString();
//    }

//    public static String reverseWords(String s) {
//        StringBuilder res = new StringBuilder(s.length());
//        StringBuilder temp = new StringBuilder();
//
//        char[] charArray = s.toCharArray();
//        for(int i = charArray.length - 1; i >= 0; i--) {
//            if(charArray[i] != ' ') {
//                temp.append(charArray[i]);
//            } else {
//                if(!temp.isEmpty()) {
//                    temp.reverse();
//                    if(!res.isEmpty()) {
//                        res.append(' ');
//                    }
//                    res.append(temp);
//                    temp = new StringBuilder();
//                }
//            }
//        }
//
//        if(!temp.isEmpty()) {
//            temp.reverse();
//            if(!res.isEmpty()) {
//                res.append(' ');
//            }
//
//            res.append(temp);
//        }
//
//        return res.toString();
//    }
}
