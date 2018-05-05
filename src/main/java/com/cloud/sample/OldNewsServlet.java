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
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloud.sample.util.StorageUtil;


@SuppressWarnings("serial")
@WebServlet(name = "oldnews", value = "/oldnews")
public class OldNewsServlet extends HttpServlet {
	private final static Logger log = Logger.getLogger(OldNewsServlet.class.getName());
	

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PrintWriter out = resp.getWriter();
		
		StringBuffer sb = new StringBuffer();
		
		List<String> files = StorageUtil.list("output", ".txt");
		
		for (String file : files) {
			String[] content = StorageUtil.read(file).split("\n");
			for (String line : content) {
				String[] tmp = line.split("\\|");
				if (tmp.length==5) {
					if (Double.parseDouble(tmp[3])>0) {
						out.print("{'x':'"+tmp[0]+"','y':'"+tmp[3]+"','size':'"+tmp[4]+"'},");
					} else if (Double.parseDouble(tmp[3])<0) {
						sb.append("{'x':'").append(tmp[0]).append("','y':'")
						.append(tmp[3]).append("','size':'").append(tmp[4]).append("'},")
						;
					}
				}
			}
		}
		out.println();
		out.println(sb.toString());
		/*
		for (String link : links) {
			Document doc = Jsoup.connect(link).get();
			Elements items = doc.select("item");

			for (Element item : items) {
				News news = new News();
				news.setTitle(item.select("title").text());
				news.setDescription(item.select("description").text());
				news.setId(item.select("guid").text());
				news.setType("cnbc");
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

				Gson gson = new GsonBuilder().disableHtmlEscaping().create();
				StorageUtil.write("data/"+news.getDate()+"/"+news.getDate()+"_news_"+ news.getId()+".txt", gson.toJson(news));

		}
			}
			*/
		out.println("Done");
	}
}
