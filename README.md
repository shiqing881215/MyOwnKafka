# MyOwnKafka
My personal Kafka practice 

To run this practice, you need to first follow the QuickStart section here to setup Kafka locally https://kafka.apache.org/quickstart
<br/><br/>

General Steps : <br/>
1. Clone the repo locally <br/>
2. Start ZooKeeper server locally <br/>
3. Start Kafka server locally <br/>
4. Create two topics : "fast-topic" & "slow-topic" <br/>
5. Run the Producer and check the result <br/>
6. Run the Consumer and check the result <br/>

Notice once you create the topic, Kafka store the partiton for a certain time. <br/>
You can try to run the Producer and Consumer again, and from the Consumer offset you can notice it's incrementally adding offset based on the last time result. 
