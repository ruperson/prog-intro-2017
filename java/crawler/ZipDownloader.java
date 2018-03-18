package crawler;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ZipDownloader implements Downloader, AutoCloseable {
    private final ZipFile file;

    public ZipDownloader(final String zipFile) throws IOException {
        file = new ZipFile(zipFile);
    }

    @Override
    public InputStream download(final String url) throws IOException {
        final ZipEntry entry = file.getEntry(CachingDownloader.encode(url));
        if (entry == null) {
            throw new IOException("Url not found " + url);
        }
        return CachingDownloader.readCached(url, file.getInputStream(entry));
    }

    @Override
    public void close() throws IOException {
        file.close();
    }
}
