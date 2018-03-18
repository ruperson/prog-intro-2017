import java.util.*;
import java.lang.*;
import java.io.*;

public class ReverseMax {
    public static void main(String [] args) {
    Scanner in = new Scanner(System.in);

    ArrayList <ArrayList<Integer>> a = new ArrayList <ArrayList<Integer>>();
      
    int m = Integer.MIN_VALUE;
    while (in.hasNextLine()) {
        a.add(new ArrayList <Integer>());
        String s = in.nextLine();
        String[] t = s.split("\\p{javaWhitespace}+");
        for (String st : t) { 
            if (st.length() > 0) {
                int  cur = new Integer(st);
                a.get(a.size() - 1).add(cur);
            }
            m = Math.max(m, a.get(a.size() - 1).size());
            
        }
    }
    ArrayList <Integer> c = new ArrayList <Integer>(Collections.nCopies(a.size(), Integer.MIN_VALUE));
    for (int i = 0; i < a.size(); ++i) {
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < a.get(i).size(); ++j) {
            max = Math.max(max, a.get(i).get(j));
        }
        c.set(i, max);
    }
    ArrayList <Integer> d = new ArrayList <Integer>(Collections.nCopies(m, Integer.MIN_VALUE));
    
    for (int j = 0; j < m; ++j) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.size(); ++i) {
            if (j < a.get(i).size() && max < a.get(i).get(j)) 
                max = a.get(i).get(j);
        }
       d.set(j, max);
    }


    for (int i = 0; i < a.size(); ++i) {
        for (int j = 0; j < a.get(i).size(); ++j) {
            System.out.print(Math.max(c.get(i), d.get(j)) + " ");
        }
        System.out.println();
    }
  }
}
        