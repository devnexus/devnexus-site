---
_schema: default
layout: post-article
title: Title for post and slug
img: /assets/img/posts/java.png
---

In a compelling presentation at Devnexus, **Cedric Clyburn** guided the audience from the fundamentals of containerization to advanced AI integration using **Podman** and **Podman Desktop**. He opened by explaining Podman’s key advantages, including its daemonless, open-source design and compatibility with existing container images. Unlike other container engines, Podman runs containers as child processes, enabling secure, rootless execution. For developers and operators working in hybrid cloud environments, this makes container workflows more efficient and secure. Clyburn also noted Podman’s acceptance as a **CNCF sandbox project**, signaling enterprise-ready stability and licensing for the future.  

The session came alive with a series of **live demos**. Clyburn first showed how to take a simple Python app with a Redis backend, containerize it with Podman, combine the services into a Pod, and then deploy seamlessly to both local Kubernetes and a remote OpenShift cluster. This illustrated a straightforward path for developers to take applications from a laptop to production cloud environments. He then shifted focus to AI, using the **Podman AI Lab extension** to demonstrate how to build a Retrieval-Augmented Generation (RAG) application that combines a private enterprise dataset (like a PDF) with an AI model for smarter, context-aware responses.  

In his final demo, Clyburn showcased how developers can add AI directly into existing applications. Using **LangChain4j**, he connected a Java app to a locally running AI model to process insurance claims, demonstrating how machine learning can be integrated into enterprise workflows with minimal setup. This approach scales easily, as the same configuration can run in production using a serving runtime like KServe on Kubernetes. The key takeaway: Podman and Podman Desktop provide developers with powerful, user-friendly tools to bridge the gap between containers, Kubernetes, and AI, making complex workflows approachable and production-ready.  

---

## Watch the Session  

[![Watch the talk](https://img.youtube.com/vi/BHdOs_glitg/0.jpg)](https://www.youtube.com/watch?v=BHdOs_glitg)  

🎟️ Watch the full session and join us at **Devnexus 2026** for more hands-on demos, expert insights, and the latest innovations driving software development forward.  