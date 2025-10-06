---
_schema: default
layout: post-article
title: 'TDD & Generative AI: A Perfect Pairing? Bouke Nijhuis'
img: /assets/images/bouke-video-still.jpg
---
In his Devnexus 2025 session, *TDD & Generative AI: A Perfect Pairing*, Bouke Nijhuis introduced a groundbreaking approach to integrating Large Language Models (LLMs) into software development: **Test-Driven Generation (TDG)**. The talk began with one of the biggest challenges developers face when using AI tools—the tendency of models to “hallucinate” or generate incorrect code. Nijhuis argues that the common workflow, where AI produces code first and developers write tests afterward, is fundamentally flawed. His solution is simple yet profound: invert the process. The human developer becomes the “Navigator,” writing the unit test first, while the LLM serves as the “Driver,” generating the implementation. This workflow mirrors the principles of **Test-Driven Development (TDD)** but applies them to the AI era, creating an automated and verifiable feedback loop that ensures correctness and reliability. Nijhuis emphasizes that this structured approach is not just good practice—it’s essential, citing examples like the infamous Air Canada chatbot that fabricated its own refund policy  as a warning of what happens when AI operates unchecked.

TDG transforms human-AI collaboration into a repeatable and testable engineering process: **Write Test → Feed Test to LLM → LLM Generates Code → Run Test to Verify**. While this may sound straightforward, automating the workflow required solving some deep technical challenges typically handled by an IDE. Nijhuis outlined the steps he took to build an automated Test Driven Generator tool capable of compiling generated Java code at runtime, injecting classes dynamically via a custom class loader, and executing JUnit tests programmatically. The power of this approach was demonstrated through real-world examples, from generating a simple Prime Number Generator to using the tool to improve its own source code—creating a better regular expression for class name extraction based on new test cases. The result is a compelling demonstration of how AI can become a productive partner in development rather than an unreliable assistant.

The implications for engineering teams are profound. TDG introduces a future where adding new features may be as simple as adding new test cases. Developers define the intent and boundaries through testing, and AI handles the implementation details—accelerating delivery without compromising quality. Nijhuis also addressed the cost consideration of cloud-based LLMs, pointing out that while API usage carries a price tag, developer time is far more expensive. The trade-off, therefore, leans heavily toward efficiency and value. His vision culminates in a potential IDE plugin —a tool that allows developers to right-click a test file, select “Generate Implementation,” and instantly produce validated, production-ready code. This idea embodies the future of assisted development: AI-driven speed grounded in the discipline of software craftsmanship.

Bouke Nijhuis’s session captures a crucial mindset shift for developers—AI isn’t replacing good engineering practice; it’s reinforcing it. By blending TDD’s rigor with AI’s generative power, TDG offers a path forward where automation amplifies, rather than undermines, developer skill. It’s a model that not only makes coding faster but also smarter, safer, and more human-centered.

🎥 Watch the full session here: [Devnexus 2025 – TDD & Generative AI: A Perfect Pairing – Bouke Nijhuis](https://www.youtube.com/watch?v=XXXXXXXX)

---

Want to experience more forward-thinking sessions like this?  
Join us next year at **Devnexus 2026** to explore the future of development, AI, and engineering innovation — together with the global Java community. 🚀  
[devnexus.com](https://devnexus.com)
