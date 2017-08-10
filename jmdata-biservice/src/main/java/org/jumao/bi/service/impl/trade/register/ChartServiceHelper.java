package org.jumao.bi.service.impl.trade.register;

import org.apache.log4j.Logger;
import org.jumao.bi.utis.DesensitizationUtils;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.RegCalUtils;
import org.jumao.bi.utis.RegDateUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.Key;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by kartty on 2017/5/3.
 */
public class ChartServiceHelper {

    private Logger logger = Logger.getLogger(ChartServiceHelper.class);

    protected static final int Top_Ele0 = 0;
    protected static final int Top_Ele5 = 5;
    protected static final int Top_Ele10 = 10;

    public static Map<String, String> dataSrcNameMap = new HashMap<String, String>();
    public static Map<String, String> compCategoryMap = new HashMap<String, String>();
    public static Map<String, String> licTpyeNameMap = new HashMap<String, String>();
    public static Map<String, String> industryIdNameMap = new HashMap<String, String>();
    public static Map<String, String> lineTypeNameMap = new HashMap<String, String>();
    public static Map<String, String> enLineTypeNameMap = new HashMap<String, String>();
    public static Map<String, String> sourceTypeNameMap = new HashMap<String, String>();
    public static String ELSE_NAME = "其他";

    static {
        //1 前台注册 2 后台创建 3 中台自己注册 4 中台管理员创建 98 手动创建 99 认证中心同步
        dataSrcNameMap.put(String.valueOf(Key.Num1), "前台注册");
        dataSrcNameMap.put(String.valueOf(Key.Num2), "后台创建");
        dataSrcNameMap.put(String.valueOf(Key.Num3), "中台自己注册");
        dataSrcNameMap.put(String.valueOf(Key.Num4), "中台管理员创建");
        dataSrcNameMap.put(String.valueOf(Key.Num98), "手动创建");
        dataSrcNameMap.put(String.valueOf(Key.Num99), "认证中心同步");

        compCategoryMap.put(String.valueOf(Key.Num1), "工厂");
        compCategoryMap.put(String.valueOf(Key.Num2), "贸易商");
        compCategoryMap.put(String.valueOf(Key.Num3), "工贸一体");

        //0 多证合一 1 普通三证
        licTpyeNameMap.put(String.valueOf(Key.Num0), "多证合一");
        licTpyeNameMap.put(String.valueOf(Key.Num1), "普通三证");

        industryIdNameMap.put(String.valueOf(Key.Num1), "煤炭");
        industryIdNameMap.put(String.valueOf(Key.Num2), "有色");
        industryIdNameMap.put(String.valueOf(Key.Num3), "矿产");
        industryIdNameMap.put(String.valueOf(Key.Num4), "农产品");
        industryIdNameMap.put(String.valueOf(Key.Num5), "钢铁");
        industryIdNameMap.put(String.valueOf(Key.Num6), "消费品");
        industryIdNameMap.put(String.valueOf(Key.Num7), "食品");
        industryIdNameMap.put(String.valueOf(Key.Num8), "工业品");
        industryIdNameMap.put(String.valueOf(Key.Num9), "机械");
        industryIdNameMap.put(String.valueOf(Key.Num10), "化工");//Key.NVLL 可以忽略某个 name
        industryIdNameMap.put(String.valueOf(Key.Num11), "石油");

        lineTypeNameMap.put(String.valueOf(Key.Num231), "公路");
        lineTypeNameMap.put(String.valueOf(Key.Num232), "铁路");
        lineTypeNameMap.put(String.valueOf(Key.Num233), "内河航运");
        lineTypeNameMap.put(String.valueOf(Key.Num234), "海运");
        lineTypeNameMap.put(String.valueOf(Key.Num235), "仓储");
        lineTypeNameMap.put(String.valueOf(Key.Num236), "联运");
        lineTypeNameMap.put(String.valueOf(Key.Num237), "空运");

        enLineTypeNameMap.put(String.valueOf(Key.Num120), "Sea Freight");
        enLineTypeNameMap.put(String.valueOf(Key.Num121), "Air Freight");
        enLineTypeNameMap.put(String.valueOf(Key.Num122), "Rail Transport");
        enLineTypeNameMap.put(String.valueOf(Key.Num123), "Load transport");
        enLineTypeNameMap.put(String.valueOf(Key.Num124), "Warehouse");

        sourceTypeNameMap.put(Key.Referral, "外部链接");
        sourceTypeNameMap.put(Key.None, "直接访问");
        sourceTypeNameMap.put(Key.Organic, "搜索引擎");
    }


    protected String checkTypeName(String type, String name) {
        if (name == null) {
            //String typeStr = null;
            //if (type == null) {
                //typeStr = "n";
            //} else {
                //typeStr = String.valueOf(type);
            //}
            //name = StringUtils.joinStr(ChartServiceHelper.ELSE_NAME, "#", typeStr);
            return ChartServiceHelper.ELSE_NAME;
        }
        return name;
    }


