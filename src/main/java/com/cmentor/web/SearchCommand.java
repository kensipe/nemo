package com.cmentor.web;

import com.cmentor.search.SearchResultLink;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kensipe
 */
public class SearchCommand {

    private String q;
    private String strategy;

    private List<String> strategyList = new ArrayList<String>();
    private List<SearchResultLink> links = new ArrayList<SearchResultLink>();

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public List<String> getStrategyList() {
        return strategyList;
    }

    public void setStrategyList(List<String> strategyList) {
        this.strategyList = strategyList;
    }

    public List<SearchResultLink> getLinks() {
        return links;
    }

    public void setLinks(List<SearchResultLink> links) {
        this.links = links;
    }
}
