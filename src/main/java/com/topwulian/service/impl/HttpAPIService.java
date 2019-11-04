package com.topwulian.service.impl;

import com.alibaba.fastjson.JSON;
import com.topwulian.dto.HttpResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.checkerframework.common.reflection.qual.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class HttpAPIService {
 
    @Autowired
    private CloseableHttpClient httpClient;
 
    @Autowired
    private RequestConfig config;

    private static Logger logger = LoggerFactory.getLogger(HttpAPIService.class);



    /**
     * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     * 
     * @param url
     * @return
     * @throws Exception
     */
    public String doGet(String url) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);
 
        // 装载配置信息
        httpGet.setConfig(config);
 
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);
 
        // 判断状态码是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            // 返回响应体的内容
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return null;
    }
 
    /**
     * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     * 
     * @param url
     * @return
     * @throws Exception
     */
    public String doGet(String url, Map<String, Object> map) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);
 
        if (map != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
 
        // 调用不带参数的get请求
        return this.doGet(uriBuilder.build().toString());
 
    }
 
    /**
     * 带参数的post请求
     * 
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url, Map<String, Object> map) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);
 
        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (map != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
 
            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }
 
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }
 
    /**
     * 不带参数post请求
     * 
     * @param url
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url) throws Exception {
        return this.doPost(url, null);
    }

    /**
     * post 请求
     * @param url
     * @param data
     * @return
     */
    public static String post(String url,String data){
        String result = "";
        try{
            DefaultHttpClient httpClient = new DefaultHttpClient();
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5 * 60 * 1000);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5 * 60 * 1000);
            //超时重发三次
            httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(3,true));
//			logger.info("request url:" + url);
            HttpPost method = new HttpPost(url);
//			logger.info("request data:" + data);
            StringEntity entity = new StringEntity(data,"UTF-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            method.setEntity(entity);
            try {
                HttpResponse response = httpClient.execute(method);
                int stateCode = response.getStatusLine().getStatusCode();
                if(stateCode != 200){
                    return result;
                }
                result = EntityUtils.toString(response.getEntity(),"UTF-8");
            } catch (ClientProtocolException e) {
                logger.error("request error", e);
            } catch (ParseException e) {
                logger.error("request error", e);
            } catch (IOException e) {
                logger.error("request error", e);
            }finally {
                if (method != null) {
                    method.releaseConnection();
                }
                if (httpClient != null) {
                    //httpClient.getConnectionManager().shutdown();
                    httpClient.close();
                }
            }
        }catch (Exception e){
            logger.error("request error",e);
        }
        return result;
    }


    /**get请求(用于key-value格式的参数,且带有header请求头)
     * @param url
     * @param headerKey        headers Key
     * @param headerValue    headers Value
     * @return
     */
    public static String doGet(String url,String headerKey,String headerValue) {
        Response response = null;
        String str = "";
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader(headerKey, headerValue)
                    .addHeader("User-Agent", "PostmanRuntime/7.15.0")
                    .addHeader("Accept", "*/*")
                    .addHeader("Cache-Control", "no-cache")
                    .addHeader("Postman-Token", "d3c133a9-a4b9-4414-8c4d-903ad7a3728d,e7fc1348-67d0-454b-9d36-a54b132e0652")
                    .addHeader("Host", "light.sciento.top:9810")
                    .addHeader("accept-encoding", "gzip, deflate")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("cache-control", "no-cache")
                    .build();

            response = client.newCall(request).execute();
            str = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }




    public static void main(String[] args) {

        /*String token = "eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOjExNDQwNjI1NDMwMTEyNTQyNzIsInN1YiI6ImFkbWluMjAxOTUyMTUxOTgiLCJleHAiOjE1NjE3NzM1NDAsImlhdCI6MTU2MTYwMDc0MH0.-i9MWIoRXWaMwt179ntaKKdO2gOnGDzXREaHx5Yh-vEI7OHhxHFf2K7JS4JItUyi0rMnhh-8BU9JOuc0nJgExQ";

        String GET_URL = "http://light.sciento.top:9810/area/user";

        try {
            String ss = HttpAPIService.doGet(GET_URL,"jwtauthorization",token);
            System.out.println(JSONObject.fromObject(ss).get("data").toString());
            JSONArray jsonArray = JSONArray.fromObject(JSONObject.fromObject(ss).get("data").toString());
            System.out.println(jsonArray.getJSONObject(0).getString("id"));

        } catch (Exception e) {
            e.printStackTrace();
        }*/


       /* String token2="eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOjExNDA5MjAyNjE2MDA4NzQ0OTYsInN1YiI6ImFkbWluMjAxOTUyMTUxOTgiLCJleHAiOjE1NjEwMjQzNjIsImlhdCI6MTU2MDg1MTU2Mn0._6qJV0G3ozYBBJS4-qLc8up9zysGj4kM7ViF4OmHAlb6m6EHpdMx6-H_-ffEdi5PgRG5qCugMn7nr_1b83lyvQ";
        String areaid = "987239679478972788";
        String GET_USER_AREA_URL="http://light.sciento.top:9810/device/collection/area?areaId="+areaid;
        String result= HttpAPIService.doGet(GET_USER_AREA_URL,"jwtauthorization" ,token2);
        System.out.println(result);
        JSONArray jsonArray = JSONArray.fromObject(JSONObject.fromObject(result).get("data").toString());
        for(int i=0;i<jsonArray.size();i++) {
            String date = jsonArray.getJSONObject(i).getString("lastestData").toString();//实际值
            String typename = jsonArray.getJSONObject(i).getString("typeName").toString();//类型名称
            String unit = jsonArray.getJSONObject(i).getString("unit").toString(); //单位
            String type = jsonArray.getJSONObject(i).getString("type").toString(); //type //sn
        }*/




    }


}