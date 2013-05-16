package com.cmentor.search.bing

import spock.lang.Specification

/**
 *
 * @author kensipe
 */
class BingJsonResultParserSpec extends Specification {

    def "parse bing web search result set"() {

        given:
        def parser = new BingJsonResultParser()
        def json = conjureBingJson()

        when:
        def result = parser.parse(json)

        then:
        result.data.results.size() == 3
        with(result.data.results[0]) {
            title == "Ken Sipe | LinkedIn"
            description == "View Ken Sipe's professional profile on LinkedIn."
            url == "http://www.linkedin.com/in/kensipe"
        }
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
