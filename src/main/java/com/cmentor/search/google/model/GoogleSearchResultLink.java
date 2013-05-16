package com.cmentor.search.google.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author kensipe
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleSearchResultLink {

    @JsonProperty("title")
    private String title;
    @JsonProperty("content")
    private String description;
    @JsonProperty("url")
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
