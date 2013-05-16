package com.cmentor.search.bing

import com.cmentor.search.bing.service.BingInternalSearchService
import spock.lang.Specification

/**
 * 
 * @author kensipe
 */
class BingSearchServiceSpec extends Specification {

    BingSearchService searchService = new BingSearchService();

    def "no failure on bad json"() {

        when:
        def links = searchService.parseJson(json)

        then:
        links.size() == 0

        where:
        json << ["", "bad json", null]
    }

    def "json to link results"() {
        given:
        def internalSearch = Mock(BingInternalSearchService)
        searchService.searchService = internalSearch
        internalSearch.search("") >> conjureBingJson()

        when:
        def links = searchService.search("")

        then:
        links.size() == 3
        links[0].source == "bing"

    }

    def conjureBingJson() {
        // sample from bing search with some mods
        // modified because the $ in &$skip=0&$top=1", cause groovy issues... not need for this test
        """ {
            	d: {
            		results: [{
            			__metadata: {
            				uri: "https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/Web?Query='Ken Sipe'",
            				type: "WebResult"
            			},
            			ID: "5d9dc44e-ee72-4395-93ba-f3aa5569a0fe",
            			Title: "Ken Sipe | LinkedIn",
            			Description: "View Ken Sipe's professional profile on LinkedIn.",
            			DisplayUrl: "www.linkedin.com/in/kensipe",
            			Url: "http://www.linkedin.com/in/kensipe"
            		}, {
            			__metadata: {
            				uri: "https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/Web?Query='Ken Sipe'",
            				type: "WebResult"
            			},
            			ID: "b091f972-1300-444a-8f70-e32b75f2cac0",
            			Title: "Ken's Technical Thoughts",
            			Description: "ever seen a C: on a unix... good times!, apparently the tests build this out. Let's hope the day gets better than this :)",
            			DisplayUrl: "kensipe.blogspot.com",
            			Url: "http://kensipe.blogspot.com/"
            		}, {
            			__metadata: {
            				uri: "https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/Web?Query='Ken Sipe'",
            				type: "WebResult"
            			},
            			ID: "45db852a-1257-4622-9d5f-af396f36efc6",
            			Title: "Ken Sipe - Executive Profile",
            			Description: "and is located in Harrisonburg, VA. ... Updated 3/12/2013 - This profile of Ken Sipe was created using data from Dun & Bradstreet",
            			DisplayUrl: "www.corporationwiki.com/Virginia/Harrisonburg/ken-sipe/47907864.aspx",
            			Url: "http://www.corporationwiki.com/Virginia/Harrisonburg/ken-sipe/47907864.aspx"
            		}],
            		__next: "https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/Web?Query='Ken%20Sipe'"
            	}
            }""" as String
    }


}
