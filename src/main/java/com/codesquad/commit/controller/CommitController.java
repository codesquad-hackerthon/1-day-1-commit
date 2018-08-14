package com.codesquad.commit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommitController {

    @GetMapping("/commit")
    public ResponseEntity getCount(String userId){

        return ResponseEntity.ok("");
    }
}
