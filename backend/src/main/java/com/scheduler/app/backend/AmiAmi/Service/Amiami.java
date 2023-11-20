package com.scheduler.app.backend.AmiAmi.Service;
import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

import com.scheduler.Base.Base;
@Service
public class Amiami extends Base {
    
    public void testRequest(String url){
        String output="";
        try {
            output=httpUtil.httpRequestAmiAmi(url, 200);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(output);
    }
}
