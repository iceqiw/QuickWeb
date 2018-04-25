#基于java8的镜像
FROM frolvlad/alpine-oraclejdk8:slim
#在镜像内创建项目的文件夹
VOLUME /tmp

RUN mkdir /data
#将打包好的代码jar包添加到镜像内
ADD  target/*.jar /data/app.jar
#启动容器内的jar包
ENTRYPOINT ["java","-jar","/data/app.jar"]