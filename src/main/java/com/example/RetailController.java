package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.valueobjects.Shop;

@RestController
@RequestMapping("/retail")
public class RetailController {

	private final List<Shop> shopList = new ArrayList<Shop>();

    @RequestMapping(path="addshop",method=RequestMethod.POST)
    public Shop AddShop(@RequestBody Shop shop) {
    	shopList.add(shop);
        return shop;
    }
    
    @RequestMapping(path="shoplist",method=RequestMethod.GET)
    public List<Shop> AddShop() {
    	return shopList;
    }
}
