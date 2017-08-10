/** 
 * Project Name:jmdata-biservice 
 * File Name:BaiduTabBean.java 
 * Package Name:org.jumao.bi.entites.baidu
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 1
 * @date 2017年7月19日 下午4:28:47
 */
package org.jumao.bi.entites.baidu;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Function: 百度指标表格展示
 * 
 * @author 1
 * @date 2017年7月19日 下午4:28:47
 * @version 
 * @see
 */
public class BaiduTabBean implements Serializable {
    
    private static final long serialVersionUID = 2374645991863479630L;
    private String name;
    private BigDecimal pv;
    private BigDecimal uv;
    private BigDecimal ip;
    private BigDecimal exitRate;
    
    public BaiduTabBean(String name, BigDecimal pv, BigDecimal uv, BigDecimal ip) {
        this.name = name;
        this.pv = pv;
        this.uv = uv;
        this.ip = ip;
    }
    
    public BaiduTabBean(String name, BigDecimal pv, BigDecimal uv, BigDecimal ip, BigDecimal exitRate) {
        this.name = name;
        this.pv = pv;
        this.uv = uv;
        this.ip = ip;
        this.exitRate = exitRate;    
    }
    
    
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getPv() {
        return pv;
    }
    public void setPv(BigDecimal pv) {
        this.pv = pv;
    }
    public BigDecimal getUv() {
        return uv;
    }
    public void setUv(BigDecimal uv) {
        this.uv = uv;
    }
    public BigDecimal getIp() {
        return ip;
    }
    public void setIp(BigDecimal ip) {
        this.ip = ip;
    }
    public BigDecimal getExitRate() {
        return exitRate;
    }
    public void setExitRate(BigDecimal exitRate) {
        this.exitRate = exitRate;
    }
    
        
    

}
