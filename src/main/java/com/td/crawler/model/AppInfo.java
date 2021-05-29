package com.td.crawler.model;

import java.util.List;

import lombok.Data;

@Data
public class AppInfo {

	public String appID;
	public String appVersion;
	public String name;
	public String category;
	public String logo;
	public String shortDescription;
	public String description;
	
	public String userID;
	public String company;
	public String email;
	public String installed;
	public String appSupport;
	public String publishDate;
	public String updateDate;
	public String appSize;
	public List<String> screenShot;
	 
	public String ratingAvg;
	public String ratingTotal;
	public String reviewTotal;
	public String rating1;
	public String rating2;
	public String rating3;
	public String rating4;
	public String rating5;
	
	public List<AppReview> appReview;
	 
}
