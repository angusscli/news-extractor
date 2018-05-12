######################################################################
#This script will export the twitter.properties and start the process

#oauthConsumerKey=
#oauthConsumerSecret=
#oauthAccessToken=
#oauthAccessTokenSecret=
######################################################################
#!/bin/sh

cd /opt/source/news-extractor
nohup mvn cargo:run > cargo.out &
echo $! > cargo.pid

