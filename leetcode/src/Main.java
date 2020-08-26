import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    

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
