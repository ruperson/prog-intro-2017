package crawler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Image implements Serializable {
    private final String url;
    private final String file;
    private final List<Page> pages = new ArrayList<>();

    public Image(final String url, final String file) {
        this.url = url;
        this.file = file;
    }

    public String getUrl() {
        return url;
    }

    public String getFile() {
        return file;
    }

    public List<Page> getPages() {
        return pages;
    }
}
