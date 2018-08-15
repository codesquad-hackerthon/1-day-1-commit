package com.codesquad.commit.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CommitTest {
    private String date;

    @Before
    public void setup() {
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Seoul"));
        date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
    }

    @Test
    public void 업데이트_값_0() {
        Commit commit = Commit.builder().todayCommit(0).count(0).updateDate("0000-00-00").build();
        Commit updatedCommit = Commit.builder().todayCommit(0).count(0).updateDate("0000-00-00").build();
        updatedCommit.update(0, date);
        assertThat(updatedCommit, is(commit));
    }

    @Test
    public void 업데이트_값_1() {
        Commit commit = Commit.builder().todayCommit(0).count(0).updateDate("0000-00-00").build();
        Commit updatedCommit = Commit.builder().todayCommit(0).count(0).updateDate(date).build();
        updatedCommit.update(1, date);
        assertNotEquals(updatedCommit, is(commit));
        assertThat(updatedCommit.getCount(), is(1));
    }

}