---
_schema: default
layout: post-article
title: 'We Hate Code: The !Joy of Maintaining Dead Code with Gerrit Grunwald'
img: /assets/images/gerrit-grunwald-video-still.jpg
---

In his Devnexus presentation, **Gerrit Grunwald** of Azul addressed one of the most expensive and persistent challenges in software development: legacy and dead code. His talk, *“We Hate Code: The !Joy of Maintaining Dead Code,”* painted a vivid picture of how the “build now, fix later” mindset can leave behind unmaintained, undocumented, and intimidating code. Developers often hesitate to touch it, fearing unintended side effects. The costs are staggering—Grunwald shared that poor-quality code costs the industry an estimated **$85 billion annually**, with every 100,000 lines of code carrying about **$361,000 in technical debt**. He distinguished between traditional **legacy code**—old, hard-to-test, and lacking documentation—and the more subtle **dead code**, such as unused imports, variables, methods, or classes that clutter projects, bloat applications, and introduce security risks.

To combat this, Grunwald outlined strategies for identifying and cleaning up unused code. For truly dead code, static analysis tools like **IntelliJ IDEA inspections**, **SonarQube**, and **Checkstyle** can flag unreferenced elements. But for large systems, manual cleanup is impractical. That’s where **OpenRewrite** proves invaluable. This open-source, community-driven tool uses “recipes” to automatically refactor source code, including the removal of unused private methods. By integrating OpenRewrite into CI/CD pipelines, teams can automate much of the cleanup process, steadily reducing technical debt with minimal overhead.

The most dangerous type of unused code, however, is what Grunwald calls **zombie code**—logic that’s written, tested, and even deployed, but never actually executed by end-users. Unlike dead code, zombie code evades IDE warnings and static analysis, making it harder to detect. To tackle this, Grunwald recommended runtime analysis approaches such as **custom logging with static initializers** or **JDK Flight Recorder (JFR) custom events** to trace method execution in production. For a more advanced solution, he highlighted **Azul Intelligence Cloud**, which, when combined with a lightweight Java agent, can monitor running JVMs to identify zombie code in production. When paired with OpenRewrite, this enables a phased process: annotate suspicious code, confirm its dormancy over time (even accounting for infrequent jobs), and finally remove it automatically. The result is a cleaner, leaner, and more secure codebase.

Grunwald’s message was clear: legacy and zombie code don’t have to be a permanent burden. With the right combination of analysis, automation, and runtime monitoring, developers can take back control of their codebases, cut down on technical debt, and prevent waste. By making code cleanup a deliberate, continuous process, teams can transform messy projects into more maintainable and secure systems, freeing up time and energy for innovation.

---

## Watch the Talk
<div align="center">  
<iframe width="560" height="315" src="https://www.youtube.com/embed/iD-JGCfRSQQ" title="We Hate Code: Gerrit Grunwald at Devnexus" frameborder="0" allowfullscreen></iframe>  
</div>

---

### Join the Developer Conversation
Talks like this are what make Devnexus more than just a conference—it’s a community where developers share practical solutions to real-world problems. Join us at **Devnexus 2026** to gain insights like these, connect with peers, and explore the future of software development together.  

👉 [Reserve your spot at devnexus.com](https://devnexus.com)
