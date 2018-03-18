package markup;


import java.util.List;

public class Strong extends AbstractEmphasis {
    public Strong( List<MarkDownStruct> components) {
        super(components);
        surrounding = "__";
        HtmlOpen = "<strong>";
        HtmlClose = "</strong>";
    }
}