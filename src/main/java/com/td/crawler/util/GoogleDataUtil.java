package com.td.crawler.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Component
public class GoogleDataUtil {
	
	public String finalData = "";

	public String getStringData(JSONArray jsonArray, List<String> list) {
		int i = 0;
		int key;
		//System.out.println("---------------"+ list.toString());
		//System.out.println("---------------"+ jsonArray.toString());
		for (i = 0; i < list.size() - 1; i++) {
			key = Integer.valueOf(list.get(i));
			if(jsonArray!=null && !jsonArray.isNull(key)) {
				jsonArray = jsonArray.getJSONArray(key);
			}
			//System.out.println("---------------"+ jsonArray.toString());
		} 
		if(jsonArray!=null && !jsonArray.isNull(Integer.valueOf(list.get(i)))) {
			Object obj = jsonArray.get(Integer.valueOf(list.get(i))); 
			//System.out.println("---------------"+ obj);
			return obj.toString(); 
		}
		else
			return "";
	}

	public String getStringData(JSONArray jsonArray, List<String> list, String startWith) { 
		jsonArray = getLastList(jsonArray, list); 
		finalData = "";
		getMatchedString(jsonArray, startWith);
		return finalData; 
	}
	
	public List<String> getStringList(JSONArray jsonArray, List<String> list, String startWith) { 
		jsonArray = getLastList(jsonArray, list); 
		List<String> dataList = new ArrayList<>();
		for(int i=0; i<jsonArray.length(); i++) { 
			Object obj = jsonArray.get(i); 
			if(obj instanceof JSONArray) { 
				finalData = "";
				getMatchedString(jsonArray.getJSONArray(i), startWith);
				if(finalData!="") {
					dataList.add(finalData);
				}
			}
			else { 
				if(obj.toString().startsWith(startWith)) { 
					dataList.add(obj.toString()); 
				}
			} 
		}
		return dataList; 
	}
	

	public JSONArray getLastList(JSONArray jsonArray, List<String> list) {
		int i = 0;
		for (i = 0; i < list.size(); i++) {
			jsonArray = jsonArray.getJSONArray(Integer.valueOf(list.get(i)));
		}
		return jsonArray;
	}
	
	public void getMatchedString(JSONArray jsonArray , String startWith) {  
		for(int i=0; i< jsonArray.length(); i++ ) {
			Object obj = jsonArray.get(i); 
			if(obj instanceof JSONArray) { 
				getMatchedString( jsonArray.getJSONArray(i) ,  startWith);
			}
			else { 
				if(obj.toString().startsWith(startWith)) { 
					finalData = obj.toString();
					break;
				}
			}
		}
		 
	}

	public String removeFunction(String input) {
		int sLength = "AF_initDataCallback(".length();
		if (input.endsWith(");")) {
			input = input.substring(0, input.length() - 2);
		} else if (input.endsWith("})")) {
			input = input.substring(0, input.length() - 1);
		}
		input = input.substring(sLength);
		return input;
	}

}
