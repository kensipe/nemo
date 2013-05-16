package com.cmentor.search.google.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author kensipe
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleResults {

    @JsonProperty("responseData")
    private GoogleData data;

    public GoogleData getData() {
        return data;
    }

    public void setData(GoogleData data) {
        this.data = data;
    }
}
