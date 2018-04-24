package com.cloud.sample;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.cloud.sample.bean.News;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.rpc.ApiException;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;


public class ExtractTest {
	private final static Logger log = Logger.getLogger(ExtractTest.class.getName());
	private final static String PROJECT_ID = "traded-risk-project-1";
	private final static String TOPIC_ID = "news-topic";
	
	/*
	@Test
	public void writeTest() throws IOException {
		StorageUtil.write("test.csv", "123");
	}*/
	
	@Test
	public void testDate() throws ParseException {
		/*
	       String input = "Thu Jun 18 20:56:02 EDT 2009";
	        SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
	        */
		String input = "2018-04-24T07:27:52Z";
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        parser.setTimeZone(TimeZone.getTimeZone("GMT"));
        
	        Date date = parser.parse(input);
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	        String formattedDate = formatter.format(date);
	        log.info(formattedDate);
	}
	
	//@Test
	public void extractTest2() throws Exception {
		String url = "https://api.stocktwits.com/api/2/streams/symbol/SPY.json";
		
		String doc = Jsoup.connect(url).ignoreContentType(true).execute().body();
		log.info(doc);
	}
	
	//@Test
	void extractTest() throws Exception {
	    Document doc = Jsoup.connect("https://www.cnbc.com/id/15839135/device/rss/rss.html").get();
	    
	    Elements items = doc.select("item");
	    
	    for (Element item : items) {
	    		News news = new News();
	    		news.setTitle(item.select("title").text());
	    		news.setDescription(item.select("description").text());
	    		news.setId(item.select("guid").text());
	    		news.setDate(item.select("pubDate").text());
	    		
	    	    	LanguageServiceClient language = LanguageServiceClient.create();
	    	      com.google.cloud.language.v1.Document lang = com.google.cloud.language.v1.Document.newBuilder()
	    	          .setContent(news.getTitle() + " " + news.getDescription()).setType(Type.PLAIN_TEXT).build();

	    	      Sentiment sentiment = language.analyzeSentiment(lang).getDocumentSentiment();
	    	      
	    	      /*
	    	      news.setScore(sentiment.getScore());
	    	      news.setMagnitude(sentiment.getMagnitude());
	    	      */
	    	      
	    		/*
	    			Document doc2 = Jsoup.connect(item.select("link").text()).get();
	    			news.setDescription(doc2.select("div.group").get(0).text());
	    			*/
	    }
	}
	
	//@Test
	void pubSubTest() throws Exception {
		 ProjectTopicName topicName = ProjectTopicName.of(PROJECT_ID, TOPIC_ID);
		 

		    Publisher publisher = null;
		    try {
		      // Create a publisher instance with default settings bound to the topic
		      publisher = Publisher.newBuilder(topicName).build();

		        String message = "message-test";

		        // convert message to bytes
		        ByteString data = ByteString.copyFromUtf8(message);
		        PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
		            .setData(data)
		            .build();

		        //schedule a message to be published, messages are automatically batched
		        ApiFuture<String> future = publisher.publish(pubsubMessage);

		        // add an asynchronous callback to handle success / failure
		        ApiFutures.addCallback(future, new ApiFutureCallback<String>() {

		          @Override
		          public void onFailure(Throwable throwable) {
		            if (throwable instanceof ApiException) {
		              ApiException apiException = ((ApiException) throwable);
		              // details on the API exception
		              System.out.println(apiException.getStatusCode().getCode());
		              System.out.println(apiException.isRetryable());
		            }
		            System.out.println("Error publishing message : " + message);
		          }

		          @Override
		          public void onSuccess(String messageId) {
		            // Once published, returns server-assigned message ids (unique within the topic)
		            System.out.println(messageId);
		          }
		        });
		    } finally {
		      if (publisher != null) {
		        // When finished with the publisher, shutdown to free up resources.
		        publisher.shutdown();
		      }
		    }
		 
	}
	
	
}
