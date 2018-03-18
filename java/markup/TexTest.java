package markup;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class TexTest extends MarkdownTest {
    public static void main(String[] args) {
        new TexTest().run();
    }

    @Override
    protected void test(final Paragraph paragraph, final String expected) {
        super.test(paragraph, expected);
        test(
                paragraph::toTex,
                expected
                        .replace("*<", "\\emph{")
                        .replace("__<", "\\textbf{")
                        .replace("~<", "\\textst{")
                        .replaceAll("\\*>|__>|~>", "}")
        );
    }
}
