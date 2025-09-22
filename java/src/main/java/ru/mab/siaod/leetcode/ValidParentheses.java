package ru.mab.siaod.leetcode;

/**
 * 20. Valid Parentheses
 */
public class ValidParentheses {
    public static void main(String[] args) {
        ValidParentheses alg = new ValidParentheses();
        System.out.println(alg.isValid("()") == true);
        System.out.println(alg.isValid("()[]{}") == true);
        System.out.println(alg.isValid("(]") == false);
        System.out.println(alg.isValid("([])") == true);
        System.out.println(alg.isValid("([)]") == false);
        System.out.println(alg.isValid("([") == false);
    }

    public boolean isValid(String s) {
        int length = s.length();
        if ((length & 1) == 1) {
            return false;
        }

        char[] stack = new char[length / 2];
        int j = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                if (j == stack.length) {
                    return false;
                }
                stack[j++] = c;
            } else {
                if (j == 0) {
                    return false;
                }

                char pop = stack[--j];
                if (!((c == ')' && pop == '(') ||
                        (c == '}' && pop == '{') ||
                        (c == ']' && pop == '['))) {
                    return false;
                }
            }
        }

        return j == 0;
    }
}
