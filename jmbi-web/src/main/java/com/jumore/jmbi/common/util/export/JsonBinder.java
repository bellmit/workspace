package com.jumore.jmbi.common.util.export;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON工具类，采用org.codehaus.jackson处理JSON
 * @author 
 *
 */
public class JsonBinder {

  private static Logger logger = LoggerFactory.getLogger(JsonBinder.class);

  private ObjectMapper mapper;
  
  private static JsonBinder jsonBinder1;
  
  private static JsonBinder jsonBinder2;
  
  private static JsonBinder jsonBinder3;

  private JsonBinder(Inclusion inclusion) {
    mapper = new ObjectMapper();
    //设置输出包含的属性
    mapper.getSerializationConfig().setSerializationInclusion(inclusion);
    //设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
    mapper.getDeserializationConfig().set(
        org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
  }

  /**
   * 创建输出全部属性到Json字符串的Binder.
   */
  public static synchronized JsonBinder getAlways(){
    if(jsonBinder1 == null){
      jsonBinder1 = new JsonBinder(Inclusion.ALWAYS); 
    }
    return jsonBinder1;
  }
  
  /**
   * 创建只输出非空属性到Json字符串的Binder.
   */
  public static synchronized JsonBinder getNonNull(){
    if(jsonBinder2 == null){
      jsonBinder2 = new JsonBinder(Inclusion.NON_NULL); 
    }
    return jsonBinder2;
  }

  /**
   * 创建只输出初始值被改变的属性到Json字符串的Binder.
   */
  public static synchronized JsonBinder getNonDefault(){
    if(jsonBinder3 == null){
      jsonBinder3 = new JsonBinder(Inclusion.NON_DEFAULT); 
    }
    return jsonBinder3;
  }

  /**
   * 如果JSON字符串为Null或"null"字符串,返回Null.
   * 如果JSON字符串为"[]",返回空集合.
   * 
   * 如需读取集合如List/Map,且不是List<String>这种简单类型时使用如下语句:
   */
  public <T> T fromJson(String jsonString, Class<T> clazz) {
    if (StringUtil.isEmpty(jsonString)) {
      return null;
    }

    try {
      return mapper.readValue(jsonString, clazz);
    } catch (IOException e) {
      logger.warn("parse json string error:" + jsonString, e);
      return null;
    }
  }
  
  /**
   * 如果JSON字符串为Null或"null"字符串,返回Null.
   * 如果JSON字符串为"[]",返回空集合.
   * 
   * 如需读取集合如List/Map,且不是List<String>这种简单类型时使用如下语句:
   */
  public <T> T fromJson(JsonNode jsonNode, TypeReference<T> t) {
      if (jsonNode == null) {
        return null;
      }

      try {
        return mapper.readValue(jsonNode, t);
      } catch (IOException e) {
        logger.warn("parse json string error:" + jsonNode.toString(), e);
        return null;
      }
    }

  /**
   * 如果对象为Null,返回"null".
   * 如果集合为空集合,返回"[]".
   */
  public String toJson(Object object) {

    try {
      return mapper.writeValueAsString(object);
    } catch (IOException e) {
      logger.warn("write to json string error:" + object, e);
      return null;
    }
  }

  

  /**
   * 设置转换日期类型的format pattern,如果不设置默认打印Timestamp毫秒数.
   */
  public void setDateFormat(String pattern) {
    if (StringUtil.isNotEmpty(pattern)) {
      DateFormat df = new SimpleDateFormat(pattern);
      mapper.getSerializationConfig().setDateFormat(df);
      mapper.getDeserializationConfig().setDateFormat(df);
    }
  }

  /**
   * 取出Mapper做进一步的设置或使用其他序列化API.
   */
  public ObjectMapper getMapper() {
    return mapper;
  }

}
