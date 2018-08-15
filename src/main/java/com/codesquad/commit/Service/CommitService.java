package com.codesquad.commit.Service;

import com.codesquad.commit.domain.Commit;
import com.codesquad.commit.repository.CommitRepository;
import com.codesquad.commit.util.Crawling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class CommitService {
    private final static Logger log = LoggerFactory.getLogger(CommitService.class);

    @Autowired
    private CommitRepository commitRepository;

    public Commit getCommit(String userId) throws IOException {
        Commit commit;
        if (!commitRepository.findById(userId).isPresent()) {
            commit = new Commit(userId);
        } else {
            commit = commitRepository.findById(userId).get();
        }
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Seoul"));
        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (commit.isFirst()) {
            init(userId, commit, localDate);
        } else {
            int count = Crawling.getCount(userId, date);
            commit.update(count, date);
        }

        commitRepository.save(commit);
        return commit;
    }

    private void init(String userId, Commit commit, LocalDate localDate) throws IOException {
        // 한달 치를 만들어 준다...
        Crawling crawling = new Crawling();
        crawling.setElement(userId);
        LocalDate beforelocalDate = localDate;
        for (int i = 10; i >= 0; i--) {

            beforelocalDate = localDate.plusDays(-i);
            String beforeDate = beforelocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int beforeCount = crawling.getInitCount(beforeDate);

            log.debug("beforelocalDate : {}", beforelocalDate.toString());
            log.debug("beforeCount : {}", beforeCount);

            commit.update(beforeCount, beforeDate);
        }
        commit.changeFirstCommit();
    }

    public void setAlarm(String userId, String time) throws IOException {
        Commit commit;
        if (!commitRepository.findById(userId).isPresent()) {
            commit = getCommit(userId);
        } else {
            commit = commitRepository.findById(userId).get();
        }

        commit.setAlarmTime(time);
        commitRepository.save(commit);
    }
}
