#!/bin/sh
while true
do 
	response=`curl -s http://localhost:8080/demo`
	echo "$(date +"%T") : ${response}"
	sleep 60
done
