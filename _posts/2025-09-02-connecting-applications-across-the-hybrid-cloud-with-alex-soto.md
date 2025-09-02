---
_schema: default
layout: post-article
title: Connecting Applications Across the Hybrid Cloud with Alex Soto
img: /assets/images/alex-soto-video-still.jpg
---
In his Devnexus presentation, **Alex Soto** addressed one of the biggest challenges in modern distributed systems: how to securely and efficiently connect applications running across on-premise, private, and public cloud environments. Traditional approaches—such as VPNs—are often expensive, complex, and difficult to scale, not to mention prone to vendor lock-in. For developers, these hurdles are more than just infrastructure headaches; they directly slow down **cloud migration, scalability, and high availability** efforts. Soto framed the issue as both a technical and business challenge: how can teams build resilient systems when their applications are scattered across different regions and providers?

The answer, Soto argued, is **Scupper**, an open-source project purpose-built to simplify secure networking for distributed applications. Instead of working at the infrastructure layer, Scupper operates at the **application layer**, creating a secure service-to-service mesh that makes services appear local, no matter where they’re actually running. Features like **automatic mTLS encryption**, fine-grained isolation, and low setup complexity make it developer-friendly, while its ability to abstract away firewall rules and IP configurations means teams don’t need deep networking expertise to take advantage of it. In practice, Scupper allows developers to manage connectivity directly, without depending on heavy-handed network administration.

To make these ideas concrete, Soto shared two live demonstrations. The first showcased **high availability**: when a backend service in one cloud failed, Scupper seamlessly rerouted traffic to a healthy instance in another cloud, with no user intervention required. The second demo illustrated **hybrid cloud migration**, where a frontend service in one environment accessed a database in another cloud as if it were local. Both examples demonstrated that Scupper isn’t just a clever concept—it’s a **practical, production-ready solution** for developers building distributed, cloud-native applications.

The key takeaway is that Scupper provides a secure, simple, and portable way to connect services across any environment. By eliminating the complexity of traditional networking approaches, it empowers developers to focus on delivering resilient applications that can scale across regions and providers without the usual cost and friction.

---

## Watch the Session

[![Watch the talk](https://img.youtube.com/vi/jE3ks8HRrr0/0.jpg)](https://www.youtube.com/watch?v=jE3ks8HRrr0)

🎟️ Don’t miss out—watch the full session above, and be sure to join us at **Devnexus 2026** for more hands-on talks, cutting-edge demos, and practical insights that help developers tackle the hardest challenges in modern software development.

---