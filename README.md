# kafka-producer-consumer-demo

This example demonstrates how to create a simple Kafka producer template and Kafka consumer listener.  


Some things you'll need (or might want)
- 7-Zip
- Development IDE (I use STS)
- A kafka tool, like kafkatool https://www.kafkatool.com/download.html
- Stable version of Kafka from here: https://kafka.apache.org/downloads

Kafka Producer 
- For this demo, I started with SpringInitializr and added a test POJO model called 'Demo', then
- Created a kafka producer config and template bean: See @KafkaProducerConfig
- Created a publisher service:  See @PublishServiceImpl
- Created a test class that populates a Demo payload and publishes the payload to the demo topic. 

To run:
- Follow steps for 'Kafka Server Setup for Windows', below.
- Create a topic using KafkaTool (or command line)
- Set the demoTopic property in application.properties to the topic name.  
- If you are not running Kafka on localhost:9092, update the bootstrapServer property in application.properties
- Remove comments from the following line in ProducerTest: 

```java
//TODO: uncomment this line to run demo-> publishService.publishMessage(createDemoModelForTest());
```
- Run ProducerTest as JUnit
- Check demoTopic for new entry.

Kafka Consumer Listener
~~~~~~ coming soon! ~~~~~~~

Kafka Server Setup for Windows (using PowerShell)
- Extract kafka to local drive (I used 7-zip to extract to C:/)
- Open PowerShell in bin/windows folder
- Start ZooKeeper:  .\zookeeper-server-start.bat ../../config/zookeeper.properties
- Start Kafka: .\kafka-server-start.bat ../../config/server.properties
- Check for the started log message in the console: INFO [KafkaServer id=0] started (kafka.server.KafkaServer)
