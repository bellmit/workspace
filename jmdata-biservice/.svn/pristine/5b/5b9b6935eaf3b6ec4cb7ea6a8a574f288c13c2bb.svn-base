package org.jumao.bi.entites.trade.register;

import org.jumao.bi.entites.CommonResponse;
import org.jumao.bi.entites.charts.PieChart;
import org.jumao.bi.entites.trade.register.vo.TableVo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChartResp extends CommonResponse implements Serializable {

    private RegLineChart lineChart = new RegLineChart(); //折线图或柱状图格式一样，只是前端展示不同
    private PieChart pieChart = new PieChart();
    private List<TableVo> tableData = new ArrayList<TableVo>();

    private long counts = 0; //图表中数量（如用户数）
    private String percent = ""; //占总量百分比
    private long totalSize = 0;
    private String nowDate = ""; //更新截止时间


    public RegLineChart getLineChart() {
        return lineChart;
    }

    public void setLineChart(RegLineChart lineChart) {
        this.lineChart = lineChart;
    }

    public PieChart getPieChart() {
        return pieChart;
    }

    public void setPieChart(PieChart pieChart) {
        this.pieChart = pieChart;
    }

    public List<TableVo> getTableData() {
        return tableData;
    }

    public void setTableData(List<TableVo> tableData) {
        this.tableData = tableData;
    }

    public long getCounts() {
        return counts;
    }

    public void setCounts(long counts) {
        this.counts = counts;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public String getNowDate() {
        return nowDate;
    }

    public void setNowDate(String nowDate) {
        this.nowDate = nowDate;
    }
}
