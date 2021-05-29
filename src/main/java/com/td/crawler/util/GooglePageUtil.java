package com.td.crawler.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.td.crawler.model.AppInfo;
import com.td.crawler.model.AppReview;
import com.td.crawler.property.StoreProperty;

@Component
public class GooglePageUtil {

	Map<String, List<String>> pageInfo; 
	AppInfo appInfo;
	List<AppReview> appReviewList;
	
	
	@Autowired
	StoreProperty googleProperty;
	@Autowired
	GoogleDataUtil googleDataUtil;
	

	public AppInfo getAppPage(String appID) {
		appInfo = new AppInfo();
		appReviewList = new ArrayList<>(); 
		pageInfo = googleProperty.getGoogleStore();
		appInfo.setAppID(appID);
		String url = pageInfo.get("appPage").get(0) + appID;
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements domList = doc.select("script");

			for (Element d : domList) {
				String nodeName = d.html().trim();
				if (nodeName.startsWith("AF_initDataCallback(")) {

					String rawJsonString = googleDataUtil.removeFunction(d.html());
					JSONObject jsonObject = new JSONObject(rawJsonString);
					if (jsonObject != null && jsonObject.has("key")) {
						Object jkey = jsonObject.get("key");
						if (jsonObject.has("data")) {
							JSONArray jsonArray = new JSONArray(jsonObject.get("data").toString());
							if (jkey.equals("ds:5")) {
								  parseGoogleAppInfo(jsonArray); // parseGoogleAppInfo(jsonArray); parseData(jsonArray); "";
							} else if (jkey.equals("ds:6")) {
								 parseGoogleAppRating(jsonArray); // parseGoogleAppRating(jsonArray); //parseGoogleAppRating(jsonArray);
							} else if (jkey.equals("ds:18")) {
								 parseGoogleAppReview(jsonArray); // parseGoogleAppReview(jsonArray);
							} else if (jkey.equals("ds:8")) {
								 parseGoogleAppVersion(jsonArray); // parseGoogleAppVersion(jsonArray);
							} 
							// data += jsonObject.toString() + " - ---- <br><br>";
						}
					} 
				}
			}
			if(appReviewList.size()>0)
			appInfo.setAppReview(appReviewList); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return appInfo;
	}

	public void parseGoogleAppInfo(JSONArray jsonArray) { 
		jsonArray = jsonArray.getJSONArray(0);
		// data += jsonArray.toString();
		///////////// Get Title//////////////
		// System.out.println("---------Title-----------"); 
		appInfo.setName(googleDataUtil.getStringData(jsonArray, pageInfo.get("title")));
		///////////// Get Logo//////////////
		// System.out.println("---------Logo-----------");
		appInfo.setLogo(googleDataUtil.getStringData(jsonArray, pageInfo.get("logo"), "http")); 
		///////////// Get Short Description//////////////
		// System.out.println("---------Short Description-----------"); 
		appInfo.setShortDescription(googleDataUtil.getStringData(jsonArray, pageInfo.get("shortDescription")));
		///////////// Get Description//////////////
		// System.out.println("---------Description-----------");
		appInfo.setDescription(googleDataUtil.getStringData(jsonArray, pageInfo.get("description")));  
		///////////// Get Screenshot//////////////
		// System.out.println("---------ScreenShot-----------");
		appInfo.setScreenShot( googleDataUtil.getStringList(jsonArray, pageInfo.get("screenShot"), "http")); 
		///////////// Get Userid//////////////
		// System.out.println("---------Userid-----------");
		appInfo.setUserID(googleDataUtil.getStringData(jsonArray, pageInfo.get("userID"))); 
		///////////// Get Company//////////////
		// System.out.println("---------Company-----------");
		appInfo.setCompany(googleDataUtil.getStringData(jsonArray, pageInfo.get("company"))); 
		///////////// Get Email//////////////
		// System.out.println("---------Email-----------");
		appInfo.setEmail(googleDataUtil.getStringData(jsonArray, pageInfo.get("email"))); 
		///////////// Get installed//////////////
		// System.out.println("---------installed-----------");
		appInfo.setInstalled(googleDataUtil.getStringData(jsonArray, pageInfo.get("installed"))); 
		///////////// Get category//////////////
		// System.out.println("---------category-----------");
		appInfo.setCategory(googleDataUtil.getStringData(jsonArray, pageInfo.get("category"))); 
		///////////// Get category//////////////
		// System.out.println("---------publishDate-----------");
		appInfo.setPublishDate(googleDataUtil.getStringData(jsonArray, pageInfo.get("publishDate"))); 
		///////////// Get category//////////////
		// System.out.println("---------updateDate-----------");
		appInfo.setUpdateDate(googleDataUtil.getStringData(jsonArray, pageInfo.get("updatedDate"))); 
		// System.out.println("---------info End-----------");
 
	}

