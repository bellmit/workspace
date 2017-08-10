package com.jumore.jmbi.controller.jdt.company;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jumore.dove.common.log.LogHelper;
import com.jumore.dove.controller.base.BaseController;
import com.jumore.jmbi.common.util.export.HttpUtilForExport;
import com.jumore.jmbi.common.util.export.JsonParams;
import com.jumore.jmbi.common.util.poi.ExportExcel;
import com.jumore.jmbi.common.util.properties.PropertiesUtil;
import com.jumore.jmbi.entites.CompanyAmountBean;

@Controller
@RequestMapping("/v1/company")
public class CompanyAmountController extends BaseController {
    
    private static final LogHelper Log_Helper = LogHelper.getLogger(CompanyAmountController.class);
    
    public static final String Company_Type_Customer  = "客户";
    public static final String Company_Type_Partner  = "拍档";

	/**
	 * 企业分类统计：由于几个top统计hbase的表结构一致；这里用一个方法。把表名传过来，进行查询
	 * 
	 * @param timeType
	 *            时间类型：week、month、quarter、year
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param customsCode
	 *            海关/关区：编码
	 * @param chart
	 *            查询的图表：{企业分类统计的}1、客户企业订单数top；；2、拍档企业订单数top；； 3、客户企业支出top
	 *            ；；4、拍档企业收入top；；5、客户企业:订单货值top；；6、拍档企业:订单货值top
	 * @return
	 */
	@RequestMapping("/exportCompanyAmount/timeType/{timeType}/startDate/{startDate}/endDate/{endDate}/customsCode/{customsCode}/chart/{chart}")
	public ModelAndView exportCompanyAmount(@PathVariable String timeType,
			@PathVariable String startDate, @PathVariable String endDate,
			@PathVariable String customsCode, @PathVariable String chart,
			HttpServletRequest request, HttpServletResponse response)
			 {
		response.setContentType("application/binary;charset=ISO8859_1");

		String urlPath = "/v1/company/getCompanyAmountExport/timeType/"
				+ timeType + "/startDate/" + startDate + "/endDate/" + endDate
				+ "/customsCode/" + customsCode + "/chart/" + chart + "";// 请求方法路径
		// "/v1/company/getInOutCountryAmountExport/timeType/month/startDate/20160101/endDate/20170307/customsCode/all/inout/in"
		try {
			ServletOutputStream outputStream = response.getOutputStream();

			String title = "";
			String companyType = "";// 企业类型：客户、拍档
			String sheetName = "";// 表头
			if ("1".equals(chart)) {// 根据不同表产生对应：标题、表头
				title = "客户企业订单数TOP";
				companyType = Company_Type_Customer;
				sheetName = "排名,企业类型,企业名称,订单数（票）";
			} else if ("2".equals(chart)) {
				title = "拍档企业订单数TOP";
				companyType = Company_Type_Partner;
				sheetName = "排名,企业类型,企业名称,订单数（票）";
			} else if ("3".equals(chart)) {
				title = "客户企业支出TOP";
				companyType = Company_Type_Customer;
				sheetName = "排名,企业类型,企业名称,收入（人民币万元）";
			} else if ("4".equals(chart)) {
				title = "拍档企业收入TOP";
				companyType = Company_Type_Partner;
				sheetName = "排名,企业类型,企业名称,收入（人民币万元）";
			} else if ("5".equals(chart)) {
				title = "客户企业订单货值TOP";
				companyType = Company_Type_Customer;
				sheetName = "排名,企业类型,企业名称,总金额（万美元）";
			} else if ("6".equals(chart)) {
				title = "拍档企业订单货值TOP";
				companyType = Company_Type_Partner;
				sheetName = "排名,企业类型,企业名称,总金额（万美元）";
			}
			String fileName = new String((title).getBytes(), "ISO8859_1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");// 组装附件名称和格式
			// 获取要导出的数据
			JsonNode result = HttpUtilForExport.doGetForExport(PropertiesUtil
					.getBiServiceUrl() + urlPath);
			
			if (null == result) {//返回null;表示前面的返回发生了错误。这里返回null
				return null;//返回一个只有文件名、没有列名的空表格
			}
			List<CompanyAmountBean> dataList = JsonParams.formJson(result,
					new TypeReference<List<CompanyAmountBean>>() {
					});

			// 拼接：数据列
			List<String[]> excelData = new ArrayList<String[]>();
			for (int i = 0; i < dataList.size(); i++) {
				String[] array = { dataList.get(i).getIndex() + "",
				        companyType, dataList.get(i).getCompanyName(),
						dataList.get(i).getTotalAmount().toPlainString() };
				excelData.add(array);
			}

			// 生成EXCEL

			ExportExcel eu = new ExportExcel();
			HSSFWorkbook workbook = eu.exportExcel(sheetName, "country",
					excelData);
			// ByteArrayOutputStream output = new ByteArrayOutputStream();
			workbook.write(outputStream);

			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
		    Log_Helper.getBuilder().error(e.getMessage());
		}
		return null;

	}

}
