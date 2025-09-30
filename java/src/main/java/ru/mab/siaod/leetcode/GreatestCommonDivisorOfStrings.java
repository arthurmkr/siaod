package ru.mab.siaod.leetcode;

/**
 * 1071. Greatest Common Divisor of Strings
 */
public class GreatestCommonDivisorOfStrings {
    public static void main(String[] args) {
        System.out.println(gcd(4*3*7, 4*3*5));
        GreatestCommonDivisorOfStrings alg = new GreatestCommonDivisorOfStrings();
//        System.out.println(alg.gcdOfStrings("ABCABC", "ABC"));
//        System.out.println(alg.gcdOfStrings("ABABAB", "ABAB"));
//        System.out.println(alg.gcdOfStrings("LEET", "CODE"));
        System.out.println(alg.gcdOfStrings("010201020102", "010101010101010101010101010101010101010101010101010101010101"));
//        System.out.println(alg.gcdOfStrings("TAUXXTAUXXTAUXXTAUXXTAUXX1", "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX6"));
    }

    public static int gcd(int x, int y){
        if(y == 0){
            return x;
        }else{
            return gcd(y, x % y);
        }
    }

    public String gcdOfStrings(String str1, String str2) {
        if(!(str1 + str2).equals(str2 + str1)){
            return "";
        }else{
            return str1.substring(0, gcd(str1.length(), str2.length()));
        }
    }

}
