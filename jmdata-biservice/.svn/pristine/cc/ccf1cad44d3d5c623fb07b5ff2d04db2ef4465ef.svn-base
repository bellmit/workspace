package org.jumao.bi.service.impl.baidu;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jumao.bi.constant.MapConstants;
import org.jumao.bi.entites.baidu.AreasDistrictResponse;
import org.jumao.bi.entites.baidu.BaiduTabBean;
import org.jumao.bi.entites.baidu.DailyMetricBean;
import org.jumao.bi.entites.baidu.MetricDescBean;
import org.jumao.bi.entites.baidu.NOVisitorsBean;
import org.jumao.bi.entites.baidu.TopPageBean;
import org.jumao.bi.entites.baidu.TopPageResponse;
import org.jumao.bi.entites.baidu.TrendChart;
import org.jumao.bi.entites.baidu.TrendResponse;
import org.jumao.bi.entites.baidu.VisitorsSourcesBean;
import org.jumao.bi.entites.baidu.VistSourcesResponse;
import org.jumao.bi.entites.baidu.VistorBean;
import org.jumao.bi.entites.baidu.VistorsTypeResponse;
import org.jumao.bi.entites.baidu.WorldDistrictResponse;
import org.jumao.bi.entites.baidu.WorldVisitBean;
import org.jumao.bi.entites.charts.BarChart;
import org.jumao.bi.entites.charts.CommonBean;
import org.jumao.bi.entites.charts.LineChart;
import org.jumao.bi.entites.charts.PieChart;

public class BaiduSvcHelper {

	private static final int Top_Five = 5;
	private static final int Ten_Num = 10;
	private static final int Sixty_Num = 60;
	private static final int Hundred_Num = 100;
	private static final String Area_List_Str = "vAreaList";
	private static final String Max_Value_Str = "maxValue";
	private static final String Pv_Str = "1_pv";
	private static final String Uv_Str = "2_uv";
	private static final String Ip_Str = "3_ip";
	private static final String Average_Time_Str = "4_averageTime";
	private static final String Avg_Visit_Pages_Str = "5_avgVisitPages";
	private static final String Exit_Rate_Str = "6_exitRate";
	
    private static Map<String, String> metricMap = new TreeMap<String, String>();
    static {
        metricMap.put(Pv_Str, "浏览量PV_次");
        metricMap.put(Uv_Str, "访客数UV_个");
        metricMap.put(Ip_Str, "IP数_个");
        metricMap.put(Average_Time_Str, "平均停留时长_秒");
        metricMap.put(Avg_Visit_Pages_Str, "平均访问页数_次");
        metricMap.put(Exit_Rate_Str, "跳出率_%");
    }	
	
	/**
	 * buildTrendCharts:构建百度6项基础指标趋势统计图
	 * 
	 * @param data
	 * @param dates
	 * @param resultMap
	 */
	public static void buildTrendCharts(List<DailyMetricBean> data,
			List<String> dates, Map<String, Object> resultMap) {

		Map<String, DailyMetricBean> metricMap = new HashMap<String, DailyMetricBean>();
		for (DailyMetricBean dailyBean : data) {
			String date = dailyBean.getDate();
			metricMap.put(date, dailyBean);
		}

		int size = dates.size();
		String[] xaxisData = (String[]) dates.toArray(new String[size]);
		BigDecimal[] pvSeriesData = new BigDecimal[size];
		BigDecimal[] uvSeriesData = new BigDecimal[size];
		BigDecimal[] ipSeriesData = new BigDecimal[size];
		BigDecimal[] averageTimeSeriesData = new BigDecimal[size];
		BigDecimal[] exitRateSeriesData = new BigDecimal[size];
		BigDecimal[] avgVisitPagesSeriesData = new BigDecimal[size];

		for (int i = 0; i < size; i++) {

			if (metricMap.containsKey(xaxisData[i])) {
				DailyMetricBean dailyBean = metricMap.get(xaxisData[i]);
				pvSeriesData[i] = new BigDecimal(dailyBean.getPv());
				uvSeriesData[i] = new BigDecimal(dailyBean.getUv());
				ipSeriesData[i] = new BigDecimal(dailyBean.getIps());
				averageTimeSeriesData[i] = new BigDecimal(dailyBean.getStayTime());
				exitRateSeriesData[i] = new BigDecimal(dailyBean.getExits())
						.setScale(2, RoundingMode.HALF_UP);
				avgVisitPagesSeriesData[i] = new BigDecimal(
						dailyBean.getAvgVisitPages()).setScale(2,
						RoundingMode.HALF_UP);
			} else {
				pvSeriesData[i] = BigDecimal.ZERO;
				uvSeriesData[i] = BigDecimal.ZERO;
				ipSeriesData[i] = BigDecimal.ZERO;
				averageTimeSeriesData[i] = BigDecimal.ZERO;
				exitRateSeriesData[i] = BigDecimal.ZERO;
				avgVisitPagesSeriesData[i] = BigDecimal.ZERO;
			}

		}

		resultMap.put("xAxisData", xaxisData);
		String seriesData = "SeriesData";
		resultMap.put(Uv_Str + seriesData, uvSeriesData);	
		resultMap.put(Pv_Str + seriesData, pvSeriesData);
		resultMap.put(Ip_Str + seriesData, ipSeriesData);
		resultMap.put(Average_Time_Str + seriesData, averageTimeSeriesData);
		resultMap.put(Avg_Visit_Pages_Str + seriesData, avgVisitPagesSeriesData);
		resultMap.put(Exit_Rate_Str + seriesData, exitRateSeriesData);

	}

