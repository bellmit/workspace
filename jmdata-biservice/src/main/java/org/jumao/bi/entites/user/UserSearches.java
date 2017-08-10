/** 
 * Project Name:jmdata-biservice 
 * File Name:UserSearches.java 
 * Package Name:org.jumao.bi.entites.user
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 1
 * @date 2017年6月21日 下午7:51:00
 */
package org.jumao.bi.entites.user;

import java.util.List;

/**
 * Function: 用户搜索信息集合
 * 
 * @author 1
 * @date 2017年6月21日 下午7:51:00
 * @version 
 * @see
 */
public class UserSearches {
    
    private List<UserSearchInfo> list;

    public List<UserSearchInfo> getList() {
        return list;
    }

    public void setList(List<UserSearchInfo> list) {
        this.list = list;
    }
    

}
