package com.codesquad.commit.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Commit {
    @Id
    @Column
    private String userId;
    @Column
    private int count;
    private String alarmTime;
    //    @Column
//    private String todayCommits;
    @Column
    private int todayCommit;
    @Column
    private boolean isFirst;
    @Column
    private String updateDate;

    public Commit(String userId) {
        this.userId = userId;
        this.count = 0;
        // 알림 시간은 어떻게 할까 다시 얘기 해보자
        this.todayCommit = 0;
        this.isFirst = true;
        this.updateDate = "0000-00-00";
    }

    public boolean isTodayCommit(String date) {
        if (!date.equals(updateDate)) {
            todayCommit = 0;
        }
        return todayCommit != 0;
    }

    public void update(int todayCommitCount, String date) {
        todayCommit = todayCommitCount;
        if (todayCommitCount != 0 && !date.equals(updateDate)) {
            this.count++;
            this.todayCommit = todayCommitCount;
            updateDate = date;
        }
        if (todayCommitCount == 0 && !date.equals(updateDate)) {
            this.count = 0;
            updateDate = date;
        }
        if (todayCommitCount != 0 && date.equals(updateDate)) {
            this.todayCommit = todayCommitCount;
            updateDate = date;
        }
    }

    public void changeFirstCommit() {
        isFirst = false;
    }
}
