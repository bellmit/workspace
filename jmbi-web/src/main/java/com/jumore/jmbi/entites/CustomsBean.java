package com.jumore.jmbi.entites;

import java.io.Serializable;

/**
 * 进出口分类统计：进出口贸易国别top
 * 
 * @author liquan
 *
 */
public class CustomsBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String            customsCode;           // 关区/海关：编码
    private String            customsName;           // 关区/海关：名称

    // private int index;// top序号：pc端用到

    public CustomsBean() {
        super();
    }

    /**
     * Creates a new instance of CustomsBean
     * 
     * @param customsCode
     * @param customsName
     */
    public CustomsBean(String customsCode, String customsName) {
        super();
        this.customsCode = customsCode;
        this.customsName = customsName;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

    public String getCustomsName() {
        return customsName;
    }

    public void setCustomsName(String customsName) {
        this.customsName = customsName;
    }

    // public int getIndex() {
    // return index;
    // }
    //
    // public void setIndex(int index) {
    // this.index = index;
    // }

}
