package com.jumore.jmbi.controller.jdt.inout;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Consts;
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
import com.jumore.jmbi.entites.InOutCountryBean;
import com.jumore.jmbi.entites.InOutCustomsBean;
import com.jumore.jmbi.entites.InOutGoodsBean;

@Controller
@RequestMapping("/v1/inout")
public class InOutController extends BaseController {
    private static LogHelper    Log_Helper           = LogHelper.getLogger(InOutController.class);

    private static final String Content_Type         = "application/binary;charset=ISO8859_1";
    private static final String In_Str               = "in";
    private static final String In_Name_Str          = "进口";
    private static final String Out_Str              = "out";
    private static final String Out_Name_Str         = "出口";
    private static final String File_Type_Xls        = ".xls";
    private static final String Content_Disposition  = "Content-disposition";
    private static final String Attachment_File_Name = "attachment; filename=";
    private static final String Start_Date           = "/startDate/";
    private static final String End_Date             = "/endDate/";
    private static final String Customs_Code             = "/customsCode/";
    private static final String In_Out             = "/inout/";

    /**
     * 进出口分类统计：出口贸易国别top：：导出
     * 
     * @param timeType 时间类型：week、month、quarter、year
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param customsCode 海关/关区：编码
     * @param inout in进口、out出口
     * @return
     */
    @RequestMapping("/exportInOutCountryAmount/timeType/{timeType}/startDate/{startDate}/endDate/{endDate}/customsCode/{customsCode}/inout/{inout}")
    public ModelAndView exportInOutCountryAmount(@PathVariable String timeType, @PathVariable String startDate,
            @PathVariable String endDate, @PathVariable String customsCode, @PathVariable String inout, HttpServletRequest request,
            HttpServletResponse response) {
        response.setContentType(Content_Type);

        String urlPath = "/v1/inout/getInOutCountryAmountExport/timeType/" + timeType + Start_Date + startDate + End_Date + endDate
                + Customs_Code + customsCode + In_Out + inout + "";// 请求方法路径
        // "/v1/inout/getInOutCountryAmountExport/timeType/month/startDate/20160101/endDate/20170307/customsCode/all/inout/in"
        try {
            ServletOutputStream outputStream = response.getOutputStream();

            String title = "";
            String inoutType = "";

            if (In_Str.equals(inout)) {
                title = "进口贸易国别TOP";
                inoutType = In_Name_Str;
            } else if (Out_Str.equals(inout)) {
                title = "出口贸易国别TOP";
                inoutType = Out_Name_Str;
            }
            String fileName = new String((title).getBytes(), Consts.ISO_8859_1);
            response.setHeader(Content_Disposition, Attachment_File_Name + fileName + File_Type_Xls);// 组装附件名称和格式
            // 获取要导出的数据
            JsonNode result = HttpUtilForExport.doGetForExport(PropertiesUtil.getBiServiceUrl() + urlPath);
            if (null == result) {// 返回null;表示前面的返回发生了错误。这里返回null
                return null;// 返回一个只有文件名、没有列名的空表格
            }
            List<InOutCountryBean> dataList = JsonParams.formJson(result, new TypeReference<List<InOutCountryBean>>() {
            });

            // 拼接：数据列
            List<String[]> excelData = new ArrayList<String[]>();
            for (int i = 0; i < dataList.size(); i++) {
                String[] array = { dataList.get(i).getIndex() + "", inoutType, dataList.get(i).getCountryName(),
                        dataList.get(i).getTotalAmount().toPlainString() };
                excelData.add(array);
            }

            // 生成EXCEL

            String sheetName = "排名,进出口类型,贸易国别,金额（万美元）";
            ExportExcel eu = new ExportExcel();
            HSSFWorkbook workbook = eu.exportExcel(sheetName, "country", excelData);
            // ByteArrayOutputStream output = new ByteArrayOutputStream();
            workbook.write(outputStream);

            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            Log_Helper.getBuilder().error(e.getMessage());
        }
        return null;

    }

