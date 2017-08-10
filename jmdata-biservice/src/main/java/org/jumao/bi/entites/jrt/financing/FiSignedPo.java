package org.jumao.bi.entites.jrt.financing;

/**
 * 融资 签约 po
 */
public class FiSignedPo {

    private String create_time;
    private String loan_no;//贷款单编号
    private String contract_no;//合同编号
    private String company_name;//企业公司名称
    private String product_name;//产品名称
    private String org_company_name;//机构名称
    private double reply_amount;//批复的金额（万元）
    private int replay_term;//批复的融资期限


    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getLoan_no() {
        return loan_no;
    }

    public void setLoan_no(String loan_no) {
        this.loan_no = loan_no;
    }

    public String getContract_no() {
        return contract_no;
    }

    public void setContract_no(String contract_no) {
        this.contract_no = contract_no;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getOrg_company_name() {
        return org_company_name;
    }

    public void setOrg_company_name(String org_company_name) {
        this.org_company_name = org_company_name;
    }

    public double getReply_amount() {
        return reply_amount;
    }

    public void setReply_amount(double reply_amount) {
        this.reply_amount = reply_amount;
    }

    public int getReplay_term() {
        return replay_term;
    }

    public void setReplay_term(int replay_term) {
        this.replay_term = replay_term;
    }
}
