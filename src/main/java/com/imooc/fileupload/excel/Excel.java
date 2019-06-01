package com.imooc.fileupload.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Excel {
    /**
     * 记录日志
     */
    private static final Logger log = LoggerFactory.getLogger(Excel.class);
    /**
     * 创建工作簿
     */
    private HSSFWorkbook workbook = new HSSFWorkbook();
    /**
     * 工作表
     */
    private List<ExcelSheet<T>> sheets ;
    /**
     * 工作表表头
     */
    private List<String> titleNameList;
    /**
     *
     */
    private List<String> getMethodList;

    public Excel(List<ExcelSheet<T>> sheets) {
        this.sheets = sheets;
    }
    public Excel(ExcelSheet<T> sheet) {
        this.sheets = new ArrayList<ExcelSheet<T>>();
        this.sheets.add(sheet);
    }

    private void inspect(){
        if (null == sheets || sheets.isEmpty()){
            throw new NullPointerException("sheets can not be null");
        }
        for(Iterator<ExcelSheet<T>> it = sheets.iterator();it.hasNext();){
            ExcelSheet sheet = it.next();
            if(null == sheet.getDataList() || sheet.getDataList().isEmpty()){
                throw new NullPointerException("dataList can not be null");
            }
            if (StringUtils.isEmpty(sheet.getTitle()) && (null == sheet.getTitles() || sheet.getTitles().isEmpty())){
                throw new NullPointerException("sheetTitle can not be null");
            }
        }
    }


    public void export(OutputStream out){
        try {
            inspect();
            createSheets();
            workbook.write(out);
        } catch (IOException e) {
            log.error("导出表格失败");
            log.error(e.getMessage());
        }
    }

    private void createSheets() {
    }
}
