package com.imooc.fileupload.excel;

import java.util.List;
import java.util.Properties;

public class ExcelSheet<T> {
    private String title;
    private Properties titles;
    private List<T> dataList;

    public ExcelSheet(String title, List<T> dataList) {
        this.title = title;
        this.dataList = dataList;
    }

    public ExcelSheet(Properties titles, List<T> dataList) {
        this.titles = titles;
        this.dataList = dataList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Properties getTitles() {
        return titles;
    }

    public void setTitles(Properties titles) {
        this.titles = titles;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
