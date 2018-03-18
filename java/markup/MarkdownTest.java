package markup;

import base.Asserts;
import base.TestCounter;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Consumer;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class MarkdownTest {
    private TestCounter counter = new TestCounter();

    public static void main(String... args) {
        new MarkdownTest().run();
    }

    protected void run() {
        test();
        counter.printStatus(getClass());
    }

    private void test() {
        test(new Paragraph(Collections.singletonList(new Text("Hello"))), "Hello");
        test(new Paragraph(Collections.singletonList(new Emphasis(Collections.singletonList(new Text("Hello"))))), "*<Hello*>");
        test(new Paragraph(Collections.singletonList(new Strong(Collections.singletonList(new Text("Hello"))))), "__<Hello__>");
        test(new Paragraph(Collections.singletonList(new Strikeout(Collections.singletonList(new Text("Hello"))))), "~<Hello~>");
        final Paragraph paragraph = new Paragraph(Collections.singletonList(
                new Strong(Arrays.asList(
                        new Text("1"),
                        new Strikeout(Arrays.asList(
                                new Text("2"),
                                new Emphasis(Arrays.asList(
                                        new Text("3"),
                                        new Text("4")
                                )),
                                new Text("5")
                        )),
                        new Text("6")
                ))
        ));
        test(
            paragraph,
            "__<1~<2*<34*>5~>6__>"
        );
        test(new Paragraph(Collections.singletonList(
                new Strong(Arrays.asList(new Text("sdq"), new Strikeout(Arrays.asList(new Emphasis(Collections.singletonList(new Text("r"))), new Text("vavc"))), new Text("zg"))))),
                "__<sdq~<*<r*>vavc~>zg__>"
        );
    }

    protected void test(Paragraph paragraph, final String expected) {
        test(paragraph::toMarkdown, expected.replaceAll("[<>]", ""));
    }

    public void test(Consumer<StringBuilder> f, final String expected) {
        counter.nextTest();
        final StringBuilder sb = new StringBuilder();
        f.accept(sb);
        final String actual = sb.toString();
        Asserts.assertEquals("Result", expected, actual);
        counter.passed();
    }
}
