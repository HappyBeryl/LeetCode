
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

    public static void main(String[] args) {
        func("ABCBACAGFGFUNU");
    }




}
