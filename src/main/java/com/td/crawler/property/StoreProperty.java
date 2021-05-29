package com.td.crawler.property;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "custom")
public class StoreProperty {
	private Map<String, List<String>> googleStore; 
	@Override
	public String toString() {
		return "GoogleProperty [title=" + googleStore + "]";
	} 
}
