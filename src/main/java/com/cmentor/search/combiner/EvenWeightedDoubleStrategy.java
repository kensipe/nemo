package com.cmentor.search.combiner;

import com.cmentor.search.SearchResultLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author kensipe
 */
@Component("even_weighted_double")
public class EvenWeightedDoubleStrategy implements CombinerStrategy {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    PartitionCombiner combiner = new PartitionCombiner(2);

    @Override
    public List<SearchResultLink> combine(Map<String, List<SearchResultLink>> linksMap) {

        List<SearchResultLink> results = new ArrayList<SearchResultLink>();
        // fail fast
        if (linksMap == null || !CombinerStrategyUtil.atLeastOneListIsNotEmpty(linksMap)) {
            logger.debug("failing fast, no lists provided");
            return results;
        }

        results = combiner.combine(linksMap);
        return results;
    }

    @Override
    public String getStrategyName() {
        return "even_weighted_double";
    }
}
