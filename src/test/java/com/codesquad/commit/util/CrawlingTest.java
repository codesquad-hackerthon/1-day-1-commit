package com.codesquad.commit.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CrawlingTest {
    private final static Logger log = LoggerFactory.getLogger(CrawlingTest.class);

    @Test
    public void 크롤링_테스트() throws IOException {
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Seoul"));
        log.debug("count : {}" ,Crawling.getCount("Hue9010", "2018-05-07"));
        assertThat(Crawling.getCount("Hue9010", "2018-05-07"), is(1));
    }

    @Test
    public void 전체_크롤링_개선() throws IOException {
        Crawling crawling = new Crawling();
        crawling.setElement("hue9010");
    }

}