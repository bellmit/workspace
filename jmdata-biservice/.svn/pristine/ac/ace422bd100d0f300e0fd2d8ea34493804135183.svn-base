package org.jumao.bi.service.impl.enjyt.deal;

import org.jumao.bi.component.BaseChartBuilder;
import org.jumao.bi.component.BaseDaoCallback;
import org.jumao.bi.component.BaseDataExtract;
import org.jumao.bi.component.ComponentContext;
import org.jumao.bi.service.enjyt.deal.EnLogisticsType;
import org.jumao.bi.service.impl.jyt.deal.LogisticsTypeImpl;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by Administrator on 2017/6/16.
 */
public class EnLogisticsTypeImpl extends BaseDataExtract implements EnLogisticsType {
    @Autowired
    private BaseDaoCallback baseDaoCallback;

    private BaseChartBuilder baseChartBuilder;

    private LogisticsTypeImpl logisticsTypeImpl;

    /**
     * 把中文的表名改成英文的
     * @param dateType
     * @param methodType
     * @return
     * @throws Exception
     */
    private String getCustomSql(String dateType, String methodType) throws Exception {
        logisticsTypeImpl = new LogisticsTypeImpl();
        String sql = logisticsTypeImpl.joinSql(dateType,methodType);
        //sql = sql.replaceAll("jmbi_lg","jmbi_en_lg");
        return sql;
    }

    @Override
    public Response getFreightFlow(String platform, String startDate, String endDate, String lineType) throws Exception {
        Map<String,String> colNameMap = new LinkedHashMap();
        String sql = getCustomSql(lineType,"getFreightFlow");
        colNameMap.put("itemName","departure_area");
        colNameMap.put("itemValue1","destination_area");
        colNameMap.put("itemValue2","total_money");
        baseChartBuilder = SpringContextUtil.getBean("flowChartBuilder");
        ComponentContext context = logisticsTypeImpl.setCustomContext(platform, startDate, endDate, null, sql,colNameMap);
        return extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }

    @Override
    public Response getTopLgTypeByRMB(String platform, String startDate, String endDate, String lineType) throws Exception {
        Map<String,String> colNameMap = new LinkedHashMap();
        String sql = getCustomSql(lineType,"getTopLgTypeByRMB");
        colNameMap.put("itemName","发送方向");
        colNameMap.put("itemValue1","产品类型(个)");
        colNameMap.put("itemValue2","重量(T)");
        colNameMap.put("itemValue3","体积(m^2)");
        colNameMap.put("itemValue4","金额(万元)");
        baseChartBuilder = SpringContextUtil.getBean("tableChartBuilder");
        ComponentContext context = logisticsTypeImpl.setCustomContext(platform, startDate, endDate, lineType, sql,colNameMap);
        return extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }

    @Override
    public Response getTopLgTypeByDollar(String platform, String startDate, String endDate, String lineType) throws Exception {
        return null;
    }
}
