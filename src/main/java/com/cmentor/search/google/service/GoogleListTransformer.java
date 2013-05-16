package com.cmentor.search.google.service;

import com.cmentor.search.SearchResultLink;
import com.cmentor.search.google.model.GoogleSearchResultLink;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kensipe
 */
public class GoogleListTransformer {

    public static List<SearchResultLink> transform(List<GoogleSearchResultLink> googleLinks) {
        List<SearchResultLink> links;

        if (googleLinks != null) {
            links = new ArrayList<SearchResultLink>(googleLinks.size());
            for (GoogleSearchResultLink googleLink : googleLinks) {
                SearchResultLink link = new SearchResultLink();
                link.setSource("google");
                link.setDescription(googleLink.getDescription());
                link.setTitle(googleLink.getTitle());
                link.setUrl(googleLink.getUrl());
                links.add(link);
            }
        } else {
            links = new ArrayList<SearchResultLink>();
        }
        return links;
    }
}
