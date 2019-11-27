# New Extractor App Engine Flexible environment

## Setup

### Building

    $ mvn package
  
### Deploying

    $ mvn appengine:deploy
    
### Running locally

    $ mvn clean install -Dmaven.test.skip=true
    $ mvn cargo:run
	
### PubSub

	$ gcloud pubsub topics create twitter-topic
	$ gcloud pubsub subscriptions create twitter-subscription --topic twitter-topic

	$ To clear the message
	$ gcloud pubsub subscriptions delete twitter-subscription
		
## API

### News Crawler

    $ curl http://<server ip>:<port>/cnbc

### StockTwits Crawler

    $ curl http://<server ip>:<port>/twitter
    
### Stream Demo

    $ curl http://<server ip>:<port>/demo


### Twistter Stream Demo

	Start:
    $ curl http://<server ip>:<port>/twisterStream
    
    Stop:
    curl "http://localhost:8080/twitterStream?action=stop"
    
    Update Keywords:
    	curl "http://localhost:8080/twitterStream?action=update&keywords=FC4&keyword=FC5"
    or
    	curl "http://localhost:8080/twitterStream?action=update&keywords=FC4%20FC5&keyword=%23trading"
    
    Show current Keywords:
    curl "http://localhost:8080/twitterStream?action=show"

## Deploy Cron

### Update Cron Job
	
	$ gcloud app deploy ./src/main/appengine/cron.yaml
