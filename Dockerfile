FROM maven:3.6-jdk-11 as builder

WORKDIR /app/

# cache dependency libs to 1 image layer
# COPY pom.xml ./
# RUN mvn dependency:go-offline

# build package
COPY . ./

RUN mvn package

FROM tikivn/oraclejava:11-jdk

WORKDIR /app/
COPY --from=builder /app/target/lib ./hazelcast-cluster/lib/
COPY --from=builder /app/target/*.jar ./hazelcast-cluster/lib/

WORKDIR /app/hazelcast-cluster/
CMD java -cp .:./lib/* -Dfile.encoding=utf-8 $MEM_ARGS -server -XX:+UseG1GC -XX:MaxGCPauseMillis=10 -XX:ParallelGCThreads=20 -XX:ConcGCThreads=5 -XX:InitiatingHeapOccupancyPercent=70 vn.tiki.hazelcast_cluster.Main