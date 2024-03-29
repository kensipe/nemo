package com.cmentor.search;

/**
 * All search engines must transform to this type
 *
 * @author kensipe
 */
public class SearchResultLink {

    private String source;
    private String title;
    private String url;
    private String description;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