	/**
	 * buildTrendChart:构建趋势图
	 * 
	 * @param resultMap
	 * @param key
	 * @return
	 */
	public static TrendChart buildTrendChart(Map<String, Object> resultMap,
			String key) {
		TrendChart chart = new TrendChart();
		String[] nameArray = metricMap.get(key).split("_");
		chart.setTitle(nameArray[0] + "趋势情况");
		chart.setSeriesName(nameArray[0]);
		chart.setUnit(nameArray[1]);
		BigDecimal[] seriesData = (BigDecimal[]) resultMap.get(key
				+ "SeriesData");
		chart.setSeriesData(seriesData);
		return chart;
	}

	/**
	 * buildTrendResponse:构建百度趋势service响应json格式
	 * 
	 * @param response
	 * @param resultMap
	 */
	public static void buildTrendResponse(TrendResponse response,
			Map<String, Object> resultMap) {
		response.setxAxisData((String[]) resultMap.get("xAxisData"));
		response.setPvChart(BaiduSvcHelper.buildTrendChart(resultMap, Pv_Str));
		response.setUvChart(BaiduSvcHelper.buildTrendChart(resultMap, Uv_Str));
		response.setIpChart(BaiduSvcHelper.buildTrendChart(resultMap, Ip_Str));
		response.setAverageTimeChart(BaiduSvcHelper.buildTrendChart(resultMap,
		        Average_Time_Str));
		response.setAvgVisitPagesChart(BaiduSvcHelper.buildTrendChart(
				resultMap, Avg_Visit_Pages_Str));
		response.setExitRateChart(BaiduSvcHelper.buildTrendChart(resultMap,
		        Exit_Rate_Str));
	}

	/**
	 * buildBarCharts:构建访问排名，退出排名柱状统计图
	 * 
	 * @param data
	 * @param resultMap
	 */
	public static void buildBarCharts(List<TopPageBean> data,
			Map<String, Object> resultMap) {
		Map<String, BigDecimal[]> nameMap = new HashMap<String, BigDecimal[]>();
		for (TopPageBean pageBean : data) {
			String name = pageBean.getName();

			BigDecimal pvCount = pageBean.getPvCount();
			BigDecimal exitCount = pageBean.getExitcount();
			if (nameMap.containsKey(name)) {
				BigDecimal[] tempArray = nameMap.get(name);
				BigDecimal[] newArray = { tempArray[0].add(pvCount),
						tempArray[1].add(exitCount) };
				nameMap.put(name, newArray);

			} else {
				BigDecimal[] array = { pvCount, exitCount };
				nameMap.put(name, array);
			}
		}

		List<TopPageBean> pvList = new ArrayList<TopPageBean>();
		List<TopPageBean> existList = new ArrayList<TopPageBean>();
		for (Map.Entry<String, BigDecimal[]> entry : nameMap.entrySet()) {
			String name = entry.getKey();
			BigDecimal[] values = entry.getValue();
			TopPageBean pvBean = new TopPageBean(name, values[0],
					BigDecimal.ZERO);
			TopPageBean existBean = new TopPageBean(name, BigDecimal.ZERO,
					values[1]);
			pvList.add(pvBean);
			existList.add(existBean);
		}

		BarChart pvChart = buildPvTopChart(pvList);

		BarChart existCountChart = buildExistTopChart(existList);

		resultMap.put("pvChart", pvChart);
		resultMap.put("existCountChart", existCountChart);
	}

