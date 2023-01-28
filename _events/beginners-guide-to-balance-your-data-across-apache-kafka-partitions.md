---
questionAnswers: []
id: '406814'
title: Beginners guide to balance your data across Apache Kafka partitions
description: "Apache Kafka is a distributed system. At the heart of Apache Kafka is
  a set of brokers, which allow to store the records persistently across different
  topics. Topics, in turn, are split into partitions. Dividing topics into such pieces
  allows us to use data from multiple partitions in parallel, so that producers and
  consumers can work with data simultaneously and achieve higher data throughput.\r\n\r\nSuch
  parallelization is the key to a performant cluster, however it comes with a price.
  The thing is, reading from multiple partitions will eventually mess up the order
  of records, meaning  that the resulting order will be different from when the data
  was pushed into the cluster. \r\n\r\nThis happens because when consuming data from
  multiple partitions, the order of partitions is not guaranteed. Instead, we must
  rely on the order of the records within a single partition, where the data is guaranteed
  to maintain the original sequence. We need to use this characteristic of Apache
  Kafka to our advantage in those cases where the ordering of the records is important
  for our system.\r\n\r\nTherefore, when building our product architecture we should
  carefully weigh up how we will balance records across partitions and what mechanisms
  we will use to ensure that the sequence of the messages remains correct when data
  is read by multiple consumers. And even more importantly, how to achieve this and
  still maintain good performance.\r\n\r\nIn this talk we'll discuss mechanisms you
  can use to balance your data, such as keys and custom partitioners, but also practices
  that will help you to balance data evenly and produce and consume data efficiently.\r\n\r\nIf
  you are fresh to Apache Kafka, or you're looking for good practices to design your
  partitions and avoid common pitfalls, you'll find this session useful!"
startsAt: 
endsAt: 
isServiceSession: false
isPlenumSession: false
speakers:
- id: 5ecff6ff-7704-433e-a38f-a057d05eacec
  name: Olena Kutsenko
categories:
- id: 43783
  name: Track
  categoryItems:
  - id: 143427
    name: Architecture
  sort: 0
- id: 43785
  name: Session Format
  categoryItems:
  - id: 143440
    name: session
  sort: 2
roomId: 
room: 
liveUrl: 
recordingUrl: 
track: Architecture
format: session
slug: beginners-guide-to-balance-your-data-across-apache-kafka-partitions

---
