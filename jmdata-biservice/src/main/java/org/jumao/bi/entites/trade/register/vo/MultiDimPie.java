package org.jumao.bi.entites.trade.register.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kartty on 2017/5/8.
 */
public class MultiDimPie {

    private StrArrData legend = new StrArrData();
    private List<PieMultiSeriesVo> series = new ArrayList<PieMultiSeriesVo>();

    public StrArrData getLegend() {
        return legend;
    }

    public void setLegend(StrArrData legend) {
        this.legend = legend;
    }

    public List<PieMultiSeriesVo> getSeries() {
        return series;
    }

    public void setSeries(List<PieMultiSeriesVo> series) {
        this.series = series;
    }

}
