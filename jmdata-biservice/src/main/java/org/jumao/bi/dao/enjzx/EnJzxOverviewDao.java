package org.jumao.bi.dao.enjzx;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/14.
 */
public interface EnJzxOverviewDao {


    /** 入驻企业 */
    Map<String,String> settledEnterprise(String startDate, String endDate);

    /** 已服务企业 */
    Map<String,String> financingEnterprise(String startDate, String endDate);

    /** 入驻服务机构 */
    Map<String,String> settlingInstitution(String startDate, String endDate);

    /** 上架服务 */
    Map<String,String> shelvesFinancingProducts(String startDate, String endDate);

    /** 项目订单 */
    Map<String,String> financingRequest(String startDate, String endDate);

    /** 合同金额 */
    Map<String,String> contractedMoney(String startDate, String endDate);

    Map<String,String> financingContract(String startDate, String endDate);

    Map<String,String> register(String startDate, String endDate);

    Map<String,String> authentication(String startDate, String endDate);

    Map<String,String> submitDemand(String startDate, String endDate);

    Map<String,String> submitApply(String startDate, String endDate);

    Map<String,String> signSuccess(String startDate, String endDate);

    /** 新增项目订单 */
    Map<String, String> newProjectOrder(String startDate, String endDate);

    /** 市场营销订单  各行业占比 */
    Map<String,String> industryShare(String startDate, String endDate, String flag);

    /** 市场营销订单  各细分服务类型占比 */
    Map<String,String> typeRatio(String startDate, String endDate, String flag);
}
