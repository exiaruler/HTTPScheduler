package com.scheduler.app.backend.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ApiController  {
    @GetMapping(value = "/")
    public String home(){
        return "Scheduler System";
    }
    @GetMapping(value = "/test")
    public String test(){
        return "test";
    }
    @GetMapping(value = "/test/test2")
    public String test2(String a){
        String test="hello";
        String b="p";
        return test+" "+b;
    }

}
