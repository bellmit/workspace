package com.jumore.jmbi.common.util.export;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;

import com.alibaba.fastjson.JSONObject;
import com.jumore.dove.common.log.LogHelper;

public class HttpUtilForExport {

    public static RequestConfig    config     = RequestConfig.custom().setConnectionRequestTimeout(3000).setConnectTimeout(3000)
                                                      .setSocketTimeout(3000).build();
    public static final String     Accept     = "Accept";

    private static final LogHelper Log_Helper = LogHelper.getLogger(HttpUtilForExport.class);

    /**
     * main:main方法.
     * 
     * @author Administrator
     * @date 2017年6月2日 下午3:12:42
     * @param args
     */
    public static void main(String[] args) {

        HttpUtilForExport
                .doGetForExport("http://localhost:8080/biservice/v1/inout/getInOutCountryAmountExport/timeType/month/startDate/20160101/endDate/20170307/customsCode/all/inout/in");
    }

    /**
     * 该方法只为导出； 有数据则返回，没有数据
     * 
     * @param url
     * @return
     */
    public static JsonNode doGetForExport(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(url);
            request.setConfig(config);
            request.addHeader(Accept, "application/json;charset=UTF-8");
            request.addHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8" + ";charset=utf-8");
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            // 处理返回=================================
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, Consts.UTF_8);
            } else {
                return null;
            }

            EntityUtils.consume(entity);
            JsonNode jsonNode = JsonParams.formJsonNode(result);
            if (jsonNode.has("status")) {// 在导出的service中，如果发生错误返回的是带有该属性的ResultList<T>对象。正确饭的话则返回的是List<T>
                return null;
            }
            return jsonNode;

        } catch (ConnectTimeoutException e) {
            Log_Helper.getBuilder().error("", e);
        } catch (SocketTimeoutException e) {
            Log_Helper.getBuilder().error("", e);
        } catch (Exception e) {
            Log_Helper.getBuilder().error("", e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                Log_Helper.getBuilder().error("", e);
            }
        }

        return null; //
    }

    /**
     * doPOST:执行post方法.
     * 
     * @author Administrator
     * @date 2017年6月2日 下午3:25:09
     * @param url
     * @param sendData
     * @return
     */
    public static JSONObject doPOST(String url, JSONObject sendData) {
        JSONObject resJSON = new JSONObject();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            HttpPost request = new HttpPost(url);
            request.setConfig(config);
            request.setEntity(new StringEntity(sendData.toString(), Consts.UTF_8));
            request.addHeader(Accept, "application/json");
            request.setHeader(HTTP.CONTENT_TYPE, "text/json");
            HttpResponse response;
            response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

        } catch (ConnectTimeoutException e) {
            Log_Helper.getBuilder().error("", e);
        } catch (SocketTimeoutException e) {
            Log_Helper.getBuilder().error("", e);
        } catch (Exception e) {
            Log_Helper.getBuilder().error("", e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                Log_Helper.getBuilder().error("", e);
            }
        }
        return resJSON;
    }

    /**
     * 
     * doPut:执行put方法.
     * 
     * @author Administrator
     * @date 2017年6月2日 下午3:28:39
     * @param url
     * @param sendData
     * @return
     */
    public static JSONObject doPut(String url, JSONObject sendData) {
        JSONObject resJSON = new JSONObject();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            HttpPut request = new HttpPut(url);
            request.setConfig(config);
            request.setEntity(new StringEntity(sendData.toString(), Consts.UTF_8));
            request.addHeader(Accept, "application/json");
            request.setHeader(HTTP.CONTENT_TYPE, "text/json");
            HttpResponse response;
            response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
        } catch (ConnectTimeoutException e) {
            Log_Helper.getBuilder().error("", e);
        } catch (SocketTimeoutException e) {
            Log_Helper.getBuilder().error("", e);
        } catch (Exception e) {
            Log_Helper.getBuilder().error("", e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                Log_Helper.getBuilder().error("", e);
            }
        }
        return resJSON;
    }

}
