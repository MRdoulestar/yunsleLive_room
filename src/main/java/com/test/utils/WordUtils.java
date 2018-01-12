package com.test.utils;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * Created by Doublestar on 2017/12/27 22:05).
 */
@Component("wordUtils")
public class WordUtils {
    //日志记录
    private static Logger logger = Logger.getLogger(WordUtils.class);

    public JSONObject filterWords(String str) {
        try {
            //访问准备
            URL url = new URL("http://www.hoapi.com/index.php/Home/Api/check");
            //post参数
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("str", str);
            params.put("token", "0f4cea10a79680c224ff76f6711c3d7c");

            //开始访问
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0; )
                sb.append((char) c);
            String response = sb.toString();
            //解析成JSON返回
            JSONObject data = JSONObject.fromObject(response);
            return data;
        } catch (Exception e) {
            logger.error("过滤敏感词WordUtils中出现错误");
            return JSONObject.fromObject("{'status':'true'}");
        }
    }
}
