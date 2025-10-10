---
_schema: default
layout: post-article
title: Containers All the Way Down! With Mark Heckler
img: /assets/images/mark-heckler.jpg
---

In his Devnexus session, **Mark Heckler** explored a challenge many developers face but few initially address—optimizing Spring Boot applications for the cloud. What often begins as a simple “containerized” project can quickly balloon into a 500 MB image bloated with unnecessary layers, full operating systems, and entire JDKs. The result: slow builds, inefficient scaling, and higher cloud costs. Heckler emphasized that while Java has long promised “Write Once, Run Anywhere,” the modern reality of containers has introduced a new layer of complexity—getting applications truly lean, secure, and cloud-native. Achieving this, he argued, is no longer optional—it’s a must-do for any production-grade system.

The key to achieving this optimization lies in smarter tooling and a shift in mindset. Developers can start by choosing lighter base images such as **Distroless**, combined with optimized JDK distributions to immediately trim unnecessary weight. However, the real breakthrough comes from **Cloud Native Buildpacks**, which are built directly into Spring Boot’s Maven and Gradle plugins. These automatically handle layering, dependency extraction, and efficient image creation without the need to handcraft Dockerfiles. Heckler also demonstrated how taking the next step—building a **Native Executable** with **GraalVM**—removes the JVM entirely at runtime, producing a minimal, self-contained binary that’s tailor-made for the cloud.

The performance gains from this transformation are significant. Using Buildpacks alone can cut image sizes by half while improving startup times, but the leap to a native executable can reduce an application to as small as **117 MB**, with startup speeds measured in milliseconds. While building a native image is slower and loses some JVM runtime flexibility, the trade-off is well worth it for production environments where memory efficiency and cold-start performance matter most. Heckler’s closing advice to developers was practical: use traditional JVM containers during development for faster iteration, then switch to native builds for production deployment to fully realize the benefits of cloud-native scalability and cost savings.

🎥 **Watch the full session:** [Devnexus 2025 – Containers All the Way Down: Optimizing Spring Boot for Cloud-Native Excellence – Mark Heckler](https://www.youtube.com/watch?v=kcp77oY8J3A)

---

Want to explore more cutting-edge Spring and cloud-native talks? Join us at **Devnexus 2026**—the ultimate developer conference where innovation meets practical engineering.  
👉 [Learn more at devnexus.com](https://devnexus.com)
