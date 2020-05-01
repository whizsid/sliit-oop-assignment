TOMCAT_HOME=/usr/share/tomcat9/
CLASS_DIR=WebContent/WEB-INF/classes/
SRC_DIR=src/main/java/com/sliit/musicstore

javac -classpath WebContent/WEB-INF/lib/*.jar -d ${CLASS_DIR} ${SRC_DIR}/**/*.java
jar cfv deploy/MusicStore.war -C WebContent .
sudo cp deploy/MusicStore.war ${TOMCAT_HOME}webapps