package com.td.crawler.model;

import lombok.Data;

@Data
public class AppReview { 
	public String reviewID;
	public String reviewName;
	public String reviewRating;
	public String reviewMessage;
	public String reviewDate;
	public String reviewVersion; 
}
