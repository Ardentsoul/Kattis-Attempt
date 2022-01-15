import java.util.Scanner;

public class ShatteredCake {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int originalWidth = sc.nextInt();
        sc.nextLine();
        int numOfShatteredPieces = sc.nextInt();
        sc.nextLine();

        // store area
        int totalArea = 0;

        for (int i = 0; i < numOfShatteredPieces; i++) {
            int w = sc.nextInt();
            int l = sc.nextInt();
            totalArea += w * l;
        }

        int length = totalArea/ originalWidth;
        System.out.println(length);
    }
}
