import java.util.HashSet;
import java.util.Set;

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


}
