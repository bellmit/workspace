package org.jumao.bi.entites.baidu;

import java.io.Serializable;
import java.text.DecimalFormat;

public class NOVisitorsChart implements Serializable {
	DecimalFormat df = new DecimalFormat("######0.00");

	private static final long serialVersionUID = -3260813461139072284L;
	/**
	 * 老访客pv
	 */
	private long oVisiotsPVCount = 0;
	/**
	 * 新访客pv
	 */
	private long nVisiotsPVCount = 0;
	/**
	 * 老访客uv
	 */
	private long oVisiotsUVCount = 0;
	/**
	 * 新访客uv
	 */
	private long nVisiotsUVCount = 0;
	/**
	 * 老访客占比
	 */
	private String oUVRatio = "";
	/**
	 * 新访客占比
	 */
	private String nUVRatio = "";

	/**
	 * 老访客访问时长
	 */
	private double oVisitTime = 0.0;
	/**
	 * 新访客访问时长
	 */
	private double nVisitTime = 0.0;

	/**
	 * @return 老访客占比
	 */
	public String getoUVRatio() {
		if ((oVisiotsUVCount + nVisiotsUVCount) > 0) {
			String a = df.format(oVisiotsUVCount * 1.0	/ (oVisiotsUVCount + nVisiotsUVCount));
			 return a;
		}
		return "0";
	}

	/**
	 * @return 新访客占比
	 */
	public String getnUVRatio() {
		if ((oVisiotsUVCount + nVisiotsUVCount) > 0) {
			String a = df.format(nVisiotsUVCount * 1.0/ (oVisiotsUVCount + nVisiotsUVCount));
			return a;
		}
		return "0";
	}

	public long getoVisiotsPVCount() {
		return oVisiotsPVCount;
	}

	public void setoVisiotsPVCount(long oVisiotsPVCount) {
		this.oVisiotsPVCount = oVisiotsPVCount;
	}

	public long getnVisiotsPVCount() {
		return nVisiotsPVCount;
	}

	public void setnVisiotsPVCount(long nVisiotsPVCount) {
		this.nVisiotsPVCount = nVisiotsPVCount;
	}

	public long getoVisiotsUVCount() {
		return oVisiotsUVCount;
	}

	public void setoVisiotsUVCount(long oVisiotsUVCount) {
		this.oVisiotsUVCount = oVisiotsUVCount;
	}

	public long getnVisiotsUVCount() {
		return nVisiotsUVCount;
	}

	public void setnVisiotsUVCount(long nVisiotsUVCount) {
		this.nVisiotsUVCount = nVisiotsUVCount;
	}

	public String getoVisitTime() {
		return df.format(oVisitTime);
	}

	public void setoVisitTime(double oVisitTime) {
		this.oVisitTime = oVisitTime;
	}

	public String getnVisitTime() {
		return df.format(nVisitTime);
	}

	public void setnVisitTime(double nVisitTime) {
		this.nVisitTime = nVisitTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
