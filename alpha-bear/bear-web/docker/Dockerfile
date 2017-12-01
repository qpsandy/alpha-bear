FROM  nginx:1.10
copy bond-web.tar.gz /usr/share/nginx/bond-web.tar.gz
WORKDIR /usr/share/nginx/
RUN tar -zxvf bear-web.tar.gz
RUN rm -rf bear-web.tar.gz
RUN chmod +x /usr/share/nginx/bear-web/docker/start-nginx.sh

# Configuring time zone
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone

ENTRYPOINT sh /usr/share/nginx/bear-web/docker/start-nginx.sh