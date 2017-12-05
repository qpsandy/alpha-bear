package com.haah.bear.db;

import java.io.Serializable;
import java.util.Date;

public class HotelDb extends BaseDb implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int poiId;
	private String hotelname;
	private String fx;
	private String zc;
	private String kc;
	private String price;
	private String priceChange;
	private Date updatetime;
	
	public int getPoiId() {
		return poiId;
	}
	
	public void setPoiId(int poiId) {
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
