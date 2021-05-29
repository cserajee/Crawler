package com.td.crawler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.td.crawler.model.AppInfo;
import com.td.crawler.util.GooglePageUtil;

@Service
public class GoogleStoreService {

	@Autowired
	GooglePageUtil googlePageUtil;
	
	public AppInfo getAppData(String appID) {
		
		return googlePageUtil.getAppPage(appID);
	}
}
