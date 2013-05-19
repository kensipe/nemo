package com.cmentor.search.combiner;

import com.cmentor.search.SearchResultLink;

import java.util.List;
import java.util.Map;

/**
 * This interface takes a map of results of searches, where the key of the map is the name of the service.
 * all implementations will provide 1 list back, each implementation will have it's own strategy for how to do that.
 * <p/>
 * assuming balanced lists... if not the remainder of the list is not expected to be weighted
 *
 * NOTE:  there are several limitations... my preference would be that the search services provide a relevancy score, then the services could
 * have weighted values multiplied by relevancy.  Without this the only way to combine the search results is to interleave them based
 * on a partition which is the assumption that a combiner will make... that based on a "preference" for a service, it's partition size
 * could be greater... thereby see that search result more often in clusters.
 *
 * NOTE: additionally there is nothing that that adjusts which service is the first service... for example with a 1 to 1 interleave, there
 * were no requires that indicated that google should or shouldn't be first in the list
 *
 * @author kensipe
 */
public interface CombinerStrategy {
    List<SearchResultLink> combine(Map<String, List<SearchResultLink>> resultsMap);

    String strategyName();
}
