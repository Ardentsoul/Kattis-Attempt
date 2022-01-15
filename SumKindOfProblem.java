import java.util.Scanner;
// 1+2+...+n = n(n+1)/2 = (n^2+n)/2
// 1+3+...+2n-1 = ((2-1) + (4-1) + ... + (2n-1)) = (2+4+...2n)-n = 2(1+2+...+n)-n = 2n(n+1)/2-n = n^2
// 2+4+...+2n = 2(1+2+...+n) = 2n(n+1)/2 = n(n+1) = n^2+n

public class SumKindOfProblem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalInputs = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= totalInputs; i++) {
            sc.nextInt();
            int N = sc.nextInt();
            int square = N * N;
            int s1 = (square + N)/2;
            int s2 = square;
            int s3 = square + N;

            System.out.println(i + " " + s1 + " " + s2 + " " + s3);
        }
    }
}
