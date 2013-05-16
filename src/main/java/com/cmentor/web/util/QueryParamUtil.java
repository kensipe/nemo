package com.cmentor.web.util;

/**
 * @author kensipe
 */
public class QueryParamUtil {

    public static String encodeParamValue(String name, String value) {
        StringBuilder builder = new StringBuilder(name);
        builder.append("=").append(URLEncoderUtil.encode(value));
        return builder.toString();
    }
}
