/*Design a data structure that supports the following two operations:

        void addWord(word)
        bool search(word)
        search(word) can search a literal word or a regular expression string containing only letters a-z or ..
        A . means it can represent any one letter.

        For example:

        addWord("bad")
        addWord("dad")
        addWord("mad")
        search("pad") -> false
        search("bad") -> true
        search(".ad") -> true
        search("b..") -> true
        Note:
        You may assume that all words are consist of lowercase letters a-z.

        click to show hint.

        You should be familiar with how a Trie works.
        If not, please work on this problem: Implement Trie (Prefix Tree) first.*/

package com.computinglife.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by youngliu on 2/22/16.
 */
public class AddandSearchWordDatastructuredesign {

    private TrieNode root;

    class TrieNode {
        private char c;
        private HashMap<Character, TrieNode> children = new HashMap<>();
        private boolean isLeaf;

        public TrieNode() {
        }

        public TrieNode(char c) {
            this.c = c;
        }
    }

    public AddandSearchWordDatastructuredesign() {
        this.root = new TrieNode();
    }


    // Adds a word into the data structure.
    public void addWord(String word) {
        HashMap<Character, TrieNode> children = root.children;
        TrieNode t = null;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (children.containsKey(c)) {
                t = children.get(c);
            } else {
                t = new TrieNode(c);
                children.put(c, t);
            }
            children = t.children;

            if (i == word.length() - 1) {
                t.isLeaf = true;
            }
        }

    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return dfsSearch(root.children, word, 0);
    }

    private boolean dfsSearch(HashMap<Character, TrieNode> children, String word, int start) {
        if (start == word.length()) {
            if (children.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
        char c = word.charAt(start);

        if (children.containsKey(c)) {
            if (start == word.length() - 1 && children.get(c).isLeaf) {
                return true;
            }
            return dfsSearch(children.get(c).children, word, start + 1);
        } else if (c == '.') {
            boolean result = false;
            for (Map.Entry<Character, TrieNode> child : children.entrySet()) {
                if (start == word.length() - 1 && child.getValue().isLeaf) {
                    return true;
                }

                if (dfsSearch(child.getValue().children, word, start + 1)) {
                    result = true;
                }
            }
            return result;
        } else {
            return false;
        }
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
