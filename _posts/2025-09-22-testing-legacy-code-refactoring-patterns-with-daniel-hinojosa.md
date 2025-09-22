---
_schema: default
layout: post-article
title: Testing Legacy Code & Refactoring Patterns with Daniel Hinojosa
img: /assets/images/daniel-hinojosa-video-still.jpg
---

In his Devnexus presentation, **Daniel Hinojosa** tackled the challenge that nearly every developer faces at some point: working with legacy code. He offered practical strategies for testing and refactoring that enable developers to make changes with confidence rather than fear. Drawing an analogy to *“meatball surgery”* from the TV show *MASH*, Hinojosa emphasized that sometimes developers must be quick and efficient, all while preserving the integrity of the existing codebase. While he acknowledged the enduring relevance of classics like Michael Feathers’ *Working Effectively with Legacy Code*, he pointed out that modern tools and features—such as lambdas—require developers to adapt and rethink how these techniques are applied today. The central question he explored: how can we improve the testability of messy, fragile code without breaking what already works?

Hinojosa introduced a set of foundational principles for creating more testable code, drawing on sources like Misko Hevery’s *Guide to Testable Code*. He outlined common **code smells**—including overuse of the `new` keyword in constructors, static method calls, digging into collaborators, and reliance on global state through singletons. These issues, he argued, make code brittle and difficult to change. To counteract them, he reinforced the importance of the **Single Responsibility Principle**, urging that classes should focus on doing one thing well, making them easier to debug and evolve. He also highlighted the **Open/Closed Principle**, which states that software entities should be open for extension but closed for modification, encouraging developers to lean on design patterns like Adapter or Decorator to add new behavior without rewriting stable core logic.

From there, the presentation moved into concrete **refactoring techniques**, often demonstrated live in IntelliJ IDEA. Hinojosa showcased the **Extract Delegate** pattern to replace primitive obsessions with richer objects, such as moving area code and number from a `Person` class into a dedicated `PhoneNumber` object. He also demonstrated **Extract Interface** to allow for multiple implementations of an existing class, enabling flexibility for testing or benchmarking. Other techniques included the **Parameterized Method** pattern, which replaces hard-to-test `new` or static calls with injected behavior through lambdas. To introduce new features safely, he described **Sprout Method** and **Sprout Class**, while **Wrap Method** and **Wrap Class** (closely aligned with the Decorator Pattern) allow developers to layer in cross-cutting concerns like logging or auditing without altering the original class.

Hinojosa’s message was clear: legacy code is not a dead end but an opportunity. By applying proven principles and modern refactoring techniques, developers can **transform brittle systems into flexible, testable, and maintainable codebases**. His live examples showed that with the right mindset and patterns, improving old code becomes less about survival and more about building a stronger foundation for the future.


---

## Watch the Talk
<div align="center">  
<iframe width="560" height="315" src="https://www.youtube.com/embed/Wp8tUr57XKY" title="Tackling Legacy Code with Confidence - Daniel Hinojosa" frameborder="0" allowfullscreen></iframe>  
</div>

---

### Looking Ahead
Talks like this embody what Devnexus is all about—practical strategies, modern tools, and shared wisdom from the community. Don’t miss your chance to be part of the conversation at **Devnexus 2026**, where developers from around the world come together to learn, connect, and push the boundaries of software development.  

👉 [Save your spot at devnexus.com](https://devnexus.com)
