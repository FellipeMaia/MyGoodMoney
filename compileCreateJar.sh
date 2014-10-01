#!/bin/bash

# check by libs
if [ ! -d lib ]; then
   echo "Creating lib folder and gettins libs.."
   mkdir lib
   
   echo "Getting jcommon"
   wget -q https://www.dropbox.com/s/32axi138x00a82n/jcommon-1.0.21.jar?dl=0 -O lib/jcommon-1.0.21.jar
   
   echo "Getting jfreechart-experimental"
   wget -q https://www.dropbox.com/s/fajzckqgeyp9nmy/jfreechart-1.0.17-experimental.jar?dl=0 -O lib/jfreechart-1.0.17-experimental.jar
   
   echo "Getting jfreechart"
   wget -q https://www.dropbox.com/s/ut8gpwvgz5kg0rb/jfreechart-1.0.17.jar?dl=0 -O lib/jfreechart-1.0.17.jar
   
   echo "Getting sqlite"
   wget -q https://www.dropbox.com/s/4k38s6naezv992i/sqlite-jdbc-3.7.15-SNAPSHOT-2.jar?dl=0 -O lib/sqlite-jdbc-3.7.15-SNAPSHOT-2.jar
fi

if [ ! -f lib/jcommon-1.0.21.jar ]; then
   echo "Getting jcommon"
   wget -q https://www.dropbox.com/s/32axi138x00a82n/jcommon-1.0.21.jar?dl=0 -O lib/jcommon-1.0.21.jar
fi

if [ ! -f lib/jfreechart-1.0.17-experimental.jar ]; then
   echo "Getting jfreechart-experimental"
   wget -q https://www.dropbox.com/s/fajzckqgeyp9nmy/jfreechart-1.0.17-experimental.jar?dl=0 -O lib/jfreechart-1.0.17-experimental.jar
fi

if [ ! -f lib/jfreechart-1.0.17.jar ]; then
   echo "Getting jfreechart"
   wget -q https://www.dropbox.com/s/ut8gpwvgz5kg0rb/jfreechart-1.0.17.jar?dl=0 -O lib/jfreechart-1.0.17.jar
fi

if [ ! -f lib/sqlite-jdbc-3.7.15-SNAPSHOT-2.jar ]; then
   echo "Getting sqlite"
   wget -q https://www.dropbox.com/s/4k38s6naezv992i/sqlite-jdbc-3.7.15-SNAPSHOT-2.jar?dl=0 -O lib/sqlite-jdbc-3.7.15-SNAPSHOT-2.jar
fi

if [ ! -d bin ]; then
   echo "Creating bin folder"
   mkdir bin
fi

cd src/
javac -d ../bin -cp "../lib/*" mygoodmoney/*.java
cp mygoodmoney/*.png mygoodmoney/*.gif ../bin/mygoodmoney/

cd ../bin

jar -cfm MyGoodMoney.jar MANIFEST.MF mygoodmoney/*

cd ..

if [ -e bin/MyGoodMoney.jar ]; then
  mv bin/MyGoodMoney.jar .
  echo "Done"
else
  echo "Error.."
fi
