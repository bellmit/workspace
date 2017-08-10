package org.jumao.bi.service.impl.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jumao.bi.entites.charts.CommonBean;
import org.jumao.bi.entites.charts.LineChart;
import org.jumao.bi.entites.charts.PieChart;
import org.jumao.bi.entites.user.BrowseBean;
import org.jumao.bi.entites.user.BrowseResponse;
import org.jumao.bi.entites.user.LoginBean;
import org.jumao.bi.entites.user.LoginResponse;

public class UserTraceSvcHelper {
    
    private static final int Hours_Of_Day = 24;

    /**
     * buildLoginLineChart:构建用户登人曲线统计图
     * 
     * @param dates
     * @param logins
     * @param response
     */
    public static void buildLoginLineChart(List<String> dates, List<LoginBean> logins, LoginResponse response) {

        int size = dates.size();
        String[] xaxisData = (String[]) dates.toArray(new String[size]);
        LineChart lineChart = new LineChart();
        lineChart.setxAxisData(xaxisData);
        Map<String , Integer> loginMap = new HashMap<String,  Integer>();
        for (LoginBean login : logins) {
            String key = login.getLogin();
            if (key.length() == 2) {//按小时统计
                key = key + ":00";
            }
            loginMap.put(key, login.getNums());
        }
        
        int[] seriesData = new int[size];
        for (int i = 0; i < size; i++) {
            String dateStr = xaxisData[i];
            if(loginMap.containsKey(dateStr)) {
                seriesData[i] = loginMap.get(dateStr);
            }
        }
        
        List<int[]> data = new ArrayList<int[]>(); 
        data.add(seriesData);
        lineChart.setSeriesData(data);
        
        response.setLineChart(lineChart);
    }

    
    /**
     * buildBrowserPieChart:构建浏览器饼图统计图
     * 
     * @param browses
     * @param response
     */
    public static void buildBrowserPieChart(List<BrowseBean> browses, BrowseResponse response) {
        PieChart pieChart = new PieChart();
        List<CommonBean> seriesData = new ArrayList<CommonBean>();
        String[] legendData = new String[browses.size()];
        int i = 0;
        for (BrowseBean browse : browses) {

            String browseType = browse.getBrowseType();
            Integer nums = browse.getNums();
            CommonBean commonBean = new CommonBean(browseType, nums.toString());
            seriesData.add(commonBean);
            legendData[i] = browseType;
            i++;
        }

        pieChart.setSeriesFromCb(seriesData);
        pieChart.setLegendData(legendData);
        
        response.setPieChart(pieChart);
    }

    /**
     * buildDayHours: 按天统计X轴坐标集合.
     * 
     * @return List
     */
    public static List<String> buildDayHours() {
        List<String> hours = new ArrayList<String>();
        for (int i = 0; i < Hours_Of_Day; i++) {
            String hour = String.valueOf(i);
            hour = (hour.length() < 2) ?  "0" + hour : hour;
            hours.add(hour + ":00");
        }
        
        return hours;
    }

}
