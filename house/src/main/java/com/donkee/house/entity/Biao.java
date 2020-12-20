package com.donkee.house.entity;

public class Biao {
	private int bid;
	private int hid;
	private int eid;
	private Float dkd;
	private Float skd;
	private Float mkd;
	private String mtime;

	private String haddress;
	private String hfh;
	private String erealname;

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public double getDkd() {
		return dkd;
	}

	public void setDkd(Float dkd) {
		this.dkd = dkd;
	}

	public double getSkd() {
		return skd;
	}

	public void setSkd(Float skd) {
		this.skd = skd;
	}

	public double getMkd() {
		return mkd;
	}

	public void setMkd(Float mkd) {
		this.mkd = mkd;
	}

	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}

	public String getHaddress() {
		return haddress;
	}

	public void setHaddress(String haddress) {
		this.haddress = haddress;
	}

	public String getHfh() {
		return hfh;
	}

	public void setHfh(String hfh) {
		this.hfh = hfh;
	}

	public String getErealname() {
		return erealname;
	}

	public void setErealname(String erealname) {
		this.erealname = erealname;
	}

	public Biao(int hid, int eid, Float dkd, Float skd, Float mkd, String mtime) {
		this.hid = hid;
		this.eid = eid;
		this.dkd = dkd;
		this.skd = skd;
		this.mkd = mkd;
		this.mtime = mtime;
	}

	public Biao() {
		super();
	}

	public Biao(int bid, int hid, int eid, Float dkd, Float skd, Float mkd, String mtime) {
		this.bid = bid;
		this.hid = hid;
		this.eid = eid;
		this.dkd = dkd;
		this.skd = skd;
		this.mkd = mkd;
		this.mtime = mtime;
	}
}
