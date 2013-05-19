package com.cmentor.search.google;

import com.cmentor.search.google.model.GoogleResults;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author kensipe
 */
public class GoogleJsonResultParser {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    ObjectMapper jsonMapper = null;

    public GoogleJsonResultParser() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        jsonMapper = mapper;
    }

    public GoogleResults parse(String json) throws IOException {
        GoogleResults results;
        try {
            results = jsonMapper.readValue(json, GoogleResults.class);
        } catch (IOException e) {
            logger.error("unable to parse json for google", e);
            throw e;
        }
        return results;
    }
}
