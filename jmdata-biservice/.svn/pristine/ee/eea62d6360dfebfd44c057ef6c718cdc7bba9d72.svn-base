package org.jumao.bi.dao.trade.operate.impl;

import java.util.List;

import org.jumao.bi.dao.trade.operate.IOperationGoodsDao;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.entites.operate.GoodsInfo;
import org.jumao.commons.frameworks.jmframework.hbaseutil.dao.IHBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository("operationGoodsDao")
public class OperationGoodsDaoImpl  implements IOperationGoodsDao {

    @Autowired
    IHBaseDao hbasedao;
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    /**
     * 获取商品分类
     */
    public List<GoodsInfo> getGoodsCate(ParamBean param) throws Exception{
		// TODO Auto-generated method stub
    	String sql = "SELECT c.goods_cate_id as goodscateid,"
    			+ "c.goods_cate_name as goodscatename,"
    			+ "count(DISTINCT goods_id) as goodscatecnt"
    			+ " FROM jmbi_trade_goods g,jmbi_trade_goods_category c"
				+ " WHERE g.goods_category_grade1_id = c.goods_cate_id"
				+ " and strleft(cast(g.create_time as string),10) between '" + param.getStartDate()
				+ "' and '" + param.getEndDate()
				+ "' and g.industry_id = " + Integer.valueOf(param.getPlatform())
				+ " group by c.goods_cate_id,c.goods_cate_name;";
    	RowMapper<GoodsInfo> rowMapper = new BeanPropertyRowMapper<GoodsInfo>(GoodsInfo.class);
        List<GoodsInfo> goodsInfos = jdbcTemplate.query(sql, rowMapper);
        
        return goodsInfos;
	}

}
