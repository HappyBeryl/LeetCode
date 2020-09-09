import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;
public class Solution {

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4,5,6,7,8,9,10,0};
        ArrayList<Integer> list = GetLeastNumbers_Solution1(a, 5);
        System.out.println(list);
    }

    public static ArrayList<Integer> GetLeastNumbers_Solution1(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if(input == null || k <= 0 || k > input.length){
            return list;
        }
        //按照数值从大到小
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for(int i = 0; i < input.length ; i++){
            if(i < k){
                queue.offer(input[i]);
            }else{
                //每次淘汰最大的，剩下就是最小的
                if(input[i] < queue.peek()){
                    queue.poll();
                    queue.offer(input[i]);
                }
            }
        }
        //返回对应的结果
        for(int i = 0 ; i < k; i++){
            list.add(queue.poll());
        }
        return list;
    }


    public int TreeDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root); //将root入队列
        int depth = 0;
        while(!q.isEmpty()){
            int size = q.size(); //获取队列中的元素个数
            depth++;
            for(int i = 0; i < size; i++){
                TreeNode curr = q.poll(); //获取队列头部元素,并在队列中删除
                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
            }
        }
        return depth;
    }

    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        num1[0] = 0;
        num2[0] = 0;
        int result= array[0];
        for (int i = 1; i < array.length; i++) {
            result ^= array[i];
        }

        int len = Integer.SIZE;
        int flg = 1;
        while (len >= 0) {
            len--;
            if (((flg << len) & result) != 0) {
                flg <<= len;
                break;
            }
        }
        for (int i = 0; i < array.length; i++) {
            if ((flg & array[i]) == 0) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }
        }
    }

    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int low = 1;
        int high = 2;
        while (low < high) {
            int total = (low+high)*(high-low+1)/2;
            if (total == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = low; i <= high; i++) {
                    list.add(i);
                }
                result.add(new ArrayList<>(list));
                low++;
            } else if (total > sum) {
                low++;
            }else {
                high++;
            }
        }
        return result;
    }
    public String LeftRotateString(String str,int n) {
            if (str == null || str.length() == 0) {
                return str;
            }
            char[] list = str.toCharArray();
            n %= list.length;
            while (n > 0) {
                n--;
                leftMove(list);
            }
            return new String(list);
        }

        private void leftMove(char[] list) {
            char tmp = list[0];
            int i = 0;
            for (;i < list.length-1;i++) {
                list[i] = list[i+1];
            }
            list[i] = tmp;
        }

        public String ReverseSentence(String str) {
            if (str.length() == 0 || str == null) {
                return str;
            }
            int i = 0;
            int j = i;
            int size = str.length();
            char[] list = str.toCharArray();
            while (i < size) {
                while (i < size && !Character.isSpaceChar(list[i])) i++;
                Reverse(list, j, i-1);
                while (i < size && Character.isSpaceChar(list[i])) i++;
                j = i;
            }
            Reverse(list, j, i-1);
            Reverse(list, 0, i-1);
            return new String(list);
        }

        private void Reverse(char[] list, int j, int i) {
            while (j < i) {
                char tmp = list[j];
                list[j] = list[i];
                list[i] = tmp;
                j++;
                i--;
            }
        }


    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        Queue<TreeNode> queue = new LinkedList<>();
        stack.push(pRoot);
        int dir = 1;
        ArrayList<Integer> list = new ArrayList<>();
        while (!stack.empty()) {
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = stack.pop();
                list.add(cur.val);
                TreeNode first = (dir == 1)? cur.left : cur.right;
                TreeNode second = (dir == 1)? cur.right : cur.left;
                if (first != null) queue.offer(first);
                if (second != null) queue.offer(second);
            }
            result.add(new ArrayList<>(list));
            list.clear();
            while (!queue.isEmpty()) {
                stack.push(queue.poll());
            }
            dir = (dir == 1) ? 2 :1;
        }
        return result;
    }

    TreeNode KthNode(TreeNode pRoot, int k) {
        if (k <= 0 || pRoot == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = pRoot;
        while (node != null && !stack.empty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            k--;
            if (k == 0) {
               return node;
            }
            node = node.right;
        }
        return null;
    }

    public String PrintMinNumber2(int [] numbers) {
        if (numbers == null) {
            return new String();
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int e: numbers) {
            list.add(e);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                String xs = x+y+"";
                String ys = y+x+"";
                return xs.compareTo(ys);
            }
        });
        String result = new String();
        for(Integer e : list){
            result += e;
        }
        return result;
    }

    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        while (num > 0) {
            StringBuffer sb =new StringBuffer(sc.next());
            int[] start = new int[1];
            int[] end = new int[1];
            if (isPar(sb, start, end)) {
                System.out.println(-1);
            } else {
                sb.deleteCharAt(start[0]);
                if (isPar(sb, null, null)) {
                    System.out.println(start[0]);
                } else {
                    System.out.println(end[0]);
                }
            }
            num--;
        }
    }

    private static boolean isPar(StringBuffer sb, int[] start, int[] end) {
        int i = 0;
        int j = sb.length()-1;
        boolean result = true;
        while (i <= j) {
            if (sb.charAt(i) != sb.charAt(j)) {
                result = false;
                break;
            }
            i++;
            j--;
        }
        if (start != null) start[0] = i;
        if (end != null) end[0] = j;
        return result;
    }

    public int FindGreatestSumOfSubArray(int[] array) {
        int[] dp = new int[array.length];
        dp[0] = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            dp[i] = Math.max(dp[i]+array[i], array[i]);
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        if (input == null || input.length == 0 || k <= 0 || k > input.length) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, Collections.reverseOrder());
        for (int i = 0; i < input.length; i++) {
            if (i < k) {
                queue.add(input[i]);
            } else {
                if (queue.peek() > input[i]) {
                    queue.poll();
                    queue.add(input[i]);
                }
            }
        }
        for (int i = 0; i < k; i++) {
            list.add(queue.poll());
        }
        return list;
    }


    public int MoreThanHalfNum_Solution(int [] array) {
        if (array == null) {
            return 0;
        }
        int target = array[0];
        int times = 1;
        for (int i = 1; i < array.length; i++) {
            if (times == 0) {
                target = array[i];
                times = 1;
            } else if (array[i] == target) {
                times++;
            } else {
                times--;
            }
        }
        times = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                times++;
            }
        }
        return times > array.length/2 ? target : 0;
    }
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        if (str.length() > 0 && str != null) {
            PermutationChild(str.toCharArray(), 0, list);
        }
        Collections.sort(list);
        return list;
    }

    private void PermutationChild(char[] str, int start, ArrayList<String> list) {
        if (start == str.length-1) {
            if (!isExist(list, str)) {
                list.add(new String(str));
            }
            return;
        }
        for (int i = start; i < str.length; i++) {
            Swap(str, start, i);
            PermutationChild(str, start+1, list);
            Swap(str, start, i);
        }
    }

    private boolean isExist(ArrayList<String> list, char[] str) {
        return list.contains(String.valueOf(str));
    }

    private void Swap(char[] str, int i, int j) {
        char temp = str[i]; str[i] = str[j]; str[j] = temp;
    }


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
