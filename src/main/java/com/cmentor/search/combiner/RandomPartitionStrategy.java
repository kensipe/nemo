package com.cmentor.search.combiner;

import com.cmentor.search.SearchResultLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * provides a 2 element partition for google and 1 element for all others results resulting in 1-1 interleave.  This implementation may be
 * more complex then is demanded based on the interleave, however it provides some consistency resulting in
 * having 1 tested solution.
 *
 * @author kensipe
 */

public class RandomPartitionStrategy implements CombinerStrategy {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final static int DEFAULT_RANDOM_LIMIT = 3;
    private int randomLimit = DEFAULT_RANDOM_LIMIT;

    public RandomPartitionStrategy() {
    }

    public RandomPartitionStrategy(int randomLimit) {
        if (randomLimit > 0) {
            this.randomLimit = randomLimit;
        }
    }

    @Override
    public List<SearchResultLink> combine(Map<String, List<SearchResultLink>> linksMap) {
        List<SearchResultLink> results = new ArrayList<SearchResultLink>();
        // fail fast
        if (linksMap == null || !CombinerStrategyUtil.atLeastOneListIsNotEmpty(linksMap)) {
            logger.debug("failing fast, no lists provided");
            return results;
        }

        Map<String, Integer> partitionMap = getRandomPartitionSizeMap(linksMap.keySet());
        PartitionCombiner combiner = new PartitionCombiner(partitionMap);



        results = combiner.combine(linksMap);
        return results;
    }

    private Map<String, Integer> getRandomPartitionSizeMap(Set<String> engines) {
        Map<String, Integer> sizeMap = new HashMap<String, Integer>(engines.size());
        for (String engine : engines) {
            sizeMap.put(engine, getRandomNumber());
        }
        return sizeMap;
    }

    private Integer getRandomNumber() {
        return (int) (Math.random() * randomLimit) + 1;
    }
}
