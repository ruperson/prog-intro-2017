import java.math.BigInteger;
public class SumBigInteger {
    public static void main(String [] args) {
        String s = "";
        for(int i = 0; i < args.length; ++i) {
            s += args[i] + " ";
        }
    String[] t = s.split("\\p{javaWhitespace}+");
       BigInteger ans = BigInteger.ZERO;
       for (String st : t) 
       if (st.length() > 0) {
           BigInteger cur = new BigInteger(st);
           ans = ans.add(cur);
       }
       System.out.println(ans);
    }
}
       