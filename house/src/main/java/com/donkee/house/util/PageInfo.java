package com.donkee.house.util;

import java.util.List;

public class PageInfo<T> {

    private int pageNum = 1;
    private int pageSize = 5;
    private int total;
    private int pages;
    private int up;
    private int next;
    private List<T> list;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        pages = total / pageSize;
        if (total%pageSize!=0){
            pages++;
        }
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getUp() {
        up = pageNum-1;
        if (up < 1)up = 1;

        return up;

    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getNext() {
        next = pageNum+1;
        if (next>pages)next=pages;
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", pages=" + pages +
                ", up=" + up +
                ", next=" + next +
                ", list=" + list +
                '}';
    }
}
