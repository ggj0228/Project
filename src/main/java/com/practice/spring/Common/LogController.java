package com.practice.spring.Common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogController {

    @GetMapping("/log/test")
    public String logTest () {
        try{
            log.info("error test start");
            throw new RuntimeException("error test");
        } catch (RuntimeException e) {
            log.error("error: ", e.getMessage());
        }
        return "finish";
    }
}