	public void parseGoogleAppRating(JSONArray jsonArray) { 
		jsonArray = jsonArray.getJSONArray(0);
		// data += jsonArray.toString();
		///////////// Get ratingAvg//////////////
		//System.out.println("---------ratingAvg-----------");
		appInfo.setRatingAvg(googleDataUtil.getStringData(jsonArray, pageInfo.get("ratingAvg"))); 
		///////////// Get rating1//////////////
		//System.out.println("---------rating1-----------");
		appInfo.setRating1(googleDataUtil.getStringData(jsonArray, pageInfo.get("rating1"))); 
		///////////// Get rating2//////////////
		//System.out.println("---------rating2-----------");
		appInfo.setRating2(googleDataUtil.getStringData(jsonArray, pageInfo.get("rating2"))); 
		///////////// Get rating3//////////////
		//System.out.println("---------rating3-----------");
		appInfo.setRating3(googleDataUtil.getStringData(jsonArray, pageInfo.get("rating3"))); 
		///////////// Get rating4//////////////
		//System.out.println("---------rating4-----------");
		appInfo.setRating4(googleDataUtil.getStringData(jsonArray, pageInfo.get("rating4"))); 
		///////////// Get rating5//////////////
		//System.out.println("---------rating5-----------");
		appInfo.setRating5(googleDataUtil.getStringData(jsonArray, pageInfo.get("rating5"))); 
		///////////// Get ratingTotal//////////////
		//System.out.println("---------ratingTotal-----------");
		appInfo.setRatingTotal(googleDataUtil.getStringData(jsonArray, pageInfo.get("ratingTotal"))); 
		///////////// Get reviewTotal//////////////
		//System.out.println("---------reviewTotal-----------");
		appInfo.setReviewTotal(googleDataUtil.getStringData(jsonArray, pageInfo.get("reviewTotal"))); 
		// System.out.println("---------rating End-----------");
 
	}

	public void parseGoogleAppReview(JSONArray jsonArrayList) { 
		jsonArrayList = jsonArrayList.getJSONArray(0);
		// data += jsonArray.toString();
		for (int i = 0; i < jsonArrayList.length(); i++) {
			Object obj = jsonArrayList.get(i);
			if (obj instanceof JSONArray) {
				JSONArray jsonArray = (JSONArray) obj;
				AppReview appReview = new AppReview();
				///////////// Get reviewID//////////////
				//System.out.println("---------reviewID-----------");
				appReview.setReviewID(googleDataUtil.getStringData(jsonArray, pageInfo.get("reviewID"))); 
				///////////// Get reviewName//////////////
				//System.out.println("---------reviewName-----------");
				appReview.setReviewName(googleDataUtil.getStringData(jsonArray, pageInfo.get("reviewName"))); 
				///////////// Get reviewRating//////////////
				//System.out.println("---------reviewRating-----------");
				appReview.setReviewRating(googleDataUtil.getStringData(jsonArray, pageInfo.get("reviewRating"))); 
				///////////// Get reviewMessage//////////////
				//System.out.println("---------reviewMessage-----------");
				appReview.setReviewMessage(googleDataUtil.getStringData(jsonArray, pageInfo.get("reviewMessage"))); 
				///////////// Get reviewDate//////////////
				//System.out.println("---------reviewDate-----------");
				appReview.setReviewDate(googleDataUtil.getStringData(jsonArray, pageInfo.get("reviewDate"))); 
				///////////// Get reviewVersion//////////////
				//System.out.println("---------reviewVersion-----------");
				appReview.setReviewVersion(googleDataUtil.getStringData(jsonArray, pageInfo.get("reviewVersion")));  
				appReviewList.add(appReview);
			}
		} 
		 //System.out.println("---------review End-----------");
	}

	public void parseGoogleAppVersion(JSONArray jsonArray) { 
		// data += jsonArray.toString();
		///////////// Get appSize//////////////
		//System.out.println("---------appSize-----------");
		appInfo.setAppSize(googleDataUtil.getStringData(jsonArray, pageInfo.get("appSize"))); 
		///////////// Get appVersion//////////////
		//System.out.println("---------appVersion-----------");
		appInfo.setAppVersion(googleDataUtil.getStringData(jsonArray, pageInfo.get("appVersion"))); 
		///////////// Get appSupport//////////////
		//System.out.println("---------appSupport-----------");
		appInfo.setAppSupport(googleDataUtil.getStringData(jsonArray, pageInfo.get("appSupport"))); 
		//System.out.println("---------version End-----------");
	}

	public String parseData(JSONArray jsonArray) {
		String data = "";
		jsonArray = jsonArray.getJSONArray(0);
		for (int i = 0; i < jsonArray.length(); i++) {
			data += "<br>" + i + "------------------------<br><br>" + jsonArray.get(i).toString();
		}

		return data;
	}

}
