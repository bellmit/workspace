package com.jumore.jmbi.common.requests;


/** 
* 分页方法响应格式
* @author: panweiqiang
* @since: 2016年7月1日  上午9:59:45
* @history:
*/
public class CommPageResponse extends CommResponse {

    /**
     * 总记录数
     */
    private int auctionTotal;

    public CommPageResponse(Object data, int auctionTotal) {
        super(data);
        this.auctionTotal = auctionTotal;
    }

    public CommPageResponse(StatusCodeEnum statusCode, String message) {
        super(statusCode, message);
    }

    public CommPageResponse(Object data, int auctionTotal, StatusCodeEnum statusCode, String message) {
        super(data, statusCode, message);
        this.auctionTotal = auctionTotal;
    }

    public int getAuctionTotal() {
        return auctionTotal;
    }

    public void setAuctionTotal(int auctionTotal) {
        this.auctionTotal = auctionTotal;
    }
}
