package org.jumao.bi.utis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public class ImpalaJDBCDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void getTotal() {
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from jmbi_trade_shop_operate");
        List<Map<String, Object>> map = jdbcTemplate.queryForList("select 'memberTotal',count(*) from jmbi_uc_jumore_user\n" +
                "union all\n" +
                "SELECT 'OpenStoreTotal',COUNT(*) FROM jmbi_trade_shop_operate WHERE operate_type=0\n" +
                "union all\n" +
                "select 'ItemTotal', count(*) from  jmbi_trade_goods\n" +
                "union all\n" +
                "select 'OrderTotal', count(*) from jmbi_trade_order\n" +
                "union all\n" +
                "select 'PayTotal', count(*) from jmbi_ep_trade where status in (2,3,4)\n" +
                "union all\n" +
                "select 'TranTotal', sum(pay_amt) from jmbi_ep_trade  where status in (2,3,4)");
//        return jdbcTemplate.queryForObject("select * from jmbi_trade_shop_operate",long.class);
        System.out.println();
    }


    public static void main(String[] args) {
        /*ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext-bigdata.xml");
        ImpalaJDBCDAO impalaJDBCDAO = ctx.getBean(ImpalaJDBCDAO.class);
        impalaJDBCDAO.getTotal();

        System.out.println();*/

    }

}