package com.cmentor.search.google.service

import com.cmentor.search.google.model.GoogleSearchResultLink
import spock.lang.Specification

/**
 *
 * @author kensipe
 */
class GoogleListTransformerSpec extends Specification {
    def "transform same number of links"() {

        when:
        def links = GoogleListTransformer.transform(bingLinks)

        then:
        links.size() == size

        where:
        bingLinks                                                    | size
        []                                                           | 0
        [new GoogleSearchResultLink()]                               | 1
        [new GoogleSearchResultLink(), new GoogleSearchResultLink()] | 2
        null                                                         | 0
    }

    def "transform the right values"() {

        when:
        def links = GoogleListTransformer.transform([new GoogleSearchResultLink(title: "title", description: "desc", url: "url")])

        then:
        with(links[0]) {
            title == "title"
            description == "desc"
            url == "url"
            source == "google"
        }
    }
}
