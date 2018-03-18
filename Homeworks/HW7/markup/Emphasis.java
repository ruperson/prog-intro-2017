package markup;

import java.util.List;

public class Emphasis extends AbstractEmphasis {
    public Emphasis(List<MarkDownStruct> components) {
        super(components);
        surrounding = "*";
        HtmlOpen = "<em>";
        HtmlClose = "</em>";
    }
}