    private static BarChart buildExistTopChart(List<TopPageBean> existList) {
        Collections.sort(existList, new Comparator<TopPageBean>() {
			public int compare(TopPageBean obj1, TopPageBean obj2) {
				return obj2.getExitcount().compareTo(obj1.getExitcount());
			}
		});


		List<TopPageBean> topExistList = new ArrayList<TopPageBean>();
		if (existList.size() > Top_Five) {// 只取top5
			topExistList = existList.subList(0, Top_Five);
		} else {
			topExistList.addAll(existList);
		}

		int existSize = topExistList.size();
		String[] yaxisData4Exist = new String[existSize];
		BigDecimal[] seriesData4Exist = new BigDecimal[existSize];
		for (int i = 0; i < existSize; i++) {
			TopPageBean existBean = topExistList.get(i);
			yaxisData4Exist[i] = existBean.getName();
			seriesData4Exist[i] = existBean.getExitcount();
		}
		BarChart existCountChart = new BarChart();
		existCountChart.setyAxisData(yaxisData4Exist);
		existCountChart.setSeriesData(seriesData4Exist);
		existCountChart.setSeriesName("页面跳出排名");
        return existCountChart;
    }

    private static BarChart buildPvTopChart(List<TopPageBean> pvList) {
        Collections.sort(pvList, new Comparator<TopPageBean>() {
			public int compare(TopPageBean obj1, TopPageBean obj2) {
				return obj2.getPvCount().compareTo(obj1.getPvCount());
			}
		});
		
        List<TopPageBean> topPVList = new ArrayList<TopPageBean>();
        if (pvList.size() > Top_Five) {// 只取top5
            topPVList = pvList.subList(0, Top_Five);
        } else {
            topPVList.addAll(pvList);
        }
		
        int pvSize = topPVList.size();
        String[] yaxisData4PV = new String[pvSize];
        BigDecimal[] seriesData4PV = new BigDecimal[pvSize];
        for (int i = 0; i < pvSize; i++) {
            TopPageBean pvBean = topPVList.get(i);
            yaxisData4PV[i] = pvBean.getName();
            seriesData4PV[i] = pvBean.getPvCount();
        }
        BarChart pvChart = new BarChart();
        pvChart.setyAxisData(yaxisData4PV);
        pvChart.setSeriesData(seriesData4PV);
        pvChart.setSeriesName("页面流量排名");
        return pvChart;
    }

