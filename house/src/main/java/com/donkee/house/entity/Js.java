package com.donkee.house.entity;

public class Js {
    private int jid;
    private String jname;

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }

    public Js(String jname) {
        this.jname = jname;
    }

    public Js() {
        super();
    }
}
