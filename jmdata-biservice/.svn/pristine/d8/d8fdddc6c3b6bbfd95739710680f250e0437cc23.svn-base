/** 
 * Project Name:jmdata-biservice 
 * File Name:UserAttentionSvcHelper.java 
 * Package Name:org.jumao.bi.service.impl.user
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 1
 * @date 2017年6月26日 下午4:05:07
 */
package org.jumao.bi.service.impl.user;

import java.math.BigDecimal;
import java.util.List;

import org.jumao.bi.entites.charts.BarChart;
import org.jumao.bi.entites.user.SqlResultBean;
import org.jumao.bi.entites.user.UserSearchResponse;

/**
 * Function: 用户关注服务接口辅助类
 * 
 * @author 1
 * @date 2017年6月26日 下午4:05:07
 * @version 
 * @see
 */
public class UserAttentionSvcHelper {

    /**
     * buildWordRankBarChart:构建搜索词排行柱状图.
     * 
     * @author 1
     * @date 2017年6月26日 下午4:34:22
     * @param keywords
     * @param response
     */
    public static void buildWordRankBarChart(List<SqlResultBean> keywords, UserSearchResponse response) {
        
        BarChart barChart = new BarChart();
        int size = keywords.size();
        BigDecimal[] seriesData = new BigDecimal[size];
        String[] yaxisData = new String[size];
        int i = 0;
        for (SqlResultBean bean : keywords) {
            yaxisData[i] = bean.getName();
            seriesData[i] = new BigDecimal(bean.getValue());
            i++;         
        }
        barChart.setSeriesData(seriesData);
        barChart.setyAxisData(yaxisData);
        barChart.setSeriesName("用户搜索关键词排行");
        response.setBarChart(barChart);
    }

}
