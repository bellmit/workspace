package org.jumao.bi.utis.constants;

/**
 * Created by kartty on 2017/5/23.
 */
public class BasicUrl {

    protected static final String V1 = "v1";
    protected static final String En = "en";
    /** 聚运通 */
    protected static final String Jyt = "jyt";
    /** 聚融通 */
    protected static final String Jrt = "jrt";
    protected static final String Slash = "/";
    protected static final String Slash_Brace = "/{";
    protected static final String Brace_Slash = "}/";
    protected static final String Left_Brace = "{";
    protected static final String Right_Brace = "}";

    protected static final String Pv_Top10 = "pvTop10";

    protected static final String V1_En = Slash + V1 + Slash + En;

    protected static final String Order_Target_Param = Slash + Key.Order_Target + Slash_Brace + Key.Order_Target + Right_Brace;
    protected static final String Order_Type_Param = Slash + Key.Order_Type + Slash_Brace + Key.Order_Type + Right_Brace;

    protected static final String Platform_And_Date_Range_Path_Param =
            Slash + Key.Platform + Slash_Brace + Key.Platform + Brace_Slash + Key.Start_Date + Slash_Brace
                    + Key.Start_Date + Brace_Slash + Key.End_Date + Slash_Brace + Key.End_Date + Right_Brace;

    protected static final String Limit_And_Offset_Param = Slash + Key.Limit + Slash_Brace + Key.Limit +
            Brace_Slash + Key.Offset + Slash_Brace + Key.Offset + Right_Brace;

    protected static final String Tail_Type_Parm = Slash + Key.Type + Slash_Brace + Key.Type + Right_Brace;

    protected static final String Numbers = "numbers";
    protected static final String Auth_User = "authUser";
    protected static final String Overview = "overview";
    protected static final String Cert_Three = "cert3";
    protected static final String Lic_Percent = "licPercent";
    protected static final String Area_Dist = "areaDist";

    protected static final String Newly_Incr = "newlyIncr";

    protected static final String Line_Chart = "lineChart";
    protected static final String Pie_Chart = "pieChart";
    protected static final String Bar_Chart = "barChart";//条形图，横着的柱状图
    protected static final String Table = "table";
    protected static final String Histogram = "histogram";
    protected static final String China_Map = "chinaMap";
    protected static final String Global = "global";
    protected static final String Bubble_Gradient = "bubbleGradient";


}
