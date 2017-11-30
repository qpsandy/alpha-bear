FROM  registry.ssecloud.com/library/nginx:1.10
copy bond-web.tar.gz /usr/share/nginx/bond-web.tar.gz
WORKDIR /usr/share/nginx/
RUN tar -zxvf bond-web.tar.gz
RUN rm -rf bond-web.tar.gz
RUN chmod +x /usr/share/nginx/bond-web/docker/start-nginx.sh

# Configuring time zone
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone

ENTRYPOINT sh /usr/share/nginx/bond-web/docker/start-nginx.sh