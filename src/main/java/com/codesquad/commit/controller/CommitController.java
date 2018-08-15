package com.codesquad.commit.controller;

import com.codesquad.commit.Service.CommitService;
import com.codesquad.commit.domain.Commit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CommitController {
    private final static Logger log = LoggerFactory.getLogger(CommitController.class);

    @Autowired
    private CommitService commitService;

    @GetMapping("/commit")
    public ResponseEntity getCount(String userId) throws IOException {
        log.debug("userId : {}", userId);

        Commit commit = commitService.getCommit(userId);
        return ResponseEntity.ok(commit);
    }
}
