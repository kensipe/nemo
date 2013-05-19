package com.cmentor.search.bing.service;

import com.cmentor.web.util.QueryParamUtil;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Makes a https request to Bing for a web search
 * <p/>
 * Bing requires a application ID as a user name and password in order to function
 * <p/>
 * given a query of "ken sipe" it will make a bing request of: https://api.datamarket.azure.com/Bing/SearchWeb/v1/Web?$format=JSON&Query=%27Ken+Sipe%27
 * minor issues in that URLEncoder in Java is built for application/x-www-form-urlencoded providing a slightly different output then preferred javascript encodeURIComponent
 *
 * @author kensipe
 */
@Component
public class BingInternalWebSearchService implements BingInternalSearchService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("bing.user")
    private String user;

    @Value("bing.password")
    private String password;

    @Value("bing.url")
    private String urlStr;

    private URL url;

    @PostConstruct
    public void init() {
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            // todo:  don't remember.. initialization failure
//            throw new
        }
    }

    /**
     * given a query string will make a web search request against bing and return a json string back
     *
     * @param query
     * @return
     */
    @Override
    public String search(String query) {

        String urlValue = buildUrl(query);
        DefaultHttpClient httpClient = getDefaultHttpClient();

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

    private DefaultHttpClient getDefaultHttpClient() {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getCredentialsProvider().setCredentials(
                new AuthScope(url.getHost(), url.getPort(), AuthScope.ANY_SCHEME),
                new UsernamePasswordCredentials(user, password));
        return httpClient;
    }

    private String buildUrl(String query) {
        logger.debug("http GET request to {} with user: {}", url, user);
        StringBuilder urlQuery = new StringBuilder(url.toString());
        urlQuery.append("?$format=JSON&")
                .append(QueryParamUtil.encodeParamValue("Query", bingifyQuery(query)));
        String urlValue = urlQuery.toString();
        logger.debug("query: {}", urlValue);
        return urlValue;
    }

    /**
     * silly bing requires query to be in quotes ''
     */
    private String bingifyQuery(String query) {
        return "'" + query + "'";
    }
}
