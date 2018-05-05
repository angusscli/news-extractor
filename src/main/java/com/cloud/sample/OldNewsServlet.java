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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import com.cloud.sample.util.StorageUtil;


@SuppressWarnings("serial")
@WebServlet(name = "oldnews", value = "/oldnews")
public class OldNewsServlet extends HttpServlet {
	private final static Logger log = Logger.getLogger(OldNewsServlet.class.getName());
	

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PrintWriter out = resp.getWriter();
		
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		
		List<String> files = StorageUtil.list("output", ".txt");
		
		for (String file : files) {
			String[] content = StorageUtil.read(file).split("\n");
			for (String line : content) {
				String[] tmp = line.split("\\|");
				
				long unix = 0l;

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String dateInString = tmp[0];
				try {
				Date date = sdf.parse(dateInString);
				
			    DateTime dateTime = new DateTime(date);
			    unix = dateTime.getMillis(); 
				} catch (Exception e) {
					log.severe(e.getMessage());
				}
			   
				if (tmp.length==5) {
					if (Double.parseDouble(tmp[3])>0) {
						sb.append("{'x':'").append(unix).append("','y':'")
						.append(tmp[3]).append("','size':'").append(tmp[4]).append("'},")
						;
					} else if (Double.parseDouble(tmp[3])<0) {
						sb2.append("{'x':'").append(unix).append("','y':'")
						.append(tmp[3]).append("','size':'").append(tmp[4]).append("'},")
						;
					}
				}
			}
		}
		out.println("var chartdata3 = [{key: 'Positive', values: [");
		out.println(sb.substring(0,sb.length()-1).toString());
		out.println("]},{key: 'Negative', values: [");
		out.println(sb2.substring(0,sb2.length()-1).toString());
		out.println("]}];");
		
	}
}
