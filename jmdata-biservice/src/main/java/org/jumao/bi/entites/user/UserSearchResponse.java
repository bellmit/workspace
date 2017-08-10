/** 
 * Project Name:jmdata-biservice 
 * File Name:UserSearchResponse.java 
 * Package Name:org.jumao.bi.entites.user
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 1
 * @date 2017年6月23日 下午5:54:41
 */
package org.jumao.bi.entites.user;

import org.jumao.bi.entites.CommonResponse;
import org.jumao.bi.entites.charts.BarChart;

/**
 * Function: 用户搜索相应Bean
 * 
 * @author 1
 * @date 2017年6月23日 下午5:54:41
 * @version 
 * @see
 */
public class UserSearchResponse extends CommonResponse{
    

    private static final long serialVersionUID = -3761249371138559323L;

    private BarChart barChart;

    public BarChart getBarChart() {
        return barChart;
    }

    public void setBarChart(BarChart barChart) {
        this.barChart = barChart;
    }
    
    

}
