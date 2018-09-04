package com.lj.common.util;

import com.lj.common.enums.ErrorCode;
import com.lj.common.exception.LJShopException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 操作excel的工具类
 * @author chao.zhang
 *
 */
public class WorkbookUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkbookUtil.class) ;
	public static final int IMPORT_MAX_ROWS = 2000 ; //导入最大行数
	private static final int EXPORT_MAX_ROWS = 2000 ; //导出最大行数
	
	private WorkbookUtil(){}
    /**
     * 根据数据生成workbook对象
     * @param headers 标题列
     * @param columns 列的瞬间顺序
     * @param dataset 数据结构
     * @return
     */
    public static Workbook getExcelXlsx(String[] headers, String[] columns, List<Map<String, Object>> dataset) {

    	SXSSFWorkbook workbook = new SXSSFWorkbook(SXSSFWorkbook.DEFAULT_WINDOW_SIZE); //产生工作簿对象  
        Sheet sheet = workbook.createSheet("data"); //创建sheet对象     

        writeTitleRow(headers, sheet);
        writeDataRows(columns, dataset, sheet);

        return workbook;
    }
    
    /**
     * 创建新的excel实例，写入title
     * @param headers
     * @return
     */
    public static Sheet writeTitle(Workbook workbook, String sheetName, String[] headers) {
        Sheet sheet = workbook.createSheet(sheetName); //创建sheet对象     
        writeTitleRow(headers, sheet);
        return sheet;
    }
    
    /**
     * 把数据写入workbook的数据行中
     * @param workbook 待写入数据的workbook
     * @param index 当前存在数据的最后一行
     * @param columns 行数据的名称顺序
     * @param dataset 待写入的数据集合
     * @return
     */
    public static int writeData(Sheet sheet, int index, String[] columns, List<Map<String, Object>> dataset) {
        return writeDataRows(index, columns, dataset, sheet);
    }
    
    // 写入文件title
    private static void writeTitleRow(String[] headers, Sheet sheet) {
        Row row = sheet.createRow(0);// 产生表格标题行  
        for (int i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i, Cell.CELL_TYPE_STRING);
            cell.setCellValue(headers[i]);
        }
    }
    
    // 写入数据
    private static int writeDataRows(int index, String[] columns, List<Map<String, Object>> dataset, Sheet sheet) {
        Iterator<Map<String, Object>> it = dataset.iterator();// 遍历集合数据，产生数据行  
        Row row = null;
        int seq = index;
        while (it.hasNext()) {
        	seq++;
        	if(seq>EXPORT_MAX_ROWS) {
        		LOGGER.error("导出已超过最大条数限制:"+EXPORT_MAX_ROWS+", 小心内存溢出.请使用异步导出");
        		throw new LJShopException(ErrorCode.SYSTEM_ERROR.getCode(),"导出已超过最大条数限制:"+EXPORT_MAX_ROWS+", 小心内存溢出.请使用异步导出") ;
			}
            row = sheet.createRow(seq);
            Map<String, Object> data = it.next();
            for (int i = 0; i < columns.length; i++) {
                Cell cell = row.createCell(i, Cell.CELL_TYPE_STRING);
                cell.setCellValue(Objects.toString(data.get(columns[i]), ""));
            }
        }
        
        return null == row ? -1 : row.getRowNum();
    }
    
    // 写入数据
    private static void writeDataRows(String[] columns, List<Map<String, Object>> dataset, Sheet sheet) {
        Iterator<Map<String, Object>> it = dataset.iterator();// 遍历集合数据，产生数据行  
        Row row = null;
        int index = 0;
        while (it.hasNext()) {
            index++;
            if(index>EXPORT_MAX_ROWS) {
        		LOGGER.error("导出已超过最大条数限制:"+EXPORT_MAX_ROWS+", 小心内存溢出.请使用异步导出");
        		throw new LJShopException(ErrorCode.SYSTEM_ERROR.getCode(),"导出已超过最大条数限制:"+EXPORT_MAX_ROWS+", 小心内存溢出.请使用异步导出") ;
			}
            row = sheet.createRow(index);
            Map<String, Object> data = it.next();
            for (int i = 0; i < columns.length; i++) {
                Cell cell = row.createCell(i, Cell.CELL_TYPE_STRING);
                cell.setCellValue(Objects.toString(data.get(columns[i]), ""));
            }
        }
    }
    
    /**
     * 字符串数据的判空 && 去除空格
     * @param value 待处理的数据
     * @param rowNum 数据所在的行
     * @param colNum 数据所在的列
     * @return
     */
    public static String getValue(String value, int rowNum, int colNum) {
        if (StringUtils.isBlank(value)) {
            throw new LJShopException("", String.format("第%s行%s列:不能为空", rowNum, CellReference.convertNumToColString(colNum)));
        }
        return StringUtils.trimToEmpty(value);
    }
    
    /**
     * 字符串数据的判空 && 去除空格 && 长度限制
     * @param value 待处理的数据
     * @param rowNum 数据所在的行
     * @param colNum 数据所在的列
     * @return
     */
    public static String getValue(String value, int rowNum, int colNum, int limitLength) {
        if (StringUtils.isBlank(value)) {
            throw new LJShopException("", String.format("第%s行%s列:不能为空", rowNum, CellReference.convertNumToColString(colNum)));
        }
        
        String newValue = StringUtils.trimToEmpty(value);
        if (StringUtils.length(newValue) > limitLength) {
            throw new LJShopException("", String.format("第%s行%s列:数据长度超过限制[limit:%s]", rowNum, CellReference.convertNumToColString(colNum), limitLength));
        }
        
        return StringUtils.trimToEmpty(newValue);
    }
    
    /**
     * 手机数据获取
     * @param value
     * @param rowNum
     * @param colNum
     * @return
     */
    public static String getCellphone(String value, int rowNum, int colNum) {
        String newValue = getValue(value, rowNum, colNum);
        if (!newValue.matches("^1\\d{10}$")) {
            throw new LJShopException("", String.format("第%s行%s列:手机号格式错误", rowNum, CellReference.convertNumToColString(colNum)));
        }
        return StringUtils.trimToEmpty(newValue);
    }
    
    /**
     * 字符串数据的判空 && 转化为整数
     * @param value 待处理的数据
     * @param rowNum 数据所在的行
     * @param colNum 数据所在的列
     * @return
     */
    public static int toInt(String value, int rowNum, int colNum) {
        String newValue = getValue(value, rowNum, colNum);

        if (!newValue.matches("[1-9][0-9]*[\\.[0]+]*")) {
            throw new LJShopException("", String.format("第%s行%s列:整数数值错误[%s]", rowNum, CellReference.convertNumToColString(colNum), newValue));
        }
        
        return new BigDecimal(newValue).intValue();
    }
    
    /**
     * 数据转化为日期格式
     * @param value 待转化的数据
     * @param allowNull 是否允许为空
     * @param rowNum 数据所在的行
     * @param colNum 数据所在的列
     * @param pattern 日期待转化的格式
     * @return
     */
    public static Date toDate(String value, boolean allowNull, int rowNum, int colNum, String... pattern) {
        if (allowNull && StringUtils.isBlank(value)) {
            return null;
        }
        
        String dateStr = getValue(value, rowNum, colNum);
        
        try {
            return DateUtils.parseDate(dateStr, pattern);
        } catch (Exception e) {
            throw new LJShopException("", String.format("第%s行%s列:日期数据格式错误[%s]", rowNum + 1, CellReference.convertNumToColString(colNum), dateStr));
        }
    }
    
    
    /**
     * 转化excel原始数据为字符串类型数据
     * @param cell 待读取的数据单元
     * @param datePattern 日期的转化格式
     * @param numberPattern 贷款金额的数据
     * @return
     */
    public static String getStringCellValue(Cell cell, String datePattern, String numberPattern) {
        if (cell == null) {
            return "";
        }
        String strCell = "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
            	strCell = getCellTypeNumber(cell, datePattern, numberPattern);
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_BLANK:
            default:
                strCell = "";
                break;
        }

        return strCell.trim();
    }
    
    private static String getCellTypeNumber(Cell cell, String datePattern, String numberPattern) {
    	String strCell = "";
    	if (DateUtil.isCellDateFormatted(cell)) {
            Date javaDate = DateUtil.getJavaDate(cell.getNumericCellValue());
            strCell = DateFormatUtils.format(javaDate, datePattern);
        } else {
            DecimalFormat df = new DecimalFormat(numberPattern);
            strCell = df.format(cell.getNumericCellValue());
        }
    	return strCell;
    }
    
    /***
     * 解析workbook对象为 字符数组
     * @param workbook 待解析的文件对象
     * @param maxRows 文件对象最大的行数
     * @return
     * @throws IOException
     */
    public static List<List<String>> parseExcel(Workbook workbook, int maxRows) throws IOException {
        try {
            Sheet sheet = workbook.getSheetAt(0);
            int numberOfRows = sheet.getPhysicalNumberOfRows();
            if (numberOfRows > maxRows + 1) {
                throw new LJShopException("", "导入数据最大不应超过" + maxRows + "行");
            }

            List<List<String>> records = new ArrayList<>(2048);
            List<String> rowList = null;
            boolean flag = true;
            int index = 0;
            int length = 0 ;
            
            for (Row row : sheet) {
            	if(++length>IMPORT_MAX_ROWS) { // code by XieZl 2017-08-26
            		LOGGER.error("导入已超过最大条数限制:"+IMPORT_MAX_ROWS+", 小心内存溢出.");
            		throw new LJShopException(ErrorCode.SYSTEM_ERROR.getCode(),"导入已超过最大条数限制:"+IMPORT_MAX_ROWS+", 小心内存溢出.") ;
            	}
            	
                if (flag) {
                    flag = false;
                    continue;
                }
                
                index = 0;
                rowList = new ArrayList<>(8);
                for (Cell cell : row) {
                    if (index++ == cell.getColumnIndex()) {
                        rowList.add(WorkbookUtil.getStringCellValue(cell, "yyyyMMdd", "#.##"));
                    } else {
                        break;
                    }
                }

                if (CollectionUtils.isNotEmpty(rowList)) {
                    records.add(rowList);
                }
            }
            return records;
        } finally {
        	workbook.close();
        }
    }
    
    /**
     * 上传对象转化为excel对象
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbook(MultipartFile file) throws IOException {
        Workbook workbook = null;
        if (file.getOriginalFilename().indexOf(".xlsx") > -1) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else {
            workbook = new HSSFWorkbook(file.getInputStream());
        }
        return workbook;
    }
}
