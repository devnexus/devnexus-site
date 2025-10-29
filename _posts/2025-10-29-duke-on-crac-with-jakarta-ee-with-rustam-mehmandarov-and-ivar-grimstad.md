---
_schema: default
layout: post-article
title: Duke on CRaC with Jakarta EE with Rustam Mehmandarov and Ivar Grimstad
img: /assets/images/rustam-ivar.jpg
---

For Java developers, slow startup times have long been the Achilles’ heel of deploying microservices in the cloud. In serverless and containerized environments, those extra seconds between cold start and readiness directly impact scalability, responsiveness, and cost efficiency. At **Devnexus**, **Rustam Mehmandarov** and **Ivar Grimstad** took the stage to present a cutting-edge solution that redefines this equation: **Coordinated Restore at Checkpoint (CRaC)**.  

Mehmandarov, a Senior Software Engineer, described CRaC as a kind of *“hibernate mode”* for Java applications. By capturing a fully initialized and warmed-up JVM state—including loaded classes, compiled code, and pre-initialized frameworks—CRaC allows an application to save that state to disk. When restarted, instead of performing time-consuming reinitialization, the application simply restores itself from that checkpoint, becoming *“instant-on.”* The result is a dramatic improvement in startup speed, making Java-based microservices far more competitive in the dynamic world of cloud-native deployment and autoscaling.

To show developers how to bring CRaC into existing **Jakarta EE** applications, Grimstad walked through the practical integration steps that developers can apply today, even before an official specification is finalized. The approach revolves around implementing CRaC’s `Resource` interface, which defines two essential lifecycle methods: `beforeCheckpoint()` and `afterRestore()`. These hooks allow developers to safely close resources like sockets, database connections, and file handles before saving the checkpoint, and reinitialize them after restore. The team demonstrated how Jakarta’s **CDI (Contexts and Dependency Injection)** events can be leveraged to trigger these methods, ensuring that the application maintains integrity across checkpoints.  

Mehmandarov further illustrated the process of committing a container’s running state into a *checkpoint image*—a lightweight, optimized artifact ready for near-instant startup. The live demo compared two popular Jakarta EE runtimes, **Helidon** and **Quarkus**, showing that performance gains depend on the runtime implementation. The Helidon app’s startup time dropped from over two seconds to under one, while Quarkus saw more modest improvements. This comparison underscored an important point: while CRaC is already transformative, its full potential will only be realized when runtimes and frameworks fully embrace it.

The session closed with a forward-looking challenge to the developer community: help formalize a **Jakarta CRaC specification**. Such a standard—complete with annotations like `@BeforeCheckpoint`—would establish a unified model for instant-on applications, streamline adoption across vendors, and strengthen Jakarta EE’s role as a first-class platform for performant, cloud-native workloads. As Mehmandarov and Grimstad emphasized, the future of enterprise Java is not just about resilience or scalability—it’s about *speed*. And with CRaC, “instant-on” might soon become the new normal.

---

### 🎥 Watch the Full Session  

<iframe width="800" height="450" src="https://www.youtube.com/embed/BujrB0_vbeY" title="Instant-On Jakarta EE: Cracking the Startup Code with CRaC | Devnexus 2025" frameborder="0" allowfullscreen></iframe>

---

### 🚀 Join Us at Devnexus 2026  

Be part of the conversations shaping the next generation of Java innovation. Connect with the community, learn from world-class engineers, and explore what’s next for enterprise development at **[Devnexus 2026](https://devnexus.com)**.
