---
questionAnswers: []
id: '753353'
title: Keeping Your Java Hot by Solving the JVM Warmup Problem
description: "Java bytecodes and class files deliver on the original vision of “write
  once, run anywhere”.  Using a Just-in-Time (JIT) compiler allows JVM-based applications
  to compile only the code that’s being used frequently and optimise it precisely
  for how it is being used.  Using techniques like speculative optimisation can often
  deliver better performance than static, Ahead-of-Time (AOT) compiled code.\r\n\r\nHowever,
  this flexibility and performance comes at a cost.  Each time the JVM starts an application,
  it must perform the same analysis to find hot spots of code and compile them.  This
  is referred to as the application warmup time.\r\n\r\nIn this session, we’ll look
  at several approaches to how this problem can be alleviated or even eliminated.
  \ Specifically:\r\n\r\n•\tGenerating a JIT compiler profile of a running, warmed-up
  application that can be reused when the same application is restarted, eliminating
  the need for much of the JIT compilation.\r\n•\tOpenJDK Project Leyden's ahead-of-time
  class loading, method profiling and code compilation.\r\n•\tDecoupling the JIT compiler
  from the JVM for a Cloud environment.  Providing a centralised JIT-as-a-Service
  allows caching of compiled code and offloading the compilation work when new code
  must be compiled.\r\n•\tCreating a checkpoint of a running application.  This includes
  all application state (heap, stack, etc.) in addition to the JIT-compiled code.
  \ Project CRaC will be used as an example.\r\n\r\nAt the end of the session, you’ll
  be all set to keep your Java hot!\r\n"
startsAt:
endsAt:
isServiceSession: false
isPlenumSession: false
speakers:
- id: 709043d1-5754-4459-a8d5-6dd2f5619874
  name: Simon Ritter
categories:
- id: 81703
  name: Track
  categoryItems:
  - id: 290606
    name: Core Java
  sort: 0
- id: 81704
  name: Session Format
  categoryItems:
  - id: 290619
    name: session
  sort: 1
roomId:
room:
liveUrl:
recordingUrl:
status: Accepted
isInformed: true
isConfirmed: true
track: Core Java
format: session
slug: keeping-your-java-hot-by-solving-the-jvm-warmup-problem

---
