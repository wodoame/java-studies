package leetcode.temp.stacks;

import java.util.Stack;

class MinStack {
   private final Stack<Integer> stack = new Stack<>();
   private final Stack<Integer> minstack = new Stack<>();
    public MinStack() {
    }

    public void push(int val) {
        if(minstack.isEmpty() || val <= minstack.peek())minstack.push(val);
        stack.push(val);
    }

    public void pop() {
        int lastElement = stack.pop();
        if(lastElement == minstack.peek())minstack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
       return minstack.peek();
    }
}