    /**
     * 进出口分类统计：进口商品top::导出
     *
     * @param timeType 时间类型：week、month、quarter、year
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param customsCode 海关/关区：编码
     * @param inout in进口、out出口
     * @return
     */
    @RequestMapping("/exportInOutGoodsAmount/timeType/{timeType}/startDate/{startDate}/endDate/{endDate}/customsCode/{customsCode}/inout/{inout}")
    public ModelAndView exportInOutGoodsAmount(@PathVariable String timeType, @PathVariable String startDate, @PathVariable String endDate,
            @PathVariable String customsCode, @PathVariable String inout, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(Content_Type);

        String urlPath = "/v1/inout/getInOutGoodsAmountExport/timeType/" + timeType + Start_Date + startDate + End_Date + endDate
                + Customs_Code + customsCode + In_Out + inout + "";// 请求方法路径
        // "/v1/inout/getInOutGoodsAmountExport/timeType/month/startDate/20160101/endDate/20170307/customsCode/all/inout/in"
        try {
            ServletOutputStream outputStream = response.getOutputStream();

            String title = "";
            String inoutType = "";
            if (In_Str.equals(inout)) {
                title = "进口商品TOP";
                inoutType = In_Name_Str;
            } else if (Out_Str.equals(inout)) {
                title = "出口商品TOP";
                inoutType = Out_Name_Str;
            }
            String fileName = new String((title).getBytes(), Consts.ISO_8859_1);
            response.setHeader(Content_Disposition, Attachment_File_Name + fileName + File_Type_Xls);// 组装附件名称和格式
            // 获取要导出的数据
            JsonNode result = HttpUtilForExport.doGetForExport(PropertiesUtil.getBiServiceUrl() + urlPath);
            if (null == result) {// 返回null;表示前面的返回发生了错误。这里返回null
                return null;// 返回一个只有文件名、没有列名的空表格
            }
            List<InOutGoodsBean> dataList = JsonParams.formJson(result, new TypeReference<List<InOutGoodsBean>>() {
            });

            // 拼接：数据列
            List<String[]> excelData = new ArrayList<String[]>();
            for (int i = 0; i < dataList.size(); i++) {
                String[] array = { dataList.get(i).getIndex() + "", inoutType, dataList.get(i).getGoodsName(),
                        dataList.get(i).getGoodsCode(), dataList.get(i).getTotalAmount().toPlainString() };
                excelData.add(array);
            }

            // 生成EXCEL

            String sheetName = "排名,进出口类型,商品名称,HS编码,金额（万美元）";
            ExportExcel eu = new ExportExcel();
            HSSFWorkbook workbook = eu.exportExcel(sheetName, "Goods", excelData);
            // ByteArrayOutputStream output = new ByteArrayOutputStream();
            workbook.write(outputStream);

            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            Log_Helper.getBuilder().error(e.getMessage());
        }
        return null;

    }

    /**
     * 进出口分类统计：进口口岸top::导出
     *
     * @param timeType 时间类型：week、month、quarter、year
     * 
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param customsCode 海关/关区：编码
     * @param inout in进口、out出口
     * @return
     */
    @RequestMapping("/exportInOutCustomsAmount/timeType/{timeType}/startDate/{startDate}/endDate/{endDate}/customsCode/{customsCode}/inout/{inout}")
    public ModelAndView inOutCustomsAmount(@PathVariable String timeType, @PathVariable String startDate, @PathVariable String endDate,
            @PathVariable String customsCode, @PathVariable String inout, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(Content_Type);

        String urlPath = "/v1/inout/getInOutCustomsAmountExport/timeType/" + timeType + Start_Date + startDate + End_Date + endDate
                + Customs_Code + customsCode + In_Out + inout + "";// 请求方法路径
        // "/v1/inout/getInOutCustomsAmountExport/timeType/month/startDate/20160101/endDate/20170307/customsCode/all/inout/in"
        try {
            ServletOutputStream outputStream = response.getOutputStream();

            String title = "";
            String inoutType = "";
            if (In_Str.equals(inout)) {
                title = "进口口岸TOP";
                inoutType = In_Name_Str;
            } else if (Out_Str.equals(inout)) {
                title = "出口口岸TOP";
                inoutType = Out_Name_Str;
            }
            String fileName = new String((title).getBytes(), Consts.ISO_8859_1);
            response.setHeader(Content_Disposition, Attachment_File_Name + fileName + File_Type_Xls);// 组装附件名称和格式
            // 获取要导出的数据
            JsonNode result = HttpUtilForExport.doGetForExport(PropertiesUtil.getBiServiceUrl() + urlPath);
            if (null == result) {// 返回null;表示前面的返回发生了错误。这里返回null
                return null;// 返回一个只有文件名、没有列名的空表格
            }
            List<InOutCustomsBean> dataList = JsonParams.formJson(result, new TypeReference<List<InOutCustomsBean>>() {
            });

            // 拼接：数据列
            List<String[]> excelData = new ArrayList<String[]>();
            for (int i = 0; i < dataList.size(); i++) {
                String[] array = { dataList.get(i).getIndex() + "", inoutType, dataList.get(i).getCustomsName(),
                        dataList.get(i).getTotalAmount().toPlainString() };
                excelData.add(array);
            }

            // 生成EXCEL

            String sheetName = "排名,进出口类型,关区/口岸,金额（万美元）";
            ExportExcel eu = new ExportExcel();
            HSSFWorkbook workbook = eu.exportExcel(sheetName, "Customs", excelData);
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
