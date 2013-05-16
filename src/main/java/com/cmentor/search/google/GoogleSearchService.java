package com.cmentor.search.google;

import com.cmentor.search.SearchResultLink;
import com.cmentor.search.SearchService;
import com.cmentor.search.google.model.GoogleResults;
import com.cmentor.search.google.model.GoogleSearchResultLink;
import com.cmentor.search.google.service.GoogleInternalSearchService;
import com.cmentor.search.google.service.GoogleListTransformer;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kensipe
 */
@Component
public class GoogleSearchService implements SearchService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    GoogleInternalSearchService searchService;
    GoogleJsonResultParser parser = new GoogleJsonResultParser();

    @Override
    public List<SearchResultLink> search(String criteria) {

        List<GoogleSearchResultLink> googleLinks = getGoogleSearchResults(criteria);
        List<SearchResultLink> links = GoogleListTransformer.transform(googleLinks);
        return links;
    }

    private List<GoogleSearchResultLink> getGoogleSearchResults(String criteria) {

        String json = null;
        try {
            json = searchService.search(criteria);
        } catch (Exception e) {
            logger.error("Failure to make http connection", e);
        }

        return parseJson(json);
    }

    private List<GoogleSearchResultLink> parseJson(String json) {
        List<GoogleSearchResultLink> links = null;
        if (StringUtils.isNotBlank(json)) {
            try {
                GoogleResults results = parser.parse(json);
                links = results.getData().getResults();
            } catch (IOException e) {
                logger.error("unable to parse json: {}", json, e);

            }
        }
        if (links == null) {
            links = new ArrayList<GoogleSearchResultLink>();
        }
        return links;
    }
}
