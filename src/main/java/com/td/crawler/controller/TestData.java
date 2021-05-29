package com.td.crawler.controller;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class TestData {

	@GetMapping()
	public String home(@RequestParam String url) {

		String title = "";
		String[] refCheck = new String[] { "", "", "" };

		title = "";

		return title;
	}

	public String getSearchData(String url) {
		String data = "";
		Document doc;
		try {
			doc = Jsoup.connect(url).get();

			Elements body = doc.select("body");
			data = body.html();
			Elements domList = doc.select("c-wiz");
			for (Element d : domList) {
				Elements links = d.select("a[href]");
				int flag = 0;
				for (Element a : links) {
					if (a.attr("href").startsWith("/store/apps/developer?id")) {
						flag = 1;
						break;
					}
				}
				if (flag == 1) {
					Elements lists = d.select("c-wiz");
					if (lists.size() > 0)
						getSearchList(lists.get(0).html());
					else
						getSearchList(d.html());
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public void getSearchList(String lists) {

	}

	public String getAppDetails(String url) {
		// itemprop

		String data = "";
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements domList = doc.select("*[itemprop]");

			for (Element d : domList) {
				String nodeName = d.nodeName().toLowerCase();
				if (nodeName.equals("img")) {
					data += d.nodeName() + ":::" + d.attr("itemprop") + "<br>------------------" + "<br>Alt:"
							+ d.attr("alt") + "<br>Url:" + d.attr("src") + " <br><br>";
				} else if (nodeName.equals("meta")) {
					data += d.nodeName() + ":::" + d.attr("itemprop") + "<br>------------------" + "<br>Data:"
							+ d.attr("content") + " <br><br>";
				} else if (nodeName.equals("a")) {
					data += d.nodeName() + ":::" + d.attr("itemprop") + "<br>------------------" + "<br>Data:"
							+ d.text() + "<br>URL:" + d.attr("href") + " <br><br>";
				} else if (nodeName.equals("div")) {
					data += d.nodeName() + ":::" + d.attr("itemprop") + "<br>------------------" + "<br>Content:"
							+ d.html() + " <br><br>";
				} else {
					data += d.nodeName() + ":::" + d.attr("itemprop") + "<br>------------------" + "<br>Data:"
							+ d.text() + " <br><br>";
				}
				// data += d.nodeName() + ":::" + d.attr("itemprop") +
				// "<br>------------------<br>" + d.html() + " <br><br>";
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public String getAppScriptListDetails(String url) {

		String data = "";
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements domList = doc.select("script");

			for (Element d : domList) {
				String nodedata = d.html().trim();
				data += d.nodeName() + "<br>------------------<br>" + nodedata + " <br><br>";
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
