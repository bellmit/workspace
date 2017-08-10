package org.jumao.bi.utis.constants;

/**
 */
public class EnNetFlowUrl extends BasicUrl {


    /**
     * e.g. /v1/en/netflow
     */
    public static final String V1_En_Netflow = V1_En + Slash + Key.Netflow;


    /**
     * e.g. /overview/platform/101501/startDate/20170101/endDate/20170707
     */
    public static final String Overviews = Slash + Overview +
            Platform_And_Date_Range_Path_Param;

    /**
     * e.g. /trend/lineChart/platform/101501/startDate/20170101/endDate/20170807/type/0
     */
    public static final String Netflow_Trend_LineChart = Slash + Key.Trend + Slash + Line_Chart +
            Platform_And_Date_Range_Path_Param + Tail_Type_Parm;


    /**
     * e.g. /overview/table/platform/101501/startDate/20170101/endDate/20170707/orderTarget/pv/orderType/asc
     */
    public static final String Overviews_Table = Slash + Overview + Slash + Table +
            Platform_And_Date_Range_Path_Param + Order_Target_Param + Order_Type_Param;

    /**
     * e.g. /pv/barChart/platform/101501/startDate/20170101/endDate/20170807
     */
    public static final String Netflow_PvTop5_Barchart = Slash + Key.Pv + Slash + Bar_Chart +
            Platform_And_Date_Range_Path_Param;

    /**
     * e.g. /bounce/barChart/platform/101501/startDate/20170101/endDate/20170807
     */
    public static final String Netflow_BounceTop5_Barchart = Slash + Key.Bounce + Slash + Bar_Chart +
            Platform_And_Date_Range_Path_Param;

    /**
     * e.g. /areaDist/global/pv/platform/101501/startDate/20170101/endDate/20170707
     */
    public static final String Global_Area_Dist_Pv = Slash + Area_Dist + Slash + Global +
            Slash + Key.Pv + Platform_And_Date_Range_Path_Param;


    /**
     * e.g. /areaDist/global/table/platform/101501/startDate/20170101/endDate/20170707/limit/10/offset/0/orderType/desc
     */
    public static final String Global_Area_Dist_Table = Slash + Area_Dist + Slash + Global +
            Slash + Table + Platform_And_Date_Range_Path_Param + Limit_And_Offset_Param+ Order_Type_Param;

    /**
     * e.g. /sourceMedium/uv/pieChart/platform/101501/startDate/20160501/endDate/20170701
     */
    public static final String Source_Medium_Uv_Piechart = Slash + Key.Source_Medium +
            Slash + Key.Uv + Slash + Pie_Chart + Platform_And_Date_Range_Path_Param;


    /**
     * e.g. /sourceMedium/lineChart/platform/101501/startDate/20170101/endDate/20170807/type/0
     */
    public static final String Source_Medium_LineChart = Slash + Key.Source_Medium + Slash + Line_Chart +
            Platform_And_Date_Range_Path_Param + Tail_Type_Parm;

    /**
     * e.g. /sourceMedium/table/platform/101501/startDate/20170101/endDate/20170807/orderType/asc
     */
    public static final String Source_Medium_Table = Slash + Key.Source_Medium + Slash + Table +
            Platform_And_Date_Range_Path_Param + Order_Type_Param;


    /**
     * e.g. /entrancesPage/pvTop10/table/platform/101501/startDate/20170101/endDate/20170807
     */
    public static final String Entrances_Page_Top10_Table = Slash + Key.Entrances_Page + Slash + Pv_Top10 + Slash + Table +
            Platform_And_Date_Range_Path_Param;

    /**
     * e.g. /visitedPage/pvTop10/table/platform/101501/startDate/20170101/endDate/20170807
     */
    public static final String Visited_Page_Top10_Table = Slash + Key.Visited_Page + Slash + Pv_Top10 + Slash + Table +
            Platform_And_Date_Range_Path_Param;

    /**
     * e.g. /medium/pvTop10/table/platform/101501/startDate/20170101/endDate/20170807
     */
    public static final String Medium_Top10_Table = Slash + Key.Medium + Slash + Pv_Top10 + Slash + Table +
            Platform_And_Date_Range_Path_Param;
//    public static final String
//    public static final String
//    public static final String
//    public static final String


}
