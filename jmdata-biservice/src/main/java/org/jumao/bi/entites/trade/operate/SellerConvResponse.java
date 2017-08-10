package org.jumao.bi.entites.trade.operate;

import org.jumao.bi.entites.CommonResponse;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/5.
 * 卖家转化率返回体
 */
public class SellerConvResponse extends CommonResponse implements Serializable {
    private static final long serialVersionUID = -5244773794123014069L;
    private String[] xAxisData;
    private LineChart authConv;  //  认证转化率
    private LineChart OpenStoreConv;  //  店铺开通转化率
    private LineChart ItemConv;  //  发布商品转化率
    private LineChart DealConv;  //  成交转化率

    public String[] getxAxisData() {
        return xAxisData;
    }

    public void setxAxisData(String[] xAxisData) {
        this.xAxisData = xAxisData;
    }

    public LineChart getAuthConv() {
        return authConv;
    }

    public void setAuthConv(LineChart authConv) {
        this.authConv = authConv;
    }

    public LineChart getOpenStoreConv() {
        return OpenStoreConv;
    }

    public void setOpenStoreConv(LineChart openStoreConv) {
        OpenStoreConv = openStoreConv;
    }

    public LineChart getItemConv() {
        return ItemConv;
    }

    public void setItemConv(LineChart itemConv) {
        ItemConv = itemConv;
    }

    public LineChart getDealConv() {
        return DealConv;
    }

    public void setDealConv(LineChart dealConv) {
        DealConv = dealConv;
    }
}
