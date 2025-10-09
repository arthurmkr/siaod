package ru.mab.siaod.leetcode;

/**
 * 345. Reverse Vowels of a String
 */
public class ReverseVowelsOfString {
    public static void main(String[] args) {
        System.out.println(reverseVowels("IceCreAm").equals("AceCreIm"));
        System.out.println(reverseVowels("leetcode").equals("leotcede"));
    }

    public static String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int l = 0, r = arr.length - 1;
        boolean[] vovels = new boolean[127];
        vovels['a'] = true;
        vovels['e'] = true;
        vovels['i'] = true;
        vovels['o'] = true;
        vovels['u'] = true;
        vovels['A'] = true;
        vovels['E'] = true;
        vovels['I'] = true;
        vovels['O'] = true;
        vovels['U'] = true;

        while (l < r) {
            if(vovels[arr[l]] && vovels[arr[r]]) {
                char c = arr[l];
                arr[l] = arr[r];
                arr[r] = c;
                l++;
                r--;
            } else {
                if(!vovels[arr[l]]) {
                    l++;
                }

                if(!vovels[arr[r]]) {
                    r--;
                }
            }
        }

        return new String(arr);
    }
}
