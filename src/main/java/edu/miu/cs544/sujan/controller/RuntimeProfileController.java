package edu.miu.cs544.sujan.controller;

import edu.miu.cs544.sujan.JobBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * API to change spring boot profile on runtime
 * */
@RestController
public class RuntimeProfileController {
    @PutMapping("/restart/{env}")
    public void restart(@PathVariable String env) {
        JobBootApplication.restart(env);
    }
}
