package com.jumore.jmbi.model.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 下拉菜单选项
 * 
 * @author: panweiqiang
 * @since: 2016年7月22日 上午10:35:08
 * @history:
 */
public class SelectResultVo {

    /**
     * 当前选中的内容
     */
    private String             active;

    /**
     * 下拉选项
     */
    private List<SelectResult> results = new ArrayList<SelectResult>();

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public List<SelectResult> getResults() {
        return results;
    }

    public void setResults(List<SelectResult> results) {
        this.results = results;
    }

    public void addResult(String id, String text) {
        results.add(new SelectResult(id, text));
    }

    /**
     * addResults:增加结果项.
     * 
     * @author Administrator
     * @date 2017年6月5日 下午1:39:52
     * @param object
     * @param selectResultConstructor
     */
    public <T> void addResults(List<T> object, SelectResultConstructor<T> selectResultConstructor) {
        for (T data : object) {
            results.add(new SelectResult(selectResultConstructor.getId(data), selectResultConstructor.getText(data)));
        }
    }
}
