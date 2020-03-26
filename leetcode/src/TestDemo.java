import java.math.BigInteger;
import java.util.*;

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
        for (int i = 0; i <= str1.length(); i++) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            String s1 = sb.append(str1).insert(i, str2).toString();
            String s2 = sb2.append(str1).insert(i, str2).reverse().toString();
            if (s1.equals(s2)) {
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

        if (k == pivot - low + 1) return array[pivot];
        else if (k > pivot - low + 1) return findKthChild(array, pivot + 1, high, k - (pivot - low + 1));
        else return findKthChild(array, low, pivot - 1, k);
    }

    public int findKth(int[] a, int n, int K) {
        // write code here
        return findKthChild(a, 0, n - 1, K);
    }

    public static boolean isHuiwen(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main5(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        int count = 0;
        for (int i = 0; i <= str1.length(); i++) {
            StringBuilder sb = new StringBuilder(str1);
            sb.insert(i, str2);
            if (isHuiwen(sb.toString())) {
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
        int ret = n / 3 + func(n / 3 + n % 3);
        return ret;
    }

    public static void main4(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            System.out.println(n / 2);
        }
    }

    /*
    数组中的逆序对
     */
    public int count(int[] A, int n) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length - 1 - i; j++) {
                if (A[j] > A[j + 1]) {
                    int tmp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = tmp;
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
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (isDigit(ch)) {
                if (max <= count) {
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
        for (int i = 0; i < n; i++) {
            char ch = A.charAt(i);
            if (ch != '(' && ch != ')') {
                return false;
            }
            if (ch == '(') {
                stack.push(ch);
            } else {
                if (stack.empty()) {
                    //右括号多
                    return false;
                }
                char top = stack.peek();
                if (top == '(') {
                    stack.pop();
                } else {
                    //右括号匹配错误
                    return false;
                }
            }
        }
        if (!stack.empty()) {
            //左括号多
            return false;
        }
        return true;
    }

    public static void main7(String[] args) {
        System.out.println(chkParenthesis("(()())", 6));
    }

    /*
    删除公共字符
     */

    public static void main8(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        List<Character> list = new ArrayList();
        for (int i = 0; i < str1.length(); i++) {
            if (!str2.contains(str1.charAt(i) + "")) {
                list.add(str1.charAt(i));
            }
        }
        System.out.println(list.toString());
    }

    /*
    买苹果
     */

    public static void main9(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n % 8 == 0) {
            System.out.println(n / 8);
        } else if ((n % 8) % 2 == 0 && n != 10) {
            //n % 8可以等于1 2 3 4 5 6 7 %2= 0 的有 2 4 6
            //如果是奇数 肯定不行
            //如果是偶数，可以通过增加6的袋数，减少8的袋数进行每次减2
            System.out.println(n / 8 + 1);
        } else {
            System.out.println(-1);
        }
    }

    public static void main10(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str1 = scan.nextLine();
        String str2 = scan.nextLine();
        char[] retStr = new char[str1.length()];
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < str2.length(); i++) {
            set.add(str2.charAt(i));
        }
        int k = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (!set.contains(str1.charAt(i))) {
                retStr[k++] = str1.charAt(i);
            }
        }
        System.out.println(retStr);
    }

    /*
    两个栈实现队列
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (empty()) {
            return -1;
        }
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        if (!stack2.empty()) {
            return stack2.pop();
        }
        return -1;

    }

    public boolean empty() {
        return stack1.empty() && stack2.empty();
    }

    /*
    神奇的口袋
     */

    public static void main11(String[] args) {
        //处理输入！
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = sc.nextInt();
        }
        System.out.println(count(array, n, 40));
    }

    public static int count(int[] A, int i, int s) {
        if (s == 0) {
            return 1;
        } else if (i == 1) {
            if (A[i] == s) {
                return 1;
            } else {
                return 0;
            }
        } else if (A[i] > s) {
            return count(A, i - 1, s);
        } else {
            return count(A, i - 1, s - A[i]) + count(A, i - 1, s);
        }
    }

    public static void main12(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        int A = (a + c) / 2;
        int B = (b + d) / 2;
        int C = d - (b + d) / 2;
        if ((a == A - B) && (b == B - C) && (c == A + B) && (d == B + C)) {
            System.out.println(A + " " + B + " " + C);
        } else {
            System.out.println("No");
        }
    }

    public static void main13(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            Map<Integer, Integer> map = new TreeMap<>();
            String[] strs = sc.nextLine().split(" ");
            for (int i = 0; i < strs.length; i++) {
                int s = Integer.valueOf(strs[i]);
                if (map.containsKey(s)) {
                    map.put(s, map.get(s) + 1);
                } else {
                    map.put(s, 1);
                }
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() >= strs.length / 2) {
                    System.out.println(entry.getKey());
                }
            }
        }
    }

    /*
    最小公倍数
     */
    public static void main14(String[] args) {
        //  Scanner sc = new Scanner(System.in);
        //  int A = sc.nextInt();
        //   int B = sc.nextInt();
        int A = 5;
        int B = 7;
        int tmp = 0;
        while (A % B != 0) {
            tmp = A % B;
            A = B;
            B = tmp;
        }
        //tmp求出来的最大公约数，两数相乘除以最大公约数就是最小公倍数
        System.out.println(A * B / tmp);
    }

    public static void main17(String[] args) {
        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt();
        int h = sc.nextInt();
        int sum = 0;
        if (w % 4 == 0 || h % 4 == 0) {
            sum = w * h >> 1;
        } else if (w % 2 == 0 && h % 2 == 0) {
            sum = (w * h >> 1) + 2;
        } else {
            sum = (w * h >> 1) + 1;
        }
        System.out.println(sum);
    }

    public static void main15(String[] args) {
        // Scanner sc = new Scanner(System.in);
        Queue<Integer> queue = new LinkedList<>();
        //放数据到队列
        int[] array = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
        for (int i = 0; i < array.length; i++) {
            queue.add(array[i]);
        }
        while (queue.size() > 1) {
            int count = 2;
            while (count != 0) {
                queue.add(queue.poll());
                count--;
            }
            queue.poll();
        }
        System.out.println(queue.poll());
    }

    public static void main16(String[] args) {
        //     Scanner sc = new Scanner(System.in);
        int[] array = new int[]{3, 9, 6, 8, -10, 7, -11, 19, 30, 12, 23, 5};
        int i = array.length - 1;
//        while(sc.hasNext()) {
//            int num = sc.nextInt();
//            array[i++] =  num;
//        }
        int[] arrayTmp = new int[i];
        System.arraycopy(array, 0, arrayTmp, 0, i);
        Arrays.sort(arrayTmp);
        for (int j = 0; j < array[i]; i++) {
            if (j == array[i] - 1) {
                System.out.println(arrayTmp[j]);
            } else {
                System.out.println(arrayTmp[j] + " ");
            }
        }
    }

    public static void main18(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] s = str.split(" ");
        int[] array = new int[str.length() - 1];
        for (int i = 0; i < str.length() - 1; i++) {
            array[i] = Integer.valueOf(s[i]);
        }
        Arrays.sort(array);
        int k = Integer.valueOf(s[s.length - 1]);
        for (int i = 0; i < k; i++) {
            System.out.println(array[i] + " ");
        }
    }

    public static void main19(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        StringBuffer sb = new StringBuffer(input + "");
        System.out.println(sb.reverse().toString());
    }

    public static void main21(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();
        int prev = (n / 4) * 2 + (n % 4 < 2 ? n % 4 : 2);
        int last = (n - 2) / 4 * 2 + ((n - 2) % 4 < 2 ? (n - 2) % 4 : 2);
        int ret = m / 4 * (prev + last) * 2;
        if (m % 4 == 1) ret += prev;
        if (m % 4 == 2) ret += prev;
        if (m % 4 == 3) ret += last;
        System.out.println(ret);
    }

    /*
 两数之和
  */
    //方法一暴力解法 显然是不可取的 时间复杂度O(n^2),空间复杂度O(1)
    public static int[] twoSum(int[] nums, int target) {
        int[] array = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    //哈希映射--为了达到查找方便的意图，可以使用哈希表存放值和对应下标 进行比较
    //map.get()返回key对应的value
    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int numTmp = target - nums[i];
            if (map.containsKey(numTmp)) {
                return new int[]{map.get(numTmp), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main20(String[] args) {
        int[] a = new int[]{2, 7, 11, 15};
        int[] ret = twoSum1(a, 9);
        System.out.println(Arrays.toString(ret));
    }

    /*
    个位数统计
     */
    public static void main22(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int[] array = new int[10];
        for (int i = 0; i < str.length(); i++) {
            array[str.charAt(i) - '0']++;
        }
        for (int j = 0; j < array.length; j++) {
            if (array[j] != 0) {
                System.out.println(j + ":" + array[j]);
            }
        }

    }

    public static void main23(String[] args) {
        Scanner sc = new Scanner(System.in);
        int col = sc.nextInt();
        String c = sc.next();
        int row = (col % 2 == 0) ? col / 2 : col / 2 + 1;
        //打印第一行
        for (int i = 0; i < col; i++) {
            System.out.print(c);
        }
        System.out.println();
        //打印中间
        for (int i = 0; i < row - 2; i++) {
            System.out.print(c);
            for (int j = 0; j < col - 2; j++) {
                System.out.print(" ");
            }
            System.out.print(c);
            System.out.println();
        }
        //打印最后一行
        for (int i = 0; i < col; i++) {
            System.out.print(c);
        }

    }

    public static void main24(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String addend = sc.next();
            String augend = sc.next();
            BigInteger num1 = new BigInteger(addend);
            BigInteger num2 = new BigInteger(augend);
            System.out.println(num1.add(num2));
        }

    }

    public static void main25(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] array = new int[]{2, 2, 0, 0, 0, 3, 0, 0, 1, 0};
        int size = 0;
   /*     while(sc.hasNext()) {
            for(int i = 0; i < array.length; i++) {
                array[i] = sc.nextInt();
                size++;
            }
        }*/

        Arrays.sort(array);
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                count++;
            }
            if (array[i] != 0) {
                sb.append(array[i] + "");
                while (count != 0) {
                    sb.append("0" + "");
                    count--;
                }
            }

        }
        System.out.println(sb.toString());
    }

    public static void main26(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new LinkedList();
        while (sc.hasNext()) {
            list.add(sc.nextInt());
        }
        Collections.sort(list);
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) {
                count++;
            }
            if (list.get(i) != 0) {
                sb.append(list.get(i) + "");
                while (count != 0) {
                    sb.append("0" + "");
                    count--;
                }
            }

        }
        System.out.println(sb.toString());
    }

    //找规律
    public static void main27(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        sc.nextLine();
        GetSequeOddNum(m);
    }

    public static void GetSequeOddNum(int m) {
        StringBuffer sb = new StringBuffer();
        int tmp = m * (m - 1) + 1;
        for (int i = 0; i < m; i++) {
            if (i == m - 1) {
                sb.append(tmp);
            } else {
                sb.append(tmp + "+");
            }
            tmp += 2;
        }
        System.out.println(sb);
    }

    public static void main28(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x0 = sc.nextInt();
        long x1 = 4 * x0 + 3;
        long x2 = 4 * x1 + 3;
        int count = 0;
        if(func(x0, ++count) || func(x1, ++count) || func(x2, ++count)) {}
        else System.out.println("-1");
    }

    public static boolean func(long x, int count) {
        boolean result = false;
        while(count < 100000) {
            long tmp=8*x+7;
            if(tmp % 1000000007==0){
                result=true;
                System.out.println(count);
                break;
            }else {
                x=tmp % 1000000007;
                count++;
            }
        }
        return result;
    }

    public static void main29(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] array = new int[1000];
        while(sc.hasNext()) {
            int N = sc.nextInt();
            for(int i = 0; i < N; i++) {
                array[i] = sc.nextInt();
            }
            int score = sc.nextInt();
            int count = 0;
            for(int i = 0; i < N; i++) {
                if(array[i] == score) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }

    public static void main30(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            int m = sc.nextInt();
            while(m != 0) {
                int n = sc.nextInt(); //一半数据的个数
                int k = sc.nextInt(); //洗多少次牌
                int[] array = new int[2*n];
                //计算下标
                for(int i = 0; i < 2 * n; i++) {
                    int tmp = i;
                    for(int j = 0; j < k; j++) {
                        if(tmp < n) {
                            tmp = 2 * tmp;
                        } else {
                            tmp = 2 * (tmp - n) + 1;
                        }
                    }
                    array[tmp] = sc.nextInt();
                }

                //输出
                for(int i = 0;i < 2*n;i++){
                    if(i == 2*n-1){
                        System.out.print(array[i]);
                    }else {
                        System.out.print(array[i]+" ");
                    }
                }
                System.out.println();
            }
        }
    }

    public static void main31(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int n = sc.nextInt();
        int max = 0;
        int start = 0;
        for(int i = 0; i < str.length() - n; i++) {
            int count = 0;
            for(int j = i; j < i + n; j++) {
                if(str.charAt(j) == 'C' || str.charAt(j) == 'G') {
                    count++;
                }
            }
            if(count > max) {
                max = count;
                start = i;
            }
        }
        System.out.println(str.substring(start, start + n));
    }

    public boolean[] chkSubStr(String[] p, int n, String s) {
        boolean[] ret = new boolean[p.length];
        for(int i = 0; i < p.length; i++) {
            if(s.contains(p[i])) {
                ret[i] = true;
            }
        }
        return ret;
    }

    public static int getValue(int[] gifts, int n) {
        if(gifts.length <n) return 0;
        if(gifts.length == 0) return 0;
        int num = 0;
        int count = 0;
        for(int gift: gifts) {
            if(count == 0) {
                num = gift;
            }
            count += (num == gift) ? 1 : -1;
        }
        if(count > 0) {
            return num;
        }
        return 0;
    }

    public static void main32(String[] args) {
        int[] array = new int[]{1,2,3,2,1};
        System.out.println(getValue(array, 5));
    }

    public static void main33(String[] args) {
 //       Scanner sc = new Scanner(System.in);
 //       int mon = sc.nextInt();
        System.out.println(getTotalCount(9));
    }

    public static int getTotalCount(int mon) {
        if(mon < 3) {
            return 1;
        }
        return getTotalCount(mon-1) + getTotalCount(mon-2);
    }

        public static void main34(String[] args) {
            Scanner sc = new Scanner(System.in);
            while(sc.hasNext()) {
                int mon = sc.nextInt();
                System.out.println(getTotalCount(mon));
            }
        }

        public static int getTotalCount1(int mon) {
            if(mon == 1 || mon == 2) {
                return 1;
            }
            int a = 0; //一个月的兔子
            int b = 1; //二个月的兔子
            int c = 0; //三个月的兔子
            for(int i = 2; i < mon; i++) { //从第三个月开始算
                c += b; //成熟的可以生兔子的兔子
                b = a; //一个月大的兔子变成了两个月大的兔子
                a = c; //成熟的兔子生下的小兔子
            }
            return a + b + c;  //返回所有的兔子
        }

    public static void main35(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String str = sc.nextLine();
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i <str.length(); i++) {
                if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                    sb.append((char)((str.charAt(i) - 'A' + 21) % 26 + 'A'));
                } else {
                    sb.append(" ");
                }
            }
            System.out.println(sb.toString());
        }
    }

    public static void main36(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        List<Character> list = new ArrayList<>();
        //用list保存 str1
        for(int i = 0; i < str1.length(); i++) {
            list.add(str1.charAt(i));
        }

        int count = 0;
        for(int i = 0; i < str2.length(); i++) {
            char ch = str2.charAt(i);
            for(int j = 0; j < list.size(); j++) {
                if(ch == list.get(j)) {
                    list.remove(j);
                    count++;
                    break;
                }
            }
        }
        if(count == str2.length()) {
            System.out.println("Yes" + " " + (count - str2.length()));
        } else {
            System.out.println("No" + " " + (str2.length() - count));
        }
    }


}




