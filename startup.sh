#!/usr/bin/env bash

apt-get update

#install maven
sudo apt-get install -y maven

#install java 8
sudo apt-get install -y software-properties-common python-software-properties
echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
sudo add-apt-repository ppa:webupd8team/java -y
sudo apt-get update
sudo apt-get install oracle-java8-installer
echo "Setting environment variables for Java 8.."
sudo apt-get install -y oracle-java8-set-default

#install tomcat 6
sudo apt-get install -y libtomcat6-java
sudo apt-get install -y tomcat6-common
sudo apt-get install -y tomcat6
sudo apt-get install -y apache2

#set up private instance of Tomcat
sudo apt-get -y install tomcat6-user
mkdir -p /app/local
tomcat6-instance-create /app/local/tomcat

#environment variables are imported from .bash_aliases

#install git
sudo apt-get install -y git
