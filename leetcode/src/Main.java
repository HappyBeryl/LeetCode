import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void func(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length() - 3; i++) {
            if (str.charAt(i) == str.charAt(i + 2) && str.charAt(i) != str.charAt(i + 1)) {
                i += 2;
            } else {
                sb.append(str.charAt(i));
            }
        }
        System.out.println(sb);
    }
    public static void func1(String str) {
        String[] array = str.split(" ");
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (set.add(array[i])) {
                map.put(array[i], 1);
            } else {
                map.put(array[i], map.get(array[i])+1);
            }
        }
        System.out.println(array.length); //单词总数
        System.out.println(map.keySet()); //单词种类
        for(Map.Entry<String,Integer> entry:map.entrySet()){ //出现次数
            System.out.println(entry.getKey()+"->"+entry.getValue());
        }
    }


    public static void main(String[] args) {
        func("ABCBACAGFGFUNU");
    }




}
