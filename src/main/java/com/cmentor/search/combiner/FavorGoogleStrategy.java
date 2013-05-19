package com.cmentor.search.combiner;

import com.cmentor.search.SearchResultLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * provides a 2 element partition for google and 1 element for all others results resulting in 1-1 interleave.  This implementation may be
 * more complex then is demanded based on the interleave, however it provides some consistency resulting in
 * having 1 tested solution.
 *
 * @author kensipe
 */

public class FavorGoogleStrategy implements CombinerStrategy {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    PartitionCombiner combiner;

    public FavorGoogleStrategy() {
        Map<String, Integer> partitionMap = new HashMap<String, Integer>(1);
        // assumes lower case naming.. and google in the mix.  if google isn't in the mix then everything is partition of 1 (which is ok)
        partitionMap.put("google", 2);
        combiner = new PartitionCombiner(partitionMap);
    }

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
}
