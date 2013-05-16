package com.cmentor.search.bing;

import com.cmentor.search.bing.model.BingResults;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author kensipe
 */
@Component
public class BingJsonResultParser {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    ObjectMapper jsonMapper = null;

    public BingJsonResultParser() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        jsonMapper = mapper;
    }


    public BingResults parse(String json) throws IOException {
        BingResults results = null;
        try {
            results = jsonMapper.readValue(json, BingResults.class);
        } catch (IOException e) {
            logger.error("unable to parse json for bing", e);
            throw e;
        }
        return results;
    }

    public String json(BingResults results) throws IOException {
        return jsonMapper.writeValueAsString(results);
    }
}
