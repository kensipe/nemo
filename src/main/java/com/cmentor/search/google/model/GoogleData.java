package com.cmentor.search.google.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author kensipe
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleData {

    private List<GoogleSearchResultLink> results;

    public List<GoogleSearchResultLink> getResults() {
        return results;
    }

    public void setResults(List<GoogleSearchResultLink> results) {
        this.results = results;
    }
}
