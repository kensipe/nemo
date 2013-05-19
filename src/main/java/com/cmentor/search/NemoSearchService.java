package com.cmentor.search;

import com.cmentor.search.combiner.CombinerStrategy;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author kensipe
 */
@Component
public class NemoSearchService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final static String DEFAULT_STRATEGY = "even_weighted_single";

    @Autowired
    private List<SearchService> searchServices;

    @Autowired
    private List<CombinerStrategy> strategies;

    private Set<String> serviceNames = new HashSet<String>();
    private Map<String, CombinerStrategy> strategyMap = new HashMap<String, CombinerStrategy>();

    @PostConstruct
    public void init() {

        if (searchServices != null) {
            for (SearchService service : searchServices) {
                serviceNames.add(service.serviceName());
            }
        } else {
            logger.error("no services injected");
        }

        if (strategies != null) {
            for (CombinerStrategy strategy : strategies) {
                strategyMap.put(strategy.getStrategyName(), strategy);
            }
        } else {
            logger.error("no strategies injected");
        }
    }

    public Set<String> getServiceNames() {
        return serviceNames;
    }

    public Set<String> getStrategyNames() {
        return strategyMap.keySet();
    }

    public List<SearchResultLink> search(String criteria) {
        // assumed available default
        return search(criteria, DEFAULT_STRATEGY);
    }

    public List<SearchResultLink> search(String criteria, String strategy) {

        Map<String, List<SearchResultLink>> searchResults = getMapOfEachSearchEngineResults(criteria);

        if (strategy == null) {
            strategy = DEFAULT_STRATEGY;
        }

        return strategyMap.get(strategy).combine(searchResults);
    }

    private Map<String, List<SearchResultLink>> getMapOfEachSearchEngineResults(String criteria) {

        Map<String, List<SearchResultLink>> searchResults = new HashMap<String, List<SearchResultLink>>(searchServices.size());

        if (StringUtils.isNotBlank(criteria) && searchServices.size() > 0) {
            // todo:  perfect place to make calls parallel :)
            for (SearchService service : searchServices) {
                searchResults.put(service.serviceName(), service.search(criteria));
            }
        }
        return searchResults;
    }
}
