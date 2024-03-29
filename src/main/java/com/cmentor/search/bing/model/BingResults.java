package com.cmentor.search.bing.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author kensipe
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BingResults {

    @JsonProperty("d")
    private BingData data;

    public BingData getData() {
        return data;
    }

    public void setData(BingData data) {
        this.data = data;
    }
}
