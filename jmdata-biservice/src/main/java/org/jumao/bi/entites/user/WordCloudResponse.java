/** 
 * Project Name:jmdata-biservice 
 * File Name:WordCloudResponse.java 
 * Package Name:org.jumao.bi.entites.user
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 1
 * @date 2017年6月26日 下午3:33:32
 */
package org.jumao.bi.entites.user;

import java.io.Serializable;
import java.util.List;

import org.jumao.bi.entites.CommonResponse;

/**
 * Function: 搜索词云
 * 
 * @author 1
 * @date 2017年6月26日 下午3:33:32
 * @version 
 * @see
 */
public class WordCloudResponse extends CommonResponse implements Serializable{


    private static final long serialVersionUID = -5847740651635502433L;
    
    List<SqlResultBean> wordCloud;

    public List<SqlResultBean> getWordCloud() {
        return wordCloud;
    }

    public void setWordCloud(List<SqlResultBean> wordCloud) {
        this.wordCloud = wordCloud;
    }
    
      
    

}
