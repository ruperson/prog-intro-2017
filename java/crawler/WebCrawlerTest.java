package crawler;

import base.Asserts;
import base.TestCounter;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class WebCrawlerTest {
    protected final static TestCounter COUNTER = new TestCounter();

    public static void main(String[] args) {
        testSynthetic("absolute-links.html", 1);
        testSynthetic("relative-links.html", 1);
        testSynthetic("depth.html", 2);
        testSynthetic("errors.html", 3);
        testSynthetic("infinite-1.html", 100);
        testSynthetic("entities.html", 2);
        testSynthetic("nested.html", 2);
        testSynthetic("many.html", 100);
        testSynthetic("images.html", 1);
        testSynthetic("images-depth.html", 2);
        testSynthetic("whitespace.html", 2);
        testSynthetic("more.html", 2);
        testSynthetic("comments.html", 2);
        testSynthetic("comments-in-title.html", 2);
        testSynthetic("doctype.html", 2);
        testSynthetic("more-entities.html", 2);
        testBase("https://www.kgeorgiy.info/courses/prog-intro/", 1);
        testBase("https://www.kgeorgiy.info/courses/", 2);
        COUNTER.printStatus(WebCrawlerTest.class);
    }

    private static void testSynthetic(final String url, final int depth) {
        testBase("http://www.kgeorgiy.info/courses/prog-intro/tests/crawler/" + url, depth);
    }

    private static void testBase(final String test, final int depth) {
        test("base_cache.zip", test, depth);
    }

    public static void test(final String cache, final String url, final int depth) {
        try {
            try (final ZipDownloader downloader = new ZipDownloader(cache)) {
                test(url, depth, downloader);
            }
        } catch (IOException e) {
            throw new AssertionError("Invalid test cache " + cache + " for " + url, e);
        }
    }

    public static Page test(final String url, final int depth, final ZipDownloader downloader) throws IOException {
        COUNTER.nextTest();
        System.err.println("=== Testing " + url);
        final Page actual = crawl(url, depth, downloader);

        try (final ObjectInputStream is = new ObjectInputStream(downloader.download(url + ".ser"))) {
            try {
                final Page expected = (Page) is.readObject();
                compare(expected, actual, new HashMap<>(), new HashMap<>(), downloader);
            } catch (ClassNotFoundException e) {
                throw new AssertionError(e);
            }
        }
        COUNTER.passed();
        return actual;
    }

    private static Page crawl(final String url, final int depth, final Downloader downloader) {
        try {
            return new SimpleWebCrawler(downloader).crawl(url, depth);
        } catch (IOException e) {
            throw new AssertionError("Error crawling " + url, e);
        }
    }

    public static void compare(final Page expected, final Page actual, final Map<Page, Page> pages, final Map<Image, Image> images, final Downloader downloader) {
        final Page other = pages.putIfAbsent(expected, actual);
        if (other == null) {
            System.out.println("Checking page " + expected.getUrl());
            Asserts.assertEquals("Url of " + expected.getUrl(), expected.getUrl(), actual.getUrl());
            Asserts.assertEquals("Title of " + expected.getUrl(), expected.getTitle(), actual.getTitle());
            Asserts.assertEquals("Links of " + expected.getUrl(), links(expected), links(actual));
            Asserts.assertEquals("Images of " + expected.getUrl(), images(expected), images(expected));
            Asserts.assertEquals("Back links to " + expected.getUrl(), backLinks(expected), backLinks(actual));
            for (int i = 0; i < expected.getLinks().size(); i++) {
                compare(expected.getLinks().get(i), actual.getLinks().get(i), pages, images, downloader);
            }
            for (int i = 0; i < expected.getImages().size(); i++) {
                compare(expected.getImages().get(i), actual.getImages().get(i), images, downloader);
            }
        } else {
            Asserts.assertSame("Page for " + expected.getUrl(), other, actual);
        }
    }

    private static void compare(final Image expected, final Image actual, final Map<Image, Image> images, Downloader downloader) {
        final Image other = images.putIfAbsent(expected, actual);
        if (other == null) {
            if (expected.getUrl() != null) {
                System.out.println("Checking image " + expected.getUrl());
                Asserts.assertEquals("Url of " + expected.getUrl(), expected.getUrl(), actual.getUrl());
                if (expected.getFile() != null) {
                    try {
                        Asserts.assertEquals("Hash of " + expected.getUrl(), hash(expected, downloader), hash(actual.getFile()));
                    } catch (IOException e) {
                        throw new AssertionError(e);
                    }
                }
            }
            Asserts.assertEquals("Links of " + expected.getUrl(), links(expected), links(actual));
        } else {
            Asserts.assertSame("Image for " + expected.getUrl(), other, actual);
        }
    }

    private static int hash(final Image expected, final Downloader downloader) throws IOException {
        try (final InputStream is = downloader.download(expected.getUrl())) {
            return hash(is);
        }
    }

    private static List<String> images(final Page page) {
        return extract(page.getImages(), Image::getUrl);
    }

    private static List<String> links(final Page page) {
        return extract(page.getLinks(), Page::getUrl);
    }

    private static List<String> links(final Image image) {
        return sort(extract(image.getPages(), Page::getUrl));
    }

    private static List<String> backLinks(final Page page) {
        return sort(extract(page.getBackLinks(), Page::getUrl));
    }

    private static List<String> sort(final List<String> links) {
        Collections.sort(links);
        return links;
    }

    private static <T> List<String> extract(final List<T> ts, Function<T, String> extractor) {
        return ts.stream().map(extractor).collect(Collectors.toList());
    }

    private static int hash(String file) throws IOException {
        try (final InputStream is = Files.newInputStream(Paths.get(file))) {
            return hash(is);
        }
    }

    private static int hash(final InputStream is) throws IOException {
        int hash = 0;
        byte[] buffer = new byte[1 << 16];
        int read;
        while ((read = is.read(buffer)) != -1) {
            for (int i = 0; i < read; i++) {
                hash = hash * 31 + buffer[i];
            }
        }
        return hash;
    }
}
