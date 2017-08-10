package org.jumao.bi.dao.trade.operate;

import java.util.List;

import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.entites.operate.ConvFunnelInfo;


public interface IOperationConvFunnelDao {
	
	List<ConvFunnelInfo> getConvFunnel(ParamBean param) throws Exception;

}
