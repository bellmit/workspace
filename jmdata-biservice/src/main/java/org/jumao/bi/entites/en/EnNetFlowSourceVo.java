package org.jumao.bi.entites.en;

/**
 */
public class EnNetFlowSourceVo {

    private String type;
    private int pv;
    private int uv;
    private String bounce_rate;

    public EnNetFlowSourceVo() {
    }

    public EnNetFlowSourceVo(String type, int pv, int uv, String bounce_rate) {
        this.type = type;
        this.pv = pv;
        this.uv = uv;
        this.bounce_rate = bounce_rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public String getBounce_rate() {
        return bounce_rate;
    }

    public void setBounce_rate(String bounce_rate) {
        this.bounce_rate = bounce_rate;
    }
}
