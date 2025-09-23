---
_schema: default
layout: post-article
title: AI’s Big Leap with Justine Gehring
img: /assets/images/justine-gehring-video-still.jpg
--- 

In her Devnexus session, **Justine Gehring**, researcher in the Field of Machine Learning for Code, explored how artificial intelligence is reshaping the future of code refactoring and migration. Traditionally, code has been treated as little more than text, with approaches focused on string-based manipulation or large-scale pattern matching. Gehring challenged this view, showing how Moderne is moving toward a deeper model of **“code as data”**—a perspective that enables intelligent, context-aware refactoring at scale.  

At the heart of this approach are **Lossless Semantic Trees (LSTs)**, enriched Abstract Syntax Trees (ASTs) that preserve information typically lost during parsing. Unlike standard ASTs, LSTs retain critical details such as comments, formatting, and type attribution (for instance, understanding that a `Cat` class extends `Animal`). This richer representation provides the structural and semantic context needed to perform safe, large-scale, and precise code transformations across entire repositories.  

Gehring then illustrated how this data-centric foundation powers **OpenRewrite recipes**, modular transformation rules that act like “Lego blocks” for automated migrations. She demonstrated real-world examples, such as upgrading thousands of lines of code in a **Spring Boot migration**, where OpenRewrite recipes handled countless small changes that would otherwise take developers weeks or months. These recipes scale from simple tasks—like detecting specific method calls—to solving nuanced problems, such as fixing encoding issues in **French Javadoc comments** that defy deterministic pattern matching. It’s here that AI shows its strength: augmenting structured transformations with intelligent, context-driven problem-solving.  

The presentation culminated with the introduction of **Moddy**, an AI-powered assistant that serves as a conversational interface to the OpenRewrite platform. Modi can search for recipes, run them, and explain results without requiring users to know recipe syntax or technical details. By turning thousands of manual edits into guided, conversational workflows, Moddy lowers the barrier for developers of all backgrounds to take advantage of large-scale refactoring. Gehring also touched on the promise of **knowledge graphs**, where AI builds rich, contextual descriptions of methods and classes based on their LSTs and usage history, paving the way for automated documentation and smarter tooling.  

Gehring’s talk makes it clear: the future of code modernization isn’t about brute-forcing AI with millions of lines of text—it’s about pairing AI with a deep, structured understanding of code. By treating code as data and combining automated recipes with intelligent agents, developers can refactor faster, migrate more safely, and tackle challenges that once seemed insurmountable. This shift represents a **new era of tooling** where developers spend less time on repetitive changes and more time innovating.  

---

## Watch the Talk  
<div align="center">  
<iframe width="560" height="315" src="https://www.youtube.com/embed/fIzbIy9rbaI" title="AI's Big Leap: Justine Gehring at Devnexus" frameborder="0" allowfullscreen></iframe>  
</div>  

---

### Explore More at Devnexus  
Devnexus is where the future of development takes shape—through hands-on insights, real-world solutions, and community-driven innovation. Join us next year to connect, learn, and shape what’s coming next.  
👉 [Discover more at devnexus.com](https://devnexus.com)  
