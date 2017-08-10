package org.jumao.bi.entites.en;

/**
 */
public class EnBasicVisitPo {

    private String name;//is platform en name
    private Integer pv;
    private Integer sessions;
    private Integer uv;
    private Double avg_session_dur;
    private String bounce_rate;//parsed to xx%
    private Double bounce_rate_d;//get from DB



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getSessions() {
        return sessions;
    }

    public void setSessions(Integer sessions) {
        this.sessions = sessions;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public Double getAvg_session_dur() {
        return avg_session_dur;
    }

    public void setAvg_session_dur(Double avg_session_dur) {
        this.avg_session_dur = avg_session_dur;
    }

    public Double getBounce_rate_d() {
        return bounce_rate_d;
    }

    public void setBounce_rate_d(Double bounce_rate_d) {
        this.bounce_rate_d = bounce_rate_d;
    }

    public String getBounce_rate() {
        return bounce_rate;
    }

    public void setBounce_rate(String bounce_rate) {
        this.bounce_rate = bounce_rate;
    }
}
