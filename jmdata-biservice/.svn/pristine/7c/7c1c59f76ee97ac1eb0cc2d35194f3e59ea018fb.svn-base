package org.jumao.bi.utis.enums;

import org.jumao.bi.utis.StringUtils;

/**
 */
public enum GaFields {

    BOUNCE_RATE("bounceRate", "bounce_rate"), AVG_SESSION_DURATION("avgSessionDuration", "avg_session_dur"),
    NEW_USERS("newUsers", "new_users"), LANDING_PAGE_PATH("landingPagePath", "landing_page_path"),
    BASIC_VISIT("basicVisit", "basic_visit"), SOURCE_MEDIUM("sourceMedium", "source_medium"),
    ENTRANCES_PAGE("entrancesPage", "entrances_page"), ALL_HOST_NAME("allHostName", "all_host_name");


    public static String getFieldByOriginStr(String originStr) throws Exception {
        if (BOUNCE_RATE.getOrgin().equals(originStr)) {
            return BOUNCE_RATE.getForMysql();

        } else if (AVG_SESSION_DURATION.getOrgin().equals(originStr)) {
            return AVG_SESSION_DURATION.getForMysql();

        } else if (NEW_USERS.getOrgin().equals(originStr)) {
            return NEW_USERS.getForMysql();

        } else if (LANDING_PAGE_PATH.getOrgin().equals(originStr)) {
            return LANDING_PAGE_PATH.getForMysql();

        } else if (BASIC_VISIT.getOrgin().equals(originStr)) {
            return BASIC_VISIT.getForMysql();

        } else if (SOURCE_MEDIUM.getOrgin().equals(originStr)) {
            return SOURCE_MEDIUM.getForMysql();

        } else if (ENTRANCES_PAGE.getOrgin().equals(originStr)) {
            return ENTRANCES_PAGE.getForMysql();

        } else if (ALL_HOST_NAME.getOrgin().equals(originStr)) {
            return ALL_HOST_NAME.getForMysql();
        }
        return originStr;
//        throw new Exception(StringUtils.joinStr("don't find field to adjust originStr:", originStr));
    }


    private String orgin;
    private String forMysql;


    GaFields(String orgin, String forMysql) {
        this.orgin = orgin;
        this.forMysql = forMysql;
    }

    public String getOrgin() {
        return orgin;
    }

    public void setOrgin(String orgin) {
        this.orgin = orgin;
    }

    public String getForMysql() {
        return forMysql;
    }

    public void setForMysql(String forMysql) {
        this.forMysql = forMysql;
    }
}
