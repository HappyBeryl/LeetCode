import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main1 {
    public static void func(String str) {
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
}
