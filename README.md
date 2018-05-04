# New Extractor App Engine Flexible environment

## Setup

### Building

    $ mvn package
  
### Deploying

    $ mvn appengine:deploy
    
### Running locally

    $ mvn cargo:run

### PubSub

	$ gcloud pubsub topics create news-topic
	$ gcloud pubsub subscriptions create news-subscription --topic news-topic
	$ gcloud pubsub subscriptions create news-subscription2 --topic news-topic

	$ gcloud pubsub topics create db-topic
	$ gcloud pubsub subscriptions create db-subscription --topic db-topic
	
	$ gcloud pubsub topics create db2-topic
	$ gcloud pubsub subscriptions create db2-subscription --topic db2-topic

	$ gcloud pubsub topics create db3-topic
	$ gcloud pubsub subscriptions create db3-subscription --topic db3-topic
	
	$ gcloud pubsub topics create txn-topic
	$ gcloud pubsub subscriptions create txn-subscription --topic txn-topic

## API

### News Crawler

    $ curl http://<server ip>:<port>/cnbc

### StockTwits Crawler

    $ curl http://<server ip>:<port>/twits
    
### Stream Demo

    $ curl http://<server ip>:<port>/demo

## Deploy Cron

### Update Cron Job
	
	$ gcloud app deploy ./src/main/appengine/cron.yaml
