---
_schema: default
layout: post-article
title: Functional Spring Boot with John Burns
img: /assets/images/john-burns.jpg
---

At **Devnexus**, John Burns, a Platform Engineer at **GrubHub**, delivered a thought-provoking session that questioned one of the most ingrained habits in modern Java development: the heavy reliance on annotations in Spring Boot. While annotations once rescued developers from the verbose XML configurations of the early Spring era, Burns argued that they’ve now introduced a new layer of complexity — and even performance overhead. From slow startup times caused by reflection and classpath scanning to a steep learning curve for newcomers, the “magic” behind annotations has become both a strength and a liability. In today’s world of ephemeral, auto-scaling microservices and serverless functions, this hidden cost is no longer acceptable. Burns challenged developers to move past implicit configurations and reclaim explicit control over their code.

The proposed alternative? **Programmatic Configuration** — or as Burns calls it, **Functional Spring**. This approach embraces Kotlin’s expressive syntax and strong typing to replace annotations with **Domain Specific Languages (DSLs)** that make wiring and routing both explicit and elegant. Within Spring Core, two major DSLs already support this transition. The **Beans DSL** allows developers to declare beans directly in code without relying on component scanning, which significantly improves testability by allowing configurations to run outside of the full application context. Meanwhile, the **Router DSL** reimagines the traditional `@RestController` and `@RequestMapping` pattern, defining routes as pure functions that take a request and return a response. This makes integration testing faster, cleaner, and easier to reason about — no more dealing with opaque MVC mocks or hidden dependencies. As Burns noted, this shift lays the groundwork for “a new level of abstraction” — one that’s explicit, type-safe, and built around developer intent.

For teams ready to adopt this mindset, Burns introduced **Spring Funk**, his open-source continuation of the original Spring Fu project. Spring Funk allows developers to write entire Spring applications with **zero annotations**, leaning instead on Kotlin’s native features like extension functions and higher-order constructs to organize and encapsulate code. At GrubHub, this approach has already been proven across more than 200 production microservices, delivering measurable improvements in startup performance, testability, and code clarity. The key takeaway for developers: learning Kotlin as a language offers more long-term value than mastering Spring’s complex annotation ecosystem. Functional Spring represents not a rejection of the framework, but a refinement — a step toward greater performance, transparency, and maintainability in modern Java development.

---

### 🎥 Watch the full session

<iframe width="800" height="450" src="https://www.youtube.com/embed/9njQ8Lun36c" title="Functional Spring Boot - John Burns | Devnexus 2025" frameborder="0" allowfullscreen></iframe>

---

### 🚀 Join us at Devnexus 2026

Explore cutting-edge insights, hands-on sessions, and forward-thinking conversations like this at **[Devnexus 2026](https://devnexus.com)**
