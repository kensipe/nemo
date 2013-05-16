package com.cmentor.search.bing.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author kensipe
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BingResults {

    @JsonProperty("d")
    private BingData bingData;

    public BingData getBingData() {
        return bingData;
    }

    public void setBingData(BingData bingData) {
        this.bingData = bingData;
    }
}
