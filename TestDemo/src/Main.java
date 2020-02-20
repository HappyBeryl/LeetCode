import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

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

    public static void main414(String[] args) {
        int[] array = new int[]{2,2,3,1,4};
        int ret = thirdMax1(array);
        System.out.println(ret);
//        System.out.println(Arrays.toString(ret));
    }

    /**
     * 两数之和
     */
    public int[] twoSum(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++) {
            for(int j = i+1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    return new int[]{i,j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     *67.二进制求和
     */
    public static String addBinary(String a, String b) {
        //补齐0
        while(a.length() < b.length()) {
            a =  "0" + a;
        }
        while(b.length() < a.length()) {
            b = "0" + b;
        }

        StringBuffer sb = new StringBuffer();
        int count = 0; //进位
        int i = a.length()-1;
        int j = b.length()-1;
        while(i >= 0 && j >= 0) {
            int sum = count;
            sum += (int)(a.charAt(i)) - 48;
            sum += (int)(b.charAt(j)) - 48;
            count = sum/2;
            sum = sum%2; //和
            sb.append(sum);
            i--;
            j--;
        }
        sb.append(count == 1 ? count : "");
        return sb.reverse().toString();
    }

    public static void main67(String[] args) {
        String str = addBinary("11", "1");
        System.out.println(str);
    }


    /**
      8.字符串转换为整数
      注意极限值的取值范围
     "2147483646""2147483646"
     */
    public static int myAtoi(String str) {
        //判断空格
        if(str == null) {
            return 0;
        }
        str = str.trim();
        if(str.length() == 0) {
            return 0;
        }
        //判断正负
        int flg = 1;
        int i = 0;
        if(str.charAt(i) == '-') {
            flg = -1;
            i++;
        }else if(str.charAt(i) == '+') {
            i++;
        }

        //得到数字
        int ret = 0;
        for(; i < str.length(); i++) {
            //处理其他非数字
            if(str.charAt(i) < '0' || str.charAt(i) > '9') {
                return ret*flg;
            }
            //处理极限值
            if(flg == 1 && ret > Integer.MAX_VALUE/10 ||
                    (flg == 1 && ret == Integer.MAX_VALUE/10 && str.charAt(i) > '7')) {
                return Integer.MAX_VALUE;
            }
            if(flg == -1 && ret > Integer.MAX_VALUE/10 ||
                    (flg == -1 && ret == Integer.MAX_VALUE/10 && str.charAt(i) > '7')) {
                return Integer.MIN_VALUE;
            } else {
                ret = ret*10 + str.charAt(i) - '0';
            }
        }
        return ret * flg;
    }


    public static void main8(String[] args) {
        System.out.println(myAtoi("2147483648"));
    }

    /*
    34.在排序数组查找元素的第一个和最后一个位置
     */

    public int[] searchRange(int[] nums, int target) {
        int ret[] = new int[]{-1,-1};
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == target) {
                ret[0] = i;
                break;
            }
        }

        //没找到
        if(ret[0] == -1) {
            return ret;
        }

        for(int j = nums.length-1; j >= 0; j--) {
            if(nums[j] == target) {
                ret[1] = j;
                break;
            }
        }
        return ret;
    }

    /*
    125.验证回文串
     */
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) i++;
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) j--;
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /*
    443.压缩字符串
     */
    public static String compress(char[] chars) {
        StringBuffer sb = new StringBuffer();
        char ch = chars[0];
        int count = 1;
        for(int i = 1; i < chars.length; i++) {
            if(ch == chars[i]) {
                count++;
            } else {
                if(count == 1) {
                    sb.append(ch);
                } else {
                    sb.append(ch).append(count);
                    ch = chars[i];
                    count = 1;
                }
            }
        }
        sb.append(ch).append(count);
        return sb.toString();
    }

    public static void main443(String[] args) {
        char[] charArray = new char[] {'a','a','b','b','c','c','c','d'};
        String ret = compress(charArray);
        System.out.println(ret);
    }

    /*
    581.最短无序连续子数组 有趣儿
     */
    public static int findUnsortedSubarray(int[] nums) {
        int[] array = Arrays.copyOf(nums,nums.length);
        Arrays.sort(nums);
        int p1 = 0;
        int p2 = nums.length-1;
        while(p1 < p2 && nums[p1] == array[p1]) p1++;
        while(p1 < p2 && nums[p2] == array[p2]) p2--;
        return p2-p1+1;
    }

    public static void main581(String[] args) {
        int[] array = new int[]{2,6,4,8,10,9,15};
        findUnsortedSubarray(array);
    }

    /*
    150.逆波兰表达式求值
    利用栈
     */
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Integer num1 = 0;
        Integer num2 = 0;
        for(String s : tokens) {
            switch (s) {
                case "+" :
                     num1 = stack.pop();
                     num2 = stack.pop();
                     stack.push(num2+num1);
                     break;
                case "-" :
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num2-num1);
                    break;
                case "*" :
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num2*num1);
                    break;
                case "/" :
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num2/num1);
                    break;
                default:
                    stack.push(Integer.valueOf(s));
                    break;
            }
        }
        return stack.pop();
    }

    /*
    78.子集 双层List嵌套
     */
        public static List<List<Integer>> subsets ( int[] nums){
            if (nums == null || nums.length < 1) {
                return null;
            }
            List<List<Integer>> ret = new ArrayList<>();
            ret.add(new ArrayList());
            for (int i = 0; i < nums.length; i++) {
                int size = ret.size();
                for (int j = 0; j < size; j++) {
                    List<Integer> tmp = new ArrayList<>(ret.get(j));
                    tmp.add(nums[i]);
                    ret.add(tmp);
                }
            }
            return ret;
        }

    public static void main78(String[] args) {
        subsets(new int[]{1,2,3});
    }

    /*
        1304.和为0的N个唯一整数
     */

    public int[] sumZero(int n) {
        int[] array = new int[n];
        int index = 0;
        for(int i = 1; i <= n/2; i++) {
            array[index++] = i;
            array[index++] = -i;
        }
        return array;
    }


    /*
    杨辉三角
     */

    public static List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> tri = new ArrayList<>();
        if (numRows == 0) {
            return tri;
        }
        tri.add(new ArrayList<>());
        tri.get(0).add(1); //第一行

        for (int i = 1; i <numRows; i++) {
            List<Integer> curRow = new ArrayList<>();
            curRow.add(1);
            List<Integer> prevRow = tri.get(i-1);
            for(int j = 1; j < i; j++) {
                int tmp = prevRow.get(j-1) + prevRow.get(j);
                curRow.add(tmp);
            }
            curRow.add(1);
            tri.add(curRow);
        }
        return tri;
    }

    /*
    383.赎金信
     */

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] buckets = new int[26];
        for(int i = 0; i < magazine.length(); i++) {
            buckets[magazine.charAt(i) - 'a']++;
        }
        for(int i = 0; i < ransomNote.length(); i++) {
            if(--buckets[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    /*
    844. 比较含退格的字符串
     */
    public boolean backspaceCompare(String S, String T) {
        if(S == null || T == null) {
            return false;
        }
        return backspaceCompareChild(S).equals(backspaceCompareChild(T));
    }

    public String backspaceCompareChild(String S){
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if(ch != '#') {
                stack.push(ch);
            } else if(!stack.isEmpty()) {
                stack.pop();
            }
        }
        return String.valueOf(stack);
    }

    /*
    682. 棒球比赛
     */
    //用栈
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        for(String s : ops) {
            if(s.equals("+")) {
                int top1 = stack.pop();
                int top = top1 + stack.peek();
                stack.push(top1);
                stack.push(top);
            } else if(s.equals("C")){
                stack.pop();
            } else if(s.equals("D")) {
                int top = stack.peek();
                stack.push(top*2);
            } else {
                stack.push(Integer.valueOf(s));
            }
        }
        int score = 0;
        for(int num : stack) {
            score += num;
        }

        return score;
    }

    /*
    栈的压入弹出序列:辅助栈，把pushA的元素压入栈中，如果栈顶元素和popA
     */
    public static boolean IsPopOrder(int [] pushA,int [] popA) {
      if (popA == null || pushA == null || popA.length == 0
                || popA.length == 0 || pushA.length != popA.length)
            return false;

        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.empty() && stack.peek() == popA[index]) {
                stack.pop();
                index++;
            }
        }
        return stack.empty();
    }


    public static void main(String[] args) {
        System.out.println(IsPopOrder (new int[]{1,2,3,4,5},new int[]{4,5,3,2,1}));
    }

    /*
    股票价格跨度
    栈
     */
    class StockSpanner {
        Stack<Integer> prices, num;

    public StockSpanner() {
            prices = new Stack();
            num = new Stack();
        }

        public int next(int price) {
            int n = 1;
            while (!prices.isEmpty() && prices.peek() <= price) {
                prices.pop();
                n += num.pop();
            }

            prices.push(price);
            num.push(n);
            return n;
        }
    }

    /*
    933.最近的请求次数
     */
    class RecentCounter {
        Queue<Integer> queue = new LinkedList<>();
        public RecentCounter() {

        }

        public int ping(int t) {
            while(!queue.isEmpty()) {
                if(t-3000 > queue.peek()) {
                    queue.poll();
                } else {
                    break;
                }
            }
            queue.add(t);
            return queue.size();
        }
    }

    /*
    641.设计循环双端队列
     */

    class MyCircularDeque {
        int[] elem;
        int front;
        int rear;
        int UsedSize;
        int cap;
        //front指向队头
        //rear指向队尾可以存放元素的位置
        /** Initialize your data structure here. Set the size of the deque to be k. */
        public MyCircularDeque(int k) {
            cap = k+1;
            elem = new int[cap];
            front = 0;
            rear = 0;
            UsedSize = 0;
        }

        /** Adds an item at the front of Deque. Return true if the operation is successful. */
        public boolean insertFront(int value) {
            if(isFull()) {
                return false;
            }

            front = (front - 1 + cap) % cap;
            elem[front] = value;
            UsedSize++;
            return true;
        }

        /** Adds an item at the rear of Deque. Return true if the operation is successful. */
        public boolean insertLast(int value) {
            if(isFull()) {
                return false;
            }
            elem[rear] = value;
            rear = (rear + 1) % cap;
            UsedSize++;
            return true;
        }

        /** Deletes an item from the front of Deque. Return true if the operation is successful. */
        public boolean deleteFront() {
            if(isEmpty()) {
                return false;
            }
            front = (front + 1) % cap;
            UsedSize--;
            return true;
        }

        /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
        public boolean deleteLast() {
            if(isEmpty()) {
                return false;
            }
            rear = (rear - 1 + cap) % cap;
            UsedSize--;
            return true;
        }

        /** Get the front item from the deque. */
        public int getFront() {
            if(isEmpty()) {
                return -1;
            }

            return elem[front];
        }

        /** Get the last item from the deque. */
        public int getRear() {
            if(isEmpty()) {
                return -1;
            }

            return elem[(rear - 1 + cap) % cap];
        }

        /** Checks whether the circular deque is empty or not. */
        public boolean isEmpty() {
            return rear == front;
        }

        /** Checks whether the circular deque is full or not. */
        public boolean isFull () {
            return (rear+1)%cap == front;
        }
    }

    /*
    反转字符串
     */
    public String reverseOnlyLetter(String S) {
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

    /*
    136.只出现一次的数字
     */
    public int singleNumber(int[] nums) {
        int ret = 0;
        for(int i = 0; i < nums.length; i++) {
            ret ^= nums[i];
        }
        return ret;
    }

    public int singleNumber2(int[] nums) {
        //选用set
        Set<Integer> set = new HashSet<>();
        //遍历数组，如果集合set中有该元素就删除
        for(int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])) {
                set.remove(nums[i]);
            } else {
                set.add(nums[i]);
            }
        }
        //遍历集合，集合中剩下的元素就是那个出现一次的元素
        int ret = 0;
        for(int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])) {
                ret = nums[i];
                break;
            }
        }
        return ret;
    }

    /*
    复制带随机指针的链表
     */

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
        }
    }
    Node head= null;
    public Node copyRandomList(Node head) {
        //new出cur节点
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while(cur != null) {
            Node node = new Node(cur.val);
            map.put(cur, node);
            cur = cur.next;
        }

        //创建对应关系
        cur = head;
        while(cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head); //node 复制的头结点
    }

    /*
    坏掉的键盘
     */
    public static void broken(String[] args) {
        Scanner sn = new Scanner(System.in);
        String str1 = sn.nextLine(); //理想的输入
        String str2 = sn.nextLine(); //实际的输入
        //实际输入的字符串
        Set<Character> setAct = new HashSet<>();
        for(Character ch : str2.toUpperCase().toCharArray()) {
            setAct.add(ch);
        }
        //看实际输入是否包含理想输入，不包含就是换掉的键盘，只记录一次
        Set<Character> setBroken = new HashSet<>();
        for(Character ch1 : str1.toUpperCase().toCharArray()) {
            if(!setAct.contains(ch1) && !setBroken.contains(ch1)) {
                setBroken.add(ch1);
                System.out.print(ch1);
            }
        }
        System.out.println();
    }

    /*
        692.前k个高频单词
     */
    public static List<String> topKFrequent(String[] words, int k) {
        //1、遍历words数组，将单词和每个单词出现的次数进行一一对应
        Map<String,Integer> map = new HashMap<>();

        for (String s : words) {
            //首先看当前字符串s,是否已经在map当中有对应关系
            if(map.get(s) == null) {
                map.put(s,1);
            }else {
                map.put(s,map.get(s)+1);
            }
        }

        PriorityQueue<Map.Entry<String,Integer>> minHeap = new PriorityQueue<>
                (k, new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        //在调整的时候，如果频率相同
                        if(o1.getValue().equals(o2.getValue())) {
                            //o2 > o1调整
                            return o2.getKey().compareTo(o1.getKey());
                        }
                        return o1.getValue().compareTo(o2.getValue());//按照频率升序
                    }
                });

        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            if(minHeap.size() < k) {
                minHeap.add(entry);
            }else {
                Map.Entry<String,Integer> top = minHeap.peek();
                //频率相同
                if(top!=null && top.getValue().equals(entry.getValue())) {
                    //字母小的入  bcdef         abcd
                    if(top.getKey().compareTo(entry.getKey()) > 0) {
                        minHeap.poll();
                        minHeap.add(entry);
                    }
                }else {
                    if(top != null && top.getValue() < entry.getValue()) {
                        minHeap.poll();
                        minHeap.add(entry);
                    }
                }
            }
        }

        System.out.println(minHeap);
        List<String> list = new ArrayList<>();
        for(int i = 0;i < k;i++) {
            String pop = minHeap.peek().getKey();
            list.add(0,pop);
            minHeap.poll();
        }
        return list;
    }











}









