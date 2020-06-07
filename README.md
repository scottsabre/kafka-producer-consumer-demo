# kafka-producer-consumer-demo

This example demonstrates how to create a simple Kafka producer template and Kafka consumer listener.  


Some things you'll need (or might want)
- 7-Zip
- Development IDE (I use STS)
- A kafka tool, like kafkatool https://www.kafkatool.com/download.html
- Stable version of Kafka from here: https://kafka.apache.org/downloads

Kafka Server Setup for Windows (using PowerShell)
- Extract kafka to local drive (I used 7-zip to extract to C:/)
- Open PowerShell in bin/windows folder
- Start ZooKeeper:  .\zookeeper-server-start.bat ../../config/zookeeper.properties
- Start Kafka: .\kafka-server-start.bat ../../config/server.properties
- Check for the started log message in the console: INFO [KafkaServer id=0] started (kafka.server.KafkaServer)

Kafka Producer 
- For this demo, I started with SpringInitializr

- Create a kafka config file

- Create a producer config file

- Create a producer template
