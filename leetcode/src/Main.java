import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //积木的数量
        int m = sc.nextInt(); //对应的二进制有多少位
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        System.out.println(1);

    }
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        Set<Integer> set = new HashSet<>();
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();

        for (int i = 0; i < array.length; i++) {
            if (set.contains(array[i])) {
                int tmp = array[i];
                while (set.contains(tmp)) {
                    set.remove(tmp);
                    map.remove(tmp);
                    tmp *= 2;
                }
                set.add(tmp);
                map.put(tmp,i);
            } else {
                set.add(array[i]);
                map.put(array[i],i);
            }
        }
        for (int num:map.keySet()) {
            System.out.print(num+" ");
        }
    }
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            queue.offer(sc.nextInt());
        }
        for (int i = 0; i < m; i++) {
            int tmp = queue.poll()+x;
            queue.offer(tmp);
        }
        System.out.println(queue.poll());

    }


}
