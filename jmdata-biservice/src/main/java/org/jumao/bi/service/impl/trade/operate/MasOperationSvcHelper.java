package org.jumao.bi.service.impl.trade.operate;

import org.apache.commons.lang3.math.NumberUtils;
import org.jumao.bi.dao.trade.operate.IOperationOverviewDao;
import org.jumao.bi.entites.trade.operate.LineChart;
import org.jumao.bi.entites.trade.operate.TotalResponse;
import org.jumao.commons.frameworks.jmframework.commutil.CalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/9.
 */
public class MasOperationSvcHelper {

    @Autowired
    private static IOperationOverviewDao operationOverviewDao;

    public static void main(String[] args) {
        BigDecimal MemberTotal = new BigDecimal(2.00);
        String MemberTotal_ = "";
        MemberTotal_ = MemberTotal.toString()/*.split(".")[0]*/;
        System.out.println(MemberTotal_);
    }

    public static void buildTotalChart(List<Map<String, Object>> topTotal, TotalResponse response) {

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            map.put("nowDate", nowDate.substring(0, 10));  //  当前查询的时间

            Object MemberTotal = topTotal.get(0).get("nums");
            String MemberTotal_ = "0";
            if (MemberTotal != null) MemberTotal_ = MemberTotal.toString();
            map.put("MemberTotal", MemberTotal_.split("\\.")[0]);

            Object OpenStoreTotal = topTotal.get(1).get("nums");
            String OpenStoreTotal_ = "0";
            if (OpenStoreTotal != null) OpenStoreTotal_ = OpenStoreTotal.toString();
            map.put("OpenStoreTotal", OpenStoreTotal_.split("\\.")[0]);

            Object ItemTotal = topTotal.get(2).get("nums");
            String ItemTotal_ = "0";
            if (ItemTotal != null) ItemTotal_ = ItemTotal.toString();
            map.put("ItemTotal", ItemTotal_.split("\\.")[0]);

            Object OrderTotal = topTotal.get(3).get("nums");
            String OrderTotal_ = "0";
            if (OrderTotal != null) OrderTotal_ = OrderTotal.toString();
            map.put("OrderTotal", OrderTotal_.split("\\.")[0]);

            Object PayTotal = topTotal.get(4).get("nums");
            String PayTotal_ = "0";
            if (PayTotal != null) PayTotal_ = PayTotal.toString();
            map.put("PayTotal", PayTotal_.split("\\.")[0]);

            Object TranTotal = topTotal.get(5).get("nums");
            String TranTotal_ = "0";
            if (TranTotal != null) {
                TranTotal_ = NumberUtils.createBigDecimal(TranTotal.toString()).divide(BigDecimal.valueOf(10000), 2, BigDecimal.ROUND_HALF_EVEN).toString();
            }
            map.put("TranTotal", TranTotal_);

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setData(map);
    }


    /**
     * 查询并组装三证审核实体
     */
    private static LineChart getThreeCard(String startDate, String endDate, int size, String[] xAxisData, String platform) {
        //  获得查询三证审核y轴数据
        startDate = startDate.substring(0, 4) + "-" + startDate.substring(4, 6) + "-" + startDate.substring(6, 8);
        endDate = endDate.substring(0, 4) + "-" + endDate.substring(4, 6) + "-" + endDate.substring(6, 8);
        String SQL = "select STRLEFT(cast(s.create_time as string), 10) as time_,count(s.create_time) as nums " +
                "from  jmbi_uc_company s  where platform = '" + platform + "' and delete_flag = 0 and  create_time between '" + startDate + "' and  '" + endDate + "' " +
                "group by  time_ order by time_;";
        Map<String, String> map = operationOverviewDao.getForMap(SQL);

        //  组装 threeCard  查询三证审核行对象
        LineChart threeCard = new LineChart();
        threeCard.setTitle("三证审核趋势情况");
        threeCard.setSeriesName("三证审核");
        threeCard.setUnit("个");
        return getBasicSeri(size, xAxisData, map, threeCard);
    }


    private static LineChart getBasicSeri(int size, String[] xAxisData, Map<String, String> map, LineChart threeCard) {
        BigDecimal[] seriesData = new BigDecimal[size];
        int i = 0;
        for (String x : xAxisData) {
            x = x.substring(0, 4) + "-" + x.substring(4, 6) + "-" + x.substring(6, 8);
            if (map.containsKey(x) && !map.get(x).equals("--") && map.get(x) != null) {
                seriesData[i] = new BigDecimal(map.get(x));
            } else {
                seriesData[i] = BigDecimal.ZERO;
            }
            i++;
        }
        threeCard.setSeriesData(seriesData);  //  y轴数据
        return threeCard;
    }


    /**
     * 返回一天24个小时的字符串数组
     */
    public static String[] getHour(String date) {
        String[] hour = new String[24];
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                hour[i] = date + " 0" + i;
            } else {
                hour[i] = date + " " + i;
            }
        }
        return hour;
    }


    /**
     * 获得x轴数据
     */
    public static String[] setXAxis(String startDate, String endDate) throws ParseException {
        String[] xAxisData = null;
        if (startDate.equals(endDate)) {  //  相等为查询一天，x轴为24小时
            xAxisData = MasOperationSvcHelper.getHour(startDate);
        } else {  //  不相等，x轴以天为单位
            List<String> dates = CalendarUtils.getAllDatesByPeriod(startDate, endDate);
            int size_ = dates.size();
            xAxisData = dates.toArray(new String[size_]);
        }
        return xAxisData;
    }

    /**
     * 根据字符串长度判断是否为24小时的x轴，如果是，重新将其处理为规范格式
     */
    public static void handleXAxis(String[] xAxisData) {
        int j = 0;
        if (xAxisData[0].length() > 8) {
            for (String s : xAxisData) {
                s = s.split(" ")[1] + ":00";
                xAxisData[j] = s;
                j++;
            }
        }
    }

    /**
     * 延续前一个转化率的值
     *
     * @param seriesData
     */
    public static BigDecimal[] setCenv(BigDecimal[] seriesData) {
        String tmp = "";
        for (int i = 0; i < seriesData.length; i++) {
            String bss = String.valueOf(seriesData[i].doubleValue());
            if (!bss.equals("0.0")) {
                tmp = bss;
            } else {
                if (!tmp.equals("")) {
                    seriesData[i] = new BigDecimal(tmp);
                }
            }
        }
        return seriesData;
    }



}