	public static void buildVistTopResponse(TopPageResponse response,
			Map<String, Object> resultMap) {
		response.setPvChart((BarChart) resultMap.get("pvChart"));
		response.setExistCountChart((BarChart) resultMap.get("existCountChart"));
	}

	
	/**
	 * buildTableCharts:构建新老访客统计表格
	 * 
	 * @param data
	 * @param resultMap
	 */
	public static void buildTableCharts(List<NOVisitorsBean> data,
			Map<String, Object> resultMap) {

		// 参照之前运营分析的逻辑，目前没有找到合适的百度API获取数据。
		Integer newVisiotsPVCount = 0;// 新访客访问pv
		Integer newVisiotsUVCount = 0;// 新访客访问uv
		Integer oldVisiotsPVCount = 0;// 老访客访问pv
		Integer oldVisiotsUVCount = 0;// 老访客访问uv
		double nvisitTimeSum = 0.0;// 新访客访问总时长
		double ovisitTimeSum = 0.0;// 老访客访问总时长
		double nvisitPageSum = 0.0;// 新访客访问总页面
		double ovisitPageSum = 0.0;// 老访客访问总页面
		for (NOVisitorsBean noVisitorsBean : data) {
			// 新访客除以总访客乘以页面数
			int visitCount = noVisitorsBean.getVisitorCount();
			int newVisitCount = noVisitorsBean.getNewVisitorCount();
			int oldVisitCount = visitCount - newVisitCount;
			int pvCount = noVisitorsBean.getPvCount();
			double avgTime = noVisitorsBean.getAvgVisitTime();
			double avgPage = noVisitorsBean.getAvgVisitPage();
			double newPV = newVisitCount * 1.0 / visitCount * pvCount;
			newVisiotsPVCount += (int) newPV;
			// 新访客uv
			newVisiotsUVCount += newVisitCount;
			// 老访客pv
			oldVisiotsPVCount += pvCount - (int) newPV;
			// 老访客uv
			oldVisiotsUVCount += oldVisitCount;

			// 新访客访问时长
			nvisitTimeSum += newVisitCount * avgTime * 1.0;
			ovisitTimeSum += oldVisitCount * avgTime * 1.0;

			nvisitPageSum += newVisitCount * avgPage * 1.0;
			ovisitPageSum += oldVisitCount * avgPage * 1.0;
		}


		DecimalFormat df = new DecimalFormat("#####0.00");
		List<VistorBean> vistors = new ArrayList<VistorBean>();
		VistorBean pvBean = new VistorBean("浏览量", newVisiotsPVCount.toString(),
				oldVisiotsPVCount.toString());
		VistorBean uvBean = new VistorBean("访客数", newVisiotsUVCount.toString(),
				oldVisiotsUVCount.toString());
		VistorBean vtBean = new VistorBean("平均访问时长", formatTime(nvisitTimeSum,
				newVisiotsUVCount), formatTime(ovisitTimeSum, oldVisiotsUVCount));
		VistorBean vpBean = new VistorBean("平均访问页面", df.format(nvisitPageSum
				/ (newVisiotsUVCount == 0 ? 1 : newVisiotsUVCount)),
				df.format(ovisitPageSum
						/ (oldVisiotsUVCount == 0 ? 1 : oldVisiotsUVCount)));
		vistors.add(pvBean);
		vistors.add(uvBean);
		vistors.add(vtBean);
		vistors.add(vpBean);
		Integer totalUVCount = newVisiotsUVCount + oldVisiotsUVCount;

		String newPercent = "0.00%";
		String oldPercent = "0.00%";
		if (totalUVCount != 0) {
			newPercent = df.format(1.0 * newVisiotsUVCount / totalUVCount * Hundred_Num)
					.toString() + "%";
			oldPercent = df.format(
					(1 - 1.0 * newVisiotsUVCount / totalUVCount) * Hundred_Num)
					.toString()
					+ "%";
		}
		resultMap.put("newPercent", newPercent);
		resultMap.put("oldPercent", oldPercent);
		resultMap.put("tableChart", vistors);
	}

	/**
	 * buildVistorsTypeResponse:构建新老访客接口响应json数据
	 * 
	 * @param response
	 * @param resultMap
	 */
	public static void buildVistorsTypeResponse(VistorsTypeResponse response,
			Map<String, Object> resultMap) {
		response.setTableData((List<VistorBean>) resultMap.get("tableChart"));
		response.setNewPercent((String) resultMap.get("newPercent"));
		response.setOldPercent((String) resultMap.get("oldPercent"));
	}

