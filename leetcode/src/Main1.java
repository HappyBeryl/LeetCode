public class Main1 {
    public static void main(String[] args) {
        String add1 = "9AZZ";
        String add2 = "ZZ";
        int numInt =ConvertNumToD(add1)+ConvertNumToD(add2);
        System.out.println(ConvertNumToH(numInt));
    }

    //三十六进制转化为十进制
    public static int ConvertNumToD(String str) {
        StringBuffer sb = new StringBuffer();
        int[] arr = new int[str.length()];
        int result = 0;
        int count = 1;
        for (int i = str.length()-1; i >= 0; i--) {
            char ch = str.charAt(i);
            if (ch >= '0' && ch <= '9') {
                arr[i] = ch - 48;
            } else if (ch >= 'A' && ch <= 'Z') {
                arr[i] = ch - 'A'+10;
            } else if (ch >= 'a' && ch <= 'z') {
                arr[i] = ch - 'a' + 10;
            } else {
                arr[i] = 0;
            }
            result += arr[i]*count;
            count *= 36;
        }
        return result;
    }

    //十进制转化为三十六进制
    public static String ConvertNumToH(int num) {
        StringBuffer sb = new StringBuffer();
        while (num > 0) {
            if (num % 36 <= 9) {
                sb.append(num%36);
            } else {
                sb.append((char) ((num%36)-10+'A'));
            }
            num /= 36;
        }
        return sb.reverse().toString();
    }
}
