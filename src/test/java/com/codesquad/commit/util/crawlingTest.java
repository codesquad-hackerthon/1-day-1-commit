package com.codesquad.commit.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class crawlingTest {
    private final static Logger log = LoggerFactory.getLogger(crawlingTest.class);

    @Test
    public void 크롤링_테스트() throws IOException {

        Connection.Response response = Jsoup.connect("https://github.com/Hue9010")
                .method(Connection.Method.GET)
                .execute();

        Document document = response.parse();
        Element element = document.select("rect[data-date=2018-04-18]").first();
        String count = element.attr("data-count");

        log.debug("element : {}", element.toString());
        log.debug("data-count : {}", count);

        assertThat(count, is("1"));
    }

}