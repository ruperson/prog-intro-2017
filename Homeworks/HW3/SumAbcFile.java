import java.io.*;
import java.util.*;

public class SumAbcFile {
    public static void main(String[] args) throws IOException {
        try {
            BufferedReader reader = new BufferedReader (new InputStreamReader(new FileInputStream(args[0]),"UTF-8"));
            String s = "";
            int ans = 0;
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    s += line + " ";               
                }
                String[] t = s.split("\\p{javaWhitespace}+");
                for (String st : t) {
                    char[] DigAr = st.toCharArray();
                    for (int i = 0; i < DigAr.length; ++i) {
                       if (Character.isLetter(DigAr[i])) {
                          DigAr[i] = Character.toLowerCase(DigAr[i]);
                          DigAr[i] = Character.forDigit((DigAr[i] - 'a'), 10);
                       }
                    }
                    String nw = new String(DigAr);
                    if (nw.length() > 0) {
                      ans += new Integer(nw);
                    }
                }
            } catch (InputMismatchException ex) {
                System.out.println("InputMismatchException");
                System.exit(1);
            } catch (IllegalStateException ex) {
                System.out.println("IllegalStateException");
                System.exit(1);
            } catch (NoSuchElementException ex) {
                // That's fine
            } finally {
                reader.close();
                try {
                    PrintWriter out = new PrintWriter (args[1], "UTF-8");
                    out.println(ans);
                    out.close();
                } catch (IOException ex) {
                    System.out.println("Something went wrong while writing the result number");
                    System.exit(1);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File " + args[0] + " not found");
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("LOLOL");
            System.exit(1);
        }
    }
}