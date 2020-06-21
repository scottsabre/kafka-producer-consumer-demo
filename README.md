# kafka-producer-consumer-demo

This example demonstrates how to create a simple Kafka producer template and Kafka consumer listener.  In addition, a simple REST API is created, which allows the REST payload to be published to a Kafka topic and consumed from a Kafka topic


### Some things you'll need (or might want)
- 7-Zip
- Development IDE (I use STS
- A REST tool like Postman to execute publish requests (if you use my controller)
- A kafka tool, like kafkatool https://www.kafkatool.com/download.html
- Stable version of Kafka from here: https://kafka.apache.org/downloads

### Kafka Producer - Publish a message to a Kafka topic
- For this demo, I started with SpringInitializr and added a test POJO model called 'Demo', then
- Created a kafka producer config and template bean: See KafkaDemoConfig.java
- Created a publisher service:  See PublishServiceImpl.java
- Created a test class that populates a Demo payload and publishes the payload to the demo topic. 
- Add/update kafka properties in application.properties: 
```xml
    kafka.bootstrapServer= localhost:9092
    kafka.demoTopic= demoTopic
```

### Test Controller - API that invokes Producer with JSON payload
- Add spring-boot-starter-web dependency.  See pom.xml
- Add new controller with injected Kafka Publisher.  The API takes our Demo object as parameter and invokes the publisher's publishMessage method.  See DemoController.java

### Kafka Consumer Listener - Consumer that listens on a topic and consumes new messages for its group ID
- Starting with the Producer implementation
- Add new configuration Beans to KafkaDemoConfig.java
```java
    @Bean
    public ConsumerFactory<String, Demo> consumerFactory()
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Demo>  kafkaListenerContainerFactory()
```
- Create ConsumerServiceImpl.java with KafkaListener annotation.  
- Add a group ID to application.properties (kafka.demoConsumerGroupId)

### Kafka Server Setup for Windows (using PowerShell)
- Extract kafka to local drive (I used 7-zip to extract to C:/)
- Open PowerShell in bin/windows folder
- Start ZooKeeper:  .\zookeeper-server-start.bat ../../config/zookeeper.properties
- Start Kafka: .\kafka-server-start.bat ../../config/server.properties
- Check for the started log message in the console: INFO [KafkaServer id=0] started (kafka.server.KafkaServer)

### Try it out!:
- Follow steps for 'Kafka Server Setup for Windows', above.
- Create a topic using KafkaTool (or command line)
- Set the demoTopic property in application.properties to the topic name.  
- If you are not running Kafka on localhost:9092, update the bootstrapServer property in application.properties
- Start the application as Spring Boot Application
- Execute REST API POST using Postman or other REST tool: localhost:8080/demo/publish (or the host that you are running from)
- Body:
```json
{
  "id": 555,
  "category": {
    "id": 333,
    "name": "Category Name"
  },
  "name": "Demo Name",
  "tags": [
    {
      "id": 111,
      "name": "Tag 1 Name"
    },
    {
      "id": 222,
      "name": "Tag 2 Name"
    }
  ],
  "status": "PENDING"
}
```
- Using kafkatool, observe that the topic with name configured by kafka.demoTopic has a new entry.
- Observe that the consumer service printed the consumed message to the console.

