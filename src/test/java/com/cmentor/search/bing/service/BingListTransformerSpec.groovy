package com.cmentor.search.bing.service

import com.cmentor.search.bing.model.BingSearchResultLink
import spock.lang.Specification

/**
 *
 * @author kensipe
 */
class BingListTransformerSpec extends Specification {

    def "transform same number of links"() {

        when:
        def links = BingListTransformer.transform(bingLinks)

        then:
        links.size() == size

        where:
        bingLinks                                                | size
        []                                                       | 0
        [new BingSearchResultLink()]                             | 1
        [new BingSearchResultLink(), new BingSearchResultLink()] | 2
        null                                                     | 0
    }

    def "transform the right values"() {

        when:
        def links = BingListTransformer.transform([new BingSearchResultLink(title: "title", description: "desc", url: "url")])

        then:
        with(links[0]) {
            title == "title"
            description == "desc"
            url == "url"
            source == "bing"
        }
    }
}
