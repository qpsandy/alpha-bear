#!/bin/bash

PROFILE=$NGINX_PROFILE
echo $PROFILE configuration is activated!
NGINX_PORT=80
if [ !"$APP_PORT" ]; then
echo param APP_PORT is $APP_PORT
NGINX_PORT=$APP_PORT
fi
sed -i 's/\$APP_PORT\$/'$NGINX_PORT'/g' /usr/share/nginx/bear-web/docker/conf/custom.conf
echo nginx port is set to $NGINX_PORT

if [ "$PROFILE" = "test" ] ; then
	`mv /usr/share/nginx/bear-web/docker/conf/test.conf /etc/nginx/conf.d/default.conf`
elif [ "$PROFILE" = "custom" ] ; then
	echo "$BEAR_SERVER_LIST is providing bear services"
	BEAR_SERVERS=$(echo $BEAR_SERVER_LIST | tr "," "\n")
	BEAR_UPSTREAM_STRING="upstream boss {"
	for BEAR_SERVER in $BEAR_SERVERS; do
		BEAR_UPSTREAM_STRING=${BEAR_UPSTREAM_STRING}" server "${BEAR_SERVER}" weight=6;"
	done
	BEAR_UPSTREAM_STRING=${BEAR_UPSTREAM_STRING}"}"
	echo $BEAR_UPSTREAM_STRING >> /etc/nginx/conf.d/default.conf
	
	`cat /usr/share/nginx/bear-web/docker/conf/custom.conf >> /etc/nginx/conf.d/default.conf`
else
	`mv /usr/share/nginx/bear-web/docker/conf/dev.conf /etc/nginx/conf.d/default.conf`
fi
`service nginx start`