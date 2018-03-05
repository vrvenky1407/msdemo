# Solution Central RESTful APIs

This is a POC to showcase a few simple REST APIs built using sparkjava.com. It follows the microservice architecture. 

## To run the project in Eclipse:
- Import this maven project
- Right click Project|Maven|Update Project to update the Maven dependencies
- Right click project|Run As|Java Application and select MainApp (com.fisglobal.emtech.products). This would start the app on localhost port 9091

## Build the jar
- From within the folder on command line, execute "mvn package". 
- This builds two jar files one without the dependencies (screst.jar) and one with all the dependencies (screst-jar-with-dependencies.jar). The jar with dependencies is the best one to run with as it has all the necessary dependencies.


## To run the project from command line
- java -jar screst-jar-with-dependencies.jar  
- On Linux, if you want the app to keep running even after you exit the ssh session, use this
-- nohup java -jar screst-jar-with-dependencies.jar &

## Access the application on the browser
- Go to http://localhost:9091
- Access these endpoints
-- /product/id/{id} &nbsp; e.g. /product/id/1998 or /product/id/2466,2353,1888,1843,1998,10455
-- /product/name/{name} e.g. /product/name/profile or /product/name/positive pay