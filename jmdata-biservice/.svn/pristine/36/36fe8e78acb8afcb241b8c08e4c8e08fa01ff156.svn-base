/** 
 * Project Name:jmdata-biservice 
 * File Name:UserAttentionDaoImpl.java 
 * Package Name:org.jumao.bi.dao.user.impl
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 1
 * @date 2017年6月22日 上午9:57:15
 */
package org.jumao.bi.dao.user.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.jumao.bi.dao.CommonDaoImpl;
import org.jumao.bi.dao.user.IUserAttentionDao;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.entites.user.SqlResultBean;
import org.jumao.bi.entites.user.UserSearchInfo;
import org.springframework.stereotype.Repository;

/**
 * Function: 用户关注Dao接口实现
 * 
 * @author 1
 * @date 2017年6月22日 上午9:57:15
 * @version 
 * @see
 */
@Repository("userAttentionDao")
public class UserAttentionDaoImpl extends CommonDaoImpl implements IUserAttentionDao {

    public static final String User_Attention_Tab = "jmbi:user_attention";
    
    @Override
    public void addUserSearchInfo(List<UserSearchInfo> userSearches) throws IOException {
        List<Put> puts = new ArrayList<Put>();
        for (UserSearchInfo userSearch : userSearches) {
            putUserSearchInfo(puts, userSearch);
        }
        
        if (!puts.isEmpty()) {
            super.hbasedao.save(puts, User_Attention_Tab);
        }
        
    }
    
    private void putUserSearchInfo(List<Put> puts, UserSearchInfo userSearch) {
        String rowKey = userSearch.getRowKey();
        String platform = userSearch.getPlatform();
        String keyWord = userSearch.getKeyword();
        String createTime = userSearch.getCreateTime();
        
        Put put = new Put(Bytes.toBytes(rowKey)); 
        put.addColumn(Column_Family_Name, "platform".getBytes(), platform.getBytes());
        put.addColumn(Column_Family_Name, "keyword".getBytes(), keyWord.getBytes());
        put.addColumn(Column_Family_Name, "create_time".getBytes(), createTime.getBytes());
    
        puts.add(put);
    }

    @Override
    public List<SqlResultBean> getTopNSearchWords(ParamBean param) {
        //包含结束日期的数据：如结束日期为20170626实际上为20170626235959
        String sql = "select keyword as name, count(keyword) as value from jmbi_user_attention where "
                + "platform='" + param.getPlatform()
                + "' and create_time between '" + param.getStartDate()
                + "' and '" + param.getEndDate() + "235959"
                + "' group by keyword order by value desc limit " + param.getItemId();
        
        return super.getSqlResult(sql, SqlResultBean.class);
    }

}
