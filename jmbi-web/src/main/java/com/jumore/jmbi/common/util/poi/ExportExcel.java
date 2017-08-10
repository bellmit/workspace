package com.jumore.jmbi.common.util.poi;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jumore.dove.common.log.LogHelper;

public class ExportExcel {
    private static final LogHelper Log_Helper   = LogHelper.getLogger(ExportExcel.class);
    public static final short      Column_Width = 3000;
    public static final short      Font_Height  = 200;

    /**
     * exportExcel:导出Excel.
     * 
     * @author Administrator
     * @date 2017年6月2日 下午3:55:35
     * @param tmpContentCn
     * @param sheetName
     * @param dataList
     * @return
     * @throws Exception
     */
    public HSSFWorkbook exportExcel(String tmpContentCn, String sheetName, List dataList) throws Exception {
        HSSFWorkbook workbook = null;
        String[] titlesCN = tmpContentCn.split(",");
        try {
            // 这里的数据即时你要从后台取得的数据

            // 创建工作簿实例
            workbook = new HSSFWorkbook();
            // 创建工作表实例
            HSSFSheet sheet = workbook.createSheet(sheetName);
            // 设置列宽
            this.setSheetColumnWidth(titlesCN, sheet);
            // 获取样式
            HSSFCellStyle style = this.createTitleStyle(workbook);
            if (dataList != null && dataList.size() > 0) {
                // 创建第一行标题
                HSSFRow row = sheet.createRow((short) 0);// 建立新行

                for (int i = 0; i < titlesCN.length; i++) {
                    this.createCell(row, i, null, HSSFCell.CELL_TYPE_STRING, titlesCN[i]);
                }
                // 给excel填充数据
                for (int i = 0; i < dataList.size(); i++) {
                    // 将dataList里面的数据取出来
                    String[] model = (String[]) dataList.get(i);
                    HSSFRow row1 = sheet.createRow((short) (i + 1));// 建立新行
                    for (int j = 0; j < model.length; j++) {
                        this.createCell(row1, j, style, HSSFCell.CELL_TYPE_STRING, model[j]);
                    }

                }
            } else {
                this.createCell(sheet.createRow(0), 0, style, HSSFCell.CELL_TYPE_STRING, "无数据");
            }
        } catch (Exception e) {
            Log_Helper.getBuilder().error("", e);
        }
        return workbook;

    }

    private void setSheetColumnWidth(String[] titlesCN, HSSFSheet sheet) {

        // 根据你数据里面的记录有多少列，就设置多少列
        for (int i = 0; i < titlesCN.length; i++) {
            sheet.setColumnWidth(i, Column_Width);
        }

    }

    // 设置excel的title样式
    private HSSFCellStyle createTitleStyle(HSSFWorkbook wb) {

        HSSFFont boldFont = wb.createFont();

        boldFont.setFontHeight(Font_Height);

        HSSFCellStyle style = wb.createCellStyle();

        style.setFont(boldFont);

        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));

        return style;

    }

    // 创建Excel单元格

    private void createCell(HSSFRow row, int column, HSSFCellStyle style,

    int cellType, Object value) {

        HSSFCell cell = row.createCell(column);

        if (style != null) {

            cell.setCellStyle(style);

        }

        switch (cellType) {

            case HSSFCell.CELL_TYPE_BLANK: {

            }

                break;

            case HSSFCell.CELL_TYPE_STRING: {

                cell.setCellValue(value.toString());

            }

                break;

            case HSSFCell.CELL_TYPE_NUMERIC: {

                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);

                cell.setCellValue(Double.parseDouble(value.toString()));

            }

                break;

            default:

                break;

        }

    }
}
