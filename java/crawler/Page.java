package crawler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Page implements Serializable {
    private final String url;
    private final String title;
    private final List<Page> links = new ArrayList<>();
    private final List<Page> backLinks = new ArrayList<>();
    private final List<Image> images = new ArrayList<>();

    public Page(final String url, final String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public List<Page> getLinks() {
        return links;
    }

    public List<Page> getBackLinks() {
        return backLinks;
    }

    public List<Image> getImages() {
        return images;
    }

    public void addImage(final Image image) {
        images.add(image);
        image.getPages().add(this);
    }

    public void addLink(final Page other) {
        links.add(other);
        other.backLinks.add(this);
    }
}
