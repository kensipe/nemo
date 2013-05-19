package com.cmentor.search;

import java.util.List;

/**
 * canonical service interface... any search service needs to have an implement for this interface and it must transform the results to
 * the canonical search result link list.
 *
 * @author kensipe
 */
public interface SearchService {

    List<SearchResultLink> search(String criteria);

    String serviceName();
}
