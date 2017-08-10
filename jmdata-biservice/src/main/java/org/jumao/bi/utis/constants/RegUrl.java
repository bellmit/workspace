package org.jumao.bi.utis.constants;




public class RegUrl extends BasicUrl {


    private static final String Ms = "ms";//master station

    //以下注册分析-日新增用户
    /**
     * e.g. /newlyIncr/lineChart/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String Newlyincr_Linechart = Slash + Newly_Incr + Slash + Line_Chart + Platform_And_Date_Range_Path_Param;
    /**
     * e.g. /ms/newlyIncr/lineChart/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String Ms_Newlyincr_Linechart = Slash + Ms + Slash + Newly_Incr + Slash + Line_Chart + Platform_And_Date_Range_Path_Param;

    /**
     * e.g. /newlyIncr/pieChart/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String Newlyincr_Piechart = Slash + Newly_Incr + Slash + Pie_Chart + Platform_And_Date_Range_Path_Param;
    /**
     * e.g. /ms/newlyIncr/pieChart/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String Ms_Newlyincr_Piechart = Slash + Ms + Slash + Newly_Incr + Slash + Pie_Chart + Platform_And_Date_Range_Path_Param;

    /**
     * e.g. /newlyIncr/table/platform/1025/startDate/20160501/endDate/20170701/limit/20/offset/0
     */
    public static final String Newlyincr_Table = Slash + Newly_Incr + Slash + Table +
            Platform_And_Date_Range_Path_Param + Limit_And_Offset_Param;



    //-------------------------------------------------------------------------------------------------------------------------
    //以下注册分析-认证用户

    /**
     * e.g. /authUser/overview/lineChart/platform/1025/startDate/20160501/endDate/20170701/type/0
     */
    public static final String Auth_User_Overview_Linechart = Slash + Auth_User + Slash + Overview + Slash + Line_Chart +
            Platform_And_Date_Range_Path_Param + Tail_Type_Parm;
    /**
     * e.g. /authUser/cert3/history/platform/1025
     */
    public static final String Auth_User_Cert3_History = Slash + Auth_User + Slash + Cert_Three + Slash + Key.History + Slash +
            Key.Platform + Slash_Brace + Key.Platform + Right_Brace;
    /**
     * e.g. /authUser/passed/histogram/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String Auth_User_Passed_Histogram = Slash + Auth_User + Slash + Key.Passed + Slash + Histogram +
            Platform_And_Date_Range_Path_Param;
    /**
     * e.g. /authUser/licPercent/pieChart/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String Auth_User_Lic_Pie = Slash + Auth_User + Slash + Lic_Percent + Slash + Pie_Chart +
            Platform_And_Date_Range_Path_Param;
    /**
     * e.g. /authUser/areaDist/chinaMap/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String Auth_User_Area_Dist = Slash + Auth_User + Slash + Area_Dist + Slash + China_Map +
            Platform_And_Date_Range_Path_Param;
    /**
     * e.g. /authUser/areaDist/barChart/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String Auth_User_Area_Dist_Top10 = Slash + Auth_User + Slash + Area_Dist + Slash + Bar_Chart +
            Platform_And_Date_Range_Path_Param;

    //-------------------------------------------------------------------------------------------------------------------------
    //以下注册分析-签章支付
    private static final String Visa_And_Pay = "visaAndPay";
    private static final String Visa = "visa";
    private static final String Pay = "pay";
    /**
     * e.g. /visaAndPay/overview/lineChart/platform/102000/startDate/20160501/endDate/20170701/type/1
     */
    public static final String Visa_And_Pay_Overview_Linechart = Slash + Visa_And_Pay + Slash + Overview + Slash + Line_Chart +
            Platform_And_Date_Range_Path_Param + Tail_Type_Parm;
    /**
     * e.g. /visaAndPay/history/pieChart/platform/1025
     */
    public static final String Visa_And_Pay_History_Pie = Slash + Visa_And_Pay + Slash + Key.History + Slash + Pie_Chart + Slash +
            Key.Platform + Slash_Brace + Key.Platform + Right_Brace;
    /**
     * e.g. /visaAndPay/areaDist/chinaMap/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String Visa_And_Pay_Area_Dist = Slash + Visa_And_Pay + Slash + Area_Dist + Slash + China_Map +
            Platform_And_Date_Range_Path_Param;
    /**
     * e.g. /visa/areaDist/barChart/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String Visa_Area_Dist_Top10 = Slash + Visa + Slash + Area_Dist + Slash + Bar_Chart +
            Platform_And_Date_Range_Path_Param;
    /**
     * e.g. /pay/areaDist/barChart/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String Pay_Area_Dist_Top10 = Slash + Pay + Slash + Area_Dist + Slash + Bar_Chart +
            Platform_And_Date_Range_Path_Param;



}
