package com.jumore.jmbi.model.vo.data;

import java.util.ArrayList;
import java.util.List;

/** 
* 封装了页面商品类别下拉菜单
* @author: panweiqiang
* @since: 2016年7月21日  下午3:06:08
* @history:
*/
public class CategoryTreeVo {

    /**
     * 类目等级越高的放前面
     */
    private List<List<CategoryVo>> categories = new ArrayList<List<CategoryVo>>();

    public List<List<CategoryVo>> getCategories() {
        return categories;
    }

    public void setCategories(List<List<CategoryVo>> categories) {
        this.categories = categories;
    }

    /** 
     * 功能说明：添加一组下级类目列表
     * 
     * @param categories 
     * void
     */
    public void addChildCategories(List<CategoryVo> categories) {
        this.categories.add(categories);
    }

    /** 
     * 功能说明：添加一组上级类目列表
     * 
     * @param categories 
     * void
     */
    public void addParentCategories(List<CategoryVo> categories) {
        this.categories.add(0, categories);
    }

}
