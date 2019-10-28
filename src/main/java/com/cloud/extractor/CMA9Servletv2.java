/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cloud.extractor;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cloud.sample.bean.CMAEntity;
import com.cloud.sample.bean.News;
import com.cloud.sample.util.StorageUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@SuppressWarnings("serial")
@WebServlet(name = "cnbc", value = "/cnbc")
public class CMA9Servletv2 extends HttpServlet {
	
	private final static String PROJECT_ID = "eep19-iceberg-poc-1";
	private final static String TOPIC_ID = "cma9-client-transaction-topic";
	
	private final static Logger log = Logger.getLogger(CMA9Servletv2.class.getName());
	
	private final static List<CMAEntity> CMAEntities = new ArrayList<CMAEntity>();
	
	public CMA9Servletv2() {
		
		super();
		//Set Entity Here 
		
		CMAEntity entity_RBS = new CMAEntity();
		
		entity_RBS.setClientId("J8l4MZQoiovsUm4e13_oKxUAevnozEmvHcW7QKOBoB4=");
		entity_RBS.setClientSecret("PNdny_MCWocmYyfAOJQzx_PjfBiarrJVpaxTiMrrtGw=");
		entity_RBS.setObEndPoint("https://ob.rbs.useinfinite.io/open-banking/v3.1/aisp");
		entity_RBS.setApiEndPoint("https://api.rbs.useinfinite.io");
		entity_RBS.setRedirectURL("https://37a62d74-a93b-49a8-90a0-3359cbf0dca9.example.org/redirect");
		entity_RBS.setObFinanceId("0015800000jfwB4AAI");
		
		List<String> users = new ArrayList<String>();
		users.add("123456789012@37a62d74-a93b-49a8-90a0-3359cbf0dca9.example.org");
		entity_RBS.setUsers(users);
		
		CMAEntities.add(entity_RBS);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();

		
		for (CMAEntity cmaEntity : CMAEntities) {
				
				log.info("Pushing Data to " + cmaEntity.getClientId() );
		
			
		}

		log.info("Done");
	}
	



	//	private static String[] links = new String[] {
//			"https://www.cnbc.com/id/15839135/device/rss/rss.html",
//			"https://www.cnbc.com/id/10000664/device/rss/rss.html",
//			"https://www.cnbc.com/id/15839069/device/rss/rss.html",
//			"https://www.cnbc.com/id/100003114/device/rss/rss.html",
//			"https://www.cnbc.com/id/15837362/device/rss/rss.html"
//	};
//	@Override
//	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		PrintWriter out = resp.getWriter();
//
//		for (String link : links) {
//			Document doc = Jsoup.connect(link).get();
//			Elements items = doc.select("item");
//
//			for (Element item : items) {
//				News news = new News();
//				news.setTitle(item.select("title").text());
//				news.setDescription(item.select("description").text());
//				news.setId(item.select("guid").text());
//				news.setType("cnbc");
//		        SimpleDateFormat parser = new SimpleDateFormat("EEE, d MMM yyyy HH:mm zzz");
//		        Date date;
//
//		        try {
//					date = parser.parse(item.select("pubDate").text());
//
//					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//					String formattedDate = formatter.format(date);
//					news.setDate(formattedDate);
//				} catch (ParseException e) {
//						e.printStackTrace();
//				}
//
//				Gson gson = new GsonBuilder().disableHtmlEscaping().create();
//				StorageUtil.write("data/"+news.getDate()+"/"+news.getDate()+"_news_"+ news.getId()+".txt", gson.toJson(news));
//
//				/*
//				try {
//					NewsPublisher.publish(gson.toJson(news));
//				} catch (Exception e) {
//					log.severe(e.getMessage());
//				}
//				*/
//			}
//		}
//
//		out.println("Done");
//	}
}
