---
_schema: default
layout: post-article
title: 'Jakarta EE: Connected Industries with an Edge with Petr Aubrecht'
img: /assets/images/petr-aub.jpg
---
In modern manufacturing, where **Digital Twins** and **smart robotics** power entire production lines, one architectural challenge stands above all others: how to connect thousands of real-time machines to the cloud—**safely, efficiently, and without latency**. In his Devnexus session, *“Jakarta EE: Connected Industries with an Edge,”* **Petr Aubrecht** addressed this challenge head-on. As he explained, the traditional cloud model simply breaks down in this environment. Factories generate massive streams of data that must be processed within milliseconds, and even brief network interruptions can disrupt or damage expensive, precision-controlled machinery. For industrial automation, resilience and uptime aren’t just performance goals—they’re **mission-critical** requirements.

Aubrecht outlined how **Edge Computing** is redefining industrial architecture by moving compute power closer to the machines themselves—right to the secure edge between the factory floor and the public cloud. This localized layer processes data in real time while maintaining synchronization with cloud systems. The session’s core insight was that **Jakarta EE** is an ideal platform for building this edge layer. Its **decades-long API stability**, **enterprise-grade reliability**, and **robust security model** make it exceptionally suited for environments that demand both longevity and precision. Aubrecht described Jakarta EE’s security model as “so simple it’s like cheating,” emphasizing how its standardized APIs make implementing secure, stable industrial systems far more straightforward than with many proprietary frameworks.

Because Jakarta EE is **vendor-neutral and open-source**, development teams can leverage its mature infrastructure—connection pooling, transaction management, messaging, and more—without reinventing the wheel. This allows teams to focus entirely on business logic, confident that their foundation is as “rock-stable” as their machinery.

One of the highlights of the session was a live demonstration where Aubrecht deployed an **Edge server** powered by **Pyra**, **Prometheus**, and **Grafana**, handling an enormous volume of concurrent data from hundreds of simulated factory robots. The demo showcased the integration of **Virtual Threads**, a powerful feature introduced in **Java 21** and part of **Jakarta EE 11**. Virtual Threads dramatically reduce the memory footprint of concurrent operations, allowing servers to handle thousands of simultaneous connections with minimal resource overhead. This innovation directly addresses one of the hardest problems in industrial systems: managing extreme concurrency without compromising stability.

The takeaway for developers was clear—**Jakarta EE has evolved into a powerful platform for industrial edge computing**, combining the reliability of enterprise Java with the performance and scalability required for real-time systems. It’s a compelling alternative to the churn of lightweight frameworks, offering a stable, open, and forward-looking foundation for mission-critical deployments at the industrial edge.

---

### 🎥 Watch the Full Session

<iframe width="800" height="450" src="https://www.youtube.com/embed/QpP6h-fIYBk" title="Connecting the Factory Floor to the Cloud: Jakarta EE on the Industrial Edge | Devnexus 2025" frameborder="0" allowfullscreen=""></iframe>

---

### ⚙️ Join Us at Devnexus 2026

Continue exploring how Jakarta EE and modern Java are driving innovation at the edge and in the cloud. Join us at **[Devnexus 2026](https://devnexus.com)** to connect with the global Java community, learn from industry experts, and experience hands-on sessions shaping the future of enterprise development.