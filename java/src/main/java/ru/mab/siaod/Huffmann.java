package ru.mab.siaod;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Huffmann {
    public static void main(String[] args) {
        String s = "The output from Huffman's algorithm can be viewed as a variable-length code table for encoding a source symbol (such as a character in a file). The algorithm derives this table from the estimated probability or frequency of occurrence (weight) for each possible value of the source symbol. As in other entropy encoding methods, more common symbols are generally represented using fewer bits than less common symbols. Huffman's method can be efficiently implemented, finding a code in time linear to the number of input weights if these weights are sorted.[2] However, although optimal among methods encoding symbols separately, Huffman coding is not always optimal among all compression methods - it is replaced with arithmetic coding[3] or asymmetric numeral systems[4] if better compression ratio is required";
        int[] counts = calcFrequency(s);

        List<Symbol> sorted = new ArrayList<>();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                sorted.add(new Symbol((char) i, counts[i]));
            }
        }

        Comparator<Symbol> c = Comparator.comparingInt(o -> o.count);
        sorted.sort(c.reversed());

        System.out.println(sorted);
    }

    private static int[] calcFrequency(String s) {
        int[] counts = new int[Character.MAX_VALUE];

        for (char cur : s.toCharArray()) {
            counts[cur]++;
        }

        return counts;
    }

    static class Symbol {
        char symbol;
        int count;

        public Symbol(char symbol, int count) {
            this.symbol = symbol;
            this.count = count;
        }

        @Override
        public String toString() {
            return "\n" + symbol + ": " + count;
        }
    }
}
