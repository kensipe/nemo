package com.cmentor.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author kensipe
 */
public class URLEncoderUtil {

    private static final Logger logger = LoggerFactory.getLogger(URLEncoderUtil.class);

    public static String encode(String value) {
        String encodedValue = "";
        try {
            encodedValue = URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("failure to encoder parm value", value, e);
        }
        return encodedValue;
    }
}
