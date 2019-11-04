# intelligent_agriculture

#### 项目介绍
智慧农业-机智物联

此项目源码采用前后端分离方式，spring boot开发后端，使用shiro进行权限控制,layui、bootstrap、jquery、html为前端,基于json进行交互,接口完全采用Restful的风格，实现按钮级权限控制，可以作为开发项目的脚手架，做为基础项目。

#### 环境要求
1. jdk8、mysql、maven

#### 初始化工作
1. 运行数据库脚本，在/文档和sql/db/boot_backend.sql
2. 配置信息在application.yml里，数据库的用户名、密码、ip、端口等，根据情况修改一下

#### 本地开发
1. 安装运行文档在/文档和sql/01 安装运行.docx
2. 右键运行启动类com.topwulian.ServerApplication
3. 访问http://localhost:8080，用户名和密码都是admin

#### 打包运行
打包后的路径:/Users/szz/IdeaProjects/intelligent_agriculture/intelligent_agriculture/target/intelligent_agriculture.jar
首次部署当前程序需要在jar包所在文件夹中执行以下命令
1. 启动程序 nohup java -jar  /usr/local/src/ig/intelligent_agriculture.jar --spring.profiles.active=prod  > nohup.log 2>&1 &
2. 退出 ctrl + c 
3. 查看日志 tail -500f nohup.out
4. 读取不同配置文件的方式启动 java -jar xxx.jar --spring.profiles.active=test

非首次部署当前程序需要在对应的文件夹中执行以下命令
1. 捕获上一个版本程序的进程 ps - ef|grep intelligent_agriculture.jar 
2. 杀死对应的进程 kill 进程号 
3. 启动程序 nohup java -jar intelligent_agriculture.jar & 
4. 退出 ctrl + c 
5. 查看日志 tail -500f nohup.out

#### 参与贡献
1. 沧海 gentoo111@163.com
2. Steel.D

#### maven安装jar包到本地
mvn install:install-file -DgroupId=com.beidouapp -DartifactId=SSDK -Dversion=4.0.2.0 -Dfile=/Users/szz/IdeaProjects/intelligent_agriculture1/intelligent_agriculture/src/main/resources/lib/SSDK-Release-4.0.2.0.jar -Dpackaging=jar

#### 数据库表参考
省气象台数据中心表结构:https://wenku.baidu.com/view/acc457e8172ded630b1cb602.html?rec_flag=default&sxts=1539093893853
