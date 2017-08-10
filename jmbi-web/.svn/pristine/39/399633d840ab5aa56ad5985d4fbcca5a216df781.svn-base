package com.jumore.jmbi.common.util.export;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.jumore.dove.common.log.LogHelper;

/**
 * JSON工具类，采用.JsonBinder处理JSON
 * 
 * @author
 *
 */
public class JsonParams {
    private static final LogHelper Log_Helper  = LogHelper.getLogger(JsonParams.class);

    private static JsonBinder      binder      = JsonBinder.getNonNull();

    private static String          Err_Message = "JSON参数解析错误！";

    /**
     * formJson:json串转化为实体.
     * 
     * @author Administrator
     * @date 2017年6月2日 下午3:42:54
     * @param jsonString
     * @param clazz
     * @return
     */
    public static synchronized <T> T formJson(String jsonString, Class<T> clazz) {
        try {
            return binder.getMapper().readValue(formJsonNode(jsonString), clazz);
        } catch (Exception e) {
            Log_Helper.getBuilder().error(Err_Message, e);
            return null;
        }
    }

    /**
     * formJson:JsonNode转化为实体.
     * 
     * @author Administrator
     * @date 2017年6月2日 下午3:44:29
     * @param jsonRequest
     * @param clazz
     * @return
     */
    public static synchronized  <T> T formJson(JsonNode jsonRequest, Class<T> clazz) {
        try {
            return binder.getMapper().readValue(jsonRequest, clazz);
        } catch (Exception e) {
            Log_Helper.getBuilder().error(Err_Message, e);
            return null;
        }
    }

    /**
     * formJson:JsonNode转化为实体.
     * 
     * @author Administrator
     * @date 2017年6月2日 下午3:45:53
     * @param jsonNode
     * @param t
     * @return
     */
    public static <T> T formJson(JsonNode jsonNode, TypeReference<T> t) {
        return binder.fromJson(jsonNode, t);

    }

    /**
     * formJsonNode:json串转化为JsonNode.
     * 
     * @author Administrator
     * @date 2017年6月2日 下午3:45:00
     * @param jsonString
     * @return
     */
    public static synchronized  JsonNode formJsonNode(String jsonString) {
        try {
            return binder.getMapper().readTree(jsonString);
        } catch (Exception e) {
            Log_Helper.getBuilder().error(Err_Message, e);
            return null;
        }
    }

    public static String toJson(Object object) {
        return binder.toJson(object);
    }

    public static ObjectMapper getMapper() {
        return binder.getMapper();
    }

}
