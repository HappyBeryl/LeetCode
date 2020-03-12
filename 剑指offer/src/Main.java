import java.util.*;

public class Main {
    /*
    1. 数组中重复的数字
     */
    //用set做，因为set中不能添加重复元素
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            if(!set.add(nums[i])) {
                return nums[i];
            }
        }
        return -1;
    }
}
