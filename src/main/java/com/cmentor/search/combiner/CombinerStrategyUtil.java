package com.cmentor.search.combiner;

import com.cmentor.search.SearchResultLink;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kensipe
 */
public class CombinerStrategyUtil {

    public static boolean atLeastOneListIsNotNull(Map<String, List<SearchResultLink>> resultsMap) {
        boolean notNull = false;
        if (resultsMap != null) {
            for (List<SearchResultLink> list : resultsMap.values()) {
                notNull = notNull || list != null;
            }
        }
        return notNull;
    }

    public static List<List<SearchResultLink>> partition(List<SearchResultLink> list, int size) {
        return Lists.partition(list, size);
    }

    /**
     * java generics making things ugly again
     * List<SearchResultLink>> and List<List<SearchResultLink>> are both types of list we want this op on
     * however they both have the same erasure, preventing us from providing an explicit static type
     */
    public static int largestListSize(Map<String, List> resultsMap) {
        int size = 0;
        for (List<SearchResultLink> list : resultsMap.values()) {
            if (list != null) {
                size = Math.max(size, list.size());
            }
        }
        return size;
    }

    /**
     * it is expected that each listsOfLists is already weighted in that the size of the inner list is the right size for combining
     * Map<String, List<List<SearchResultLink>>> is the expected type of map
     */
    public static List<SearchResultLink> combine(Map<String, List> mapOfListsOfLists) {
        List<SearchResultLink> result = new ArrayList<SearchResultLink>();

        int partitionListMaxIndex = largestListSize(mapOfListsOfLists);

        Map<Integer, String> engineMap = getEngineIndexMap(mapOfListsOfLists);
        int searchEngineMaxIndex = engineMap.keySet().size();

        for (int partitionListIndex = 0; partitionListIndex < partitionListMaxIndex; partitionListIndex++) {
            for (int searchEngineIndex = 0; searchEngineIndex < searchEngineMaxIndex; searchEngineIndex++) {
                List currentPartition = (List) mapOfListsOfLists.get(engineMap.get(searchEngineIndex)).get(partitionListIndex);
                if (currentPartition != null) {
                    result.addAll(currentPartition);
                }
            }
        }

        return result;
    }

    private static Map<Integer, String> getEngineIndexMap(Map<String, List> mapOfListsOfLists) {
        Map<Integer, String> engineMap = new HashMap<Integer, String>(mapOfListsOfLists.keySet().size());
        int engineIndex = 0;
        for (String engine : mapOfListsOfLists.keySet()) {
            engineMap.put(engineIndex++, engine);
        }
        return engineMap;
    }
}
