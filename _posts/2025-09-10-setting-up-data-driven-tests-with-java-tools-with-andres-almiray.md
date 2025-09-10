---
_schema: default
layout: post-article
title: Setting up Data Driven Tests with Java Tools with Andres Almiray
img: /assets/images/andres-almiray-video-still.jpg
---

In his Devnexus presentation, **Andres Almiray**, Senior Principal Product Manager at Oracle, addressed a challenge many developers know too well: setting up reliable, data-driven tests. He shared a story about the headaches of provisioning and sharing a database across teams—often leading to delays, inconsistent environments, and unreliable results. While in-memory databases can be quick to spin up, they rarely replicate the full behavior of production systems, creating a false sense of confidence in test coverage. The core problem is clear: how can developers run tests against a consistent, fully featured database without the overhead of managing complex infrastructure?  

To answer this, Almiray introduced two powerful open-source tools. The first is **Testcontainers**, a Java library that provisions databases and services in isolated Docker containers for testing. Containers are started before a test run and shut down afterward, ensuring each test begins with a clean slate. The second is **DBUnit**, a tool for seeding a database with datasets and validating results. Building on this, Almiray showcased **Database Writer**, a modern extension of DBUnit that simplifies the workflow with annotation support and JUnit 5 integration, making it far easier to use in modern test suites.  

His live demonstration drove the point home. Using Testcontainers, he spun up an Oracle database container on demand, then populated it with data from external YAML files via Database Writer. The result was a test setup that was both realistic and repeatable—no fragile shared environments, no incomplete in-memory substitutes. With this approach, developers can ensure tests run against production-like conditions, catching issues earlier and reducing wasted debugging time.  

The key takeaway is clear: by combining Testcontainers and Database Writer, developers can dramatically improve the reliability and maintainability of their database tests. This workflow not only makes tests more robust and repeatable but also streamlines the entire development process, allowing teams to focus less on infrastructure and more on building quality applications.  

---

## 🎥 Watch the Talk  

<iframe width="560" height="315" src="https://www.youtube.com/embed/XxRsjxR-Bwk" title="Reliable Database Testing with Testcontainers and Database Writer" frameborder="0" allowfullscreen></iframe>  

---

🚀 Be part of **Devnexus 2026**, where developers like Andres Almiray share practical strategies to solve real-world challenges. Join us to learn, connect, and sharpen your skills with the community.  

