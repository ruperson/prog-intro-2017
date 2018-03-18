import java.util.*;
import java.lang.*;
import java.io.*;

public class Reverse {
    public static void main(String [] args) {
    Scanner in = new Scanner(System.in);

    ArrayList <String> a = new ArrayList <String>();
 
    while (in.hasNextLine()) {
        a.add(in.nextLine());
    }
    Collections.reverse(a);
    
    for (String s : a) {
        String[] t = s.split("\\p{javaWhitespace}+");
        Collections.reverse(Arrays.asList(t));
        for (String st : t) 
            System.out.print(st + " ");
        System.out.println();
        /*

        ArrayList <Integer> ans = new ArrayList <Integer>();
        for (String st : t) { 
            if (st.length() > 0) {
                int cur = new Integer(st);
                ans.add(cur);
            }
       }
       System.out.println(ans);
       */
    }
  }
}
        