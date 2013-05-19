package com.cmentor.web

import com.cmentor.search.NemoSearchService
import com.cmentor.search.combiner.CombinerStrategy
import spock.lang.Specification

/**
 *
 * @author kensipe
 */
class SearchControllerSpec extends Specification {

    def controller = new SearchController()
    def service = new NemoSearchService()

    def "is Strategy in List"() {
        controller.searchService = service

        def combinerList = []
        strategyList.each { strategyName ->
            def strategy = Mock(CombinerStrategy)
            strategy.strategyName() >> strategyName
            combinerList << strategy
        }
        service.strategies = combinerList
        service.init()

        expect:
        found == controller.isInStrategyList(strategy)

        where:
        strategyList                | strategy | found
        ["amanda", "amelia", "jon"] | "jon"    | true
        ["amanda", "amelia", "jon"] | "Jon"    | true
        ["amanda", "amelia", "jon"] | "kenny"  | false
        ["amanda", "amelia", "jon"] | null     | false

    }
}
