package org.jumao.bi.service.impl.trade.operate;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.jumao.bi.component.BaseChartBuilder;
import org.jumao.bi.component.BaseDaoCallback;
import org.jumao.bi.component.BaseDataExtract;
import org.jumao.bi.component.ComponentContext;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.service.trade.operate.IOperationGoodsSvc;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/5/8.
 */
public class OperationGoodsNSvcImpl extends BaseDataExtract implements IOperationGoodsSvc {
	
	@Autowired
	private BaseDaoCallback baseDaoCallback;
	
	private BaseChartBuilder baseChartBuilder;
	
	@Override
    public Response getGoodsCate(String platform, String startDate, String endDate) throws Exception{
		String sql = "SELECT c.goods_cate_id as itemId,"
    			+ "c.goods_cate_name as itemName,"
    			+ "count(DISTINCT goods_id) as itemValue"
    			+ " FROM jmbi_trade_goods g,jmbi_trade_goods_category c"
				+ " WHERE g.goods_category_grade1_id = c.goods_cate_id"
				+ " and strleft(cast(g.create_time as string),10) between ':startDate'"
				+ " and ':endDate'"
				+ " and g.industry_id = :platform"
				+ " group by c.goods_cate_id,c.goods_cate_name";
		List<String> transList = new ArrayList<String>();
    	transList.add("platform");
    	transList.add("startDate");
    	transList.add("endDate");
    	ParamBean param = new ParamBean(platform,startDate,endDate);
		ComponentContext context = SpringContextUtil.getBean(ComponentContext.class);
    	super.setContext(param, transList, sql, null,null,null,context);
    	baseChartBuilder = SpringContextUtil.getBean("pieChartBuilder");
    	return extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }

}
