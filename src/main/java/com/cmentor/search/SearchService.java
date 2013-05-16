package com.cmentor.search;

import java.util.List;

/**
 * @author kensipe
 */
public interface SearchService {

    List<SearchResultLink> search(String criteria);

    String serviceName();
}
