package com.Resources;

import java.util.ArrayList;
import java.util.List;

import com.Pojo.Add_Place;
import com.Pojo.Location;

public class TestDataBuild {
	
	public Add_Place addPlacePayload(String name, String language, String address){
		Add_Place ap=new Add_Place();
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setLanguage(language);
		ap.setName(name);
		ap.setWebsite("http://google.com");
		
		List<String> typeList= new ArrayList<String>();
		typeList.add("shoe park");
		typeList.add("shop");
		ap.setTypes(typeList);
		
		Location loc=new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		ap.setLocation(loc);
		
		return ap;
	}

	public String deleteBody(String place_id){
		return "{\"place_id\":\""+place_id+"\"}";
		
	}
}
