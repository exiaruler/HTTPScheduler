package com.scheduler.app.HTTPHandle;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Paths;
import java.time.Duration;
// Use for single quest not mulithreading

public class HttpUtil {
    
    
    public String request(String ip){
        String responseBody="";
        try {
            responseBody=httpRequest(routeWithOutParam(ip));
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
        String routeSet=createRoute(ip,route,param);
        try {
            responseBody=httpRequest(routeSet);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return responseBody;
    }
    
   
    public String httpRequest(String url) throws URISyntaxException, IOException, InterruptedException{
        String responseBody="";
        System.out.println(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .uri(URI.create(url))
            .timeout(Duration.ofMillis(200))
            .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        responseBody = response.body();
        //int responseStatusCode = response.statusCode();
        return responseBody;
    }
    // routes query
    public void getRoutes(String ip,String deviceName){
        deviceName=deviceName.toLowerCase();
        String route=createRoute(ip,"routes",deviceName);
        try {
            httpRequest(route);
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
    }
    
    private String routeWithOutParam(String ip){
        if(ip=="") return "";
        String requestUrl="http://"+ip;
        return requestUrl;
    }
    
    private String createRoute(String ip,String route,String param){
        if(route==""||ip=="") return "";
        String requestUrl="http://"+ip+"/"+route;
        if(param!=""){
            requestUrl=requestUrl+"?params="+param;
        }
        return requestUrl;
    }

    public void test(){
        
        WebClient client = WebClient.create();

        WebClient.ResponseSpec responseSpec = client.get()
        .uri("http://192.168.1.194/saberPush?params=1")
        .retrieve();
        String responseBody = responseSpec.bodyToMono(String.class).block();
        System.out.println(responseBody);
        client = WebClient.create();
        WebClient.ResponseSpec responseSpec2 = client.get()
        .uri("http://192.168.1.194")
        .retrieve();
        String responseBody2 = responseSpec2.bodyToMono(String.class).block();
        String [] test=responseBody2.split("},");
        System.out.println(responseBody2);
    }
}
