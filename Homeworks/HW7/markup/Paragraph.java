package markup;

import java.util.List;

public class Paragraph implements MarkDownStruct  {
    private List<MarkDownStruct> components;

    public Paragraph(List<MarkDownStruct> components) {
        this.components = components;
    }

    public void toMarkdown(StringBuilder sb) {
        for (MarkDownStruct el : components) {
            el.toMarkdown(sb);
        }
    }
    public void toHtml(StringBuilder sb) {
        for (MarkDownStruct el : components) {
            el.toHtml(sb);
        }
    }
}
