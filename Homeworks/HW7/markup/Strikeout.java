package markup;


import java.util.List;

public class Strikeout extends AbstractEmphasis{
    public Strikeout(List<MarkDownStruct> components) {
        super(components);
        surrounding = "~";
        HtmlOpen = "<s>";
        HtmlClose = "</s>";
    }
}