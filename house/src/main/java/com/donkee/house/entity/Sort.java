package com.donkee.house.entity;

public class Sort {
    private int sid;
    private String sname;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Sort(String sname) {
        this.sname = sname;
    }

    public Sort() {
        super();
    }
}
