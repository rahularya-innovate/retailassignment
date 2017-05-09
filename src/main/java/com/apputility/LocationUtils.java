package com.apputility;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.valueobjects.Position;


public class LocationUtils {
	public static Position getAddressInfo(String sAddress) {
	    HttpGet httpGet = new HttpGet("http://maps.google.com/maps/api/geocode/json?address=" + sAddress + "&region=it&language=it&sensor=false");
	    HttpClient client = new DefaultHttpClient();
	    HttpResponse response;
	    StringBuilder stringBuilder = new StringBuilder();
	    
	    JSONObject jsonObject = null;
	    Double lat = null;
	    Double lng = null;
	    
	    try {
	        response = client.execute(httpGet);
	        HttpEntity entity = response.getEntity();
	        InputStream stream = entity.getContent();
	        int b;
	        while ((b = stream.read()) != -1) {
	            stringBuilder.append((char) b);
	        }
	    } catch (ClientProtocolException e) {
	    } catch (IOException e) {
	    }

	    jsonObject = new JSONObject();
	    try {
	        jsonObject = new JSONObject(stringBuilder.toString());
	        //Log.d("Google Geocoding Response", stringBuilder.toString());
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    
	    //process json request here
	    Position position = new Position();
	    try {
	        String sStatus = jsonObject.getString("status");
	        if (sStatus.equals("OK")) {
	            lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng");
	            lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
	            
	            position.setLatitude(lat);
	            position.setLongitude(lng);
	            
	        }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    
	    return position;
	    
	}
}
