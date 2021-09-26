@echo off
%~d0
cd %~dp0
cd ..
call mvn clean package -Dmaven.test.skip=true
set JAVA_OPTS=-Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m
echo Æô¶¯ruoyi-admin.jar ruoyi-web
start cmd /c "title ruoyi-admin && java -jar %JAVA_OPTS% ../ruoyi-admin/target/ruoyi-admin.jar  --spring.profiles.active=dev &"
start cmd /c "title ruoyi-web && java -jar %JAVA_OPTS%  ../ruoyi-web/target/ruoyi-web.jar  --spring.profiles.active=dev"
pause

