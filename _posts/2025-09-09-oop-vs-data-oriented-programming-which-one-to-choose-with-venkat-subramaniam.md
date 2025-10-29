---
_schema: default
layout: post-article
title: OOP vs Data Oriented Programming Which One to Choose with Venkat Subramaniam
img: /assets/images/venkat-s-video-still.jpg
---

In his thought-provoking presentation at Devnexus, **Venkat Subramaniam** challenges the assumption that object-oriented programming (OOP) is always the best approach. He argues that both OOP and data-oriented programming (DOP) are valuable tools, and success lies in knowing when to apply each. While he acknowledges OOP’s four pillars—abstraction, encapsulation, inheritance, and polymorphism—he identifies polymorphism as its true strength. By walking through an example of processing an order with a `PaymentMethod` interface, Subramaniam demonstrates how polymorphism enables developers to extend functionality (like adding new payment types) without altering the core business logic.

He then shifts to a scenario where OOP becomes less effective: handling data from third-party libraries that developers cannot change. In such cases, attempting to build an OOP hierarchy of interceptor classes often leads to messy design, redundant code, and type-checking logic that undermines compile-time safety. Subramaniam explains how this approach can introduce **accidental complexity**, forcing developers into brittle patterns that risk runtime errors whenever new third-party classes appear. Instead of solving the problem at hand, OOP in this situation can actually make it worse—what he calls *“smelly code.”*

To address this challenge, Subramaniam presents a clean alternative: **data-oriented programming**. Leveraging modern Java features such as **sealed interfaces** and **records**, developers can model data in a way that is both simpler and safer. Using sealed interfaces ensures the compiler validates that all possible data types are accounted for in a single switch expression, removing the risk of unhandled cases at runtime. The end result is code that’s easier to maintain, less error-prone, and more aligned with the realities of working with external data. His conclusion is clear: OOP shines when polymorphism is key, but DOP is the right choice for processing external data—underscoring the importance of choosing the right tool for the job.

---

## 🎥 Watch the Session

<iframe width="560" height="315" src="https://www.youtube.com/embed/iw8E6rJ-v1U" title="OOP vs. DOP: The Right Tool for the Right Job" frameborder="0" allowfullscreen=""></iframe>

---
🚀 Don’t miss **Devnexus 2026**, where leading voices like Venkat Subramaniam share insights that help developers sharpen their skills and rethink their approach to modern software. Be part of the conversation and secure your spot early! 
