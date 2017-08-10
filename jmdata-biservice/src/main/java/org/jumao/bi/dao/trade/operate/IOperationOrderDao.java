package org.jumao.bi.dao.trade.operate;

import java.util.List;

import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.entites.operate.OrderInfo;


public interface IOperationOrderDao {
	
	List<OrderInfo> getOrderType(ParamBean param) throws Exception;
	
	List<OrderInfo> getOrderPay(ParamBean param) throws Exception;

}
