public class Sum {
    public static void main(String [] args) {
        String s = "";
        for(int i = 0; i < args.length; ++i) {
            s += args[i] + " ";
        }
       String[] t = s.split("\\p{javaWhitespace}+");
       int ans = 0;
       for (String st : t) 
           if (st.length() > 0) {
               int cur = new Integer(st);
               ans += cur;
           }
       System.out.println(ans);
    }
}
       