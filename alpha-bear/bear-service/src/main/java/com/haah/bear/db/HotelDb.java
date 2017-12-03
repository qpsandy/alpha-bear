package com.haah.bear.db;

import java.util.Date;

public class HotelDb {
	
	private String poiId;
	private String hotelname;
	private String fx;
	private String zc;
	private String kc;
	private String price;
	private String priceChange;
	private Date updatetime;
	
	public HotelDb(String poiId, String hotelname, String fx, String zc,
			String kc, String price, String priceChange, Date updatetime) {
		super();
		this.poiId = poiId;
		this.hotelname = hotelname;
		this.fx = fx;
		this.zc = zc;
		this.kc = kc;
		this.price = price;
		this.priceChange = priceChange;
		this.updatetime = updatetime;
	}
	public String getPoiId() {
		return poiId;
	}
	public void setPoiId(String poiId) {
		this.poiId = poiId;
	}
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	public String getFx() {
		return fx;
	}
	public void setFx(String fx) {
		this.fx = fx;
	}
	public String getZc() {
		return zc;
	}
	public void setZc(String zc) {
		this.zc = zc;
	}
	public String getKc() {
		return kc;
	}
	public void setKc(String kc) {
		this.kc = kc;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPriceChange() {
		return priceChange;
	}
	public void setPriceChange(String priceChange) {
		this.priceChange = priceChange;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}
