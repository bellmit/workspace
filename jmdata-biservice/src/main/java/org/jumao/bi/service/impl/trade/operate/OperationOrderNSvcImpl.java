package org.jumao.bi.service.impl.trade.operate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.jumao.bi.component.BaseChartBuilder;
import org.jumao.bi.component.BaseDaoCallback;
import org.jumao.bi.component.BaseDataExtract;
import org.jumao.bi.component.ComponentContext;
import org.jumao.bi.component.ParamConst;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.service.trade.operate.IOperationOrderSvc;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/5/3.
 */
public class OperationOrderNSvcImpl extends BaseDataExtract implements IOperationOrderSvc {

	@Autowired
	private BaseDaoCallback baseDaoCallback;

	private BaseChartBuilder baseChartBuilder;
    
    private ComponentContext setCustomContext(String platform, String startDate, String endDate, String[] legendName,String sql){
		ComponentContext context = SpringContextUtil.getBean(ComponentContext.class);
    	List<String> transList = new ArrayList<String>();
    	transList.add("platform");
    	transList.add("startDate");
    	transList.add("endDate");
    	ParamBean param = new ParamBean(platform,startDate,endDate);
    	Map<String,String[]> utilMap = new HashMap();
		utilMap.put(ParamConst.Legend_Name,legendName);
    	super.setContext(param, transList, sql, utilMap,null,null,context);
    	return context;
    }
    @Override
    public Response getOrderType(String platform, String startDate, String endDate) throws Exception{
    	String sql = "with need_deal_tab as (SELECT c.goods_cate_id as itemId,c.goods_cate_name as itemName,"
    			+ "count(DISTINCT o.order_id) as itemValue,"
    			+ "row_number() over(order by count(DISTINCT o.order_id) desc) as rownum"
    			+ " FROM jmbi_trade_order o,jmbi_trade_order_detail d,jmbi_trade_goods g,"
				+ "jmbi_trade_goods_category c"
				+ " WHERE o.order_id = d.order_id AND d.goods_id = g.goods_id"
				+ " AND g.goods_category_grade1_id = c.goods_cate_id"
				+ " and strleft(cast(o.create_time as string),10) between ':startDate'"
				+ " and ':endDate'"
				+ " and o.industry_id = :platform" 
				+ " group by c.goods_cate_id,c.goods_cate_name),"
				+ " result_tab as (select itemId,itemName,itemValue from need_deal_tab where rownum<=5"
				+ " union select 0,'其它',sum(itemValue) from need_deal_tab where rownum>5)"
				+ "select * from result_tab where itemValue is not null order by itemValue desc";
		ComponentContext context = this.setCustomContext(platform, startDate, endDate,null, sql);
    	baseChartBuilder = SpringContextUtil.getBean("pieChartBuilder");
    	return extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }
    @Override
	public Response getOrderPay(String platform, String startDate, String endDate) throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT c.goods_cate_id as itemId,c.goods_cate_name as itemName,"
				+ "count(DISTINCT o.order_id) as itemLValue,round(sum(pay_money)/10000,2) as itemRValue"
    			+ " FROM jmbi_trade_order o,jmbi_trade_order_detail d,jmbi_trade_goods g,"
				+ "jmbi_trade_goods_category c"
				+ " WHERE o.order_id = d.order_id AND d.goods_id = g.goods_id"
				+ " AND g.goods_category_grade1_id = c.goods_cate_id"
				+ " and strleft(cast(o.create_time as string),10) between ':startDate'"
				+ " and ':endDate'"
				+ " and o.industry_id = :platform" 
				+ " group by c.goods_cate_id,c.goods_cate_name";
		String[] legendName = {"数量","金额"};
		ComponentContext context = this.setCustomContext(platform, startDate, endDate, legendName, sql);
		baseChartBuilder = SpringContextUtil.getBean("doubleYLineChartBuilder");
    	return extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
	}
}
