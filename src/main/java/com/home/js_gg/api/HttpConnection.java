package com.home.js_gg.api;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Component
public class HttpConnection {
    //TODO : 리턴값을 상태메세지를 포함하는 도메인으로 만들어 전달
    public String connectHttp(String _url, Map<String, Object> _params, String _method) throws IOException {
        String result = "";
        try {
            // POST params 입출력
            /*StringBuilder paramData = new StringBuilder();
            if(_params != null){
                for(Map.Entry<String, Object> param : _params.entrySet()){
                    if(paramData.length() != 0) paramData.append("\n");
                    paramData.append(param.getKey());
                    paramData.append(":");
                    paramData.append(param.getValue());
                }
            }
            byte[] paramDataBytes = paramData.toString().getBytes("UTF-8");*/

            URL url = new URL(_url);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.addRequestProperty("X-Riot-Token", _params.get("key").toString());
            // POST params 입출력
//            conn.setRequestProperty("Content-Length", String.valueOf(paramDataBytes.length));

            conn.setDoOutput(false);

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // POST params 입출력
//                conn.getOutputStream().write(paramDataBytes);
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line;
                while((line = br.readLine()) != null) {
                    result = line;
                }
                br.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
