package com.jumore.jmbi.model.param;

import java.io.Serializable;

/**
 * 分页查询参数：：按照bootstrap table的{起始行数、每页数量}进行分页
 * 
 * @author: panweiqiang
 * @since: 2016年7月14日 下午1:27:11
 * @history:
 */
public class PageQueryParam extends BaseParam implements Serializable {

    private static final long   serialVersionUID = 1L;

    // ++++++++++++分页查询参数：：按照bootstrap table的{起始行数、每页数量}进行分页+++++++++++++++
    /**
     * 默认起始行数
     */
    public static final Integer Default_Start    = 1;

    /**
     * 默认每页数量
     */
    public static final Integer Default_Limit    = 10;

    /**
     * 起始行数
     */
    private Integer             start;

    /**
     * 每页数量
     */
    private Integer             limit;

    /**
     * 功能说明：获取起始行数 为了与框架对应，这里的起始行数是从1开始的，实际查询时需要减1
     * 
     * @return Integer
     */
    public Integer getStart() {
        if (start == null) {
            start = Default_Start;
        }
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     * getLimit:获取每面显示的行数.
     * 
     * @author Administrator
     * @date 2017年6月5日 下午1:36:11
     * @return
     */
    public Integer getLimit() {
        if (limit == null) {
            limit = Default_Limit;
        }
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    // ++++++++++++分页查询参数：：按照bootstrap table的{起始行数、每页数量}进行分页+++++++++++++++

    // ==========================================================
    // ++++++++++++分页查询参数：：按照正常的{第几页、每页数量}进行分页+++++++++++++++

    /**
     * 第几页
     */
    private Integer offset;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    // ++++++++++++分页查询参数：：按照正常的{第几页、每页数量}进行分页+++++++++++++++

}
