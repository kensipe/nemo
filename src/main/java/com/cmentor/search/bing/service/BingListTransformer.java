package com.cmentor.search.bing.service;

import com.cmentor.search.SearchResultLink;
import com.cmentor.search.bing.model.BingSearchResultLink;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kensipe
 */
public class BingListTransformer {

    public static List<SearchResultLink> transform(List<BingSearchResultLink> bingLinks) {
        List<SearchResultLink> links;

        if (bingLinks != null) {
            links = new ArrayList<SearchResultLink>(bingLinks.size());
            for (BingSearchResultLink bingLink : bingLinks) {
                SearchResultLink link = new SearchResultLink();
                link.setSource("bing");
                link.setDescription(bingLink.getDescription());
                link.setTitle(bingLink.getTitle());
                link.setUrl(bingLink.getUrl());
                links.add(link);
            }
        } else {
            links = new ArrayList<SearchResultLink>();
        }
        return links;
    }
}
