import java.util.*;

public class Solution {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        FindPathChild(root, target, list, result);
        return result;
    }

    private void  FindPathChild(TreeNode root, int target, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> result) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        target -= root.val;
        if (root.left == null && root.right == null && target == 0) {
            result.add(new ArrayList<>(list));
        }
        FindPathChild(root.left, target, list, result);
        FindPathChild(root.right, target, list, result);
        list.remove(list.size()-1);
    }

    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence.length == 0) {
            return false;
        }
        return VerifySquenceOfBSTChild(sequence, 0, sequence.length-1);
    }

    private boolean VerifySquenceOfBSTChild(int[] sequence, int start, int end) {
        if (start >= end) {
            return true;
        }
        int i = 0;
        int root = sequence[end];
        while (i < end && sequence[i] < root) {
            i++;
        }
        for (int j = i; j < end; j++) {
            if (sequence[j] < root) {
                return false;
            }
        }
        return VerifySquenceOfBSTChild(sequence, start, i-1) && VerifySquenceOfBSTChild(sequence,i, end-1);
    }

    public ListNode deleteDuplication(ListNode pHead) {
        ListNode newHead = new ListNode(-1);
        ListNode tmp = newHead;
        ListNode cur = pHead;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
            } else {
                tmp.next = cur;
                cur = cur.next;
                tmp = tmp.next;
            }
        }
        tmp.next = null;
        return newHead.next;
    }

    public void Mirror(TreeNode root) {
        if(root == null){
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        Mirror(root.left);
        Mirror(root.right);
    }

    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        if (isSame(root1, root2)) return true;
        if (isSame(root1.left, root2)) return true;
        if (isSame(root1.right, root2)) return true;
        return false;
    }

    private boolean isSame(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return true;
        }
        if (root1 == null || root1.val != root2.val) {
            return false;
        }
        return  isSame(root1.left, root2.left)&& isSame(root1.right, root2.right);
    }

    public ListNode Merge(ListNode list1,ListNode list2) {
        ListNode newHead = new ListNode(-1);
        ListNode tmp = newHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tmp.next = list1;
                list1 = list1.next;
                tmp = tmp.next;
            } else {
                tmp.next = list2;
                list2 = list2.next;
                tmp = tmp.next;
            }
        }
        //如果a为空
        if (list1 == null) {
            tmp.next = list2;
        } else {
            tmp.next = list1;
        }
        return newHead.next;
    }
    public ListNode ReverseList(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode prev = null;
        ListNode cur = head;
        ListNode newHead = null;
        while (cur != null) {
            ListNode curNext = cur.next;
            if (curNext == null) {
                newHead = cur;
            }
            cur.next = prev;
            prev = cur;
            cur = curNext;
        }
        return newHead;
    }
    public ListNode FindKthToTail(ListNode head,int k) {
        if(head == null || k < 0) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k-1; i++) {
            if (fast.next == null) {
                return null;
            }
            fast = fast.next;
        }
        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public void reOrderArray(int [] array) {
        if (array == null || array.length == 0) {
            return;
        }
        int k = 0;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & 1) == 1) { //奇数
                int tmp = array[i];
                int j = i;
                while (k < j) {
                    array[j] = array[j-1];
                    j--;
                }
            array[k++] = tmp;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    Map<Integer, Integer> map = new HashMap<>();
    public int Fibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int prevv = 0;
        if (map.containsKey(n-2)) {
            prevv = map.get(n-2);
        } else {
            prevv = Fibonacci(n-2);
            map.put(n-2, prevv);
        }
        int prev = 0;
        if (map.containsKey(n-1)) {
            prev = map.get(n-1);
        } else {
            prev = Fibonacci(n-1);
            map.put(n-1, prev);
        }
        return prev+prevv;
    }

     public int Fibonacci1(int n) {
        if(n == 0) return 0;
        int a = 1;
        int b = 1;
        int c = 1;
        while(n > 2) {
            c = a+b;
            a = b;
            b = c;
            n--;
        }
        return c;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    int preIndex = 0;
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre == null || in == null || pre.length == 0 || in.length == 0) {
            return null;
        }
        return reConstructBinaryTreeChild(pre, in, 0, in.length-1);
    }

    private TreeNode reConstructBinaryTreeChild(int[] pre, int[] in, int begin, int end) {
        if (begin > end) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preIndex]);
        int rootIndex = find(in, begin, end, pre[preIndex]);
        preIndex++;
        root.left = reConstructBinaryTreeChild(pre, in, begin, rootIndex-1);
        root.right = reConstructBinaryTreeChild(pre, in, rootIndex+1, end);
        return root;
    }

    private int find(int[] in, int begin, int end, int val) {
        for (int i = begin; i <= end; i++) {
            if (in[i] == val) {
                return i;
            }
        }
        return -1;
    }

    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        printListFromTailToHeadChild(list, listNode);
        return list;
    }

    private void printListFromTailToHeadChild(ArrayList<Integer> list, ListNode listNode) {
        if (listNode == null) {
            return;
        }
        printListFromTailToHeadChild(list, listNode.next);
        list.add(listNode.val);
    }

    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        while (listNode != null) {
            list.add(listNode.val);
            listNode = listNode.next;
        }
        int i = 0;
        int j = list.size()-1;
        while (i < j) {
            int tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
            i++;
            j--;
        }
        return list;
    }

        public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        if (listNode == null) {
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.empty()) {
            list.add(stack.pop());
        }
        return list;
    }

    public String replaceSpace(StringBuffer str) {
        if (str == null) {
            return null;
        }
        //统计空格
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                count++;
            }
        }
        int new_len = str.length()+ 2*count;
        int old_end = str.length()-1;
        int new_end = new_len-1;
        str.setLength(new_len);

        while(old_end >= 0 && new_end >= 0) {
            if(str.charAt(old_end) == ' ') {
                str.setCharAt(new_end--, '0');
                str.setCharAt(new_end--, '2');
                str.setCharAt(new_end--, '%');
                old_end--;
            } else {
                str.setCharAt(new_len--, str.charAt(old_end));
                old_end--;
            }
        }
        return str.toString();
    }



    public boolean Find(int target, int [][] array) {
        if(array == null){
            return false;
        }
        int i = 0;
        int j = array[0].length-1;
        while (i < array.length && j >= 0) {
            if (target > array[i][j]) {
                i++;
            }
            if (target < array[i][j]) {
                j--;
            } else {
                return true;
            }
        }
        return false;
    }
}
