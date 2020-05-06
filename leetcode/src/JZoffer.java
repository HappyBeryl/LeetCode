import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class JZoffer {
    /*
    03. 数组中重复的元素
     */
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            if(!set.add(nums[i])) {
                return nums[i];
            }
        }
        return -1;
    }

    /*
    二维数组中的查找
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) {
            return false;
        }
        int m = matrix.length; //二维数组的行数
        int n = matrix[0].length; // 二维数组的列数
        int row = 0;
        int col = n - 1;
        while(row < m && col >= 0) {
            if(matrix[row][col] == target) {
                return true;
            } else if(matrix[row][col] > target) {
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
        if(s == null || s.length() < 0) {
            return null;
        }
        char[] array = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < array.length; i++) {
            if(array[i] == ' ') {
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

    }
    public int[] reversePrint(ListNode head) {
        int s = 0;
        Math.pow(0,3);
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while(cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        int len = stack.size();
        int[] array = new int[len];
        for(int i = 0; i < len; i++) {
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
      TreeNode(int x) { val = x; }
  }
    int preIndex= 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        return buildTreeChild(preorder, inorder, 0, inorder.length-1);
    }

    public TreeNode buildTreeChild(  int[] preorder,int[] inorder,int inbegin, int inend) {
        //终止条件 判断是否有左树或者右树
        if (inbegin > inend) {
            return null;
        }
        //在前序遍历中new 根节点
        TreeNode root = new TreeNode(preorder[preIndex]);
        //在中序遍历找到pi所在下标元素
        int rootIndex = findIndexOfInorder(inorder, inbegin, inend, preorder[preIndex]);
        preIndex++;
        root.left = buildTreeChild(preorder, inorder, inbegin, rootIndex-1);
        root.right = buildTreeChild(preorder, inorder, rootIndex+1, inend);
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
    public TreeNode buildTreeChild1(int[] inorder, int[] postorder,int inbegin, int inend) {

        //终止条件 判断是否有左树或者右树
        if (inbegin > inend) {
            return null;
        }

        //new 根节点
        TreeNode root = new TreeNode(postorder[postIndex]);

        //在中序遍历找到pi所在下标元素
        int rootIndex = findIndexOfInorder(inorder, inbegin, inend, postorder[postIndex]);
        postIndex--;
        root.right = buildTreeChild(inorder,postorder, rootIndex+1,inend);
        root.left = buildTreeChild(inorder, postorder, inbegin,rootIndex-1);
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

        postIndex = postorder.length-1;
        return buildTreeChild1(inorder, postorder, 0, inorder.length-1);
    }

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


    public int fib(int n) {
        int f1 = 0;
        int f2 = 1;
        int f3 = 0;
        for(int i = 0; i < n; i++) {
            f3 = (f1 + f2) % 1000000007;
            f1 = f2;
            f2 = f3;
        }
        return f1;
    }

    public int numWays(int n) {
        //台阶-跳法，1-1,2-2,3-3,4-5
        if(n == 1 || n == 2) {
            return n;
        }
        int f1 = 1;
        int f2 = 2;
        int f3 = 1;
        for(int i = 3; i <= n; i++) {
            f3 = (f1 + f2) % 1000000007;
            f1 = f2;
            f2 = f3;
        }
        return f3;
    }

    public int minArray(int[] numbers) {
        //1 3 3
        int i = 0;
        int j = numbers.length-1;
        while(i < j) {
            int mid = i + (j - i)/2; //防止溢出 超出范围
            //  int mid = (i+j) / 2;
            if(numbers[mid] < numbers[j]) {
                j = mid;
            } else if(numbers[mid] > numbers[j]) {
                i = mid + 1;
            } else {
                j--;
            }
        }
        return numbers[i];
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || board[0] == null || board.length == 0 || board[0].length == 0 ||        word == null || word.equals("")) {
            return false;
        }
        char[] words = word.toCharArray();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(existChild(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    public boolean existChild(char[][] board, char[] word, int i, int j, int k) {
        if(i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) {
            return false;
        }
        if(k == word.length - 1) return true;
        char tmp = board[i][j];
        // 修改为字符 '/' ，代表此元素已访问过，防止之后搜索时重复访问。
        board[i][j] = '/';
        boolean res = existChild(board, word, i + 1, j, k + 1) ||
                existChild(board, word, i - 1, j, k + 1) ||
                existChild(board, word, i, j + 1, k + 1) ||
                existChild(board, word, i , j - 1, k + 1);
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
        if(head == null) {
            return null;
        }
        ListNode cur = head;
        ListNode prev = head;
        while(cur != null) {
            if(cur.val == val) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
        //处理头节点
        if(head.val == val) {
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
        while(n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
    public int movingCountChild(int i, int j, int m, int n, int k, boolean[][] arr) {
        if(i < 0 || j < 0 || i >= m || j >= n || bit(i) + bit(j) > k ||
                arr[i][j] == true) {
            return 0;
        }
        arr[i][j] = true;
        return 1 + movingCountChild(i+1, j, m, n, k, arr) +
                movingCountChild(i-1, j, m, n, k, arr) +
                movingCountChild(i, j+1, m, n, k, arr) +
                movingCountChild(i, j-1, m, n, k, arr);
    }

    public int[] printNumbers(int n) {
        int num = 1;
        int i = 0;
        while(i++ < n) {
            num *= 10;
        }
        int[] arr = new int[num];
        for(i = 0; i < num-1; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }


}
