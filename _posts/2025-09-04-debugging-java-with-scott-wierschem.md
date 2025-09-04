---
_schema: default
layout: post-article
title: Debugging Java with Scott Wierschem
img: /assets/images/scott-weirschem-video-still.jpg
---
In his Devnexus talk, **Scott Wierschem** delivered a masterclass on debugging Java applications, highlighting that effective debugging is far more than setting breakpoints. He began by stressing that debugging is a skill developers can **cultivate deliberately**, noting research that shows top developers excel at both finding and preventing bugs. One of his central recommendations is to always write a **test that reproduces the bug**, which both isolates the problem and ensures it never reappears as a regression. Wierschem also encouraged developers to keep a **debugging journal**, recording actions, observations, and hypotheses—a habit that creates a structured log of the thought process and helps sharpen problem-solving skills.  

The session then shifted to practical demonstrations using **IntelliJ IDEA**. Wierschem showcased how tools like **Evaluate Expression** allow developers to run snippets or modify variables on the fly, providing instant insights into code behavior. He highlighted the **Drop Frame** feature, which makes it possible to step back in the call stack without restarting a session—saving time when investigating tricky paths. For intermittent or complex bugs, he recommended **conditional breakpoints**, which only trigger when a certain condition is met, eliminating unnecessary noise. One of the standout features was **Stream Trace**, a visualization that breaks down how a Java stream is evaluated step by step, making even complex pipelines more understandable.  

To round out the talk, Wierschem addressed one of the most frustrating challenges: **memory leaks**. Using **VisualVM**, a free profiling tool, he demonstrated how to analyze heap dumps and spot problematic objects consuming excessive memory. He shared examples of common pitfalls, such as static collections that never release references and listeners that aren’t properly unregistered. The overarching lesson was that debugging is a **continuous learning process**—combining deliberate practice, the right tools, and a methodical approach. Wierschem concluded with a reminder that developers should not hesitate to seek help and should treat every bug as a learning opportunity, strengthening both their code and their craft.  

---

## Watch the Session  

[![Watch the talk](https://img.youtube.com/vi/24JvjXAtcVg/0.jpg)](https://www.youtube.com/watch?v=24JvjXAtcVg)  

🎟️ Don’t miss out—watch the full session above, and join us at **Devnexus 2026** for more practical talks, live demos, and strategies that will help you grow as a developer and build better software.  

---