package crawler;

import java.io.*;
import java.net.URLEncoder;
import java.nio.file.*;

/**
 * Downloads pages from upstream downloader and stores them in the local cache;
 *
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class CachingDownloader implements Downloader {
    private final Downloader upstream;
    private final Path directory;
    private static final byte[] OK = {(byte) '+'};

    public CachingDownloader(final Downloader upstream, final String directory) throws IOException {
        this.upstream = upstream;
        this.directory = Paths.get(directory);
        createDirectory(this.directory);
    }

    public static void createDirectory(final Path directory) throws IOException {
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
        if (!Files.isDirectory(directory)) {
            throw new IOException(directory + " is not a directory");
        }
    }

    @Override
    public InputStream download(final String url) throws IOException {
        final Path file = directory.resolve(encode(url));
        if (Files.notExists(file)) {
            upstream(url, file);
        } else {
//            System.out.println("Already downloaded " + url);
        }
        try {
            final InputStream is = Files.newInputStream(file);
            return readCached(url, is);
        } catch (FileSystemException e) {
            throw new AssertionError(e);
        }
    }

    public static InputStream readCached(final String url, final InputStream is) throws IOException {
        final int marker = is.read();
        if (marker == '+') {
            return is;
        } else if (marker == '-') {
            throw new IOException(new DataInputStream(is).readUTF());
        } else {
            throw new AssertionError("Invalid file cache  for " + url);
        }
    }

    public static String encode(final String url) {
        try {
            final String encoded = URLEncoder.encode(url, "UTF-8");
            return encoded.length() < 200 ? encoded : encoded.substring(0, 200);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    private void upstream(final String url, final Path file) {
        try {
            System.out.println("Downloading " + url);
            try (final InputStream is = upstream.download(url)) {
                final SequenceInputStream sis = new SequenceInputStream(new ByteArrayInputStream(OK), is);
                Files.copy(sis, file, StandardCopyOption.REPLACE_EXISTING);
            }
            System.out.println("Downloaded " + url);
        } catch (IOException e) {
            System.out.println("Error downloading " + url + ": " + e.getMessage());
            try {
                try (final DataOutputStream os = new DataOutputStream(Files.newOutputStream(file, StandardOpenOption.CREATE))) {
                    os.write('-');
                    os.writeUTF(e.getMessage());
                }
            } catch (IOException ee) {
                throw new AssertionError("Cannot write error info for " + url + " to " + file, ee);
            }
        }
    }
}
