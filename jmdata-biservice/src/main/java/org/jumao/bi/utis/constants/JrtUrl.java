package org.jumao.bi.utis.constants;

/**
 */
public class JrtUrl extends BasicUrl {


    /**
     * e.g. /v1/jrt/financing
     */
    public static final String V1_Jrt_Financing = Slash + V1 + Slash + Jrt + Slash + Key.Financing;


    /**
     * e.g. /trend/lineChart/type/0
     */
    public static final String Trend_LineChart = Slash + Key.Trend + Slash + Line_Chart + Tail_Type_Parm;


    /**
     * e.g. /trend/analysis/lineChart/platform/1025/startDate/20170101/endDate/20170707/type/0
     */
    public static final String Trend_Analysis_LineChart = Slash + Key.Trend + Slash + Key.Analysis + Slash + Line_Chart +
            Platform_And_Date_Range_Path_Param + Tail_Type_Parm;


    /**
     * e.g. /trend/detail/limit/10/offset/0/type/0
     */
    public static final String Trend_Detail_Table = Slash + Key.Trend + Slash + Key.Detail +
            Limit_And_Offset_Param + Tail_Type_Parm;

}
