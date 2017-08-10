package org.jumao.bi.service.jrt;

import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.trade.operate.CakeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/11.
 */
@Service
public class KylinImpl implements Kylin {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Response test() {
        CakeResponse response = new CakeResponse();
        String SQL = "select fact.time_key, sum(fact.quantity_ordered), sum(fact.order_dollars), " +
                "sum(fact.cost_dollars) from fact_order as fact  " +
                "where fact.time_key between '2016-05-01' and '2016-05-15' " +
                "group by fact.time_key order by fact.time_key";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(SQL);

        try {
            response.setProportion(list);
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        return Response.ok().entity(response).build();
    }

    @Override
    public Response test1() {
        CakeResponse response = new CakeResponse();
        String SQL = "select count(*) from  fact_order";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(SQL);

        try {
            response.setProportion(list);
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        return Response.ok().entity(response).build();
    }
}
