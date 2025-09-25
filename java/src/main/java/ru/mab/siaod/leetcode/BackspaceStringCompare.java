package ru.mab.siaod.leetcode;

/**
 * 844. Backspace String Compare
 */
public class BackspaceStringCompare {
    public static void main(String[] args) {
        BackspaceStringCompare alg = new BackspaceStringCompare();
        System.out.println(alg.backspaceCompare("ab#c", "ad#c") == true);
        System.out.println(alg.backspaceCompare("ab##", "c#d#") == true);
        System.out.println(alg.backspaceCompare("nzp#o#g", "b#nzp#o#g") == true);
        System.out.println(alg.backspaceCompare("a#c", "b") == false);
        System.out.println(alg.backspaceCompare("bxj##tw", "bxo#j##tw") == true);
        System.out.println(alg.backspaceCompare("a##c", "#a#c") == true);
        System.out.println(alg.backspaceCompare("xywrrmp", "xywrrmu#p") == true);
        System.out.println(alg.backspaceCompare("ab#c", "ad#c") == true);
        System.out.println(alg.backspaceCompare("bxj##tw", "bxj###tw") == false);
    }

    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;

        while (i >= 0 || j >= 0) {
            if (i >= 0 && s.charAt(i) == '#') {
                i = evalNextIndex(i, s);
            }

            if (j >= 0 && t.charAt(j) == '#') {
                j = evalNextIndex(j, t);
            }

            if (j < 0 && i < 0) {
                return true;
            } else if(i < 0 || j < 0) {
                return false;
            } else if (s.charAt(i) != t.charAt(j)) {
                return false;
            }

            i--;
            j--;
        }
        return true;
    }

    private int evalNextIndex(int i, String s) {
        int cnt = 0;
        do {
            if (s.charAt(i) == '#') {
                cnt++;
                i--;
            } else {
                if (cnt == 0) {
                    break;
                }
                cnt--;
                i--;
            }
        } while (i >= 0);

        return i;
    }
}
