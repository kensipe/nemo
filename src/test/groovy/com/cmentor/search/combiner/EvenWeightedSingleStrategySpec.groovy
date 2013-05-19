package com.cmentor.search.combiner

import com.cmentor.search.SearchResultLink
import spock.lang.Specification

/**
 *
 * @author kensipe
 */
class EvenWeightedSingleStrategySpec extends Specification {

    def strategy = new EvenWeightedSingleStrategy()

    def "one to one interleave"() {
        given:
        def searchMap = [:]
        searchMap.google = [new SearchResultLink(title: "g1", source: "google"), new SearchResultLink(title: "g2", source: "google")]
        searchMap.bing = [new SearchResultLink(title: "b1", source: "bing"), new SearchResultLink(title: "b2", source: "bing")]

        when:
        def result = strategy.combine(searchMap)

        then:
        result.get(0).source == "google"
        result.get(1).source == "bing"
        result.get(2).source == "google"
        result.get(3).source == "bing"
    }
}
