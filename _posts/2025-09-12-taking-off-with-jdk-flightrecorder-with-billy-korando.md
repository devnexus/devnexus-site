---
_schema: default
layout: post-article
title: Taking Off With JDK FlightRecorder with Billy Korando
img: /assets/images/billy-k-video-still.jpg
---
In a talk that offers invaluable insights for any Java developer, **Billy Korando**, a Java developer advocate at Oracle, provides a comprehensive introduction to **JDK Flight Recorder (JFR)**. He explains that JFR is a low-overhead, event-based diagnostic and profiling framework built directly into the JVM. The key takeaway is its minimal performance impact—typically less than 1%—which allows it to be used continuously in production environments without fear of a significant slowdown. Korando highlights JFR as a powerful tool for a variety of tasks, from routine performance analysis to pinpointing the root cause of catastrophic failures, all without requiring a developer to restart their application.

The presentation then shifts to the practical aspects of using JFR. Korando demonstrates how developers can easily start and configure recordings either at application launch or on an already running application using the **jcmd** tool. He provides a clear breakdown of the key parameters needed to manage recordings in a production environment, such as `maxsize` and `maxage`, which prevent recording files from growing uncontrollably. He also showcases the versatility of JFR by explaining how to analyze recordings using both the command-line `jfr` tool for remote analysis and the **JDK Mission Control (JMC)** graphical user interface for a more holistic view of the application's performance, including CPU and memory usage.

Korando concludes with a look at more advanced—but highly valuable—features of JFR. He shows how developers can create **custom events** by extending the `jdk.jfr.Event` class, which allows them to capture application-specific data like transaction IDs or other key metrics. A particularly powerful feature he highlights is the ability to set **thresholds** on events, ensuring they are only recorded when a specific duration is exceeded, filtering out normal, fast executions. He also mentions the `jfr scrub` utility, a handy tool for removing sensitive data from recordings before they are shared.  

The core message is clear: **JFR is not just a debugging tool—it’s a powerful and flexible framework that belongs in every Java developer’s toolkit.** Its ability to deliver production-ready insights with minimal overhead makes it indispensable for anyone striving to build robust, high-performing applications.

---

## Watch the Talk  
🎥 [JDK Flight Recorder: The Low-Overhead Profiling Tool You Should Be Using](https://www.youtube.com/watch?v=-vDNC9EMzUM)  

---

### Looking Ahead  
Devnexus is where insights like these come to life—straight from the experts shaping the future of Java. Don’t miss your chance to connect, learn, and grow with the community at **Devnexus 2026**.  

👉 [Join us at devnexus.com](https://devnexus.com)
