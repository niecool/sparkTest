echo begin run
cd C:\local\greenApp\hadoop-2.6.5\sbin
call start-all.cmd
cd C:\local\greenApp\apache-hive-1.1.1-bin\bin
call hive.cmd --service hiveserver2
timeout /t 10
cd C:\local\greenApp\spark-2.4.0-bin-hadoop2.6\bin
call .\spark-shell.cmd --jars C:\local\greenApp\apache-hive-1.1.1-bin\lib\mysql-connector-java-5.1.49.jar
echo run end
pause