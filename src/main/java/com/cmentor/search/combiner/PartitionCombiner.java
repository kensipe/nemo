package com.cmentor.search.combiner;

import com.cmentor.search.SearchResultLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author kensipe
 */
public class PartitionCombiner {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static int DEFAULT_PARTITION = 1;

    private Map<String, Integer> enginePartitionSizeMap;
    private int partitionSize;

    /**
     * you can control individual partition sizes through the map... otherwise it is balanced
     */
    public PartitionCombiner(Map<String, Integer> enginePartitionSizeMap) {
        // todo:  make unmodifiable
        this.enginePartitionSizeMap = enginePartitionSizeMap;
    }

    public PartitionCombiner() {
        partitionSize = DEFAULT_PARTITION;
    }

    public PartitionCombiner(int partitionSize) {
        this.partitionSize = partitionSize;
    }

    List<SearchResultLink> combine(Map<String, List<SearchResultLink>> linksMap) {

        Map<String, List> linkPartitionedMap = getPartitionMap(linksMap);
        List<SearchResultLink> results = CombinerStrategyUtil.combine(linkPartitionedMap);

        return results;

    }

    private Map<String, Integer> getPartitionSizeMap(Set<String> engines) {
        Map<String, Integer> map;

        if (enginePartitionSizeMap == null) {
            map = new HashMap<String, Integer>(engines.size());
            for (String engine : engines) {
                map.put(engine, partitionSize);
            }
        } else {
            map = enginePartitionSizeMap;
        }
        return map;
    }

    private Map<String, List> getPartitionMap(Map<String, List<SearchResultLink>> linksMap) {

        Map<String, Integer> partitionSizeMap = getPartitionSizeMap(linksMap.keySet());
        logger.debug("using partition sizes of {} ", partitionSizeMap);

        Map<String, List> linkPartitionedMap = new HashMap<String, List>();
        for (String engine : linksMap.keySet()) {
            linkPartitionedMap.put(engine, CombinerStrategyUtil.partition(linksMap.get(engine), getPartitionSize(partitionSizeMap, engine)));
        }
        return linkPartitionedMap;
    }

    private int getPartitionSize(Map<String, Integer> partitionSizeMap, String engine) {
        int size = partitionSize;
        if (partitionSizeMap.get(engine) != null) {
            size = partitionSizeMap.get(engine);
        } else {
            logger.warn("using an engine that isn't in the map");
        }
        return size;
    }
}
