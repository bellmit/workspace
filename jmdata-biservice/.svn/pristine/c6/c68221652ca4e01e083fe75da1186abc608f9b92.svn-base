package org.jumao.bi.dao.trade.operate.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jumao.bi.dao.trade.operate.IOperationConvFunnelDao;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.entites.operate.ConvFunnelInfo;
import org.jumao.commons.frameworks.jmframework.hbaseutil.dao.IHBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("operationConvFunnelDao")
public class OperationConvFunnelDaoImpl  implements IOperationConvFunnelDao {

    @Autowired
    IHBaseDao hbasedao;
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    private static final Integer Payed_State = 40;//订单已支付
    private static final Integer Ship_State = 50;//订单已发货
    private static final Integer Finish_State = 70;//订单完成
    /**
     * 获取漏斗转换率
     */
    public List<ConvFunnelInfo> getConvFunnel(ParamBean param) throws Exception{
		// TODO Auto-generated method stub
    	String wholeSql = "select count(distinct company_id)"
    			+ " from jmbi_user_traces t"
    			+ " where strleft(t.login_time,8)  between '" + param.getStartDate()
    			+ "' and '" + param.getEndDate()
    			+ "' and t.biz_code = '" + param.getPlatform()
    			+ "' and t.company_id<>'0'";
    	String listSql = "";
    	String detailSql = "";
    	String cartSql = "select count(DISTINCT u.comp_id) from jmbi_trade_cart u"
    			+ " where cast(u.comp_id as string) in ("
    			+ " select distinct company_id"
    			+ " from jmbi_user_traces t"
    			+ " where strleft(t.login_time,8)  between '" + param.getStartDate()
    			+ "' and '" + param.getEndDate()
    			+ "' and t.biz_code = '" + param.getPlatform() 
    			+ "' and t.company_id<>'0')";
//    	String cartSql = "";
    	String orderCreatedSql = "with end_login_time_tab as ( "
    			+"  select company_id,min(login_time) as end_login_time"
    			+"  from jmbi_user_traces"
    			+"  where strleft(login_time,8) >= '" + param.getEndDate() 
    			+ "' and biz_code = '" + param.getPlatform()
    			+ "' and company_id<>'0' group by company_id"
    			+"), "
    			+"begin_login_time_tab as ("
    			+"  select company_id,min(login_time) as begin_login_time"
    			+"  from jmbi_user_traces"
    			+"  where strleft(login_time,8) >= '" + param.getStartDate() + "' and login_time <= '" + param.getEndDate() 
    			+ "' and biz_code = '" + param.getPlatform()
    			+ "' and company_id<>'0' group by company_id "
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
    			+" where strleft(login_time,8)between '" + param.getStartDate() + "' and '" + param.getEndDate()
    			+ "' and biz_code = '" + param.getPlatform()
    			+ "' and company_id<>'0')";
    	
    	Integer wholeUV = jdbcTemplate.queryForObject(wholeSql, Integer.class);
//    	Integer listUV = jdbcTemplate.queryForObject(listSql, Integer.class);
//    	Integer detailUV = jdbcTemplate.queryForObject(detailSql, Integer.class);
//    	Integer cartUV = jdbcTemplate.queryForObject(cartSql, Integer.class);
//    	Integer wholeUV = 0;
    	Integer listUV = 0;
    	Integer detailUV = 0;
//    	Integer cartUV = 0;
        
        Set<Long> orderCreatedSet = new HashSet<Long>();
        Set<Long> orderPayedSet = new HashSet<Long>();
        Set<Long> shipSet = new HashSet<Long>();
        Set<Long> receiptSet = new HashSet<Long>();
        
        List<Map<String, Object>> orderCreatedRows = jdbcTemplate.queryForList(orderCreatedSql);
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
        List<ConvFunnelInfo> convFunnelList = new ArrayList<ConvFunnelInfo>();
        collectItem("整站UV",wholeUV,convFunnelList);
        //collectItem("列表UV",listUV,convFunnelList);
        //collectItem("详情UV",detailUV,convFunnelList);
        //collectItem("购物车UV",cartUV,convFunnelList);
        collectItem("下单UV",orderCreatedSet.size(),convFunnelList);
        collectItem("订单支付UV",orderPayedSet.size(),convFunnelList);
        collectItem("确认发货UV",shipSet.size(),convFunnelList);
        collectItem("确认收货UV",receiptSet.size(),convFunnelList);
        
        return convFunnelList;
	}
    
    private void collectItem(String itemName,Integer itemValue,List<ConvFunnelInfo> list){
    	ConvFunnelInfo info = new ConvFunnelInfo();
    	info.setItemName(itemName);
    	info.setItemValue(itemValue);
    	list.add(info);
    }

}
