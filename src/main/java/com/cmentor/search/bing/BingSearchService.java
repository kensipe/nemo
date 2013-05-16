package com.cmentor.search.bing;

import com.cmentor.search.SearchResultLink;
import com.cmentor.search.SearchService;
import com.cmentor.search.bing.model.BingResults;
import com.cmentor.search.bing.model.BingSearchResultLink;
import com.cmentor.search.bing.service.BingInternalSearchService;
import com.cmentor.search.bing.service.BingListTransformer;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the external exposed search service which transforms the bing search into the canonical search result model
 *
 * @author kensipe
 */

@Component
public class BingSearchService implements SearchService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BingInternalSearchService searchService;
    BingJsonResultParser parser = new BingJsonResultParser();

    @Override
    public List<SearchResultLink> search(String criteria) {

        List<BingSearchResultLink> bingLinks = getBingSearchResults(criteria);
        List<SearchResultLink> links = BingListTransformer.transform(bingLinks);
        return links;
    }

    private List<BingSearchResultLink> getBingSearchResults(String criteria) {

        String json = null;
        try {
            json = searchService.search(criteria);
        } catch (Exception e) {
            logger.error("Failure to make http connection", e);
        }

        return parseJson(json);
    }

    private List<BingSearchResultLink> parseJson(String json) {
        List<BingSearchResultLink> links = null;
        if (StringUtils.isNotBlank(json)) {
            try {
                BingResults results = parser.parse(json);
                links = results.getData().getResults();
            } catch (IOException e) {
                logger.error("unable to parse json: {}", json, e);

            }
        }
        if (links == null) {
            links = new ArrayList<BingSearchResultLink>();
        }
        return links;
    }
}
