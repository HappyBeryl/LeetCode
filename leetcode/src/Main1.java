import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main1 {
    public static void func(int num) {
        int tmp = 0;
        Map<Integer, Integer> map = new LinkedHashMap<>();
        while (num != 0) {
            tmp = num % 10;
            map.put(tmp,tmp);
            num /= 10;
        }
        System.out.println(map.keySet());
    }

    public static void main1(String[] args) {
        func(12334);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] array = str.split(" ");

    }
}
