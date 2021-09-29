Create execuatble JAR file and run the Jar with below steps
1. Press “Windows + R” button and type ‘cmd’ as shown below.
2. Go to the Java file location by typing: cd D:\BookRestApp.
3. Enter the command “gradle build”. This command will create the 
	executable JAR file under build/libs folder with the name of root folder 
	(for e.g.: BookRestApp.jar).
4.Go to the command prompt and reach root BookRestApp/build/libs.	
5.Enter the command: java –jar BookRestApp.jar
6.Verify the result. Verify the result from the request response in additional.txt 
	present inside root folder