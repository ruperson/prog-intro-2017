package crawler;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Downloader {
    InputStream download(String url) throws IOException;
}
