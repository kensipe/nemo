package com.cmentor.search.combiner

import com.cmentor.search.SearchResultLink
import spock.lang.Specification

/**
 * 
 * @author kensipe
 */
class EvenWeightedDoubleStrategySpec extends Specification {
    def strategy = new EvenWeightedDoubleStrategy()

    def "one to one interleave"() {
        given:
        def searchMap = [:]
        searchMap.google = [new SearchResultLink(title: "g1", source: "google"), new SearchResultLink(title: "g2", source: "google")]
        searchMap.bing = [new SearchResultLink(title: "b1", source: "bing"), new SearchResultLink(title: "b2", source: "bing")]

        when:
        def result = strategy.combine(searchMap)

        then:
        result.get(0).source == "google"
        result.get(1).source == "google"
        result.get(2).source == "bing"
        result.get(3).source == "bing"

        when:
        searchMap.google << new SearchResultLink(title: "g3", source: "google")
        searchMap.bing << new SearchResultLink(title: "b3", source: "bing")
        result = strategy.combine(searchMap)

        then:
        result.get(4).source == "google"
        result.get(5).source == "bing"
    }
}
