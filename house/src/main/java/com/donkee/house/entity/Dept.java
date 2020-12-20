package com.donkee.house.entity;

public class Dept {
    private int pid;
    private String pname;
    private Integer pflag;  //0 正常  1 删除
    private String premark;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getPflag() {
        return pflag;
    }

    public void setPflag(Integer pflag) {
        this.pflag = pflag;
    }

    public String getPremark() {
        return premark;
    }

    public void setPremark(String premark) {
        this.premark = premark;
    }

    public Dept(String pname,int pflag, String premark) {
        this.pname = pname;
        this.pflag = pflag;
        this.premark = premark;
    }

    public Dept(int pid, String pname, Integer pflag, String premark) {
        this.pid = pid;
        this.pname = pname;
        this.pflag = pflag;
        this.premark = premark;
    }

    public Dept() {
        super();
    }
}
