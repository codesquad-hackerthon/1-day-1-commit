package com.codesquad.commit;

import com.codesquad.commit.util.CrawlingTest;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalDateTest {
    private final static Logger log = LoggerFactory.getLogger(CrawlingTest.class);

    private String date;

    @Before
    public void setup() {
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Seoul"));
//        localDate.
        log.debug(localDate.getDayOfWeek().toString());
        date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Test
    public void 요일_확인(){

    }
}
