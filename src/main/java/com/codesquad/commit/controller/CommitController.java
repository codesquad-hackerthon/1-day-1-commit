package com.codesquad.commit.controller;

import com.codesquad.commit.Service.CommitService;
import com.codesquad.commit.domain.Commit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin
public class CommitController {
    private final static Logger log = LoggerFactory.getLogger(CommitController.class);

    @Autowired
    private CommitService commitService;

    @GetMapping("/commit")
    public ResponseEntity getCount(String userId) {
        log.debug("userId : {}", userId);

        Commit commit = null;
        try {
            commit = commitService.getCommit(userId);
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(commit);
    }

    @PostMapping("/alarm")
    public void setAlarm(@RequestBody Map<String, String> param) throws IOException {
        log.debug("param : {}", param.toString());
        String userId = param.get("userId");
        String time = param.get("time");
        log.debug("userId : {} , time : {}", userId, time);
        commitService.setAlarm(userId, time);
    }


}