    protected String dealRatePercent(double top, double bottom, boolean transToPercent) {
        double res = 0D;
        if (transToPercent) {
            res = top / bottom * Key.Num100;
        } else {
            res = top / bottom;
        }
        String rate = String.valueOf(res);
        StringBuilder sb = new StringBuilder();

        if (bottom != 0D) {
            rate = siSheWuRuAfterDot(rate);
        }

        sb.append(rate);
        if (transToPercent) {
            sb.append("%");
        }
        return sb.toString();
    }


    /**
     *
     * @param numObj  某个数字
     * @param denominator  分母
     * @return 若有小数点，第三位四舍五入，只保留两位。
     */
    public static String numDivideSth(Object numObj, int denominator) {
        if (numObj == null) {
            return Key.Zero_Str;
        } else if (denominator <= 0) {
            return numObj.toString();
        }
        BigDecimal num = new BigDecimal(numObj.toString());
        BigDecimal res = num.divide(new BigDecimal(denominator));
        return siSheWuRuAfterDot(res.toPlainString());
    }


    /**
     * 以下对小数点后 3 位四舍五入成 2位
     * @param rate 原小数
     */
    public static String siSheWuRuAfterDot(String rate) {
        int len = rate.length();
        int dotIdx = rate.indexOf(".");
        int twoNumAfterDot = dotIdx + Key.Num3;
        if (dotIdx > 0 && len > twoNumAfterDot) {
            String rateTmp = rate.substring(0, twoNumAfterDot + Key.Num1);
            int lastNum = Integer.parseInt(rateTmp.charAt(rateTmp.length() - 1) + "");

            BigDecimal rateBd = new BigDecimal(rate.substring(0, twoNumAfterDot));
            if (lastNum >= Key.Num5) {
                rateBd = rateBd.add(new BigDecimal(0.01D));
            }
            rate = rateBd.toPlainString();
            if (rate.length() > twoNumAfterDot) {
                rate = rate.substring(0, twoNumAfterDot);
            }
        }
        return rate;
    }


    public static String getDoublePercent(Double num) {
        return siSheWuRuAfterDot(new BigDecimal(num).toPlainString()) + "%";
    }

    public static String getDoublePercent(String num) {
        return siSheWuRuAfterDot(num) + "%";
    }

    /**
     * 百度统计里的 date 已经是 yyyyMMdd，所以不需要再 format
     */
    protected String parseTime(String createTime, boolean reFormatToNhAnd_, boolean accurateToHour) throws Exception {
        String timeParsed = null;
        if (accurateToHour) {
            timeParsed = getHourFromCreateTime(createTime, reFormatToNhAnd_);
        } else {
            if (reFormatToNhAnd_) {
                timeParsed = RegDateUtils.formatToNoHourAnd_(RegDateUtils.parseStartWithFF(createTime));
            } else {
                timeParsed = createTime;
            }
        }
        return timeParsed;
    }

    /**
     * @param reFormatToNhAnd_ format 到没有小时和 - 的格式，如 2017010203
     * @return
     * @throws Exception
     */
    private String getHourFromCreateTime(String createTime, boolean reFormatToNhAnd_) throws Exception {
        Date date = null;
        if (reFormatToNhAnd_) {
            date = RegDateUtils.parseStartWithFF(createTime);
        } else {
            //当不需要 reFormatToNhAnd_ 并要提取小时的时后，此时的数据格式必定是 2017010203
            date = RegDateUtils.parseToNoMinAnd_(createTime);
        }

        Calendar calendar = RegCalUtils.getCalendarBy(date);
        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
    }


    protected Map<String, String> getEmptyDateTotalMap(String startDate, String endDate, boolean accurateToHour) throws Exception {
        Map<String,String> dateTotalMap = new HashMap<String, String>();

        Calendar startCal = RegCalUtils.getCalendarBy(RegDateUtils.parseToNoHourAnd_(startDate));
        Calendar endCal = RegCalUtils.getCalendarBy(RegDateUtils.parseToNoHourAnd_(endDate));

        if (accurateToHour) {
            for (int i = 0; i <= Key.Num23; i++) {
                dateTotalMap.put(String.valueOf(i), Key.Zero_Str);
            }
        } else {
            while (!startCal.equals(endCal)) {
                String curDateStr = RegDateUtils.formatToNoHourAnd_(startCal.getTime());
                dateTotalMap.put(curDateStr, Key.Zero_Str);

                startCal.add(Calendar.DAY_OF_MONTH, 1);
            }
            dateTotalMap.put(endDate, Key.Zero_Str);
        }

        return dateTotalMap;
    }


    protected String changeHourFormat(String time) {
        if (time.length() == 1) {
            time = StringUtils.joinStr(Key.Zero_Str, time, ":00");
        } else {
            time = StringUtils.joinStr(time, ":00");
        }
        return time;
    }



}
