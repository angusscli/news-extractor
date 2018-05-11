#!/bin/sh
cd /opt/source/news-extractor
nohup mvn cargo:run > cargo.out &

curl localhost:8080/demo
curl localhost:8080/twitterStream