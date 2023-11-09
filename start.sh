#set permission -> chmod -R 777 start.sh
#excute start file -> ./start.sh

#compile java file
javac core/camera/*.java
javac core/light/*.java
javac core/shaders/*.java
javac core/*.java
javac gmaths/*.java
javac model/basic/*.java
javac model/node/*.java
javac model/*.java
javac util/*.java
javac *.java

#excute main function
java Aliens