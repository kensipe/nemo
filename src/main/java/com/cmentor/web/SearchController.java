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

        q = StringUtils.trimToEmpty(q);
        command.setQ(q);

        command.getStrategyList().addAll(searchService.getStrategyNames());
        command.setStrategy(verifyStrategy(StringUtils.trimToEmpty(strategy).toLowerCase()));

        if (StringUtils.isNotBlank(q)) {
            if (StringUtils.isBlank(command.getStrategy())) {
                command.setLinks(searchService.search(q));
            } else {
                command.setLinks(searchService.search(q, strategy));
            }
        } else {
            logger.debug("search q was blank");
        }

        model.addAttribute("searchForm", command);
    }

    private String verifyStrategy(String strategy) {
        String validStrategy = strategy;
        if (StringUtils.isBlank(strategy) || !isInStrategyList(strategy)) {
            logger.debug("missing strategy");
            validStrategy = "";
        }
        return validStrategy;
    }

    private boolean isInStrategyList(String strategy) {
        boolean isInList = false;
        strategy = StringUtils.trimToEmpty(strategy).toLowerCase();

        for (String knownStrategies : searchService.getStrategyNames()) {
            if (strategy.equals(knownStrategies)) {
                isInList = true;
                break;
            }
        }
        return isInList;
    }

    private SearchResultLink makeLink(String title, String url, String service) {
        SearchResultLink link = new SearchResultLink();
        link.setTitle(title);
        link.setUrl(url);
        link.setSource(service);
        return link;
    }
}
