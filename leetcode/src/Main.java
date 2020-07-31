import java.util.Scanner;
/*
科大讯飞
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        int start = 0;
        int end = array.length-1;
        int ret = func(array, target, start, end);
        System.out.println(ret);
    }

    private static int func(int[] array, int target, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = start + (end-start)/2;
        if (target < array[mid]) {
            return func(array, target, start, end-1);
        } else if (target > array[mid]) {
            return func(array, target, mid+1, end);
        } else {
            return mid;
        }
    }

    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n <= 1) {
            System.out.println("0");
        }
        if (n == 2) {
            System.out.println("1");
        }
        if (n == 3) {
            System.out.println(2);
        }
        int[] array = new int[n+1];
        array[0] = 0;
        array[1] = 1;
        array[2] = 2;
        array[3] = 3;
        int max = 0; //储存当前最大的f(n)
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j <= i/2; j++) { //绳子只需要剪前一段，剪i和剪n-i是相同的效果
                if (array[j] * array[i-j] > max) {
                    max = array[j] * array[i-j];
                }
                array[i] = max;
            }
        }
        System.out.println(array[n]);
    }
}
