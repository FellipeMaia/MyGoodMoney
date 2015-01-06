@ECHO OFF

IF NOT EXIST bin (
	ECHO "Creating bin folder"
	MD bin
)

CD src\

javac -d ..\bin\ -cp ..\lib\* mygoodmoney\*.java

CD ..\bin\

jar cmf MANIFEST.txt MyGoodMoney.jar mygoodmoney\*

CD ..



IF EXIST bin\MyGoodMoney.jar (
	IF EXIST MyGoodMoney.jar (
		DEL MyGoodMoney.jar
	)
	MOVE bin\MyGoodMoney.jar
	ECHO "Done"
) ELSE (
	ECHO "Error"
)

PAUSE