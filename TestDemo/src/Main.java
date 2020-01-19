import java.util.*;

public class Main {
    /**
     * 118.杨辉三角 [list]
     * 两个List嵌套相当于一个二维数组，先放一个1，再放中间元素[i] = [i-1,j]+[i-1,j-1]
     * 再放一个1
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if(numRows == 0) {
            return triangle;
        }
        triangle.add(new ArrayList<>()); //0行
        triangle.get(0).add(1);
        //行数
        for (int i = 1; i < numRows; i++) {
            List<Integer> curRow = new ArrayList<>();
            curRow.add(1);
            //上一行
            List<Integer> prevRow = triangle.get(i-1);
            //第i行第j列
            for (int j = 1; j < i; j++) {
                int tmp = prevRow.get(j-1)+prevRow.get(j);
                curRow.add(tmp);
            }
            curRow.add(1);
            triangle.add(curRow);
        }
        return triangle;
    }

    /**
     * 20.有效的括号[栈]
     * 若是左括号，入栈；若是有括号拿出栈顶元素看是否与之匹配
     * 注意"(((((((("和")))))))"的情况
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if(stack.empty()) {
                    System.out.println("右括号多");
                    return false;
                }
                char top = stack.peek();
                if(top == '(' && ch == ')' ||top == '{' && ch == '}'
                        ||top == '[' && ch == ']'){
                    stack.pop();
                } else {
                    System.out.println("右括号匹配错误");
                    return false;
                }
            }
        }

        if(!stack.empty()) {
            System.out.println("左括号多");
            return false;
        }
        return true;
    }

    /**
     * 队列实现栈
     * 入队：入不为空的队列
     * 出队：n-1到另一个队列，出最后一个
     */
    class MyStack {

        public Queue<Integer> queue1 = new LinkedList<>();
        public Queue<Integer> queue2 = new LinkedList<>();


        /** Initialize your data structure here. */
        public MyStack() {

        }

        /** Push element x onto stack. */
        public void push(int x) {
            if(!queue1.isEmpty()) {
                queue1.offer(x);
            }else if(!queue2.isEmpty()){
                queue2.offer(x);
            }else {
                queue1.offer(x);
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            if(!queue1.isEmpty()) {
                int size1 = queue1.size()-1;
                //因为：queue1.poll()--》queue1.size()发生改变
                for (int i = 0; i < size1; i++) {
                    queue2.offer(queue1.poll());
                }
                return queue1.poll();
            }
            if(!queue2.isEmpty()){
                int size2 = queue2.size()-1;
                for (int i = 0; i < size2; i++) {
                    queue1.offer(queue2.poll());
                }
                return queue2.poll();
            }
            return -1;
        }

        /** Get the top element. */
        public int top() {
            if(!queue1.isEmpty()) {
                int tmp = 0;
                int size1 = queue1.size();
                for (int i = 0; i < size1; i++) {
                    tmp = queue1.poll();
                    queue2.offer(tmp);
                }
                return tmp;
            }
            if(!queue2.isEmpty()){
                int tmp2 = 0;
                int size2 = queue2.size();
                for (int i = 0; i < size2; i++) {
                    tmp2 = queue2.poll();
                    queue1.offer(tmp2);
                }
                return tmp2;
            }
            return -1;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            if(queue1.isEmpty() && queue2.isEmpty()){
                return true;
            }
            return false;
        }
    }

    /**
     * 栈实现队列
     * 入栈：入到stack1
     * 出栈：把不为空的栈全部元素放入另一个栈，再出栈顶元素
     */
    class MyQueue {

        private Stack<Integer> stack1;

        private Stack<Integer> stack2;

        /** Initialize your data structure here. */
        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if(stack2.empty()) {
                while (!stack1.empty()) {
                    stack2.push(stack1.pop());
                }
            }
            if(!stack2.empty()) {
                return stack2.pop();
            }
            return -1;
        }

        /** Get the front element. */
        public int peek() {
            if(stack2.empty()) {
                while (!stack1.empty()) {
                    stack2.push(stack1.pop());
                }
            }
            if(!stack2.empty()) {
                return stack2.peek();
            }
            return -1;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack2.empty() && stack1.empty();
        }
    }

    /**
     * 实现一个最小栈
     * 入栈时:先入stack1。minstack如果为空，入栈；如果不为空，
     * 要保证栈顶元素是当前栈内最小的元素，如果要入栈的元素比栈顶元素大，不入栈。
     * 出栈时:若stack1和minstack栈顶元素相同，则同时出栈；否则只出stack1栈中的元素。
     */

    class MinStack {
        /** initialize your data structure here. */
        public Stack<Integer> stack = new Stack<>();
        public Stack<Integer> MinStack = new Stack<>();

        MinStack() {

        }

        void push(int x) {
            stack.push(x);
            if(MinStack.empty()) {
                MinStack.push(x);
            } else {
                int tmp = MinStack.peek();
                if(x <= tmp) {
                    MinStack.push(x);
                }
            }
        }

        void pop() {
            int tmp = stack.pop();
            if(tmp == MinStack.peek()) {
                MinStack.pop();
            }
        }

        int top() {
            return stack.peek();
        }

        int getMin() {
            return MinStack.peek();
        }
    }








}
