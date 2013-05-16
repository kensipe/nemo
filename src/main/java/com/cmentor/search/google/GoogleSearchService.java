package com.cmentor.search.google;

import com.cmentor.search.SearchResultLink;
import com.cmentor.search.SearchService;
import com.cmentor.search.google.service.GoogleInternalSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author kensipe
 */
@Component
public class GoogleSearchService implements SearchService{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    GoogleInternalSearchService searchService;

    @Override
    public List<SearchResultLink> search(String criteria) {
        return null;
    }
}
