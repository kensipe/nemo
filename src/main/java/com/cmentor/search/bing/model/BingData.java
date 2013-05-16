package com.cmentor.search.bing.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * @author kensipe
 */
public class BingData {

    private List<BingSearchResultLink> results;

    @JsonProperty("__next")
    private String nextUrl;

    public List<BingSearchResultLink> getResults() {
        return results;
    }

    public void setResults(List<BingSearchResultLink> results) {
        this.results = results;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }
}