	/**
	 * buildGrapCharts:构建中国地图展示图
	 * 
	 * @param data
	 * @param resultMap
	 * @throws IOException
	 */
	public static void buildGrapCharts(List<CommonBean> data,
			Map<String, Object> resultMap) throws IOException {
		// sum
		Map<String, Integer> tmpMap = new HashMap<String, Integer>();

		if (CollectionUtils.isEmpty(data)) {
			// set all province value 0
			for (Map.Entry<String, Long> entry : MapConstants.CHINA_MAP
					.entrySet()) {
				CommonBean bean = new CommonBean(entry.getKey(),
						String.valueOf(entry.getValue()));
				data.add(bean);
			}
			resultMap.put(Max_Value_Str, 0);
			resultMap.put(Area_List_Str, data);

		}

		for (CommonBean bean : data) {
			if (tmpMap.containsKey(bean.getName())) {
				int value = tmpMap.get(bean.getName());
				tmpMap.put(
						bean.getName(),
						Integer.valueOf(value)
								+ Integer.valueOf(bean.getValue()));
			} else {
				tmpMap.put(bean.getName(), Integer.valueOf(bean.getValue()));
			}
		}

		data = new ArrayList<CommonBean>();
		for (Map.Entry<String, Integer> entry : tmpMap.entrySet()) {
			CommonBean bean = new CommonBean(entry.getKey(), entry.getValue()
					.toString());
			data.add(bean);
		}

		// get max value
		int maxValue = getMaxValue(data);
		resultMap.put(Max_Value_Str, maxValue);
		resultMap.put(Area_List_Str, data); // name : value

	}

	private static String formatTime(double visistTime, int visitCount) {

		int avgSecond = (int) visistTime / ((visitCount == 0) ? 1 : visitCount);
		int minute = avgSecond / Sixty_Num;
		int second = avgSecond % Sixty_Num;
		String minuteStr = (minute < Ten_Num) ? ("0" + minute) : String
				.valueOf(minute);
		String secondStr = (second < Ten_Num) ? ("0" + second) : String
				.valueOf(second);
		String timeStr = "00:" + minuteStr + ":" + secondStr;

		return timeStr;

	}

	private static int getMaxValue(List<CommonBean> beanList)
			throws IOException {
		int maxValue = 0;
		List<Integer> valueList = new ArrayList<Integer>();
		for (CommonBean bean : beanList) {
			if (StringUtils.isNotBlank(bean.getValue())) {
				valueList.add(Integer.valueOf((bean.getValue())));
			}
			maxValue = Collections.max(valueList);
		}
		return maxValue;
	}

	/**
	 * buildAreasDistrictResponse:构建百度地域分布接口响应json格式
	 * 
	 * @param response
	 * @param resultMap
	 */
	public static void buildAreasDistrictResponse(
			AreasDistrictResponse response, Map<String, Object> resultMap) {
		response.setData((List<CommonBean>) resultMap.get("vAreaList"));
		response.setMax((Integer) resultMap.get("maxValue"));

	}

