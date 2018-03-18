import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.StringTokenizer;

public class Reverse {
    public static void main(String [] args) throws FileNotFoundException {
        FastScanner in = new FastScanner(System.in); 
        PrintWriter out = new PrintWriter (System.out);

        ArrayList <String> a = new ArrayList <String>();

        while (true) {
            String s = in.nextLine();
            if (s != null)
                a.add(s);
            else
                break;
        }
        
        Collections.reverse(a);
        for (String s : a) {
            String[] t =  s.split("\\p{javaWhitespace}+");
            Collections.reverse(Arrays.asList(t));
            for (String st : t) 
                out.print(st + " ");
            out.println();
        }
        out.close();
    } 

    static class FastScanner { 
        private BufferedReader in;
        private StringTokenizer st;

        public FastScanner(InputStream stream) {
            in = new BufferedReader(new InputStreamReader(stream));
        }

        String nextLine() {
            try {
                return in.readLine();
            }
            catch (IOException e) {
                throw new RuntimeException();
            }
        }     
    }
}
    