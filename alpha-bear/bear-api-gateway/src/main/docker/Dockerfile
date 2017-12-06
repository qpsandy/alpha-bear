FROM registry.ssecloud.com/library/jre7

ADD boss-api-gateway.jar ./

# Configuring time zone
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone

#JAVA_OPTS SAMPLE -Xmx256m -Xms256m -XX:PermSize=64m -XX:MaxPermSize=128m
ENTRYPOINT java $JAVA_OPTS -jar boss-api-gateway.jar

#ADD Additional Arguments In CMD