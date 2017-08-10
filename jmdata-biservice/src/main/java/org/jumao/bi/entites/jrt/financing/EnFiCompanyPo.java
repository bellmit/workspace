package org.jumao.bi.entites.jrt.financing;

/**
 */
public class EnFiCompanyPo {

    private String create_time;//入驻日期
    private String id;//企业编号
    private String company_name;//企业名称
    private double amount;//融资金额，单位美元
    private long apply_counts;//融资签约
    private double reply_amount;//签约金额


    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getApply_counts() {
        return apply_counts;
    }

    public void setApply_counts(long apply_counts) {
        this.apply_counts = apply_counts;
    }

    public double getReply_amount() {
        return reply_amount;
    }

    public void setReply_amount(double reply_amount) {
        this.reply_amount = reply_amount;
    }


}
