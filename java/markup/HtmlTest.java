package markup;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HtmlTest extends MarkdownTest {
    public static void main(String[] args) {
        new HtmlTest().run();
    }

    @Override
    protected void test(final Paragraph paragraph, final String expected) {
        super.test(paragraph, expected);
        test(
                paragraph::toHtml,
                expected
                        .replace("*<", "<em>")
                        .replace("*>", "</em>")
                        .replace("__<", "<strong>")
                        .replace("__>", "</strong>")
                        .replace("~<", "<s>")
                        .replace("~>", "</s>")
        );
    }
}
