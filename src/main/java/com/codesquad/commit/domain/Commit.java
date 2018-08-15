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
    private int totalCount;
    private int alarmTime;
    //    @Column
//    private String todayCommits;
    @Column
    private boolean isTodayCommit;
    @Column
    private boolean isFirst;
    @Column
    private String updateDate;

    public Commit(String userId){
        this.userId = userId;
        this.totalCount = 0;
        // 알림 시간은 어떻게 할까 다시 얘기 해보자
        this.isTodayCommit = false;
        this.isFirst = true;
        this.updateDate = "0000-00-00";
    }

    public boolean isTodayCommit(String date) {
        if (!date.equals(updateDate)) {
            isTodayCommit = false;
        }
        return isTodayCommit;
    }

    public void update(int count, String date) {
        if (count != 0 && !date.equals(updateDate)) {
            totalCount++;
            isTodayCommit = true;
            updateDate = date;
        }
    }

    public void changeFirstCommit() {
        isFirst = false;
    }
}
