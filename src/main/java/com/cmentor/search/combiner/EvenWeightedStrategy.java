package com.cmentor.search.combiner;

import com.cmentor.search.SearchResultLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * provides a 1 element partition for all results resulting in 1-1 interleave.  This implementation may be
 * more complex then is demanded based on the interleave, however it provides some consistency resulting in
 * having 1 tested solution.
 *
 * @author kensipe
 */

public class EvenWeightedStrategy implements CombinerStrategy {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<SearchResultLink> combine(Map<String, List<SearchResultLink>> linksMap) {

        List<SearchResultLink> results = new ArrayList<SearchResultLink>();
        // fail fast
        if (linksMap == null && CombinerStrategyUtil.atLeastOneListIsNotNull(linksMap)) {
            logger.debug("failing fast, no lists provided");
            return results;
        }

        Map<String, List> linkPartitionedMap = new HashMap<String, List>();
        for (String engine : linksMap.keySet()) {
            linkPartitionedMap.put(engine, CombinerStrategyUtil.partition(linksMap.get(engine), 1));
        }
        results = CombinerStrategyUtil.combine(linkPartitionedMap);
        return results;
    }
}
