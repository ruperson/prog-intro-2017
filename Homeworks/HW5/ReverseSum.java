import java.util.*;
import java.lang.*;
import java.io.*;
public class ReverseSum {
    public static void main(String [] args) {
    FastScanner in = new FastScanner(System.in); 
    PrintWriter out = new PrintWriter (System.out);

    List <ArrayList<Integer>> a = new ArrayList <ArrayList<Integer>>();
    List <Integer> c = new ArrayList <Integer>();
    List <Integer> d = new ArrayList <Integer>();
     
    String s;
    while ((s = in.nextLine()) != null) {
        a.add(new ArrayList <Integer>());
        String[] t = s.split("\\p{javaWhitespace}+");
        c.add(0);
        for (int i = 0; i < t.length; ++i)  
            if (t[i].length() > 0) {
                int cur = new Integer(t[i]);
                a.get(a.size() - 1).add(cur);
                c.set(c.size() - 1, c.get(c.size() - 1) + cur);
                if (i >= d.size())
                   d.add(0);
                d.set(i, d.get(i) + cur);
            }  
    }
   
    for (int i = 0; i < a.size(); ++i) {
        for (int j = 0; j < a.get(i).size(); ++j) {
            out.print(c.get(i) + d.get(j) - a.get(i).get(j) + " ");
        }
        out.println();
    }
    out.close();
  }

  static class FastScanner { 
      private BufferedReader in;
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
        
