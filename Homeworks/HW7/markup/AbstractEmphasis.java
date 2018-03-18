package markup;

import java.util.List;

public abstract class AbstractEmphasis implements MarkDownStruct {
    private List <MarkDownStruct> components;
    String surrounding;
    String HtmlOpen, HtmlClose;

    public AbstractEmphasis(List<MarkDownStruct> components) {
        this.components = components;

    }


    public void toMarkdown(StringBuilder sb) {
        sb.append(surrounding);
        for (MarkDownStruct el : components) {
            el.toMarkdown(sb);
        }
        sb.append(surrounding);
    }
    public void toHtml(StringBuilder sb) {
        sb.append(HtmlOpen);
        for (MarkDownStruct el : components) {
            el.toHtml(sb);
        }
        sb.append(HtmlClose);
    }
}