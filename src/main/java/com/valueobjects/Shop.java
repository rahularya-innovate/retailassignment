package com.valueobjects;

public class Shop {
	
	private String shopName;
	
	private Double shopLongitude;
	
	private Double shopLatitude;
	
	private ShopAddress shopAddress;
	
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Double getShopLongitude() {
		return shopLongitude;
	}

	public void setShopLongitude(Double shopLongitude) {
		this.shopLongitude = shopLongitude;
	}

	public Double getShopLatitude() {
		return shopLatitude;
	}

	public void setShopLatitude(Double shopLatitude) {
		this.shopLatitude = shopLatitude;
	}
	
	public ShopAddress getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(ShopAddress shopAddress) {
		this.shopAddress = shopAddress;
	}
}
