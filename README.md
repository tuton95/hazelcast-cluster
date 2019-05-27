# Hazelcast cluster

## Build jar file

The service can be built and installed using mvn

`mvn package` 

## Run jar file

`java -jar target.jar`


## To run with maven (direct)

`mvn compile exec:java`

## Build package from docker

`docker build -t reporting-service:v1 .`