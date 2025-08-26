---
_schema: default
layout: post-article
title: 'Keeping Your #Java Hot by Solving the #JVM Warmup Problem, with Simon Ritter'
img: /assets/images/simon-ritter-devnexus-video-still.jpg
---
In his session, Simon Ritter, Deputy CTO at Azul, takes on the persistent challenge of the **“JVM warmup problem”**—the performance lag that happens while the Just-In-Time (JIT) compiler optimizes code at runtime. This issue is especially critical in modern, cloud-native environments like microservices and serverless, where applications need to spin up instantly. The JVM, however, has to repeat a series of steps on every startup—class loading, interpretation, and JIT compilation—before hitting peak performance. Depending on the workload, this can take seconds to hours, leading to wasted resources and slower response times.

Ritter explores multiple strategies to address this challenge. Ahead-of-Time (AOT) compilation pre-compiles Java code into native binaries, enabling instant startup but sacrificing some of the dynamic optimizations JIT provides. Azul’s **ReadyNow** approach offers a different path: profiling an application’s startup, saving that data, and then reusing it on subsequent runs to drastically reduce warmup time. Ritter points out that the OpenJDK community is also working on similar innovations, reflecting how widespread the demand is for solving this long-standing problem.

The talk also covers newer and more experimental solutions. With the **Cloud Native Compiler**, JIT work is offloaded to a centralized service, freeing up local resources and even allowing compiled code to be shared across instances. Ritter then demonstrates **Coordinated Restore in User Space (CRaC)**, a Linux-based approach that “freezes” a running JVM and restores it instantly, cutting Spring Boot startup times from seconds to milliseconds. His conclusion: there’s no universal fix. The best solution depends on the use case—whether you’re running long-lived services or short-lived microservices.

---

## Watch the Session

[![Watch the talk](https://img.youtube.com/vi/ZXK5_MdHPbw/0.jpg)](https://www.youtube.com/watch?v=ZXK5_MdHPbw)

🎟️ Watch the full session and join us at **Devnexus 2026** for more great talks, deep dives, and insights from the people shaping the future of software.

---