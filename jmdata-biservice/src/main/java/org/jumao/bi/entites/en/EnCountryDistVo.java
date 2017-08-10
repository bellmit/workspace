package org.jumao.bi.entites.en;

/**
 */
public class EnCountryDistVo {

    private String country;
    private Double pv;
    private String pv_percent;
    private Double uv;
    private String uv_percent;
    private String bounce_rate;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getPv() {
        return pv;
    }

    public void setPv(Double pv) {
        this.pv = pv;
    }

    public String getPv_percent() {
        return pv_percent;
    }

    public void setPv_percent(String pv_percent) {
        this.pv_percent = pv_percent;
    }

    public Double getUv() {
        return uv;
    }

    public void setUv(Double uv) {
        this.uv = uv;
    }

    public String getUv_percent() {
        return uv_percent;
    }

    public void setUv_percent(String uv_percent) {
        this.uv_percent = uv_percent;
    }

    public String getBounce_rate() {
        return bounce_rate;
    }

    public void setBounce_rate(String bounce_rate) {
        this.bounce_rate = bounce_rate;
    }
}
