package com.jumore.jmbi.common.util;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.jumore.dove.common.log.LogHelper;

/**
 * json数据转换
 * 
 * @author wing
 * 
 */
public class JsonUtil {

    private static final LogHelper Log_Helper = LogHelper.getLogger(JsonUtil.class);

    /**
     * 将对象转为json data
     * 
     * @param value
     * @param <E>
     * @return
     */
    public static <E> String offerJson(E value) {
        String text = null;
        try {
            text = JSON.toJSONString(value);
        } catch (Exception e) {
            Log_Helper.getBuilder().error("", e);
        }

        return text;
    }

    /**
     * 将json数据转为object 如果不能转换为指定类型则返回null.
     * 
     * @param clazz
     * @param <E>
     * @return
     */
    public static <E> E poll2Obj(String data, Class<E> clazz) {
        try {

            E result;
            if (!StringUtils.isEmpty(data)) {
                try {
                    result = JSON.parseObject(data, clazz);
                } catch (Exception e) {
                    Log_Helper.getBuilder().error("", e);
                    result = null;
                }
            } else {
                result = null;
            }
            return result;
        } catch (Exception e) {
            Log_Helper.getBuilder().error("", e);
        }
        return null;
    }

}
