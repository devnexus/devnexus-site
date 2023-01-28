---
questionAnswers: []
id: '407998'
title: 'The Cloud Native Compiler: JIT-as-a-Service'
description: "Adaptive, just in time (JIT) compilation provides a massive performance
  improvement to JVM-based applications compared to only using an interpreter.  The
  downside of this is that applications have to compile frequently used methods as
  the application is running.  This can lead to reduced throughput and slower response
  times.  Another drawback is that each time an application is started, it must perform
  the same analysis to identify hot spot methods and compile them.\r\n\r\nWhen running
  an application in the cloud, the elastic nature of resources provides the ability
  to change and improve the dynamics of how the JIT compiler works.\r\n\r\nIn this
  session, we'll look at Azul's work to move the JIT compiler into a centralized service
  that can be shared by many JVMs.  This provides many advantages, such as caching
  compiled code for instant delivery when restarting the same application or spinning
  up new instances of the same service.  In addition, it removes the workload from
  individual JVMs, allowing them to deliver more transactions per second of application
  work.  Finally, there is the opportunity to apply considerably more compute resources
  to enable complex optimizations to be used that wouldn't be practical in a single
  JVM.\r\n"
startsAt: 
endsAt: 
isServiceSession: false
isPlenumSession: false
speakers:
- id: 709043d1-5754-4459-a8d5-6dd2f5619874
  name: Simon Ritter
categories:
- id: 43783
  name: Track
  categoryItems:
  - id: 143432
    name: Java Platform
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
track: Java Platform
format: session
slug: the-cloud-native-compiler-jit-as-a-service

---
