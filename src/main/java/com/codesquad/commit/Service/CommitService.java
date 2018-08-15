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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class CommitService {
    private final static Logger log = LoggerFactory.getLogger(CommitService.class);

    @Autowired
    private CommitRepository commitRepository;

    public Commit getCommit(String userId) throws IOException {
        Commit commit;
        if(!commitRepository.findById(userId).isPresent()){
            commit = new Commit(userId);
        } else {
            commit = commitRepository.findById(userId).get();
        }
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Seoul"));
        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();

        // TODO 이거 할 필요 없이 커밋 여부로 안되어 있으면 재확인??
        // 체크를 날짜로 보지 말고 여부로 볼까?
        if (commit.isFirst()) {
            // 한달 치를 만들어 준다...
            LocalDate beforelocalDate = localDate;
            for (int i = 10; i > 0; i--) {
                beforelocalDate = localDate.plusDays(-i);
                String beforeDate = beforelocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
                int beforeCount = Crawling.getCount(userId, beforeDate);
                log.debug("beforelocalDate : {}", beforelocalDate.toString());
                log.debug("beforeCount : {}", beforeCount);
                commit.update(beforeCount, beforeDate);
            }
            commit.changeFirstCommit();
        }

        // 처음인 경우 한달 치를 업데이트
//        if(commit.isChecked(localDate.toString())){
        if (commit.isTodayCommit(date)) {
            return commit;
        }
        // 크롤링 해서 데이터를 최신화
        int count = Crawling.getCount(userId, date);
        commit.update(count, date);
        // TODO 오늘 count 받은 걸로 업데이트 하고 그걸 가지고 커밋 여부와 전체 카운트에 대한 업데이트

        commitRepository.save(commit);
        return commit;
    }

    public void setAlarm(String userId, String time) {
        Commit commit = commitRepository.findById(userId).get();
        commit.setAlarmTime(time);
        commitRepository.save(commit);
    }
}