	/**
	 * buildVistSourcesCharts:构建访问来源统计图
	 * 
	 * @param data
	 * @param dates
	 * @param resultMap
	 */
	public static void buildVistSourcesCharts(List<VisitorsSourcesBean> data,
			List<String> dates, Map<String, Object> resultMap) {

		PieChart pieChart = new PieChart();
		LineChart lineChart = new LineChart();
		int size = dates.size();
		String[] xaxisData = (String[]) dates.toArray(new String[size]);

		Map<String, BigDecimal[]> nameMap = new HashMap<String, BigDecimal[]>();
		Map<String, Map<String, BigDecimal>> dateMap = new HashMap<String, Map<String, BigDecimal>>();
		for (VisitorsSourcesBean bean : data) {
			String date = bean.getDate();
			String name = bean.getName();
			String metricStr = bean.getMetrics().replace("[", "").replace("]", "");
			String[] metricArray = metricStr.split(",");
			BigDecimal pv = new BigDecimal(metricArray[0]);
			BigDecimal uv = new BigDecimal(metricArray[3]);
			BigDecimal ip = new BigDecimal(metricArray[6]);
			BigDecimal existRate = new BigDecimal(metricArray[7]);
			BigDecimal[] values = {pv, uv, ip, existRate};
						
			BigDecimal value = values[0];
			if (nameMap.containsKey(name)) {
			    BigDecimal[] temp = nameMap.get(name);
			    BigDecimal[] addArray = {pv.add(temp[0]), uv.add(temp[1]) , ip.add(temp[2]), 
			            (existRate.add(temp[3])).divide(BigDecimal.valueOf(2), 2)};
				nameMap.put(name, addArray);
			} else {
				nameMap.put(name, values);
			}

			Map<String, BigDecimal> nameValue = new HashMap<String, BigDecimal>();
			nameValue.put(name, value);
			if (dateMap.containsKey(date)) {
				Map<String, BigDecimal> temp = dateMap.get(date);
				temp.put(name, value);
				dateMap.put(date, temp);
			} else {
				dateMap.put(date, nameValue);
			}

		}

		List<CommonBean> seriesData = new ArrayList<CommonBean>();
		List<BaiduTabBean>  tabData = new ArrayList<BaiduTabBean>();
		List<String> legends = new ArrayList<String>();
		List<int[]> lineData = new ArrayList<int[]>();
		for (Map.Entry<String, BigDecimal[]> entry : nameMap.entrySet()) {
			String name = entry.getKey();
			BigDecimal[] values = entry.getValue();
			CommonBean pieBean = new CommonBean(name, values[0].toString());
			BaiduTabBean tabBean = 
			        new BaiduTabBean(name, values[0], values[1], values[2], values[3]);
			tabData.add(tabBean);
			seriesData.add(pieBean);
			legends.add(name);
		}

		for (String name : legends) {
		    int[] nameArray = new int[dates.size()];
			for (int i = 0; i < dates.size(); i++) {
				if (dateMap.containsKey(xaxisData[i])) {
					Map<String, BigDecimal> temp = dateMap.get(xaxisData[i]);
					if (temp.containsKey(name)) {
						nameArray[i] = temp.get(name).intValue();
					}
				}
			}

			lineData.add(nameArray);

		}
		String[] legendData = (String[]) legends.toArray(new String[legends
				.size()]);
		pieChart.setSeriesFromCb(seriesData);
		pieChart.setLegendData(legendData);

		lineChart.setLegendData(legendData);
		lineChart.setxAxisData(xaxisData);
		lineChart.setSeriesData(lineData);

		resultMap.put("pieChart", pieChart);
		resultMap.put("lineChart", lineChart);
		resultMap.put("tabChart", buildBaiduTabChart(tabData));

	}

	private static List<BaiduTabBean> buildBaiduTabChart(List<BaiduTabBean> tabData) {
        
        BigDecimal totalPv = BigDecimal.ZERO;
        BigDecimal totalUv = BigDecimal.ZERO;
        BigDecimal totalIp = BigDecimal.ZERO;
        BigDecimal totalExistRate = BigDecimal.ZERO;
        for (BaiduTabBean bean : tabData) {
            totalPv = totalPv.add(bean.getPv());
            totalUv = totalUv.add(bean.getUv());
            totalIp = totalIp.add(bean.getIp());
            totalExistRate = totalExistRate.add(bean.getExitRate());
        }
        
        BigDecimal size =  tabData.isEmpty() ? BigDecimal.ONE : new BigDecimal(tabData.size()) ;//size 默认值为1， 避免为0当除数的情况
        tabData.add(new BaiduTabBean("当前汇总", totalPv, totalUv, totalIp, totalExistRate.divide(size, 2)));
        return tabData;
    }

    /**
	 * buildVistSourceResponse
	 * 
	 * @param response
	 * @param resultMap
	 */
	public static void buildVistSourceResponse(VistSourcesResponse response,
			Map<String, Object> resultMap) {
		response.setPieChart((PieChart) resultMap.get("pieChart"));
		response.setLineChart((LineChart) resultMap.get("lineChart"));
		response.setTable((List<BaiduTabBean>) resultMap.get("tabChart"));
	}

