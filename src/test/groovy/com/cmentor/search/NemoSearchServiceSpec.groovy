package com.cmentor.search

import com.cmentor.search.combiner.CombinerStrategy
import spock.lang.Specification

/**
 *
 * @author kensipe
 */
class NemoSearchServiceSpec extends Specification {

    def service = new NemoSearchService()

    def setup() {
        // this will be handled by spring injection under normal execution
        service.searchServices = []
        service.strategies = []
    }

    def "search names"() {
        def google = Mock(SearchService)
        def bing = Mock(SearchService)

        service.searchServices << google
        service.searchServices << bing

        when:
        service.init()

        then:
        1 * google.serviceName() >> "google"
        1 * bing.serviceName() >> "bing"
        service.getServiceNames().size() == 2
    }


    def "strategy names"() {
        def singleStrat = Mock(CombinerStrategy)
        def random = Mock(CombinerStrategy)

        service.strategies << singleStrat
        service.strategies << random

        when:
        service.init()

        then:
        1 * singleStrat.strategyName() >> "single"
        1 * random.strategyName() >> "random"
        service.getStrategyNames().size() == 2
    }
}
