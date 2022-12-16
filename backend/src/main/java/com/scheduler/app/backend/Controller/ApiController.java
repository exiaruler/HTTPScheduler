package com.scheduler.app.backend.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ApiController {
    @GetMapping(value = "/")
    public String test(){
        return "test";
    }
}
