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

}
