import java.lang.reflect.Array;
import java.util.*;

public class Main {
    /**
     * 118.杨辉三角 [list]
     * 两个List嵌套相当于一个二维数组，先放一个1，再放中间元素[i] = [i-1,j]+[i-1,j-1]
     * 再放一个1
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if (numRows == 0) {
            return triangle;
        }
        triangle.add(new ArrayList<>()); //0行
        triangle.get(0).add(1);
        //行数
        for (int i = 1; i < numRows; i++) {
            List<Integer> curRow = new ArrayList<>();
            curRow.add(1);
            //上一行
            List<Integer> prevRow = triangle.get(i - 1);
            //第i行第j列
            for (int j = 1; j < i; j++) {
                int tmp = prevRow.get(j - 1) + prevRow.get(j);
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
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if (stack.empty()) {
                    System.out.println("右括号多");
                    return false;
                }
                char top = stack.peek();
                if (top == '(' && ch == ')' || top == '{' && ch == '}'
                        || top == '[' && ch == ']') {
                    stack.pop();
                } else {
                    System.out.println("右括号匹配错误");
                    return false;
                }
            }
        }

        if (!stack.empty()) {
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


        /**
         * Initialize your data structure here.
         */
        public MyStack() {

        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            if (!queue1.isEmpty()) {
                queue1.offer(x);
            } else if (!queue2.isEmpty()) {
                queue2.offer(x);
            } else {
                queue1.offer(x);
            }
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            if (!queue1.isEmpty()) {
                int size1 = queue1.size() - 1;
                //因为：queue1.poll()--》queue1.size()发生改变
                for (int i = 0; i < size1; i++) {
                    queue2.offer(queue1.poll());
                }
                return queue1.poll();
            }
            if (!queue2.isEmpty()) {
                int size2 = queue2.size() - 1;
                for (int i = 0; i < size2; i++) {
                    queue1.offer(queue2.poll());
                }
                return queue2.poll();
            }
            return -1;
        }

        /**
         * Get the top element.
         */
        public int top() {
            if (!queue1.isEmpty()) {
                int tmp = 0;
                int size1 = queue1.size();
                for (int i = 0; i < size1; i++) {
                    tmp = queue1.poll();
                    queue2.offer(tmp);
                }
                return tmp;
            }
            if (!queue2.isEmpty()) {
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

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            if (queue1.isEmpty() && queue2.isEmpty()) {
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

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            stack1.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            if (stack2.empty()) {
                while (!stack1.empty()) {
                    stack2.push(stack1.pop());
                }
            }
            if (!stack2.empty()) {
                return stack2.pop();
            }
            return -1;
        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (stack2.empty()) {
                while (!stack1.empty()) {
                    stack2.push(stack1.pop());
                }
            }
            if (!stack2.empty()) {
                return stack2.peek();
            }
            return -1;
        }

        /**
         * Returns whether the queue is empty.
         */
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
        /**
         * initialize your data structure here.
         */
        public Stack<Integer> stack = new Stack<>();
        public Stack<Integer> MinStack = new Stack<>();

        MinStack() {

        }

        void push(int x) {
            stack.push(x);
            if (MinStack.empty()) {
                MinStack.push(x);
            } else {
                int tmp = MinStack.peek();
                if (x <= tmp) {
                    MinStack.push(x);
                }
            }
        }

        void pop() {
            int tmp = stack.pop();
            if (tmp == MinStack.peek()) {
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

    /**
     * 58. 最后一个单词的长度
     */
    public static int lengthOfLastWord(String s) {
        if (s == null) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                count++;
            } else {
                count = 0;
            }
        }
        return count;
    }

    public static int lengthOfLastWord1(String s) {
        if (s == null && s.length() == 0) {
            return 0;
        }
        s.trim();
        int last = s.length() - 1;
        int count = 0;
        for (int i = last; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (count == 0) {
                    continue;
                }
                break;
            }
            count++;
        }
        return count;
    }

    public static void main58(String[] args) {
        String s = "a";
        int a = lengthOfLastWord1(s);
        System.out.println(a);
    }

    /**
     * 88.合并两个有序数组
     * 从后往前比较，把较大的放在nums1的最后
     * 当nums2没有比较完，直接加在最后（有序数组）
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int last = nums1.length - 1;
        while (i >= 0 && j >= 0) {
            if (nums2[j] > nums1[i]) {
                nums1[last--] = nums2[j--];
            } else {
                nums1[last--] = nums1[i--];
            }
        }

        while (i < 0 && j >= 0) {
            nums1[last--] = nums2[j--];
        }
    }

    /**
     * 回文数
     */

    public boolean isPalindrome(int x) {
        String s = x + "";
        int leftIndex = 0;
        int rightIndex = s.length() - 1;
        while (leftIndex <= rightIndex) {
            if (s.charAt(leftIndex++) != s.charAt(rightIndex--)) {
                return false;
            }
        }
        return true;
    }

    //法2：以上做法转化为字符串会消耗额外的空间，利用回文数的折叠相等，翻转后半部分
    public boolean isPalindrome1(int x) {
        //处理特殊情况
        if (x < 0 || x % 10 == 0 && x != 0) {
            return false;
        }
        //翻转后半部分
        int reverseNum = 0;
        while (x > reverseNum) {
            reverseNum = reverseNum * 10 + x % 10;
            x = x / 10;
        }
        //处理偶数位数字和奇数位数字
        return reverseNum == x || reverseNum / 10 == x;
    }

    /**
     * 217.存在重复元素
     */
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }


