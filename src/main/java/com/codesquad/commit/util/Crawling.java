package com.codesquad.commit.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Crawling {
    private Document document;
    private final static Logger log = LoggerFactory.getLogger(Crawling.class);

    public static int getCount(String userId, String date) throws IOException {
        log.debug("userId : {} ", userId);

        Connection.Response response = Jsoup.connect("https://github.com/" + userId)
                .method(Connection.Method.GET)
                .execute();

        Document document = response.parse();
        Element element = document.select("rect[data-date=" + date + "]").first();
        String count = element.attr("data-count");
        return Integer.parseInt(count);
    }

    public void setElement(String userId) throws IOException {
        log.debug("userId : {} ", userId);

        Connection.Response response = Jsoup.connect("https://github.com/" + userId)
                .method(Connection.Method.GET)
                .execute();

        document = response.parse();
    }

    public int getInitCount(String date) throws IOException {
        Element element = document.select("rect[data-date=" + date + "]").first();
        String count = element.attr("data-count");
        return Integer.parseInt(count);
    }
}
