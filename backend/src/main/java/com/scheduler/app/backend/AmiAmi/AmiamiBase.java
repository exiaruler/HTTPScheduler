package com.scheduler.app.backend.AmiAmi;

import java.io.IOException;
import java.net.URISyntaxException;

import com.scheduler.Base.Base;
import com.scheduler.Base.JsonObject.JsonObject;

public class AmiamiBase extends Base {
    private long page=1;
    private String urlSearch="https://api.amiami.com/api/v1.0/items?pagemax=50&pagecnt="+page+"&lang=eng&s_keywords=";
    private String urlConditon="&s_sortkey=preowned&s_st_condition_flg=1";

    private String combineUrl(String a,String b){
        return a+b;
    }
    public String createRequest(String search,long cnt){
        if(cnt>1){
            page=cnt;
        }
        String url=urlSearch+search;
        return combineUrl(url, urlConditon);
    }
    public JsonObject sendRequestObject(String url){
        JsonObject output=new JsonObject();
        try {
            String request=httpUtil.httpRequestExpress(url, 200);
            output=output.jsonToObject(request);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return output;
    }
    
    public long calculatePagesTotal(String total){
        long result=1;
        Integer convertTotal=Integer.valueOf(total);
        double divideTotal=convertTotal/50;
        result=Math.round(divideTotal);
        return result;
    }

}
