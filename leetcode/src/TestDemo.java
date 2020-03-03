import java.util.Scanner;

public class TestDemo {
    /*
    统计回文
  插入翻转判断是否相等
  */
    public static void main1(String[] arags) {
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

    /*
    汽水瓶子
     */
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int bool = sc.nextInt();
            int n = 0;
            int cool = 0;
            int newCool = 0;
            while (bool > 2) {
                newCool = bool / 3;
                cool += newCool;
                n = bool % 3;
                bool = n + newCool;
            }
            if (bool == 2) {
                cool++;
            }
            System.out.println(cool);
        }
    }


    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int count = func(n);
            System.out.println(count);
        }

    }

    private static int func(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        int ret = n/3 + func(n /3 + n % 3);
        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            System.out.println(n/2);
        }
    }

    /*
    数组中的逆序对
     */
    public int count(int[] A, int n) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length-1-i; j++) {
                if (A[j] > A[j+1]) {
                    int tmp = A[j];
                    A[j] = A[j+1];
                    A[j+1] = tmp;
                    count++;
                }
            }
        }
        return count;
    }
}
