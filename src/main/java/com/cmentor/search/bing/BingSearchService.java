package com.cmentor.search.bing;

import com.cmentor.search.SearchResultLink;
import com.cmentor.search.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * This is the external exposed search service which transforms the bing search into the canonical search result model
 *
 * @author kensipe
 */
public class BingSearchService implements SearchService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<SearchResultLink> search(String criteria) {
        return null;
    }
}
