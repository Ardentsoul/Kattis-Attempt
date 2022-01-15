import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class T9Spelling {
    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);

        int numOfCases = Integer.parseInt(br.readLine());

        // HashMap object called alphabetToNum
        HashMap<Character, String> alphabetToNum = new HashMap<>();

        // Add keys and values (alphabet, number)
        alphabetToNum.put('a',"2");
        alphabetToNum.put('b',"22");
        alphabetToNum.put('c',"222");
        alphabetToNum.put('d',"3");
        alphabetToNum.put('e',"33");
        alphabetToNum.put('f',"333");
        alphabetToNum.put('g',"4");
        alphabetToNum.put('h',"44");
        alphabetToNum.put('i',"444");
        alphabetToNum.put('j',"5");
        alphabetToNum.put('k',"55");
        alphabetToNum.put('l',"555");
        alphabetToNum.put('m',"6");
        alphabetToNum.put('n',"66");
        alphabetToNum.put('o',"666");
        alphabetToNum.put('p',"7");
        alphabetToNum.put('q',"77");
        alphabetToNum.put('r',"777");
        alphabetToNum.put('s',"7777");
        alphabetToNum.put('t',"8");
        alphabetToNum.put('u',"88");
        alphabetToNum.put('v',"888");
        alphabetToNum.put('w',"9");
        alphabetToNum.put('x',"99");
        alphabetToNum.put('y',"999");
        alphabetToNum.put('z',"9999");
        alphabetToNum.put(' ', "0");


        for(int i = 1; i <= numOfCases; i++) {
            String input = br.readLine();
            StringBuilder sb = new StringBuilder();
            int prevVal = Integer.MAX_VALUE;
            for(int j = 0; j < input.length(); j++) {
                char c = input.charAt(j);
                int val = Integer.parseInt(alphabetToNum.get(c)); // get the value respective of key
                int moduloLastDigitOfValue = val % 10; // get last digit of appended String value

                // do a check on prevVal then update prevVal
                if(prevVal == moduloLastDigitOfValue) {
                    sb.append(' ');
                }
                prevVal = moduloLastDigitOfValue;
                sb.append(alphabetToNum.get(c)); // append number
            }
            writer.println("Case #" + i + ": " + sb);
        }
        br.close();
        r.close();
        writer.flush();
        writer.close();
    }
}
