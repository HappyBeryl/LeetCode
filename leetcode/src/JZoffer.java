import java.util.*;

public class JZoffer {
    /*
    03. 数组中重复的元素
     */
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                return nums[i];
            }
        }
        return -1;
    }

    /*
    二维数组中的查找
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int m = matrix.length; //二维数组的行数
        int n = matrix[0].length; // 二维数组的列数
        int row = 0;
        int col = n - 1;
        while (row < m && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    /*
    替换空格
     */
    public String replaceSpace(String s) {
        //!!!别忘记判断特殊情况
        if (s == null || s.length() < 0) {
            return null;
        }
        char[] array = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == ' ') {
                sb.append("%20");
            } else {
                sb.append(array[i] + "");
            }
        }
        return sb.toString();
    }


    /*
    从尾到头打印链表
     */
    static class ListNode {
        ListNode next;
        int val;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public int[] reversePrint(ListNode head) {
        int s = 0;
        Math.pow(0, 3);
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        int len = stack.size();
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = stack.pop();
        }
        return array;
    }

    /*
    根据前序遍历和中序遍历构建二叉树
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int preIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        return buildTreeChild(preorder, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTreeChild(int[] preorder, int[] inorder, int inbegin, int inend) {
        //终止条件 判断是否有左树或者右树
        if (inbegin > inend) {
            return null;
        }
        //在前序遍历中new 根节点
        TreeNode root = new TreeNode(preorder[preIndex]);
        //在中序遍历找到pi所在下标元素
        int rootIndex = findIndexOfInorder(inorder, inbegin, inend, preorder[preIndex]);
        preIndex++;
        root.left = buildTreeChild(preorder, inorder, inbegin, rootIndex - 1);
        root.right = buildTreeChild(preorder, inorder, rootIndex + 1, inend);
        return root;
    }

    private int findIndexOfInorder(int[] inorder, int inbegin, int inend, int val) {
        for (int i = inbegin; i <= inend; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        return -1;
    }

    public int postIndex = 0;

    public TreeNode buildTreeChild1(int[] inorder, int[] postorder, int inbegin, int inend) {

        //终止条件 判断是否有左树或者右树
        if (inbegin > inend) {
            return null;
        }

        //new 根节点
        TreeNode root = new TreeNode(postorder[postIndex]);

        //在中序遍历找到pi所在下标元素
        int rootIndex = findIndexOfInorder(inorder, inbegin, inend, postorder[postIndex]);
        postIndex--;
        root.right = buildTreeChild(inorder, postorder, rootIndex + 1, inend);
        root.left = buildTreeChild(inorder, postorder, inbegin, rootIndex - 1);
        return root;
    }

    private int findIndexOfInorder1(int[] inorder, int inbegin, int inend, int val) {
        for (int i = inbegin; i <= inend; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        return -1;
    }

    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        if (postorder == null || inorder == null) {
            return null;
        }
        if (postorder.length == 0 || inorder.length == 0) {
            return null;
        }

        postIndex = postorder.length - 1;
        return buildTreeChild1(inorder, postorder, 0, inorder.length - 1);
    }

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


    public int fib(int n) {
        int f1 = 0;
        int f2 = 1;
        int f3 = 0;
        for (int i = 0; i < n; i++) {
            f3 = (f1 + f2) % 1000000007;
            f1 = f2;
            f2 = f3;
        }
        return f1;
    }

    public int numWays(int n) {
        //台阶-跳法，1-1,2-2,3-3,4-5
        if (n == 1 || n == 2) {
            return n;
        }
        int f1 = 1;
        int f2 = 2;
        int f3 = 1;
        for (int i = 3; i <= n; i++) {
            f3 = (f1 + f2) % 1000000007;
            f1 = f2;
            f2 = f3;
        }
        return f3;
    }

    public int minArray(int[] numbers) {
        //1 3 3
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2; //防止溢出 超出范围
            //  int mid = (i+j) / 2;
            if (numbers[mid] < numbers[j]) {
                j = mid;
            } else if (numbers[mid] > numbers[j]) {
                i = mid + 1;
            } else {
                j--;
            }
        }
        return numbers[i];
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || board[0] == null || board.length == 0 || board[0].length == 0 || word == null || word.equals("")) {
            return false;
        }
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existChild(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    public boolean existChild(char[][] board, char[] word, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) {
            return false;
        }
        if (k == word.length - 1) return true;
        char tmp = board[i][j];
        // 修改为字符 '/' ，代表此元素已访问过，防止之后搜索时重复访问。
        board[i][j] = '/';
        boolean res = existChild(board, word, i + 1, j, k + 1) ||
                existChild(board, word, i - 1, j, k + 1) ||
                existChild(board, word, i, j + 1, k + 1) ||
                existChild(board, word, i, j - 1, k + 1);
        board[i][j] = tmp;
        return res;
    }

    public static int PrintOneNum(int n) {
        int count = 0;
        while (n != 0) {
            if (n % 2 == 1) {
                count++;
            }
            n /= 2;
        }
        return count;
    }

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;

    }

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        ListNode prev = head;
        while (cur != null) {
            if (cur.val == val) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
        //处理头节点
        if (head.val == val) {
            head = head.next;
        }
        return head;
    }

    public int movingCount(int m, int n, int k) {
        boolean[][] arr = new boolean[m][n];
        return movingCountChild(0, 0, m, n, k, arr);
    }

    public int bit(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public int movingCountChild(int i, int j, int m, int n, int k, boolean[][] arr) {
        if (i < 0 || j < 0 || i >= m || j >= n || bit(i) + bit(j) > k ||
                arr[i][j] == true) {
            return 0;
        }
        arr[i][j] = true;
        return 1 + movingCountChild(i + 1, j, m, n, k, arr) +
                movingCountChild(i - 1, j, m, n, k, arr) +
                movingCountChild(i, j + 1, m, n, k, arr) +
                movingCountChild(i, j - 1, m, n, k, arr);
    }

    public int[] printNumbers(int n) {
        int num = 1;
        int i = 0;
        while (i++ < n) {
            num *= 10;
        }
        int[] arr = new int[num];
        for (i = 0; i < num - 1; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        if (k <= 0 || head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k - 1; i++) {
            if (fast.next == null) {
                return null;
            }
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;

        }
        return slow;
    }

    //increment函数，若发生进位则一直进行for循环，直到不产生进位则break。
    // 如果i为0（即到了最高位）还发生了进位，则设置isOverflow为true，并返回至主函数的while判断。
    public static void printNumber(int n) {
        StringBuilder str = new StringBuilder();
        // 将str初始化为n个'0'字符组成的字符串
        for (int i = 0; i < n; i++) {
            str.append('0');
        }
        while (!increment(str)) {
            // 去掉左侧的0
            int index = 0;
            while (index < str.length() && str.charAt(index) == '0') {
                index++;
            }
            System.out.println(str.toString().substring(index));
        }
    }

    public static boolean increment(StringBuilder str) {
        boolean isOverflow = false;
        for (int i = str.length() - 1; i >= 0; i--) {
            char s = (char) (str.charAt(i) + 1);
            // 如果s大于'9'则发生进位
            if (s > '9') {
                str.replace(i, i + 1, "0");
                if (i == 0) {
                    isOverflow = true;
                }
            }
            // 没发生进位则跳出for循环
            else {
                str.replace(i, i + 1, String.valueOf(s));
                break;
            }
        }
        return isOverflow;
    }

    public double myPow(double x, int n) {
        if (x == 0) return 0;
        long b = n;
        double res = 1.0;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (b > 0) {
            if ((b & 1) == 1) res *= x; //b%2 == 1
            x *= x; //x = x^2
            b >>= 1; //b /= 2
        }
        return res;
    }

    public double myPow1(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n == -1) return 1 / x;
        double half = myPow(x, n / 2);
        double mod = myPow(x, n % 2);
        return half * half * mod;
    }

    public int cuttingRope(int n) {
        if (n < 2) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(Math.max(j * dp[i - j], j * (i - j)), dp[i]);
            }
        }
        return dp[n];
    }

    /*
    表示数值的字符串
     */

    public boolean isNumber(String s) {
        char[] arr = s.trim().toCharArray();
        boolean markNum = false; //标记是否是数字
        boolean markDon = false; //标记是否为点
        boolean markE = false; //标记是否为指数
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (ch >= '0' && ch <= '9') { //如果是数字
                markNum = true;
            } else if (ch == '.') { //如果是点，前面有点/有e 返回flase
                if (markDon || markE) {
                    return false;
                }
                markDon = true;
            } else if (ch == 'e' || ch == 'E') { //如果是e，前面不是数字返回false
                if (markE || !markNum) {
                    return false;
                }
                markE = true;
                markNum = false; //防止干扰判断e后面是否有数字
            } else if (ch == '+' || ch == '-') { //如果是正负号，不在第一位，前面必须是e
                if (i != 0 && arr[i - 1] != 'e' && arr[i - 1] != 'E') {
                    return false;
                }
            } else { //其他情况返回false
                return false;
            }
        }
        return markNum;
    }

    /*
   奇数在前偶数在后
    */
    public static int[] exchange(int[] nums) {
        int len = nums.length;
        int[] tmp = new int[len];
        int k = len - 1;
        int j = 0;
        for (int i = 0; i < len; i++) {
            if (i % 2 == 1) {
                tmp[j++] = nums[i];
            } else {
                tmp[k--] = nums[i];
            }
        }
        return tmp;
    }

    /*
    链表倒数第k个节点
     */

    public ListNode getKthFromEnd1(ListNode head, int k) {
        if (k <= 0 || head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k - 1; i++) {
            if (fast.next == null) {
                return null;
            }
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /*
 翻转链表
  */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        ListNode prev = null;
        ListNode newHead = null;
        ListNode curNext = null;
        while (cur != null) {
            if (cur.next == null) {
                newHead = cur;
            }
            curNext = cur.next;
            cur.next = prev;
            prev = cur;
            cur = curNext;
        }
        return newHead;
    }

    /*
    合并两个有序链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(-1);
        ListNode tmp = newHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tmp.next = l1;
                l1 = l1.next;
                tmp = tmp.next;
            } else {
                tmp.next = l2;
                l2 = l2.next;
                tmp = tmp.next;
            }
        }
        //如果a为空
        if (l1 == null) {
            tmp.next = l2;
        } else {
            tmp.next = l1;
        }
        return newHead.next;
    }

    /*
    树的子结构
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;
        if (isSameTree(A, B)) return true;
        if (isSubStructure(A.left, B)) return true;
        if (isSubStructure(A.right, B)) return true;
        return false;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q != null || p != null && q == null) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, x = 0;
        int[] res = new int[(r + 1) * (b + 1)];
        while (true) {
            for (int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left to right.
            if ((t++) > b) break;
            for (int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top to bottom.
            if (l > (r--)) break;
            for (int i = r; i >= l; i--) res[x++] = matrix[b][i]; // right to left.
            if (t > (b--)) break;
            for (int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom to top.
            if ((l++) > r) break;
        }
        return res;
    }

    /*
    栈的压入和弹出
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num); // num 入栈
            while (!stack.isEmpty() && stack.peek() == popped[i]) { // 循环判断与出栈
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }

    /*
    从上到下打印二叉树
     */
    public int[] levelOrder1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return new int[0];
        } else {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            list.add(cur.val);
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        int[] ret = new int[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> ret = new ArrayList<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size > 0) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                size--;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            ret.add(list);
        }
        return ret;
    }

    public List<List<Integer>> levelOrder3(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> ret = new ArrayList<>();
        boolean flag = true;
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size > 0) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                size--;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // 判断是否转置（true不转置，false转置）
            if (!flag)
                list = reverse(list);
            // 每遍历一层将flag取反，实现一层转置，下一层不转置；
            flag = !flag;
            ret.add(list);
        }
        return ret;
    }

    public List reverse(List<Integer> list) {
        List<Integer> ret = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            ret.add(list.get(i));
        }
        return ret;
    }

    /*
    二叉树的后序遍历
     */
    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorderChild(postorder, 0, postorder.length - 1);
    }

    public boolean verifyPostorderChild(int[] postorder, int i, int j) {
        if (i >= j) {
            return true;
        }
        int p = i;
        while (postorder[p] < postorder[j]) p++;
        //p号下标元素大于根节点（右树）
        int m = p;
        while (postorder[p] > postorder[j]) p++;
        return p == j && verifyPostorderChild(postorder, i, m - 1) &&
                verifyPostorderChild(postorder, m, j - 1);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p == root || q == root) {
            return root;
        }
        //leftTree和rightTree接收的就是返回的root
        TreeNode leftTree = lowestCommonAncestor(root.left, p, q);
        TreeNode rightTree = lowestCommonAncestor(root.right, p, q);
        if (leftTree != null && rightTree != null) {
            return root;
        }

        if (leftTree != null) {
            return leftTree;
        }

        if (rightTree != null) {
            return rightTree;
        }
        return null;
    }

    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int curSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curSum = Math.max(curSum + nums[i], nums[i]);
        }
        if (curSum > maxSum) {
            maxSum = curSum;
        }
        return maxSum;
    }

    public double Power(double base, int exponent) {
        double res = 0;
        if (equal(base, 0)) {
            return 0;
        }
        if (exponent == 0) {
            return 1.0;
        }
        if (exponent > 0) {
            res = mutiply(base, exponent);
        } else {
            res = mutiply(1 / base, -exponent);
        }
        return res;
    }

    public double mutiply(double base, int e) {
        double sum = 1;
        for (int i = 0; i < e; i++) {
            sum = sum * base;
        }
        return sum;
    }

    public boolean equal(double a, double b) {
        if (a - b < 0.000001 && a - b > -0.000001) {
            return true;
        }
        return false;
    }

    public void printToMaxOfNDigits(int n) {
        int[] array = new int[n];
        if (n <= 0) return;
        printArray(array, 0);
    }

    private void printArray(int[] array, int n) {
        for (int i = 0; i < 10; i++) {
            if (n != array.length) {
                array[n] = i;
                printArray(array, n + 1);
            } else {
                boolean isFirstNo0 = false;
                for (int j = 0; j < array.length; j++) {
                    if (array[j] != 0) {
                        System.out.print(array[j]);
                        if (!isFirstNo0) isFirstNo0 = true;
                    } else {
                        if (isFirstNo0) System.out.print(array[j]);
                    }
                }
                System.out.println();
                return;
            }
        }
    }

    public void deleteNode(ListNode head, ListNode deListNode) {
        if (deListNode == null || head == null)
            return;
        if (head == deListNode) {
            head = null;
        } else {
            // 若删除节点是末尾节点，往后移一个
            if (deListNode.next == null) {
                ListNode pointListNode = head;
                while (pointListNode.next.next != null) {
                    pointListNode = pointListNode.next;
                }
                pointListNode.next = null;
            } else {
                deListNode.val = deListNode.next.val;
                deListNode.next = deListNode.next.next;
            }
        }
    }

    public List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            // 从左往右
            for (int c = c1; c <= c2; c++) {
                res.add(matrix[r1][c]);
            }
            // 从上往下
            for (int r = r1 + 1; r <= r2; r++) {
                res.add(matrix[r][c2]);
            }
            // 判断是否会重复打印
            if (r1 < r2 && c1 < c2) {
                // 从右往左
                for (int c = c2 - 1; c > c1; c--) {
                    res.add(matrix[r2][c]);
                }
                // 从下往上
                for (int r = r2; r > r1; r--) {
                    res.add(matrix[r][c1]);
                }
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return res;
    }

    public static int getUglyNumber(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] arr = new int[n];
        arr[0] = 1;
        int multiply2 = 0;
        int multiply3 = 0;
        int multiply5 = 0;
        for (int i = 1; i < n; i++) {
            int min = Math.min(arr[multiply2] * 2, Math.min(arr[multiply3] * 3, arr[multiply5] * 5));
            arr[i] = min;
            if (arr[multiply2] * 2 == min) {
                multiply2++;
            }
            if (arr[multiply3] * 3 == min) {
                multiply3++;
            }
            if (arr[multiply5] * 5 == min) {
                multiply5++;
            }
        }
        return arr[n - 1];
    }

    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) return -1;
        char[] c = str.toCharArray();
        LinkedHashMap<Character, Integer> hash = new LinkedHashMap<Character, Integer>();
        for (char item : c) {
            if (hash.containsKey(item))
                hash.put(item, hash.get(item) + 1);
            else
                hash.put(item, 1);
        }
        for (int i = 0; i < str.length(); i++) {
            if (hash.get(str.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    int count = 0;

    public int InversePairs(int[] array) {
        if (array == null) return 0;
        mergeSort(array, 0, array.length - 1);
        return count;
    }

    private void mergeSort(int[] data, int start, int end) {
        int mid = (start + end) / 2;
        if (start < end) {
            mergeSort(data, start, mid);
            mergeSort(data, mid + 1, end);
            merge(data, start, mid, end);
        }
    }

    private void merge(int[] data, int start, int mid, int end) {
        int arr[] = new int[end - start + 1];
        int c = 0;
        int s = start;
        int index = mid + 1;
        while (start <= mid && index <= end) {
            if (data[start] < data[index]) {
                arr[c++] = data[start++];
            } else {
                arr[c++] = data[index++];
                count += mid + 1 - start;
                count %= 1000000007;
            }
        }
        while (start <= mid) {
            arr[c++] = data[start++];
        }
        while (index <= end) {
            arr[c++] = data[index++];
        }
        for (int d : arr) {
            data[s++] = d;
        }
    }

    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if (sum < 3) return arrayList;
        int small = 1;
        int big = 2;
        while (small < (sum + 1) / 2) {
            int s = 0;
            for (int i = small; i <= big; i++) {
                s += i;
            }
            if (s == sum) {
                for (int i = small; i <= big; i++) {
                    list.add(i);
                }
                arrayList.add(new ArrayList<>(list));
                list.clear();
                small++;
            } else {
                if (s > sum) {
                    small++;
                } else {
                    big++;
                }
            }
        }
        return arrayList;
    }

    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        if (array == null) return list;
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int s = array[left] + array[right];
            if (s == sum) {
                list.add(array[left]);
                list.add(array[right]);
                return list;
            } else {
                if (s > sum) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return list;
    }

    public static int lastRemain(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        int last = 0;
        for (int i = 2; i <= n; i++) {
            // i 个人时删除数的索引等于 i-1 个人时删除数的索引+k(再对 i 取余)
            last = (last + m) % i;
        }
        return last;
    }

    public int Add(int num1, int num2) {
        while (num2 != 0) {
            // 计算个位
            int temp = num1 ^ num2;
            // 计算进位（1+1）
            num2 = (num1 & num2) << 1;
            num1 = temp;
        }
        return num1;
    }

    public int findDuplicate(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return -1;
        }
        int slow = 0;
        int fast = 0;
        for (int i = 0; i < numbers.length; i++) {
            slow = numbers[slow];
            fast = numbers[numbers[fast]];
            if (fast == slow) {
                fast = 0;
                while (fast != slow) {
                    fast = numbers[fast];
                    slow = numbers[slow];
                }
                return slow;
            }
        }
        return -1;
    }

    public int[] multiply(int[] A) {
        int length = A.length;
        int[] B = new int[length];
        if (length != 0) {
            B[0] = 1;
            //计算下三角连乘
            for (int i = 1; i < length; i++) {
                B[i] = B[i - 1] * A[i - 1];
            }
            int temp = 1;
            //计算上三角连乘
            for (int j = length - 2; j >= 0; j--) {
                temp *= A[j + 1];
                B[j] *= temp;
            }
        }
        return B;
    }

    char[] chars = new char[256];
    StringBuilder sb = new StringBuilder();

    public void Insert(char ch) {
        sb.append(ch);
        chars[ch]++;
    }

    public char FirstAppearingOnce() {
        char[] str = sb.toString().toCharArray();
        for (char c : str) {
            if (chars[c] == 1) {
                return c;
            }
        }
        return '#';
    }

    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if (num == null || size < 1 || num.length < size)
            return list;
        int length = num.length - size + 1;
        for (int i = 0; i < length; i++) {
            int current = size + i;
            int max = num[i];
            for (int j = i; j < current; j++) {
                if (max < num[j]) {
                    max = num[j];
                }
            }
            list.add(max);
        }
        return list;
    }


        public int lengthOfLongestSubstring(String s) {
            if(s.length() == 0) return 0;
            int arr[] = new int[128];
            int l = 0;
            int r = 0;
            int maxLength = 1;
            while(r < s.length()){
                if(arr[s.charAt(r)] == 0){
                    arr[s.charAt(r)]++;
                    maxLength = Math.max(maxLength,r-l+1);
                    r++;
                } else{
                    arr[s.charAt(r)]++;
                    while(arr[s.charAt(r)] > 1){
                        arr[s.charAt(l)]--;
                        l++;
                    }
                    r++;
                }
            }
            return maxLength;
        }


}
