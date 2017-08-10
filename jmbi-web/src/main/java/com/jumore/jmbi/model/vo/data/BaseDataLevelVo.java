package com.jumore.jmbi.model.vo.data;

import java.io.Serializable;

/** 
* 分级数据字典表
* @author: panweiqiang
* @since: 2016年7月15日  上午9:29:53
* @history:
*/
public class BaseDataLevelVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String            optionCode;

    private String            optionName;

    private Integer           sort;

    private Integer           isDefault;

    private String            parentCode;

    private String            classifyCode;

    private String            deleteFlag;

    private Integer           level;

    public String getOptionCode() {
        return optionCode;
    }

    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
