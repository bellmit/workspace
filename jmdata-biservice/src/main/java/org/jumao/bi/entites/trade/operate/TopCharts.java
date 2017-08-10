package org.jumao.bi.entites.trade.operate;

import org.jumao.bi.entites.CommonResponse;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/5.
 */
public class TopCharts extends CommonResponse implements Serializable {
    private static final long serialVersionUID = -5244773794123014069L;
    private TopLineChart affiliateCompanyChart;
    private TopLineChart subAccountOpening;


    public TopLineChart getAffiliateCompanyChart() {
        return affiliateCompanyChart;
    }

    public void setAffiliateCompanyChart(TopLineChart affiliateCompanyChart) {
        this.affiliateCompanyChart = affiliateCompanyChart;
    }

    public TopLineChart getSubAccountOpening() {
        return subAccountOpening;
    }

    public void setSubAccountOpening(TopLineChart subAccountOpening) {
        this.subAccountOpening = subAccountOpening;
    }
}
