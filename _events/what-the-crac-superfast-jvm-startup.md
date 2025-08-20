---
questionAnswers: []
id: '878355'
title: What the CRaC - Superfast JVM startup
description: In a world where microservices are more and more a standard architecture
  for Java based applications running in the cloud, the JVM warmup time can become
  a limitation. Especially when you look at spinning up new instances of an app as
  response to changes in load, the warmup time can be a problem. Native images are
  one solution to solve these problems because their statically ahead of time compiled
  code simply doesn’t have to warmup and so has short startup time. But even with
  the shorter startup time and smaller footprint it doesn’t come without a drawback.
  The overall performance might be slower because of the missing JIT optimizations
  at runtime. There is a new OpenJDK project called CRaC (Coordinated Restore at Checkpoint)
  which goal it is to address the JVM warmup problem with a different approach. The
  idea is to take a snapshot of the running JVM, store it in files and restore the
  JVM at a later point in time (or even on another machine.
startsAt: '2025-03-05T16:00:00'
endsAt: '2025-03-05T17:00:00'
isServiceSession: false
isPlenumSession: false
speakers:
- id: 71f3abf1-03cf-458b-b385-e10fd110e89a
  name: Gerrit Grunwald
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
roomId: 58706
room: 'Tools and Techniques '
liveUrl:
recordingUrl:
status: Accepted
isInformed: false
isConfirmed: false
track: Core Java
format: session
slug: what-the-crac-superfast-jvm-startup

---
