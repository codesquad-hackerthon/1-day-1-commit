package com.codesquad.commit.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Commit {
    @Id
    @Column
    private String userId;
    @Column
    private int count;
    @Column
    private String alarmTime;
    @Column
    private boolean isCommit;
    @Column
    private String checkDate;
}
