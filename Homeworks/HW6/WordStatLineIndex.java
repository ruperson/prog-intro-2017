import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.regex.*;
public class WordStatLineIndex {
    public static void main(String[] args) throws IOException {
        try {
            BufferedReader reader = new BufferedReader (new InputStreamReader(new FileInputStream(args[0]),"UTF-8"));
            int ans = 0;
            Map <String, List<Pair<Integer,Integer>>>  mp = new HashMap<>();
            Pattern pt = Pattern.compile("[\\p{L}\\p{Pd}\\']+");
            try {
                String s;
                int i = 0;
                while ((s = reader.readLine()) != null) { 
                    Matcher m = pt.matcher(s);
                    int j = 0;
                    while (m.find()) {
                        String st = m.group(0);
                        if (st.length() > 0) {
                            st = st.toLowerCase();
                            List <Pair<Integer,Integer>> entries = mp.get(st);
                            if (entries == null) {
                                entries = new ArrayList<>();
                                entries.add(new Pair<Integer, Integer>(i + 1, j + 1));
                                mp.put(st, entries);
                            } else 
                                entries.add(new Pair<Integer, Integer>(i + 1, j + 1));
                            ++j;
                        }
                    }
                    ++i;
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
                    mp.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> {
                    out.print(entry.getKey() + " " + Integer.toString(entry.getValue().size()));
                    for (Pair<Integer,Integer> i : entry.getValue())
                    out.print(" " + i.getFirst().toString() + ":" + i.getSecond().toString());
                    out.println();
                    });
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