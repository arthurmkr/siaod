package ru.mab.siaod.leetcode;

import java.util.Stack;

/**
 * 155. Min Stack
 */
public class MinStack {
    private Stack<int[]> vals;

    public MinStack() {
        vals = new Stack<>();
    }

    public void push(int val) {
        vals.push(new int[]{val, vals.empty() ? val : Math.min(vals.peek()[1], val)});
    }

    public void pop() {
        vals.pop();
    }

    public int top() {
        return vals.peek()[0];
    }

    public int getMin() {
        return vals.peek()[1];
    }
}
