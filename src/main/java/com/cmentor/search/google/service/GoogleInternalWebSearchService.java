package com.cmentor.search.google.service;

import com.cmentor.web.util.QueryParamUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Makes a https request to Google for a web search
 * <p/>
 * given a query of "ken sipe" it will make a google request of: https://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=Ken+Sipe
 *
 * @author kensipe
 */
@Component
public class GoogleInternalWebSearchService implements GoogleInternalSearchService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("google.url")
    private String url;

    /**
     * given a query string will make a web search request against google and return a json string back
     *
     * @param query
     * @return
     */
    @Override
    public String search(String query) {

        String urlValue = buildUrl(query);
        HttpClient httpClient = new DefaultHttpClient();

        HttpResponse response;
        String json;
        try {
            HttpGet httpGet = new HttpGet(urlValue);
            response = httpClient.execute(httpGet);
            json = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            logger.error("failure to GET http response", e);
            json = "";
        }

        return json;
    }

    private String buildUrl(String query) {
        logger.debug("http GET request to {} with user: {}", url);
        StringBuilder urlQuery = new StringBuilder(url);
        urlQuery.append("?v=1.0&")
                .append(QueryParamUtil.encodeParamValue("q", query));
        String urlValue = urlQuery.toString();
        logger.debug("query: {}", urlValue);
        return urlValue;
    }
}
