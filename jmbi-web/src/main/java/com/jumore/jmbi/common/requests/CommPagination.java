package com.jumore.jmbi.common.requests;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.jumore.dove.plugin.Page;

/**
 * 分页处理
 * @author zhuwei
 *
 */
public class CommPagination {

    // limit
    private static final String LIMIT         = "limit";

    // start
    private static final String START         = "start";

    // 默认分页长度
    private static final String DEFAULT_LIMIT = "10";

    // 默认分页起始页码
    private static final String DEFAULT_START = "0";

    /** 
     * 功能说明：从request中初始化分页参数
     * <p>为空时默认从第0页开始，每页10条数据</p>
     * 
     * @param request
     * @param page 
     * void
     */
    public static void paginationHandle(HttpServletRequest request, Page<?> page) {

        String limit = request.getParameter(LIMIT);
        String start = request.getParameter(START);
        if (StringUtils.isBlank(limit)) {
            limit = DEFAULT_LIMIT;
        }
        if (StringUtils.isBlank(start)) {
            start = DEFAULT_START;
        }
        page.setPageSize(Integer.parseInt(limit));
        page.setCurrentResult(Integer.parseInt(start));
    }

    /** 
     * 功能说明：获取分页长度，如果没有值，默认为10
     * 
     * @param reuqest
     * @return 
     * String
     */
    public static Integer getLimit(HttpServletRequest request) {
        String limit = request.getParameter(LIMIT);
        if (StringUtils.isBlank(limit)) {
            limit = DEFAULT_LIMIT;
        }
        return Integer.parseInt(limit);
    }

    /** 
     * 功能说明：获取分页起始页码，如果没有值，默认为0
     * 
     * @param reuqest
     * @return 
     * String
     */
    public static Integer getStart(HttpServletRequest request) {
        String start = request.getParameter(START);
        if (StringUtils.isBlank(start)) {
            start = DEFAULT_START;
        }
        return Integer.parseInt(start);
    }

}
