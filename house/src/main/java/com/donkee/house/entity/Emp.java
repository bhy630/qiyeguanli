package com.donkee.house.entity;

public class Emp {
    private Integer eid;
    private Integer pid;
    private Integer jid;
    private String ename;
    private String epsw;
    private String erealname;
    private String etel;
    private String eaddress;
    private int eflag;
    private String eremark;

    private String pname;
    private String jname;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getJid() {
        return jid;
    }

    public void setJid(Integer jid) {
        this.jid = jid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEpsw() {
        return epsw;
    }

    public void setEpsw(String epsw) {
        this.epsw = epsw;
    }

    public String getErealname() {
        return erealname;
    }

    public void setErealname(String erealname) {
        this.erealname = erealname;
    }

    public String getEtel() {
        return etel;
    }

    public void setEtel(String etel) {
        this.etel = etel;
    }

    public String getEaddress() {
        return eaddress;
    }

    public void setEaddress(String eaddress) {
        this.eaddress = eaddress;
    }

    public int getEflag() {
        return eflag;
    }

    public void setEflag(int eflag) {
        this.eflag = eflag;
    }

    public String getEremark() {
        return eremark;
    }

    public void setEremark(String eremark) {
        this.eremark = eremark;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String panme) {
        this.pname = pname;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }

    public Emp(Integer eid, Integer pid, Integer jid, String ename, String epsw, String erealname, String etel, String eaddress, int eflag, String eremark) {
        this.eid = eid;
        this.pid = pid;
        this.jid = jid;
        this.ename = ename;
        this.epsw = epsw;
        this.erealname = erealname;
        this.etel = etel;
        this.eaddress = eaddress;
        this.eflag = eflag;
        this.eremark = eremark;
    }

    public Emp() {
        super();
    }
}
