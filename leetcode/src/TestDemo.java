import java.util.Scanner;

public class TestDemo {
    /*
    统计回文
  插入翻转判断是否相等
  */
    public static void main(String[] arags) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        int count = 0;
        for(int i = 0; i <= str1.length(); i++) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            String s1 = sb.append(str1).insert(i, str2).toString();
            String s2 = sb2.append(str1).insert(i, str2).reverse().toString();
            if(s1.equals(s2)) {
                count++;
            }
        }
        System.out.println(count);
    }

    /*
    寻找第k大
     */
    private static int partion(int[] array, int start, int end) {
        int tmp = array[start];
        while (start < end) {
            while ((start < end) && array[end] <= tmp) {
                end--;
            }
            if (start >= end) {
                break;
            } else {
                array[start] = array[end];
            }
            while ((start < end) && array[start] >= tmp) {
                start++;
            }
            if (start >= end) {
                //array[start] = tmp;
                break;
            } else {
                array[end] = array[start];
            }
        }
        array[start] = tmp;
        return start;
    }

    public int findKthChild(int[] array, int low, int high, int k) {
        int pivot = partion(array, low, high);

        if(k == pivot-low+1) return array[pivot];
        else if(k > pivot-low+1) return findKthChild(array, pivot+1, high, k-(pivot-low+1));
        else return findKthChild(array, low, pivot-1, k);
    }

    public int findKth(int[] a, int n, int K) {
        // write code here
        return findKthChild(a, 0, n-1, K);
    }

}
