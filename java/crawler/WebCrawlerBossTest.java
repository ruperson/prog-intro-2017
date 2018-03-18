package crawler;

import base.Asserts;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class WebCrawlerBossTest extends WebCrawlerTest {
    public static void main(String... args) {
        if (args.length != 1 && args.length != 2) {
            System.err.format("Usage: java %s <boss_cache.zip> [<salt>]%n", WebCrawlerBossTest.class.getName());

        } else {
            boss(args[0], args.length >= 2 ? args[1] : "");
        }
    }

    private static void boss(final String file, final String salt) {
        try (
                final ZipDownloader downloader = new ZipDownloader(file);
                final DataInputStream dis = new DataInputStream(downloader.download("link"))
        ) {
            final String url = dis.readUTF();
            final int depth = dis.readInt();
            final String expectedHash = dis.readUTF();
            Page page = test(url, depth, downloader);
            final String actualHash = hash("", page);
            Asserts.assertEquals("Hashes", expectedHash, actualHash);
            System.out.println("OK");
            System.out.println("Certificate: " + file + ":" + salt + ":" + hash(salt, page));
        } catch (IOException e) {
            throw new AssertionError("Invalid test cache " + file, e);
        }
    }

    public static String hash(final String salt, final Page page) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.digest(salt.getBytes(StandardCharsets.UTF_8));
            hash(digest, page, new HashSet<>());
            return new HexBinaryAdapter().marshal(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    private static void hash(final MessageDigest digest, final Page page, final Set<Page> pages) {
        if (!pages.add(page)) {
            return;
        }
        for (Page p : pages) {
            digest.digest(p.getUrl().getBytes(StandardCharsets.UTF_8));
            hash(digest, p, pages);
        }
        for (Image image : page.getImages()) {
            digest.digest(image.getUrl().getBytes(StandardCharsets.UTF_8));
        }
    }
}
