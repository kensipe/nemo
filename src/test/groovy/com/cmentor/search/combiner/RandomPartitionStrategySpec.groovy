package com.cmentor.search.combiner

import com.cmentor.search.SearchResultLink
import spock.lang.Specification

/**
 *
 * @author kensipe
 */
class RandomPartitionStrategySpec extends Specification {

    def "order check on random"() {

        def strategy = Spy(RandomPartitionStrategy.class)

        given:
        def searchMap = [:]
        searchMap.google = [new SearchResultLink(title: "g1", source: "google"), new SearchResultLink(title: "g2", source: "google"), new SearchResultLink(title: "g3", source: "google"), new SearchResultLink(title: "g4", source: "google")]
        searchMap.bing = [new SearchResultLink(title: "b1", source: "bing"), new SearchResultLink(title: "b2", source: "bing")]

        // this test doesn't test randomness... it makes sure that the random number is used properly
        strategy.getRandomNumber() >>> [1, 2]
        // todo:  test when a partition is bigger than the list

        when:
        def result = strategy.combine(searchMap)

        then:
        result.get(0).source == "google"
        result.get(1).source == "bing"
        result.get(2).source == "bing"
        result.get(3).source == "google"
    }
}
