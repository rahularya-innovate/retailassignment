package com.apputility;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
	
	public static synchronized Position getAddressInfo(String sAddress){
		
		try {
			sAddress = URLEncoder.encode(sAddress, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
	    Position position = null;
	    try {
	        String sStatus = jsonObject.getString("status");
	        if (sStatus.equals("OK")) {
	        	position = new Position(); 
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
	
	public static synchronized float distanceBetweenPositions(Position pv1, Position pv2) {
	    double earthRadius = 3958.75;
	    double dLat = Math.toRadians(pv2.getLatitude()-pv1.getLatitude());
	    double dLng = Math.toRadians(pv2.getLongitude()-pv1.getLongitude());
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(pv1.getLatitude())) * Math.cos(Math.toRadians(pv2.getLatitude())) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist = earthRadius * c;

	    int meterConversion = 1609;

	    return new Float(dist * meterConversion).floatValue();
	   }
	
}
