---
_schema: default
layout: post-article
title: Enhancing LLMs with Graph Technology with Stephen Chin
img: /assets/images/stephen-chin-video-still.jpg
---
In his Devnexus presentation, **Stephen Chin**, VP of Developer Relations at Neo4j, addressed the critical shortcomings of Large Language Models (LLMs): hallucinations, bias, and a lack of explainability. He highlighted Gartner’s prediction that **30% of generative AI projects will be abandoned** after proof-of-concept by the end of 2025 due to these issues. Chin demonstrated how even advanced LLMs struggle with basic math, complex reasoning, and inherent training data biases, often producing inaccurate or unsuitable outputs for enterprise applications. The core problem he tackles is how to provide LLMs with the **accurate, factual, and explainable knowledge** they need to deliver reliable results in real-world contexts.

Chin proposes **knowledge graphs** as a powerful solution to augment LLMs, enabling them to move beyond statistical correlations to **factual reasoning**. Unlike LLMs, which operate on abstract “word vectors,” knowledge graphs represent information as interconnected nodes and relationships—a structure that is human-readable and verifiable. He cites research on **graph RAG (Retrieval Augmented Generation)** from Microsoft, showing that combining unstructured documents with vector and graph databases yields far superior results compared to using LLMs alone. This approach allows for **deep, contextual queries** that uncover hidden relationships and provide explicit, verifiable facts, improving both reliability and transparency in LLM outputs.

The presentation included practical demonstrations of **graph RAG architectures** and the **Neo4j Knowledge Graph Builder**. Chin showed how a Java application can query a vector database for relevant information and then traverse a knowledge graph to enrich LLM prompts. He discussed various graph RAG patterns, including graph filtering, agent-based architectures, and even the more complex **text-to-Cypher** approach for generating graph queries directly. He showcased the Knowledge Graph Builder, an open-source tool that can ingest data—from documents to YouTube transcripts—and automatically generate a knowledge graph. This empowers developers to create **factual, transparent, and actionable AI applications** that overcome the limitations of base LLMs, making them suitable for enterprise use cases.

Chin’s talk makes it clear that the future of reliable, enterprise-grade AI **doesn’t rest on LLMs alone**. By strategically combining them with knowledge graphs, developers can build AI systems that are not only intelligent and creative but also **accurate, transparent, and trustworthy**. Knowledge graphs provide the factual grounding and explainability needed to elevate AI from experimental to enterprise-ready.

---

## Watch the Talk

<div align="center">
<iframe width="560" height="315" src="https://www.youtube.com/embed/ByKzb6BrT38" title="Augmenting LLMs with Knowledge Graphs: Stephen Chin at Devnexus" frameborder="0" allowfullscreen=""></iframe><p></p>
</div>

---

### Experience More at Devnexus 2026

Devnexus brings together developers and tech leaders to explore the latest in software development, cloud technologies, and AI. Don’t miss the opportunity to connect, learn, and experience cutting-edge innovations at next year’s conference!<br> 👉 [Learn more at devnexus.com](https://devnexus.com)