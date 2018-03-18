import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.regex.*;
public class WordStatCount {
    public static void main(String[] args) throws IOException {
        try {
            BufferedReader reader = new BufferedReader (new InputStreamReader(new FileInputStream(args[0]),"UTF-8"));
            String s = "";
            int ans = 0;
            Map <String, Integer> mp = new LinkedHashMap<String, Integer>();
            Pattern pt = Pattern.compile("[\\p{L}\\p{Pd}\\']+");
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    s += line + " ";
                }  

                Matcher m = pt.matcher(s);
                while (m.find()) {
                String st = m.group(0);
                if (st.length() > 0) {
                    st = st.toLowerCase();
                    if (mp.containsKey(st))
                        mp.put(st, mp.get(st) + 1);
                    else
                        mp.put(st, 1);
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
                    mp.entrySet().stream().sorted(Map.Entry.comparingByValue()).
                    forEach(entry -> out.println(entry.getKey() + " " + entry.getValue()) );
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
            System.out.println("Are you, like, retarded?");
            System.exit(1);
        }
    }
}