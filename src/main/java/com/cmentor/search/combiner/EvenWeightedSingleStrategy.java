package com.cmentor.search.combiner;

import com.cmentor.search.SearchResultLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * provides a 1 element partition for all results resulting in 1-1 interleave.  This implementation may be
 * more complex then is demanded based on the interleave, however it provides some consistency resulting in
 * having 1 tested solution.
 *
 * @author kensipe
 */

@Component("even_weighted_single")
public class EvenWeightedSingleStrategy implements CombinerStrategy {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    PartitionCombiner combiner = new PartitionCombiner();

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
        return "even_weighted_single";
    }
}
