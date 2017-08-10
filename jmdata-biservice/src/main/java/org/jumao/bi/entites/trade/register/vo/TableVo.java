package org.jumao.bi.entites.trade.register.vo;

/**
 * 返回给前端的字段和数据库中不同
 */
public class TableVo {

    private String date;
    private String source;
    private String amount;
    private String rate;

    /**
     * @param date
     * @param source
     * @param amount
     * @param rate
     */
    public TableVo(String date, String source, String amount, String rate) {
        this.date = date;
        this.source = source;
        this.amount = amount;
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
