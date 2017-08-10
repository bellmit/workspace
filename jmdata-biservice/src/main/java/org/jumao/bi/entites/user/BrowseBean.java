package org.jumao.bi.entites.user;

import java.io.Serializable;

public class BrowseBean implements Serializable{
    

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -3803636728773445151L;

    private String browseType;
    private Integer nums;
    public String getBrowseType() {
        return browseType;
    }
    public void setBrowseType(String browseType) {
        this.browseType = browseType;
    }
    public Integer getNums() {
        return nums;
    }
    public void setNums(Integer nums) {
        this.nums = nums;
    }
    
    

}
