package com.cloud.sample;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;

//import twitter4j.Status;
//import twitter4j.StatusDeletionNotice;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterTest {
	private final static Logger log = Logger.getLogger(TwitterTest.class.getName());
	private final static String PROJECT_ID = "traded-risk-project-1";
	private final static String TOPIC_ID = "news-topic";
	
	//@Test
	@Test
	public void extractTest() throws Exception {
		//System.out.println("Hello");

		//FilterQuery filtered = new FilterQuery()
		
		 ConfigurationBuilder cb = new ConfigurationBuilder();
	        cb.setDebugEnabled(true)
	          .setOAuthConsumerKey("")
	          .setOAuthConsumerSecret("")
	          .setOAuthAccessToken("")
	          .setOAuthAccessTokenSecret("");
	        
	        TwitterStreamFactory tf = new TwitterStreamFactory(cb.build());
	        ;
	        
	        FilterQuery filtered = new FilterQuery();

			String keywords[] = {
		    "#AlphaStock #FC4 #FC5","#SPY"
		  };
		filtered.track(keywords);
		filtered.language("en");
				 
		StatusListener listener = new StatusListener(){
		        public void onStatus(Status status) {
		            System.out.println(status.getUser().getName() + " : " + status.getText());
		    }
		    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
		    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}

		    public void onException(Exception ex){
		        ex.printStackTrace();
		    }
		    
			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub
				  System.out.println("Got scrub_geo event userId:" + arg0 + " upToStatusId:" + arg1);
			}
			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				  System.out.println("Got stall warning:" + arg0);
				
			}
		};
		//TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		TwitterStream twitterStream = tf.getInstance();
		twitterStream.addListener(listener);

	
		
//		https://twitter.com/search-home
//		https://twitter.com/search-advanced
//		stream.filter(
//                track=['bigdata', 'kubernetes', 'bigquery', 'docker', 'google',
//                       'googlecloud', 'golang', 'dataflow',
//                       'containers', 'appengine', 'gcp', 'compute',
//                       'scalability', 'gigaom', 'news', 'tech', 'apple',
//                       'amazon', 'cluster', 'distributed', 'computing',
//                       'cloud', 'android', 'mobile', 'ios', 'iphone',
//                       'python', 'recode', 'techcrunch', 'timoreilly']
//                )
		// sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
		if (keywords.length==0) {
			twitterStream.sample();
		} else { 
		    twitterStream.filter(filtered);
		}
		
		System.out.println("connected");
		//twitterStream.setOAuthAccessToken(accessToken);
	}
	
//	@Test
//	public void extractTestTimeline() throws Exception {
//		//System.out.println("Hello");
//	
//			  // The factory instance is re-useable and thread safe.
//		    Twitter twitter = TwitterFactory.getSingleton();
//		    List<Status> statuses = twitter.getHomeTimeline();
//		    System.out.println("Showing home timeline.");
//		    for (Status status : statuses) {
//		        System.out.println(status.getUser().getName() + ":" +
//		                           status.getText());
//		    }
//	    
//		
//		
//	}
	    
}