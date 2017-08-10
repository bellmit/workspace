package org.jumao.bi.dao.jrt;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/14.
 */
public interface JrtOverviewDao {


    /**
     * 支付开通历史累计值
     */
    Map<String,String> settledEnterprise(String startDate, String endDate);

    Map<String,String> financingEnterprise(String startDate, String endDate);

    Map<String,String> settlingInstitution(String startDate, String endDate);

    Map<String,String> shelvesFinancingProducts(String startDate, String endDate);

    Map<String,String> financingRequest(String startDate, String endDate);

    Map<String,String> contractedMoney(String startDate, String endDate);

    Map<String,String> financingContract(String startDate, String endDate);

    Map<String,String> register(String startDate, String endDate);

    Map<String,String> authentication(String startDate, String endDate);

    Map<String,String> submitDemand(String startDate, String endDate);

    Map<String,String> submitApply(String startDate, String endDate);

    Map<String,String> signSuccess(String startDate, String endDate);
}
