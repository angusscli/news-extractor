#!/bin/sh
cd /opt/source/news-extractor
nohup mvn cargo:run > cargo.out &
echo $! > cargo.pid

if [[ -e /opt/source/twitter4j.properties ]] ; then
	cp /opt/source/twitter4j.properties /opt/source/news-extractor/src/main/
fi