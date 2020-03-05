import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import static java.lang.Character.isDigit;

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

    public static boolean isHuiwen(String s){
        int i = 0; int j = s.length()-1;
        while(i<j){
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }return true;
    }
    public static void main5(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        int count = 0;
        for(int i = 0; i <= str1.length();i++){
            StringBuilder sb = new StringBuilder(str1);
            sb.insert(i, str2);
            if(isHuiwen(sb.toString())){
                count++;
            }
        }
        System.out.println(count);
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

    public static void main4(String[] args) {
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

    /*
        字符串最长数字串
     */
    public static void main6(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuffer sb = new StringBuffer();
        int count = 0;
        int max = 0;
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(isDigit(ch)) {
                if(max <= count) {
                    sb.append(str.charAt(i));
                    count++;
                    max = count;
                }
            } else {
                count = 0;
            }
        }
        System.out.println(sb.toString());
    }

    /*
    合法括号序列判断
     */

    public static boolean chkParenthesis(String A, int n) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < n; i++) {
            char ch = A.charAt(i);
            if(ch != '(' && ch != ')') {
                return false;
            }
            if(ch == '(') {
                stack.push(ch);
            } else {
                if(stack.empty()) {
                    //右括号多
                    return false;
                }
                char top = stack.peek();
                if(top == '(') {
                    stack.pop();
                } else {
                    //右括号匹配错误
                    return false;
                }
            }
        }
        if(!stack.empty()){
            //左括号多
            return false;
        }
        return true;
    }

    public static void main7(String[] args) {
        System.out.println( chkParenthesis("(()())",6));
    }

    /*
    删除公共字符
     */

    public static void main8(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2  = sc.nextLine();
        List<Character> list = new ArrayList();
        for(int i = 0; i < str1.length(); i++) {
            if(!str2.contains(str1.charAt(i)+"")) {
                list.add(str1.charAt(i));
            }
        }
        System.out.println(list.toString());
    }

    /*
    买苹果
     */

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            if(n % 8 == 0) {
                System.out.println(n/8);
            } else if((n%8) % 2 == 0 && n != 10) {
                //n % 8可以等于1 2 3 4 5 6 7 %2= 0 的有 2 4 6
                //如果是奇数 肯定不行
                //如果是偶数，可以通过增加6的袋数，减少8的袋数进行每次减2
                System.out.println(n/8 + 1);
            } else {
                System.out.println(-1);
            }
        }


}
