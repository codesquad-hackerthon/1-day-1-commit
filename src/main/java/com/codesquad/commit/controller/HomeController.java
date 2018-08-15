package com.codesquad.commit.controller;

import com.codesquad.commit.Service.CommitService;
import com.codesquad.commit.domain.Commit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class HomeController {
    private final static Logger log = LoggerFactory.getLogger(CommitController.class);

    @Autowired
    private CommitService commitService;

    @GetMapping("/")
    public String home(String userId, Model model) {
        log.debug("userId : {}", userId);

        Commit commit = null;
        try {
            commit = commitService.getCommit(userId);
            model.addAttribute("commit", commit);
            return "index";
        } catch (IOException e) {
        }
        return "index";
    }
}
