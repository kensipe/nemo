package com.cmentor.search.google

import spock.lang.Specification

/**
 *
 * @author kensipe
 */
class GoogleJsonResultParserSpec extends Specification {
    def "parse bing web search result set"() {

        given:
        def parser = new GoogleJsonResultParser()
        def json = conjureGoogleJson()

        when:
        def result = parser.parse(json)

        then:
        result.data.results.size() == 3
        with(result.data.results[0]) {
            title == "<b>Ken Sipe</b> (<b>kensipe</b>) on Twitter"
            description == "The latest from <b>Ken Sipe</b> (@<b>kensipe</b>). Technical Kung Fu Artist. St. Louis, MO."
            url == "https://twitter.com/kensipe"
        }
    }

    def conjureGoogleJson() {
        """{
	responseData: {
		results: [{
			GsearchResultClass: "GwebSearch",
			unescapedUrl: "https://twitter.com/kensipe",
			url: "https://twitter.com/kensipe",
			visibleUrl: "twitter.com",
			cacheUrl: "http://www.google.com/search?q=cache:tR4K7L8AIXwJ:twitter.com",
			title: "<b>Ken Sipe</b> (<b>kensipe</b>) on Twitter",
			titleNoFormatting: "Ken Sipe (kensipe) on Twitter",
			content: "The latest from <b>Ken Sipe</b> (@<b>kensipe</b>). Technical Kung Fu Artist. St. Louis, MO."
		}, {
			GsearchResultClass: "GwebSearch",
			unescapedUrl: "http://www.nofluffjuststuff.com/conference/speaker/ken_sipe",
			url: "http://www.nofluffjuststuff.com/conference/speaker/ken_sipe",
			visibleUrl: "www.nofluffjuststuff.com",
			cacheUrl: "http://www.google.com/search?q=cache:u4IlaMwtlmQJ:www.nofluffjuststuff.com",
			title: "<b>Ken Sipe</b> - Biography - No Fluff Just Stuff",
			titleNoFormatting: "Ken Sipe - Biography - No Fluff Just Stuff",
			content: "<b>Ken</b> has been a practitioner and instructor of RUP since the late 1990s, and an extreme programmer and coach since the middle 2000s. <b>Ken</b> has worked with <b>...</b>"
		}, {
			GsearchResultClass: "GwebSearch",
			unescapedUrl: "http://www.linkedin.com/in/kensipe",
			url: "http://www.linkedin.com/in/kensipe",
			visibleUrl: "www.linkedin.com",
			cacheUrl: "http://www.google.com/search?q=cache:BvP1llzjOQcJ:www.linkedin.com",
			title: "<b>Ken Sipe</b> | LinkedIn",
			titleNoFormatting: "Ken Sipe | LinkedIn",
			content: "View <b>Ken Sipe&#39;s</b> professional profile on LinkedIn. LinkedIn is the world&#39;s largest business network, helping professionals like <b>Ken Sipe</b> discover inside <b>...</b>"
		}],
		cursor: {
			resultCount: "37,100",
			pages: [{
				start: "0",
				label: 1
			}, {
				start: "4",
				label: 2
			}, {
				start: "8",
				label: 3
			}, {
				start: "12",
				label: 4
			}, {
				start: "16",
				label: 5
			}, {
				start: "20",
				label: 6
			}, {
				start: "24",
				label: 7
			}, {
				start: "28",
				label: 8
			}],
			estimatedResultCount: "37100",
			currentPageIndex: 0,
			moreResultsUrl: "http://www.google.com/search?oe=utf8&ie=utf8&source=uds&start=0&hl=en&q=Ken+Sipe",
			searchResultTime: "0.23"
		}
	},
	responseDetails: null,
	responseStatus: 200
}""" as String
    }
}
