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


}