    public static boolean isLongPressedName1(String name, String typed) {
        if (typed == null || typed.length() == 0) {
            return false;
        }
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (i < typed.length() - 1) {
            if (typed.charAt(i) != typed.charAt(i + 1)) {
                sb.append(typed.charAt(i));
                sb.append(typed.charAt(i + 1));
                i++;
            } else {
                while ((typed.charAt(i) + "") == (typed.charAt(i + 1) + "")) {
                    i++;
                }
            }
        }
        if (typed.charAt(i) != typed.charAt(i - 1)) {
            sb.append(typed.charAt(i));
        }
        return sb.toString().equals(name);
    }

    public static boolean isLongPressedName(String name, String typed) {
        int len_n = name.length();
        int len_t = typed.length();
        if (len_n > len_t) {
            return false;
        }
        int i = 0;
        int j = 0;
        while (i < len_n && j < len_t) {
            if (name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        return i == len_n;
    }

    /**
     * 977.有序数组的平方
     */
    public int[] sortedSquares(int[] A) {
        int tmp[] = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            tmp[i] = A[i] * A[i];
        }
        Arrays.sort(tmp);
        return tmp;
    }

    //法2：双指针 i记录负数 j记录正数
    public static int[] sortedSquares1(int[] A) {
        int j = 0;
        int ret[] = new int[A.length];
        int n = 0;
        while (A[j] < 0) {
            j++;
        }
        int i = j - 1;
        while (i >= 0 && j < A.length) {
            if (A[i] * A[i] < A[j] * A[j]) {
                ret[n] = A[i] * A[i];
                i--;
            } else {
                ret[n] = A[j] * A[j];
                j++;
            }
            n++;
        }
        if (i < 0 && j < A.length && n < A.length) {
            while (j < A.length) {
                ret[n] = A[j] * A[j];
                j++;
                n++;
            }
        }
        if (i >= 0 && j >= A.length && n < A.length) {
            while (i >= 0) {
                ret[n] = A[i] * A[i];
                i--;
                n++;
            }
        }
        return ret;
    }

    public static void main977(String[] args) {
        int[] array = new int[]{-4, -1, 0, 3, 10};
        int ret[] = sortedSquares1(array);
        System.out.println(Arrays.toString(ret));
    }

    /**
     * 917.仅仅翻转字母
     */

    //双指针
    public static String reverseOnlyLetters(String S) {
        int j = S.length() - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (Character.isLetter(S.charAt(i))) {
                while (!Character.isLetter(S.charAt(j))) {
                    j--;
                }
                sb.append(S.charAt(j--));
            } else {
                sb.append(S.charAt(i));
            }
        }
        return sb.toString();
    }

    //用栈
    public String reverseOnlyLetters1(String S) {
        Stack stack = new Stack();
        for(int i = 0; i < S.length(); i++) {
            if(Character.isLetter(S.charAt(i))) {
                stack.push(S.charAt(i));
            }
        }

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < S.length(); i++) {
            if(Character.isLetter(S.charAt(i))) {
                sb.append(stack.pop());
            } else {
                sb.append(S.charAt(i));
            }
        }
        return sb.toString();
    }


    public static void main917(String[] args) {
        String s = reverseOnlyLetters("a-bj987");
        System.out.println(s);
    }

    /**
     * 905.按奇偶排序数组
     */

    public static int[] sortArrayByParity(int[] A) {
        int i = 0;
        int j = A.length-1;
        while(i < j) {
            if(A[i]%2 != 0 && A[j]%2 == 0) {
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
//            if(A[i]%2 == 0 && A[j]%2 != 0) {
//                i++;
//                j--;
//            }
//            if(A[i]%2 == 0 && A[j]%2 == 0) i++;
//            if (A[i]%2 != 0 && A[j]%2 != 0) j--;
            if(A[i]%2 == 0) i++;
            if (A[i]%2 != 0) j--;
        }
        return  A;
    }


    public static void main905(String[] args) {
        int[] array = new int[]{3,1,2,4};
        int[] ret = sortArrayByParity(array);
        System.out.println(Arrays.toString(ret));
    }

    /**
     * 寻找数组的中心索引
     */
    //利用leftSum不断动态变换，并且两边相等可得leftSum = S - nums[i] - leftSum符合情况
    public int pivotIndex(int[] nums) {
        if(nums.length < 3) {
            return -1;
        }
        int S = 0;
        for(int i = 0; i < nums.length; i++) {
            S += nums[i];
        }
        int leftSum = 0;
        for(int i = 0; i < nums.length; i++) {
            if(S - nums[i] - leftSum == leftSum) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    /**
     * 66.加1
     */
    //999为例
    public int[] plusOne(int[] digits) {
        for(int i = digits.length-1; i>= 0; i--) {
            digits[i]++;
            digits[i] %= 10;
            if(digits[i]%10 !=0)
                return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    /**
     * 414.第三大的数
     */


    static int useSized = 0;
    public static int[] keepOnly(int[] nums) {
        int[] array = new int[nums.length];
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length-1) {
            if (nums[i] == nums[i+1]) {
                while (nums[i] == nums[i+1]) {
                    i++;
                }
                array[useSized++] = nums[i++];
            } else {
                    array[useSized++] = nums[i++];
            }
        }
        if(nums[nums.length-1] != nums[nums.length-2]) {
            array[useSized++] = nums[nums.length-1];
        }
        return array;
    }

    //先去掉重复元素，再进行排序
    public static int thirdMax1(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return nums[0] > nums[1] ?nums[0] : nums[1];
        int[] array = keepOnly(nums);
      //  Arrays.sort(array);
        return array[useSized-3];
    }

    public static void main(String[] args) {
        int[] array = new int[]{2,2,3,1,4};
        int ret = thirdMax1(array);
        System.out.println(ret);
//        System.out.println(Arrays.toString(ret));
    }








}









