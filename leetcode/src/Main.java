import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{-1,0,1,2,-3};
        List<List<Integer>> ret = find(arr);
        System.out.println(ret);
    }
    public static List<List<Integer>> find(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < arr.length-2; i++) {
            int j = i+1;
            int k = arr.length-1;
            while (j < k) {
                if (arr[i]+arr[j]+arr[k]==0) {
                    ret.add(Arrays.asList(arr[i], arr[j], arr[k]));
                    j++;
                    k--;
                } else if (arr[i]+arr[j]+arr[k] > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return ret;
    }

}
