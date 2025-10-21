package com.parcialT2.parcial;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

public class HttpConnectionExample {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String Url1 = "54.197.182.126:8081";
    private static final String Url2 = "54.174.7.101:8082";
    public int bandera = 1;
    
    @GetMapping("/collatz")
    public String robin (HttpServletRequest req)throws IOException{
        if (bandera == 1){
            String parametros = req.getQueryString();
            bandera = 0;
            return getMethod(Url1 + "?" + parametros);

        }else{
            String parametros = req.getQueryString();
            bandera = 1;
            return getMethod(Url2 + "?" + parametros);
        }
    }

    private String getMethod(String url) throws IOException{
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        } else {
            return "GET request not worked";
        }
    } 
}

