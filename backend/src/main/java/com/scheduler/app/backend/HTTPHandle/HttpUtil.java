package com.scheduler.app.backend.HTTPHandle;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
// Use for single quest not mulithreading

public class HttpUtil {
    
    
    public String request(String ip){
        String responseBody="";
        long standard=500;
        try {
            responseBody=httpRequest(routeWithOutParam(ip),standard);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return responseBody;
    }

    public String requestRoute(String ip,String route,String param){
        String responseBody="";
        long standard=500;
        String routeSet=createRoute(ip,route,param);
        try {
            responseBody=httpRequest(routeSet,standard);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return responseBody;
    }
    
    public String requestDevice(String url,long timeout,String body){
        String result="";
        try {
            result= httpDeviceRequest(url,timeout,body);
        } catch (URISyntaxException e) {
           
            e.printStackTrace();
        } catch (IOException e) {
      
            e.printStackTrace();
        } catch (InterruptedException e) {
         
            e.printStackTrace();
        }
        return result;
    }
    public boolean requestRouteTest(String ip){
        boolean result=false;
        String route=routeWithOutParam(ip);
        try {
            result =testHttpDevice(route);
        } catch (URISyntaxException e) {
            
            e.printStackTrace();
            
        } catch (IOException e) {
            
            e.printStackTrace();
        } catch (InterruptedException e) {
           
            e.printStackTrace();
        }
        return result;
    }
    
   
    public String httpRequest(String url,long timeOut) throws URISyntaxException, IOException, InterruptedException{
        String responseBody="";
        System.out.println(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .uri(URI.create(url))
            .timeout(Duration.ofMillis(timeOut))
            .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        responseBody = response.body();
        return responseBody;
    }
    // send request device for action
    private String httpDeviceRequest(String url,long timeout,String body) throws URISyntaxException,IOException,InterruptedException{
        String responseBody="";
        System.out.println(url);
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request=null;
            if(body==""){
                request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .uri(URI.create(url))
                .timeout(Duration.ofMinutes(timeout))
                .build();
            }else
            {
                request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .timeout(Duration.ofMinutes(timeout))
                .build();

            }
            /* 
            request = HttpRequest.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .headers("Content-Type", "text/plain;charset=UTF-8")
            .uri(URI.create(url))
            .timeout(Duration.ofMinutes(timeout))
            .build();
            */
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            responseBody = response.body();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return responseBody;
    }
    private boolean testHttpDevice(String url) throws URISyntaxException,IOException,InterruptedException{
        boolean result=false;
        System.out.println(url);
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .uri(URI.create(url))
            .build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            int responseStatusCode = response.statusCode();
            if(responseStatusCode==200){
                result=true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return result;
    }
    // routes query
    public void getRoutes(String ip,String deviceName){
        deviceName=deviceName.toLowerCase();
        long standard=200;
        String route=createRoute(ip,"routes",deviceName);
        try {
            httpRequest(route,standard);
        } catch (URISyntaxException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
           
            e.printStackTrace();
        } catch (InterruptedException e) {
           
            e.printStackTrace();
        }
    }
    
    private String routeWithOutParam(String ip){
        if(ip=="") return "";
        String requestUrl="http://"+ip;
        return requestUrl;
    }
    
    public String createRoute(String ip,String route,String param){
        if(route==""||ip=="") return "";
        String requestUrl="http://"+ip+"/"+route;
        if(param!=""){
            requestUrl=requestUrl+"?params="+param;
        }
        return requestUrl;
    }
}
