/**
 * 
 */
package com.jumore.jmbi.model.vo;

import java.util.List;

import com.jumore.jmbi.common.requests.CommResponse;

/** 
*  分页返回对象
* @author: luochao
* @since: 2016年7月15日  下午3:58:07
* @history:
*/
public class PageResult<T> extends CommResponse {

    /**返回数据*/
    private List<T> rows;

    /**总条数*/
    private int     total;

    public PageResult() {

    }

    public PageResult(List<T> rows, int total) {
        this.rows = rows;
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
