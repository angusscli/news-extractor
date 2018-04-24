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

package com.cloud.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import com.cloud.sample.bean.News;
import com.cloud.sample.util.StorageUtil;
import com.google.gson.Gson;

@SuppressWarnings("serial")
@WebServlet(name = "twits", value = "/twits")
public class StockTwitsServlet extends HttpServlet {
	private final static Logger log = Logger.getLogger(StockTwitsServlet.class.getName());
	/*
	private static String[] links = new String[] {
			"https://api.stocktwits.com/api/2/streams/symbol/SPY.json"
	};
	*/
	private String lastId = null;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		
		/*
		RestTemplate restTemplate = new RestTemplate();
		*/
		String url = "https://api.stocktwits.com/api/2/streams/symbol/SPY.json";
		
		if (lastId != null) {
			url = url + "?since=" + lastId;
		}
		String tweets = Jsoup.connect(url).ignoreContentType(true).execute().body();

		JSONObject json = new JSONObject(tweets);
		String messages = json.get("messages").toString();
		JSONArray msg = new JSONArray(messages);
		
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		
		for (int i = 0 ; i < msg.length(); i++) {
			JSONObject latest = new JSONObject(msg.get(i).toString());
			
			sb.append(latest).append("\n");

			if (i==0) {
				lastId = latest.get("id").toString();
			}
			String sentimentTxt = ((JSONObject)latest.get("entities")).get("sentiment").toString();
			if (!("null").equals(sentimentTxt)) {
				JSONObject sentiment = (JSONObject) ((JSONObject)latest.get("entities")).get("sentiment");
				boolean isBullish = false;
				if ("Bullish".equals(sentiment.get("basic"))) {
					isBullish=true;
				} else {
					isBullish=false;
				}
				String body = latest.get("body").toString().replaceAll(",", "").replaceAll("\\$\\p{L}+", "").replaceAll("\n", "").replaceAll("\r\n", "").trim();
				String createAt = latest.get("created_at").toString();
				String id = latest.get("id").toString();
				
		        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		        parser.setTimeZone(TimeZone.getTimeZone("GMT"));

			        Date date;
			        String formattedDate = null;
					try {
						date = parser.parse(createAt);
						SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

				        formattedDate = formatter.format(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if (body.length()>0) {
					News news = new News();
					news.setTitle(body);
					news.setId(id);
					news.setDate(formattedDate);
					news.setType("StockTwits");

					Gson gson = new Gson();
					StorageUtil.write("data/"+news.getDate()+"_twits_"+ news.getId()+".txt", gson.toJson(news));
				}
			}
		}

		/*
		for (String link : links) {
			Document doc = Jsoup.connect(link).get();
			Elements items = doc.select("item");

			for (Element item : items) {
				News news = new News();
				news.setTitle(item.select("title").text());
				news.setDescription(item.select("description").text());
				news.setId(item.select("guid").text());
		        SimpleDateFormat parser = new SimpleDateFormat("EEE, d MMM yyyy HH:mm zzz");
		        Date date;

		        try {
					date = parser.parse(item.select("pubDate").text());

					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
					String formattedDate = formatter.format(date);
					news.setDate(formattedDate);
				} catch (ParseException e) {
						e.printStackTrace();
				}

				Gson gson = new Gson();
				StorageUtil.write("data/news/"+news.getDate()+"_"+ news.getId()+".txt", gson.toJson(news));

				
				try {
					NewsPublisher.publish(gson.toJson(news));
				} catch (Exception e) {
					log.severe(e.getMessage());
				}
				
			}
		}*/

		out.println("Done");
	}
}
