package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apputility.LocationUtils;
import com.valueobjects.Position;
import com.valueobjects.Shop;

@RestController
@RequestMapping("/retail")
public class RetailController {

	private final List<Shop> shopList = new ArrayList<Shop>();

    @RequestMapping(path="addshop",method=RequestMethod.POST)
    public Shop addShop(@RequestBody Shop shop) {
    	//Build address info from Shop object
    	String sAddress = shop.getShopName() + "," + shop.getShopAddress().getShopNumber() + "," + shop.getShopAddress().getPostCode();
    	Position shopPosition = LocationUtils.getAddressInfo(sAddress);
    	
    	if(shopPosition != null){
    		shop.setShopLatitude(shopPosition.getLatitude());
        	shop.setShopLongitude(shopPosition.getLongitude());
        	shopList.add(shop);
    	}
    	
        return shop;
    }
    
    @RequestMapping(path="shoplist",method=RequestMethod.GET)
    public List<Shop> getShopList() {
    	return shopList;
    }
    
    @RequestMapping(path="shopnearby",method=RequestMethod.GET)
    public Shop getNearByShop(@RequestParam("customerLatitude") Double customerLatitude,@RequestParam("customerLongitude") Double customerLongitude) {
    	Float leastDistance = null;
    	Shop nearestShop = null;
    	Position pv1 = new Position(customerLatitude, customerLongitude);
    	Position pv2 = null;
    	
    	for (Shop eachShop : shopList) {
    		pv2 = new Position(eachShop.getShopLatitude(), eachShop.getShopLongitude());
    		if(leastDistance == null){
    			leastDistance = LocationUtils.distanceBetweenPositions(pv1, pv2);
    			nearestShop = eachShop;
    		}else{
    			if(LocationUtils.distanceBetweenPositions(pv1, pv2) < leastDistance) {
    				nearestShop = eachShop;
    			}
    		}
		}
		
    	return nearestShop;
    }
}
