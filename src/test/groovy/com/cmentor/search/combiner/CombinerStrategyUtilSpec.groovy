package com.cmentor.search.combiner

import com.cmentor.search.SearchResultLink
import spock.lang.Specification
import spock.lang.Unroll

/**
 *
 * @author kensipe
 */
class CombinerStrategyUtilSpec extends Specification {

    def "null check lists on one is not null"() {

        expect:
        result == CombinerStrategyUtil.atLeastOneListIsNotNull(map)

        where:
        result | map
        true   | ["google": ["test", "test"]]
        true   | ["google": ["test", "test"], "bing": []]
        true   | ["google": [], "bing": []]
        true   | ["google": [], "bing": null]
        false  | ["google": null, "bing": null]
        false  | null
    }

    @Unroll
    def "null check lists on one is not empty"() {

        expect:
        result == CombinerStrategyUtil.atLeastOneListIsNotEmpty(map)

        where:
        result | map
        true   | ["google": ["test", "test"]]
        true   | ["google": ["test", "test"], "bing": []]
        false  | ["google": [], "bing": []]
        false  | ["google": [], "bing": null]
        false  | ["google": null, "bing": null]
        false  | null
    }

    def "largest list size"() {
        expect:
        size == CombinerStrategyUtil.largestListSize(map)

        where:
        size | map
        0    | [:]
        0    | ["google": []]
        0    | ["google": null]
        0    | ["google": [], "bing": []]
        1    | ["google": [""]]
        1    | ["google": [""], "bing": []]
        2    | ["google": [""], "bing": ["", ""]]
    }

    def "right sizing of lists"() {
        when:
        def resultList = CombinerStrategyUtil.combine(listsOfListArrray)

        then:
        resultList.size() == size

        where:
        listsOfListArrray                                               | size
        ["google": googleListOfLists()]                                 | 4
        ["google": googleListOfLists(), "google2": googleListOfLists()] | 8
        ["google": googleListOfLists(), "bing": bingListOfLists()]      | 6
    }

    def "right ordering of lists"() {
        when:
        def resultList = CombinerStrategyUtil.combine(listsOfListArrray)

        then:
        resultList.size() == size
        resultList.get(position).source == source

        where:
        listsOfListArrray                                          | size | position | source
        ["google": googleListOfLists(), "bing": bingListOfLists()] | 6    | 2        | "bing"
        ["bing": bingListOfLists(), "google": googleListOfLists()] | 6    | 1        | "google"
    }


    def googleListOfLists() {
        [[new SearchResultLink(title: "1", source: "google"), new SearchResultLink(title: "2", source: "google")], [new SearchResultLink(title: "3", source: "google"), new SearchResultLink(title: "4", source: "google")]]
    }

    def bingListOfLists() {
        [[new SearchResultLink(title: "b1", source: "bing")], [new SearchResultLink(title: "b2", source: "bing")]]
    }
}
