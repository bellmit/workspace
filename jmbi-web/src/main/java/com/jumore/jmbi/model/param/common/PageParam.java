package com.jumore.jmbi.model.param.common;

import com.jumore.jmbi.model.param.BaseParam;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 分页参数封装
 */
public class PageParam extends BaseParam {

    private static final long serialVersionUID = 4498995160256606324L;
    private static final int Twenty = 20;

    /**
     * 分页大小
     */
    private int limit = Twenty;

    /**
     * 索引位置，从0开始
     */
    private int offset = 0;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
