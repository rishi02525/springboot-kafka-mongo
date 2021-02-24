# springboot-kafka-mongo

Use Mongo as Kafka data source for setup event pipeline that can be read and updated from spring boot application .

Install Mongo in Master -slave mode ( Replica set )

Install Kafka,Zookeper  using Conflunet 

Add Mongo sourec connector in Kakka to read mongo event and push into kafka 

Read Kakka event using spring Kafka and update the mongo with latetst data 
