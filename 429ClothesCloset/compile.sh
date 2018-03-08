#!/bin/bash
javac  -d classes -classpath classes:. common/*.java database/*.java event/*.java exception/*.java impresario/*.java model/*.java userinterface/*.java
javac -classpath classes:. Closet.java
