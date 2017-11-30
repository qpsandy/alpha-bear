#!/bin/bash

PROFILE=$NGINX_PROFILE
echo $PROFILE configuration is activated!
NGINX_PORT=80
if [ !"$APP_PORT" ]; then
echo param APP_PORT is $APP_PORT
NGINX_PORT=$APP_PORT
fi
sed -i 's/\$APP_PORT\$/'$NGINX_PORT'/g' /usr/share/nginx/bond-web/docker/conf/custom.conf
echo nginx port is set to $NGINX_PORT

if [ "$PROFILE" = "test" ] ; then
	`mv /usr/share/nginx/bond-web/docker/conf/test.conf /etc/nginx/conf.d/default.conf`
elif [ "$PROFILE" = "custom" ] ; then
	echo "$UUMS_SERVER_LIST is providing uums services"
	UUMS_SERVERS=$(echo $UUMS_SERVER_LIST | tr "," "\n")
	UUMS_UPSTREAM_STRING="upstream uums {"
	for UUMS_SERVER in $UUMS_SERVERS; do
		UUMS_UPSTREAM_STRING=${UUMS_UPSTREAM_STRING}" server "${UUMS_SERVER}" weight=6;"
	done
	UUMS_UPSTREAM_STRING=${UUMS_UPSTREAM_STRING}"}"
	echo $UUMS_UPSTREAM_STRING > /etc/nginx/conf.d/default.conf
	
	echo "$BOSS_SERVER_LIST is providing boss services"
	BOSS_SERVERS=$(echo $BOSS_SERVER_LIST | tr "," "\n")
	BOSS_UPSTREAM_STRING="upstream boss {"
	for BOSS_SERVER in $BOSS_SERVERS; do
		BOSS_UPSTREAM_STRING=${BOSS_UPSTREAM_STRING}" server "${BOSS_SERVER}" weight=6;"
	done
	BOSS_UPSTREAM_STRING=${BOSS_UPSTREAM_STRING}"}"
	echo $BOSS_UPSTREAM_STRING >> /etc/nginx/conf.d/default.conf
	
	`cat /usr/share/nginx/bond-web/docker/conf/custom.conf >> /etc/nginx/conf.d/default.conf`
else
	`mv /usr/share/nginx/bond-web/docker/conf/dev.conf /etc/nginx/conf.d/default.conf`
fi
`service nginx start`