/** 
 * Project Name:jmdata-biservice 
 * File Name:IUserAttentionDao.java 
 * Package Name:org.jumao.bi.dao.user
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 1
 * @date 2017年6月22日 上午9:51:07
 */
package org.jumao.bi.dao.user;

import java.io.IOException;
import java.util.List;

import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.entites.user.SqlResultBean;
import org.jumao.bi.entites.user.UserSearchInfo;

/**
 * Function: 用户关注Dao接口
 * 
 * @author 1
 * @date 2017年6月22日 上午9:51:07
 * @version 
 * @see
 */
public interface IUserAttentionDao {
    
    void addUserSearchInfo(List<UserSearchInfo> userSearches) throws IOException;
    
    List<SqlResultBean> getTopNSearchWords(ParamBean param);

}
