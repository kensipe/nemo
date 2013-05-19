package com.cmentor.web;

import com.cmentor.search.NemoSearchService;
import com.cmentor.search.SearchResultLink;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kensipe
 */

@Controller
@RequestMapping("search")
public class SearchController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private NemoSearchService searchService;

    @RequestMapping("index")
    public void index(Model model,
                      SearchCommand command,
                      @RequestParam(required = false) String q,
                      @RequestParam(required = false) String strategy) {

        command.setQ(q);
        command.setStrategy(strategy);
        command.getStrategyList().addAll(searchService.getStrategyNames());

//        if (StringUtils.isNotBlank(q)) {
            List<SearchResultLink> links = new ArrayList<SearchResultLink>(4);
            links.add(makeLink("title1", "http://google.com", "google"));
            links.add(makeLink("title2", "http://google.com", "google"));
            links.add(makeLink("title3", "http://google.com", "bing"));
            links.add(makeLink("title4", "http://google.com", "bing"));
            links.add(makeLink("title5", "http://google.com", "google"));
            command.setLinks(links);
//        }

        model.addAttribute("searchForm", command);
        // todo:  some time there will be a model here
    }

    private SearchResultLink makeLink(String title, String url, String service) {
        SearchResultLink link = new SearchResultLink();
        link.setTitle(title);
        link.setUrl(url);
        link.setSource(service);
        return link;
    }
}
