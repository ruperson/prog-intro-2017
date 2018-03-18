package md2html;
/*java -jar md2html/artifacts-md2html-Md2HtmlUnderlineTest.jar*/
import java.io.*;
import java.util.*;

public class Md2Html {
    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static List<String> paragraph;

    public static void main(String[] args) throws IOException {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "UTF-8"));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8"));
            paragraph = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    FirstEntry();
                    paragraph.clear();
                }
                else {
                    paragraph.add(line);
                }
            }
            FirstEntry();
            reader.close();
            writer.close();
        }

    private static void FirstEntry() throws IOException {
        if (paragraph.isEmpty()) {
            return;
        }

        int headerLevel = getHeaderLevel(paragraph.get(0));
        if (headerLevel > 0)
            paragraph.set(0, new StringBuilder(paragraph.get(0)).delete(0, headerLevel + 1).toString());
        printBorders(headerLevel);
        writer.newLine();
    }

    private static int getHeaderLevel(String line) {
        int lvl = 0;
        while (line.charAt(lvl) == '#') ++lvl;
        return lvl * Boolean.compare(Character.isWhitespace(line.charAt(lvl)), false);
    }

    private static void printBorders(int lvl) throws IOException {
        writer.write( lvl > 0 ? "<h" + lvl + ">" : "<p>");
        printParagraph();
        writer.write( lvl > 0 ? "</h" + lvl + ">" : "</p>");
    }


    private static void printParagraph() throws IOException {
        Map <Character, Integer> tags =  new HashMap<>();
        tags.put('*', 0);
        tags.put('_', 0);
        tags.put('-', 0);
        tags.put('+', 0);
        tags.put('`', 0);
        tags.put('\\', 0);
        tags.put('^', 0);
        tags.put(')', 0);
        for (String line : paragraph) {
            for (int j = 0; j < line.length(); ++j) {
                char ch = line.charAt(j);
                if (tags.get(ch) != null) {
                    if (ch == '*' || ch == '_') {
                        if (j + 1 < line.length() && ch == line.charAt(j + 1)) {
                            ++j;
                            tags.put (ch, tags.get(ch) + 1);
                        }
                        else {
                            tags.put(--ch, tags.get(ch) + 1);
                        }
                    } else if (ch == '-' || ch == '+') {
                        ++j;
                        tags.put (ch, tags.get(ch) + 1);
                    } else if (ch == '`'){
                        tags.put (ch, tags.get(ch) + 1);
                    } else if (ch == '\\') {
                        ++j;
                    }
                }
            }
        }
        for (Map.Entry<Character, Integer> pair : tags.entrySet()) {
            tags.put (pair.getKey(),pair.getValue() - pair.getValue() % 2);
        }
        Map <Character, String> specials =  new HashMap<>();
        specials.put( '<', "&lt;");
        specials.put( '>', "&gt;");
        specials.put('&', "&amp;");
        for (int i = 0; i < paragraph.size(); ++i) {
            if (i > 0) writer.newLine();

            String line = paragraph.get(i);
            for (int j = 0; j < line.length(); ++j) {
                char ch = line.charAt(j);
                if (ch == '*' || ch == '_') {
                    if (j + 1 < line.length() && ch == line.charAt(j + 1) && tags.get(ch) > 0) {
                        if (tags.get(ch) % 2 == 0)
                            writer.write("<strong>");
                        else
                            writer.write("</strong>");
                        ++j;
                        tags.put(ch, tags.get(ch) - 1);
                    }
                    else {
                        --ch;
                        if (tags.get(ch) > 0) {
                            if (tags.get(ch) % 2 == 0)
                                writer.write("<em>");
                            else
                                writer.write("</em>");
                            tags.put(ch, tags.get(ch) - 1);
                        }
                        else writer.write(line.charAt(j));
                    }
                } else if (ch == '-' || ch == '+'){
                    if (j + 1 < line.length() && ch == line.charAt(j + 1) && tags.get(ch) > 0) {
                        if (tags.get(ch) % 2 == 0)
                            writer.write(ch == '+' ? "<u>" : "<s>");
                        else
                            writer.write(ch == '+' ? "</u>" : "</s>");
                        ++j;
                        tags.put(ch, tags.get(ch) - 1);
                    } else writer.write(line.charAt(j));
                } else if (ch == '`') {
                    if (tags.get(ch) > 0) {
                        if (tags.get(ch) % 2 == 0)
                            writer.write("<code>");
                        else
                            writer.write("</code>");
                        tags.put(ch, tags.get(ch) - 1);
                    }
                } else if (ch == '\\') {
                   // ++j;
                } else if (specials.containsKey(ch))
                    writer.write(specials.get(ch));
                else
                    writer.write(line.charAt(j));
            }
        }
    }
}
