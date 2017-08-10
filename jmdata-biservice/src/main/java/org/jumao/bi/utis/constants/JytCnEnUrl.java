package org.jumao.bi.utis.constants;

/**
 * @author chen qian
 */
public class JytCnEnUrl extends BasicUrl {

    //---------------------------------------
    //-----------   以下总览   ---------------
    //---------------------------------------

    /**
     * /v1/jyt/overview/
     */
    public static final String V1_Jyt_Overview = Slash + V1 + Slash + Jyt + Slash + Overview + Slash;

    public static final String V1_Enjyt_Overview = Slash + V1 + Slash + En + Jyt + Slash + Overview + Slash;

    /**
     * e.g. /numbers/itemIds/1-2-3/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String Numbers_Configable = Slash + Numbers +
            Slash + Key.Item_Ids + Slash_Brace + Key.Item_Ids + Right_Brace + Platform_And_Date_Range_Path_Param;


    /**
     * e.g. /numbers/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String En_Numbers_Configable = Slash + Numbers + Platform_And_Date_Range_Path_Param;


    /**
     * e.g. /lineChart/itemId/1/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String Both_Linechart_By_Itemid = Slash + Line_Chart + Slash + Key.Item_Id +
            Slash_Brace + Key.Item_Id + Right_Brace + Platform_And_Date_Range_Path_Param;


    /**
     * e.g. /transportLine/pieChart/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String Both_Transport_Line_Piechart = Slash + Key.Transport_Line +
            Slash + Pie_Chart + Platform_And_Date_Range_Path_Param;


    /**
     * e.g. /requireTop5/barChart/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String Both_Require_Top5_Barchart = Slash + Key.Require_Top5 +
            Slash + Bar_Chart + Platform_And_Date_Range_Path_Param;

    //---------------------------------------
    //-----------   以下注册分析  ---------------
    //---------------------------------------

    /**
     * e.g. /v1/jyt/register/
     */
    public static final String V1_Jyt_Register = Slash + V1 + Slash + Jyt + Slash + Key.Register + Slash;

    /**
     * e.g. /v1/enjyt/register/
     */
    public static final String V1_Enjyt_Register = Slash + V1 + Slash + En + Jyt + Slash + Key.Register + Slash;

    /**
     * e.g. /areaDist/chinaMap/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String Reg_Area_Dist = Slash + Area_Dist + Slash + China_Map +
            Platform_And_Date_Range_Path_Param;

    /**
     * e.g. /areaDist/barChart/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String Reg_Area_Dist_Top10 = Slash + Area_Dist + Slash + Bar_Chart +
            Platform_And_Date_Range_Path_Param;


    /**
     * e.g. /newlyIncr/barChart/platform/1025/startDate/20160501/endDate/20170701
     */
    public static final String Newlyincr_Top5 = Slash + Newly_Incr + Slash + Bar_Chart + Platform_And_Date_Range_Path_Param;


    //---------------------------------------
    //-----------   以下认证分析  ---------------
    //---------------------------------------

    /**
     * e.g. /authUser/lineChart/platform/1025/startDate/20160501/endDate/20170701/type/0
     */
    public static final String Auth_User_Linechart = Slash + Auth_User + Slash + Line_Chart +
            Platform_And_Date_Range_Path_Param + Slash + Key.Type + Slash_Brace + Key.Type + Right_Brace;


    /**
     * e.g. /authUser/history/pieChart/platform/1025
     */
    public static final String Auth_User_History = Slash + Auth_User + Slash + Key.History + Slash + Pie_Chart + Slash +
            Key.Platform + Slash_Brace + Key.Platform + Right_Brace;


    /**
     * e.g. /entrust/areaDist/chinaMap/platform/1025/startDate/20170501/endDate/20170701
     */
    public static final String Entrust_Area_Dist = Slash + Key.Entrust + Slash + Area_Dist + Slash + China_Map + Platform_And_Date_Range_Path_Param;

    /**
     * e.g. /entrust/areaDist/barChart/platform/1025/startDate/20170501/endDate/20170701
     */
    public static final String Entrust_Area_Dist_Top10 = Slash + Key.Entrust + Slash + Area_Dist + Slash + Bar_Chart + Platform_And_Date_Range_Path_Param;

    /**
     * e.g. /supply/areaDist/chinaMap/platform/1025/startDate/20170501/endDate/20170701
     */
    public static final String Supply_Area_Dist = Slash + Key.Supply + Slash + Area_Dist + Slash + China_Map + Platform_And_Date_Range_Path_Param;

    /**
     * e.g. /supply/areaDist/barChart/platform/1025/startDate/20170501/endDate/20170701
     */
    public static final String Supply_Area_Dist_Top10 = Slash + Key.Supply + Slash + Area_Dist + Slash + Bar_Chart + Platform_And_Date_Range_Path_Param;

    /**
     * e.g. /driver/areaDist/chinaMap/platform/1025/startDate/20170501/endDate/20170701
     */
    public static final String Driver_Area_Dist = Slash + Key.Driver + Slash + Area_Dist + Slash + China_Map + Platform_And_Date_Range_Path_Param;

    /**
     * e.g. /driver/areaDist/barChart/platform/1025/startDate/20170501/endDate/20170701
     */
    public static final String Driver_Area_Dist_Top10 = Slash + Key.Driver + Slash + Area_Dist + Slash + Bar_Chart + Platform_And_Date_Range_Path_Param;

}
