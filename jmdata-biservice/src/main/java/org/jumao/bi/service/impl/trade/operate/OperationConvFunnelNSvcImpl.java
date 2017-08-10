package org.jumao.bi.service.impl.trade.operate;

import org.jumao.bi.component.BaseChartBuilder;
import org.jumao.bi.component.BaseDaoCallbackImpl;
import org.jumao.bi.component.BaseDataExtract;
import org.jumao.bi.component.BaseInfo;
import org.jumao.bi.component.ComponentContext;
import org.jumao.bi.dao.IBaseDao;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.service.trade.operate.IOperationConvFunnelSvc;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/5/8.
 */
public class OperationConvFunnelNSvcImpl extends BaseDataExtract implements IOperationConvFunnelSvc {

	@Autowired
	private BaseDaoCallbackImpl baseDaoCallback;
	
	private BaseChartBuilder baseChartBuilder;
	
	private static final Integer Payed_State = 40;//订单已支付
    private static final Integer Ship_State = 50;//订单已发货
    private static final Integer Finish_State = 70;//订单完成
    private static final String Whole_Uv_Key = "整站UV";
    /*private static final String List_Uv_Key = "列表UV";
    private static final String Detail_Uv_Key = "详情UV";
    private static final String Cart_Uv_Key = "购物车UV";*/
    private static final String Order_Create_Uv_Key = "下单UV";
    private static final String Payed_Uv_Key = "订单支付UV";
    private static final String Ship_Uv_Key = "确认发货UV";
    private static final String Receipt_Uv_Key = "确认收货UV";
    @Override
    public Response getConvFunnel(String platform, String startDate, String endDate) throws Exception{
    	final Map<String,String> sqlMap = new HashMap<String,String>();
    	String wholeSql = "select count(distinct company_id)"
    			+ " from jmbi_user_traces t"
    			+ " where strleft(t.login_time,8)  between ':startDate'"
    			+ " and ':endDate'"
    			+ " and t.biz_code = ':platform'"
    			+ " and t.company_id<>'0'";
    	/*String listSql = "";
    	String detailSql = "";
    	String cartSql = "";*/
    	/*String orderCreatedSql = "select count(DISTINCT c.comp_id)"
    			+ " from jmbi_trade_order o ,jmbi_trade_company c ,jmbi_trade_center_user u"
    			+ "  where o.member_id=u.user_id and u.company_id=c.comp_id "
    			+ " and cast(u.company_id  as string) in ("
    			+ " select distinct company_id"
    			+ " from jmbi_user_traces t"
    			+ " where strleft(t.login_time,8)  between ':startDate'"
    			+ " and ':endDate'"
    			+ " and t.biz_code = ':platform'"
    			+ " and t.company_id<>'0')";*/
    	String orderCreatedSql = "with end_login_time_tab as ( "
    			+"  select company_id,min(login_time) as end_login_time"
    			+"  from jmbi_user_traces"
    			+"  where strleft(login_time,8) >= ':endDate'"
    			+ " and biz_code = ':platform' "
    			+ " and company_id<>'0' group by company_id"
    			+"), "
    			+"begin_login_time_tab as ("
    			+"  select company_id,min(login_time) as begin_login_time"
    			+"  from jmbi_user_traces"
    			+"  where strleft(login_time,8) >= ':startDate' and login_time <= ':endDate'"
    			+ " and biz_code = ':platform'"
    			+ " and company_id<>'0' group by company_id "
    			+"), "
    			+"login_time_tab as ("
    			+"  select a.company_id,a.begin_login_time,nvl(b.end_login_time,'30000101000000') end_login_time "
    			+"  from begin_login_time_tab a"
    			+"  left outer join end_login_time_tab b "
    			+"  on a.company_id = b.company_id "
    			+")"
    			+"select c.comp_id,o.order_state"
    			+" from jmbi_trade_order o ,jmbi_trade_company c ,jmbi_trade_center_user u,login_time_tab t"
    			+" where o.member_id=u.user_id and u.company_id=c.comp_id"
    			+" and cast(u.company_id as string) = t.company_id"
    			+" and unix_timestamp(o.create_time,'yyyy-MM-dd HH:mm:ss')>unix_timestamp(t.begin_login_time,'yyyyMMddHHmmss')"
    			+" and unix_timestamp(o.create_time,'yyyy-MM-dd HH:mm:ss')<unix_timestamp(t.end_login_time,'yyyyMMddHHmmss')"
    			+" and cast(u.company_id as string)in"
    			+"( select distinct company_id from jmbi_user_traces"
    			+" where strleft(login_time,8)between ':startDate' and ':endDate'"
    			+ " and biz_code = ':platform'"
    			+ " and company_id<>'0')";
    	
    	sqlMap.put(Whole_Uv_Key, wholeSql);
    	/*sqlMap.put(List_Uv_Key, listSql);
    	sqlMap.put(Detail_Uv_Key, detailSql);
    	sqlMap.put(Cart_Uv_Key, cartSql);*/
    	sqlMap.put(Order_Create_Uv_Key, orderCreatedSql);
    	
		List<String> transList = new ArrayList<String>();
    	ParamBean param = new ParamBean(platform,startDate,endDate);
		ComponentContext context = SpringContextUtil.getBean(ComponentContext.class);
    	super.setContext(param, transList, null, null,null,null,context);
    	baseChartBuilder = SpringContextUtil.getBean("funnelChartBuilder");
    	//return super.extractDataAndBuildChartBySqlMap(baseDaoCallback, sqlMap, baseChartBuilder);
    	return super.extractDataAndBuildChartBySqlMap(new BaseDaoCallbackImpl(){
    		
			@Override
			public List<BaseInfo> getListBySqlMap(IBaseDao baseDao,
					Map<String, String> sqlMap) throws Exception {
				// TODO Auto-generated method stub
				return generateResultList(baseDao, sqlMap);
			}
    		
    	}, sqlMap, baseChartBuilder,context);
    	       
    }
    /**
     * 生成结果列表
     * @param baseDao
     * @param sqlMap
     * @return
     * @throws Exception
     */
    public List<BaseInfo> generateResultList(IBaseDao baseDao, Map<String, String> sqlMap) throws Exception{
		// 得到整站uv
		Integer wholeUV = (Integer) baseDao.getObjectBySql(sqlMap.get(Whole_Uv_Key), Integer.class);
		// 得到其他uv
		Set<Long> orderCreatedSet = new HashSet<Long>();
        Set<Long> orderPayedSet = new HashSet<Long>();
        Set<Long> shipSet = new HashSet<Long>();
        Set<Long> receiptSet = new HashSet<Long>();
        List<Map<String, Object>> orderCreatedRows = baseDao.getUtilListBySql(sqlMap.get(Order_Create_Uv_Key));
        for (int i=0;i<orderCreatedRows.size();i++){
        	Map<String, Object> map = (Map<String, Object>)orderCreatedRows.get(i);
        	Long compId = (Long)map.get("comp_id");
        	Integer orderState = (Integer)map.get("order_state");
        	if (orderState==Finish_State) {        		
        		orderCreatedSet.add(compId);
        		orderPayedSet.add(compId);
        		shipSet.add(compId);
        		receiptSet.add(compId);
        	}else if (orderState==Ship_State) {
        		orderCreatedSet.add(compId);
        		orderPayedSet.add(compId);
        		shipSet.add(compId);
        	}else if (orderState==Payed_State) {
        		orderCreatedSet.add(compId);
        		orderPayedSet.add(compId);
        	}else {
        		orderCreatedSet.add(compId);
        	}
       	
        }
        //收集各种uv到list里
        List<BaseInfo> convFunnelList = new ArrayList<BaseInfo>();
        baseDaoCallback.collectItem(Whole_Uv_Key,wholeUV.toString(),convFunnelList);
        //collectItem(List_Uv_Key,listUV,convFunnelList);
        //collectItem(Detail_Uv_Key,detailUV,convFunnelList);
        //collectItem(Cart_Uv_Key,cartUV,convFunnelList);
        baseDaoCallback.collectItem(Order_Create_Uv_Key,String.valueOf(orderCreatedSet.size()),convFunnelList);
        baseDaoCallback.collectItem(Payed_Uv_Key,String.valueOf(orderPayedSet.size()),convFunnelList);
        baseDaoCallback.collectItem(Ship_Uv_Key,String.valueOf(shipSet.size()),convFunnelList);
        baseDaoCallback.collectItem(Receipt_Uv_Key,String.valueOf(receiptSet.size()),convFunnelList);
		return convFunnelList;
	}

}
