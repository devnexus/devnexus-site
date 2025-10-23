---
_schema: default
layout: post-article
title: Unlock the Power of Generative AI with Nikhil Nanivadekar
img: /assets/images/nikhil-image.jpg
---

In a world where advertisers constantly compete for consumer attention, **Nikhil Nanivadekar**, Principal Engineer at **Amazon Ads**, shared how his team transformed Generative AI from a promising experiment into a production-scale system powering real-world creativity. The challenge was clear: advertisers often lack the time, budget, or design expertise to produce high-quality, seasonally relevant visuals at scale. Campaigns require a constant flow of new content — from Valentine’s Day to Black Friday — but traditional creative pipelines simply can’t keep up. Nanivadekar’s mission was to remove that friction entirely, enabling advertisers to generate production-grade assets with *“just a click of a button.”*

The journey began in 2023 with the launch of an internal image-generation system — a composite AI model designed through rapid, iterative prototyping. The process started with product imagery, using machine learning segmentation models to isolate products from their backgrounds. Then, a fine-tuned **Large Language Model (LLM)** would generate diverse, creative prompts that guided a **text-to-image model**, producing a variety of unique visuals for advertisers to choose from. This system soon expanded to include **“Live Images”** that added motion effects and **automated video generation**, allowing advertisers to scale creative output across multiple formats without human intervention.  

Scaling this technology into production required an architectural shift. Nanivadekar’s team designed a modular system that separated engineering and science efforts, using an **AWS Lambda–based orchestration layer** to manage workflows declaratively defined by scientists through a **Python SDK**. This eliminated the “lost in translation” problem between model design and production deployment. Scientists could now focus purely on models and creative workflows, while the system automatically handled scalability, fault tolerance, and observability. For compute-heavy processes, the team leveraged **AWS Fargate** for elasticity and stability, ensuring efficient scaling across massive workloads.  

The results were transformative. Advertisers using Amazon’s AI-powered creative tools showcased **five times more products** and saw a **17% increase in return on ad spend (ROAS)**. Click-through rates rose by **8%**, proving that personalized, high-quality AI-generated visuals not only scaled efficiently but also resonated with customers. Nanivadekar closed with a powerful message for developers: success in production-grade Generative AI comes from **architecture, not just algorithms**. He emphasized key principles—decouple offline and online components for independent scaling, leverage serverless tools for fast iteration, and abstract infrastructure complexity behind clean, declarative interfaces. Together, these strategies enable teams to innovate rapidly while keeping systems reliable, maintainable, and future-proof.

---

### 🎥 Watch the Full Session

<iframe width="800" height="450" src="https://www.youtube.com/embed/j6KprOYR4DU" title="Unlocking Creative Power: How Amazon Ads Scaled Generative AI from Prototype to Production - Nikhil Nanivadekar" frameborder="0" allowfullscreen></iframe>

---

### 🚀 Join Us at Devnexus 2026

Be part of the developer community shaping the next generation of cloud-native and AI innovation.  
Connect with world-class engineers, architects, and community leaders at **[Devnexus 2026](https://devnexus.com)** — where the future of software development comes to life.
