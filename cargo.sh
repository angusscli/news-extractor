######################################################################
# This script will export the twitter.properties and start the process
#oauthConsumerKey=
#oauthConsumerSecret=
#oauthAccessToken=
#oauthAccessTokenSecret=
######################################################################
#!/bin/sh

propfile=./twitter.properties

if [ -e ${propfile} ]; then
	for prop in `cat ${propfile}`; do export $prop; done	
else
 	echo "Please create the ${propfile}"
 	exit 1 
fi

cd /opt/source/news-extractor
nohup mvn cargo:run > cargo.out &
echo $! > cargo.pid

