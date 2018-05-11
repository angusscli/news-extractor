#!/bin/sh

cd /opt/source/news-extractor
nohup mvn cargo:run > cargo.out &

( tail -f -n0 cargo.out & ) | grep -q "started on port \[8080\]"

#curl localhost:8080/demo
#curl localhost:8080/twitterStream
echo "started"
