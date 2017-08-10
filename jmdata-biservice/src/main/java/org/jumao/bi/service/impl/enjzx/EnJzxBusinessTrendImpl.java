package org.jumao.bi.service.impl.enjzx;

import org.jumao.bi.component.BaseDaoCallback;
import org.jumao.bi.component.ComponentContext;
import org.jumao.bi.component.DataExtractAdapt;
import org.jumao.bi.component.ParamConst;
import org.jumao.bi.component.builder.LineCBD;
import org.jumao.bi.service.enjzx.EnJzxBusinessTrendSvc;
import org.jumao.bi.service.impl.jzx.JzxBusinessTrendImpl;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/13.
 */
public class EnJzxBusinessTrendImpl extends DataExtractAdapt implements EnJzxBusinessTrendSvc {
    @Autowired
    private BaseDaoCallback baseDaoCallback;
    @Autowired
    private LineCBD baseChartBuilder;

    private JzxBusinessTrendImpl jzxBusinessTrendImpl;

    /**
     * 把中文的表名改成英文的
     * @param dateType
     * @param methodType
     * @return
     * @throws Exception
     */
    private String getCustomSql(String dateType, String methodType) throws Exception {
        jzxBusinessTrendImpl = new JzxBusinessTrendImpl();
        String sql = jzxBusinessTrendImpl.joinSql(dateType,methodType);
        sql = sql.replaceAll("jzx_","en_jzx_")
                .replaceAll("jmbi_uc_jumore_user","jmbi_en_uc_jumore_user_en")
                .replaceAll("pay_amt","budget_moeny")
                .replaceAll("1019","101901");
        return sql;
    }

    @Override
    public void dealOtherResultData(List<Map<String, Object>> dataInfos, ComponentContext context) {
        jzxBusinessTrendImpl.dealOtherResultData(dataInfos,context);
    }

    @Override
    public Response getIntentionOrder(String platform, String startDate, String endDate, String dateType) throws Exception {
        // baseChartBuilder = SpringContextUtil.getBean("lineCBD");
        String sql = getCustomSql(dateType,"getIntentionOrder");
        ComponentContext context = jzxBusinessTrendImpl.setCustomContext(platform, startDate, endDate, ParamConst.Unit_Bi, "意向单", dateType, sql);
        return extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }

    @Override
    public Response getProjectOrder(String platform, String startDate, String endDate, String dateType) throws Exception {
        String sql = getCustomSql(dateType,"getProjectOrder");
        ComponentContext context = jzxBusinessTrendImpl.setCustomContext(platform, startDate, endDate, ParamConst.Unit_Bi, "项目订单", dateType, sql);
        return extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }

    @Override
    public Response getContractAmount(String platform, String startDate, String endDate, String dateType) throws Exception {
        String sql = getCustomSql(dateType,"getContractAmount");
        ComponentContext context = jzxBusinessTrendImpl.setCustomContext(platform, startDate, endDate, ParamConst.Unit_Million_Yuan, "合同金额", dateType, sql);
        return extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }

    @Override
    public Response getNewUsers(String platform, String startDate, String endDate, String dateType) throws Exception {
        String sql = getCustomSql(dateType,"getNewUsers");
        ComponentContext context = jzxBusinessTrendImpl.setCustomContext(platform, startDate, endDate, ParamConst.Unit_Bi, "新增用户数", dateType, sql);
        return extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }
}
