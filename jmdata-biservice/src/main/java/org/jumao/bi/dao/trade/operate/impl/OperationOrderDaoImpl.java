package org.jumao.bi.dao.trade.operate.impl;

import java.util.List;



import org.jumao.bi.dao.trade.operate.IOperationOrderDao;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.entites.operate.OrderInfo;
import org.jumao.commons.frameworks.jmframework.hbaseutil.dao.IHBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository("operationOrderDao")
public class OperationOrderDaoImpl  implements IOperationOrderDao {

    @Autowired
    IHBaseDao hbasedao;
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    /**
     * 获取订单类型
     */
    public List<OrderInfo> getOrderType(ParamBean param) throws Exception{
		// TODO Auto-generated method stub
    	String sql = "SELECT c.goods_cate_id as goodscateid,c.goods_cate_name as goodscatename,"
    			+ "count(DISTINCT o.order_id) as goodscatecnt"
    			+ " FROM jmbi_trade_order o,jmbi_trade_order_detail d,jmbi_trade_goods g,"
				+ "jmbi_trade_goods_category c"
				+ " WHERE o.order_id = d.order_id AND d.goods_id = g.goods_id"
				+ " AND g.goods_category_grade1_id = c.goods_cate_id"
				+ " and strleft(cast(o.create_time as string),10) between '" + param.getStartDate()
				+ "' and '" + param.getEndDate()
				+ "' and o.industry_id = " + Integer.valueOf(param.getPlatform())
				+ " group by c.goods_cate_id,c.goods_cate_name";
    	RowMapper<OrderInfo> rowMapper = new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class);
        List<OrderInfo> orderInfos = jdbcTemplate.query(sql, rowMapper);
        
        return orderInfos;
	}
    /**
     * 获取订单金额
     */
	public List<OrderInfo> getOrderPay(ParamBean param) throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT c.goods_cate_id as goodscateid,c.goods_cate_name as goodscatename,"
					+ "count(DISTINCT o.order_id) as goodscatecnt,sum(pay_money) as goodscatepay"
	    			+ " FROM jmbi_trade_order o,jmbi_trade_order_detail d,jmbi_trade_goods g,"
					+ "jmbi_trade_goods_category c"
					+ " WHERE o.order_id = d.order_id AND d.goods_id = g.goods_id"
					+ " AND g.goods_category_grade1_id = c.goods_cate_id"
					+ " and strleft(cast(o.create_time as string),10) between '" + param.getStartDate()
					+ "' and '" + param.getEndDate()
					+ "' and o.industry_id = " + Integer.valueOf(param.getPlatform())
					+ " group by c.goods_cate_id,c.goods_cate_name";
		
    	RowMapper<OrderInfo> rowMapper = new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class);
        List<OrderInfo> orderInfos = jdbcTemplate.query(sql, rowMapper);
        
        return orderInfos;
	}

}
