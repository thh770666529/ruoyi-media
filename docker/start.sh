#!/bin/sh

#停止
docker-compose down

docker rmi docker_ruoyi-web

docker rmi docker_ruoyi-admin

docker rmi nginx

#进入目录
cd /home/ruoyi-media
#打包
echo 'mvn打包ruoyi-media'
mvn clean package -Dmaven.test.skip=true
echo 'cp ruoyi-admin到dockerfile目录'
cp /home/ruoyi-media/ruoyi-admin/target/ruoyi-admin.jar  /home/ruoyi-media/docker/ruoyi/admin/jar

echo 'cp ruoyi-web到dockerfile目录'
cp /home/ruoyi-media/ruoyi-web/target/ruoyi-web.jar  /home/ruoyi-media/docker/ruoyi/web/jar

#打包vue-admin
cd /home/ruoyi-media/web-ui/vue-admin
#下载依赖
npm install --registry=https://registry.npm.taobao.org
#打包
npm run build:prod
#复制
cp /home/ruoyi-media/web-ui/vue-admin/dist -r /home/ruoyi-media/docker/nginx/html/admin

#打包vue-web
cd /home/ruoyi-media/web-ui/vue-web
#下载依赖
npm install --registry=https://registry.npm.taobao.org
#打包
npm run build:prod

#复制
cp /home/ruoyi-media/web-ui/vue-web/dist -r /home/ruoyi-media/docker/nginx/html/web

cd /home/ruoyi-media/docker



#启动
docker-compose up
