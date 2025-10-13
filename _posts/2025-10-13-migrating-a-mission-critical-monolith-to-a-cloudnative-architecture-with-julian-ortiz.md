---
_schema: default
layout: post-article
title: >-
  Migrating a Mission Critical Monolith to a Cloud Native Architecture with
  Julian Ortiz
img: /assets/images/julian-ortiz-video-still.jpg
---
In his Devnexus session, **Julian Ortiz** presented a remarkable case study on modernizing Oracle’s **Order and Service Management (OSM)** product — a massive, mission-critical Java EE monolith — into a fully cloud-native architecture. OSM powers millions of transactions daily for major telecommunications companies, making any modernization effort a high-stakes operation. The challenge was clear: how do you evolve such a complex, business-critical system without disrupting service, breaking customer integrations, or jeopardizing reliability?

The legacy deployment on WebLogic made development and testing slow, feature rollouts cumbersome, and scalability difficult. However, an outright “rip and replace” approach wasn’t an option. Instead, Ortiz and his team designed a **phased transformation strategy**, emphasizing stability, continuity, and incremental improvement. The initial phase focused on **containerization** — lifting the existing WebLogic domain into Kubernetes with minimal changes to the application. This alone delivered immediate wins: improved provisioning, scalability through metrics-based autoscaling, and enhanced observability. The monolith became portable across both on-premise and public clouds, all while preserving existing customer operations.

With stability achieved, the second phase shifted to innovation. Using the **Strangler Fig Pattern**, new features were developed as microservices around the existing monolith. The team selected **Helidon MicroProfile** as their framework of choice — a modern, cloud-native Java framework aligned with Jakarta EE standards, enabling fast, modular development while maintaining interoperability. A major milestone was adopting **Helidon 4.0**, rebuilt atop Java’s **Virtual Threads (Project Loom)**. This upgrade yielded measurable gains, including a **63% reduction in platform thread usage** and **30% lower CPU consumption**, driving both performance efficiency and cost savings at scale. To further strengthen reliability, the team implemented **Fault Tolerance patterns**, notably **Bulkhead isolation**, ensuring that mission-critical JMS order queues remained stable even under transient system faults or spikes in demand.

Ortiz’s session ultimately delivered a pragmatic message: modernization doesn’t require abandoning what already works. By applying thoughtful engineering practices — containerization first, microservices second — and leveraging modern Java capabilities, Oracle transformed a decades-old system into a performant, resilient, cloud-native platform. This case study underscores that **the path to modernization is evolutionary, not revolutionary**, and that developers can achieve enterprise-grade agility through measured, strategic steps.

<div style="margin-top: 1.5em;">
<iframe width="560" height="315" src="https://www.youtube.com/embed/vQrvhN5lEIA" title="Devnexus 2025 - Migrating a Mission-Critical Monolith to Cloud-Native: An Oracle Case Study" frameborder="0" allowfullscreen=""></iframe>
</div>

---

Want to learn from real-world engineering transformations like this one?<br>
Join us at **Devnexus 2026**, where developers, architects, and innovators share how they’re building the next generation of enterprise software.<br>
👉 [Learn more at devnexus.com](https://devnexus.com)