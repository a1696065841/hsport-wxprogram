package com.hsport.wxprogram.common.util;

import java.util.ArrayList;
import java.util.List;

public class PageList<T> {
    private long total;
    private List<T> rows = new ArrayList<>();

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageList{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }

    //提供有参构造方法，方便测试
    public PageList(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
    public PageList() {
    }
}