    /**
     * buildBaiduOverview:构建百度数据概览
     * 
     * @param data
     * @param overview
     */
    public static void buildBaiduOverview(List<DailyMetricBean> data,  List<MetricDescBean> overview) {
    
        BigDecimal pvs = BigDecimal.ZERO;
        BigDecimal uvs = BigDecimal.ZERO;
        BigDecimal ips = BigDecimal.ZERO;
        BigDecimal averageTimes = BigDecimal.ZERO;
        BigDecimal exitRates = BigDecimal.ZERO;
        BigDecimal avgVisitPages = BigDecimal.ZERO;
        for (DailyMetricBean dailyBean : data) {
            pvs = pvs.add(new BigDecimal(dailyBean.getPv()));
            uvs = uvs.add(new BigDecimal(dailyBean.getUv()));
            ips = ips.add(new BigDecimal(dailyBean.getIps()));
            averageTimes = averageTimes.add(new BigDecimal(dailyBean.getStayTime()));
            exitRates = exitRates.add(new BigDecimal(dailyBean.getExits())
            .setScale(2, RoundingMode.HALF_UP));
            avgVisitPages = avgVisitPages.add(new BigDecimal(
                    dailyBean.getAvgVisitPages()).setScale(2,
                    RoundingMode.HALF_UP));
        }
        
        BigDecimal size =  data.isEmpty() ? BigDecimal.ONE : new BigDecimal(data.size()) ;//size 默认值为1， 避免为0当除数的情况
        Map<String, BigDecimal> valueMap = new HashMap<String, BigDecimal>();
        valueMap.put(Pv_Str, pvs);
        valueMap.put(Uv_Str, uvs);
        valueMap.put(Ip_Str, ips);
        valueMap.put(Average_Time_Str, averageTimes.divide(size, 0));
        valueMap.put(Avg_Visit_Pages_Str, avgVisitPages.divide(size, 2));
        valueMap.put(Exit_Rate_Str, exitRates.divide(size, 2));
      
        for (Map.Entry<String, String> entry : metricMap.entrySet()) {
            String key = entry.getKey();
            String[] metrics = entry.getValue().split("_");
            BigDecimal value = valueMap.get(key);
            MetricDescBean bean = new MetricDescBean(metrics[0], value, metrics[1]);
            overview.add(bean);
        }

    }

    /** 
     * buildWorldCharts:构建按国家统计流量分析
     * 
     * @author 1
     * @date 2017年7月17日 下午6:18:35
     * @param data
     * @param response
     */
    public static void buildWorldCharts(List<WorldVisitBean> data, WorldDistrictResponse response) {
       
        Map<String, WorldVisitBean> countryMap = new HashMap<String, WorldVisitBean>();    
        for (WorldVisitBean worldBean : data) {
            String country = worldBean.getCountry();
            BigDecimal pv = worldBean.getPv();
            BigDecimal uv = worldBean.getUv();
            BigDecimal ip = worldBean.getIp();
            if(countryMap.containsKey(country)) {//按国家分组累加
                WorldVisitBean tempBean =  countryMap.get(country);
                countryMap.put(country, new WorldVisitBean(country, 
                        tempBean.getPv().add(pv), tempBean.getUv().add(uv), tempBean.getIp().add(ip)));
            } else {
                countryMap.put(country, worldBean);
            }
                      
        }
        
        List<CommonBean> mapData = new ArrayList<CommonBean>();
        List<WorldVisitBean> tabData = new ArrayList<WorldVisitBean>();
        for (Map.Entry<String, WorldVisitBean> entry : countryMap.entrySet()) {
            String country = entry.getKey();
            WorldVisitBean bean = entry.getValue();
            mapData.add(new CommonBean(country, bean.getUv().toString()));
            tabData.add(bean);
        }
        
        Collections.sort(tabData, new Comparator<WorldVisitBean>() {
            public int compare(WorldVisitBean obj1, WorldVisitBean obj2) {
                return obj2.getPv().compareTo(obj1.getPv());
            }
        });
        
        int max = 0;
        if(!tabData.isEmpty()) {
            max = tabData.get(0).getPv().intValue();
        }
        BigDecimal totalPv = BigDecimal.ZERO;
        BigDecimal totalUv = BigDecimal.ZERO;
        BigDecimal totalIp = BigDecimal.ZERO;
        for (WorldVisitBean bean : tabData) {
            totalPv = totalPv.add(bean.getPv());
            totalUv = totalUv.add(bean.getUv());
            totalIp = totalIp.add(bean.getIp());
        }
        
      
        List<WorldVisitBean> result = new ArrayList<WorldVisitBean>();
        if(tabData.size() > Top_Five) {
            result = tabData.subList(0, Top_Five);
        }
        
        result.add(new WorldVisitBean("累计汇总", totalPv, totalUv, totalIp));

        
        response.setData(mapData);
        response.setMax(max);
        response.setTable(result);
        
    }

}
