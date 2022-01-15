import java.util.Scanner;

public class ExactlyElectrical {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.nextLine();

        int c = sc.nextInt();
        int d = sc.nextInt();
        sc.nextLine();

        int t = sc.nextInt();

        // get to final destination
        int x = c - a;
        x = Math.abs(x);
        int y = d - b;
        y = Math.abs(y);

        if(x + y > t) {
            System.out.println("N");
        } else if(x + y == t) {
            System.out.println("Y");
        } else {
            // get num of charges left
            t = t - x - y;
            if(t % 2 != 0) {
                System.out.println("N");
            } else {
                System.out.println("Y");
            }
        }
    }
}
