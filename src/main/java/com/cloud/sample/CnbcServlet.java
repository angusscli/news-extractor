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
import java.util.Random;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cloud.sample.bean.News;
import com.google.gson.Gson;

@SuppressWarnings("serial")
@WebServlet(name = "cnbc", value = "/cnbc")
public class CnbcServlet extends HttpServlet {
	private final static Logger log = Logger.getLogger(CnbcServlet.class.getName());
	private static String[] links = new String[] { "https://www.cnbc.com/id/15839135/device/rss/rss.html",
			"https://www.cnbc.com/id/10000664/device/rss/rss.html",
			"https://www.cnbc.com/id/15839069/device/rss/rss.html" };

	public double random(double start, double end) {
		double random = new Random().nextDouble();
		double result = start + (random * (end - start));
		return result;
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();

		for (String link : links) {
			Document doc = Jsoup.connect(link).get();
			Elements items = doc.select("item");

			for (Element item : items) {
				News news = new News();
				news.setTitle(item.select("title").text());
				news.setDescription(item.select("description").text());
				news.setId(item.select("guid").text());
				news.setDate(item.select("pubDate").text());

				/*
				  LanguageServiceClient language = LanguageServiceClient.create();
				  com.google.cloud.language.v1.Document lang =
				  com.google.cloud.language.v1.Document.newBuilder()
				  .setContent(news.getTitle() + " " +
				  news.getDescription()).setType(Type.PLAIN_TEXT).build();
				  
				  Sentiment sentiment = language.analyzeSentiment(lang).getDocumentSentiment();
				  
				 news.setScore(sentiment.getScore());
				  news.setMagnitude(sentiment.getMagnitude());
				 */

				news.setScore(new Float(random(-1.0, 1.0)).floatValue());
				news.setMagnitude(new Float(random(-1.0, 1.0)).floatValue());

				/*
				  Document doc2 = Jsoup.connect(item.select("link").text()).get();
				  news.setDescription(doc2.select("div.group").get(0).text());
				 */

				Gson gson = new Gson();

				try {
					NewsPublisher.publish(gson.toJson(news));
				} catch (Exception e) {
					log.severe(e.getMessage());
				}
			}
		}

		out.println("Done");
		// out.println(doc.toString());
		// log.info(doc.toString());
	}
}
