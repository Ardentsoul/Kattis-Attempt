import java.util.Scanner;

public class Apaxians {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int len = input.length();

        StringBuilder str = new StringBuilder(input);
        char temp = str.charAt(0);

        for(int i = 1; i< len; i++) {
            if(str.charAt(i -1) == str.charAt(i)) {
                temp = str.charAt(i);
                StringBuilder afterStr = str.deleteCharAt(i);
                i--;
                len -= 1;
            }
        }
        System.out.println(str);
    }
}
