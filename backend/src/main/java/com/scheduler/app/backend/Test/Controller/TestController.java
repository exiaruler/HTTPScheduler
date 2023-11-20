package com.scheduler.app.backend.Test.Controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.app.backend.aREST.Models.Task;
import com.scheduler.app.backend.AmiAmi.Service.Amiami;
import com.scheduler.app.backend.Test.Service.TestService;

@RestController
public class TestController {
    TestService test=new TestService();
    Amiami amiami=new Amiami();
    @GetMapping(value="/test/testapi")
    public void testapi(){
        amiami.testRequest("http://localhost:8000/get-amiami");
    }
}
