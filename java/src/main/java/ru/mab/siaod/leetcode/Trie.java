package ru.mab.siaod.leetcode;

public class Trie {
    private TrieNode root = new TrieNode();

    public Trie() {

    }

    public void insert(String word) {
        char[] arr = word.toCharArray();
        TrieNode cur = root;

        TrieNode prev;
        for (char c : arr) {
            prev = cur;
            cur = cur.append(c, word);
            if (prev.word != null) {
                break;
            }
        }
    }

    public boolean search(String word) {
        char[] arr = word.toCharArray();
        TrieNode cur = root;

        int i = 0;
        for (char c : arr) {


            if (cur == null) {
                return false;
            }

            if (cur.word != null) {
                int j;
                for (j = i; j < arr.length && j < cur.word.length(); j++) {
                    if (arr[j] != cur.word.charAt(j)) {
                        return false;
                    }
                }

                return j == arr.length && j == cur.word.length();
            }
            cur = cur.get(c);
            i++;
        }
        return true;
    }

    public boolean startsWith(String prefix) {
        char[] arr = prefix.toCharArray();
        TrieNode cur = root;

        int i = 0;
        for (char c : arr) {
            if (cur == null) {
                return false;
            }

            if (cur.word != null) {
                int j;
                for (j = i; j < arr.length && j < cur.word.length(); j++) {
                    if (arr[j] != cur.word.charAt(j)) {
                        return false;
                    }
                }

                return j == arr.length && j <= cur.word.length();
            }
            cur = cur.get(c);
            i++;
        }
        return true;
    }

    static class TrieNode {
        TrieNode[] children;
        int size;
        String word;
        boolean completeWord;

        public TrieNode append(char c, String word) {
            if (children == null) {
                children = new TrieNode[27];
            }

            int i = c - 'a';
            if (children[i] == null) {
                children[i] = new TrieNode();
                size++;

                if (size == 1) {
                    this.word = word;
                } else {
                    this.word = null;
                }
            }

            return children[i];
        }

        public TrieNode get(char c) {
            if (children == null) {
                return null;
            }
            return children[c - 'a'];
        }
    }
}
