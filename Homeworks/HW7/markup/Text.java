package markup;


public class Text implements MarkDownStruct {
    private final String text;

    public Text(String text) {
        this.text = text;
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(text);
    }
    public void toHtml(StringBuilder sb) { sb.append(text); }
}