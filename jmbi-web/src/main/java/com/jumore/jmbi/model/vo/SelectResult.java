package com.jumore.jmbi.model.vo;

import java.util.HashMap;
import java.util.Map;

/** 
* 代表一个下拉选项
* @author: panweiqiang
* @since: 2016年7月22日  上午10:38:36
* @history:
*/
public class SelectResult {
    private String              id;

    private String              text;

    private Map<String, Object> extMap = new HashMap<String, Object>();

    public SelectResult(String id, String text) {
        this.id = id;
        this.text = text;
    }
    
    public SelectResult() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, Object> getExtMap() {
        return extMap;
    }

    public void setExtMap(Map<String, Object> extMap) {
        this.extMap = extMap;
    }

    public void addExtInfo(String key, Object value) {
        extMap.put(key, value);
    }
}
