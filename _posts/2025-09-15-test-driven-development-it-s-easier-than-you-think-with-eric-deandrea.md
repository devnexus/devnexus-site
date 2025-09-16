---
_schema: default
layout: post-article
title: Test Driven Development It’s Easier Than You Think with Eric Deandrea
img: /assets/images/eric-d-video-still.jpg
---
In a talk that cuts through the hype and champions a **practical approach to software quality**, Java Champion **Eric Deandrea** makes a strong case for **test-driven development (TDD)** as an essential part of a developer's workflow. He argues that the ultimate goal of writing code is to deploy it to production with confidence—and that confidence comes from a solid testing strategy. Deandrea humorously highlights the *“bug-to-bug” cycle* that often plagues projects without strong tests, where fixing one issue ends up creating another. His message is clear: the earlier a bug is caught—ideally before the code is even committed—the cheaper and easier it is to fix, saving teams both time and frustration.

To guide teams toward better practices, Deandrea breaks down the **test pyramid**, a model that helps balance testing efforts by cost and value. The base is a wide layer of **unit tests**, fast and inexpensive to run. The middle layer consists of **integration tests**, which validate that different parts of the system work together. At the top sit **end-to-end tests**, slower and more expensive but offering the highest level of confidence across the system. He also notes that modern tools are blurring the lines between these categories, making it easier to build a comprehensive and efficient testing strategy.

The centerpiece of his talk is a **live coding demo** that shows a *realist’s approach* to TDD in action. Using the **Quarkus framework**, Deandrea builds a pet adoption application step by step, starting with tests for each layer—the data tier, the REST API, and asynchronous messaging with Kafka. He emphasizes testing the **contract (raw JSON)** rather than relying solely on Java objects, which makes tests more resilient to refactoring. He also showcases how tools like **Quarkus Dev Services** and continuous testing streamline the workflow, enabling faster feedback and higher developer productivity.  

The key takeaway is that TDD doesn’t have to be dogmatic or rigid. When applied pragmatically—and supported with the right tools—it becomes a powerful technique for writing **better, more maintainable code** while boosting developer confidence.

---

## Watch the Talk
<div align="center">  
<iframe width="560" height="315" src="https://www.youtube.com/embed/6SLOgbrxtLI" title="A Realist's Guide to Test-Driven Development" frameborder="0" allowfullscreen></iframe>  
</div>

---

### Looking Ahead
Talks like this capture the essence of Devnexus: **practical insights, real-world coding, and strategies that developers can apply immediately.** Don’t miss the chance to learn from industry leaders and sharpen your skills at **Devnexus 2026**.  

👉 [Join the community at devnexus.com](https://devnexus.com)
