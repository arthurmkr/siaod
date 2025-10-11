package ru.mab.siaod.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * 208. Implement Trie (Prefix Tree)
 */
public class ImplementTrie {

    public static final String SEARCH = "search";
    public static final String INSERT = "insert";
    public static final String STARTS_WITH = "startsWith";
    public static final String CREATE = "Trie";

    public static void main(String[] args) {
        List<String> cmds = List.of(CREATE, INSERT, SEARCH, SEARCH, STARTS_WITH, INSERT, SEARCH);
        List<String> params = List.of("", "apple", "apple", "app", "app", "app", "app");
        Boolean[] output = new Boolean[]{null, null, true, false, true, null, true};
        Boolean[] actualOutput = new Boolean[output.length];

        Trie trie = new Trie();
        for (int i = 0; i < cmds.size(); i++) {
            switch (cmds.get(i)) {
                case INSERT -> trie.insert(params.get(i));
                case STARTS_WITH -> actualOutput[i] = trie.startsWith(params.get(i));
                case SEARCH -> actualOutput[i] = trie.search(params.get(i));
            }
        }

        System.out.println(Arrays.equals(output, actualOutput));
    }
}
