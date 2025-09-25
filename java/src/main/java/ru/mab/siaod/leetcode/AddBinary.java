package ru.mab.siaod.leetcode;

/**
 * 67. Add Binary
 */
public class AddBinary {
    public static void main(String[] args) {
        AddBinary alg = new AddBinary();
        System.out.println(alg.addBinary("0", "0").equals("0"));
        System.out.println(alg.addBinary("1", "1").equals("10"));
        System.out.println(alg.addBinary("1", "0").equals("1"));
        System.out.println(alg.addBinary("0", "1").equals("1"));
        System.out.println(alg.addBinary("11", "1").equals("100"));
        System.out.println(alg.addBinary("10", "1").equals("11"));
        System.out.println(alg.addBinary("1", "11").equals("100"));
        System.out.println(alg.addBinary("1001", "1").equals("1010"));
    }

    public String addBinary(String a, String b) {
        char[] res = new char[Math.max(a.length(), b.length()) + 1];

        char[] tmp1 = a.toCharArray();
        char[] tmp2 = b.toCharArray();
        char[] aArr = a.length() > b.length() ? tmp1 : tmp2;
        char[] bArr = a.length() > b.length() ? tmp2 : tmp1;

        int i = aArr.length - 1;
        int j = bArr.length - 1;
        int k = res.length - 1;
        char carry = '0';
        while (i >= 0 && j >= 0) {
            if (aArr[i] == bArr[j]) { // 0 = 0, 1 = 1 -> c
                res[k] = carry;

                carry = aArr[i];
            } else {
                if (carry == '0') {
                    //a = 0, b = 1 -> 1
                    //a = 1, b = 0 -> 1

                    res[k] = '1';
                } else {
                    //a = 0, b = 1 -> 0
                    //a = 1, b = 0 -> 0
                    res[k] = '0';
                }
            }
            i--;
            j--;
            k--;
        }

        if (i >= 0) {
            while (i >= 0 && carry != '0') {
                if (aArr[i] == '0') {
                    carry = '0';
                    res[k] = '1';
                } else {
                    res[k] = '0';
                }

                i--;
                k--;
            }

            if (i >= 0) {
                System.arraycopy(aArr, 0, res, 1, i+1);
            }
        }

        res[0] = carry;

        int offset = carry == '1' ? 0 : 1;
        return new String(res, offset, res.length - offset);
    }
}
