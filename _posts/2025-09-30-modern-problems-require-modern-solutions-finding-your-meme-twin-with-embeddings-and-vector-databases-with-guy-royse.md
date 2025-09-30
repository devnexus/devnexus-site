---
_schema: default
layout: post-article
title: >-
  Modern Problems Require Modern Solutions , Finding Your Meme Twin with
  Embeddings and Vector Databases, with Guy Royse
img: /assets/images/guy-royse-video-still.jpg
---
The modern developer faces a growing challenge: making sense of the world's fastest-expanding resource—**unstructured data**. In his Devnexus 2025 talk, **Guy Royse**, Developer Advocate at Redis, explored how to transform seemingly unsearchable content—like images, audio, and documents—into quantifiable, comparable data. The cornerstone of this process is the **embedding**, where an AI model converts the meaning of an asset into a vector, or a "pile of numbers." These vectors serve as coordinates in a high-dimensional space, allowing similar items to cluster together. Royse demonstrates how features are extracted and scored (e.g., how "alcoholic" or "elderly" a person in an image appears), enabling developers to measure similarity simply by calculating the distance between vectors.

Once data is vectorized, a **vector database**—like Redis—can store and index these embeddings for lightning-fast similarity searches. Royse highlights two primary methods for comparison: **Euclidean distance**, which measures straight-line distance between points, and **Cosine similarity**, which assesses the angle between vectors. Key applications include **Semantic Search**, which finds results based on meaning rather than keywords (e.g., searching for "fast food" and returning a document mentioning "McDonald's"), and **Semantic Caching**, which detects if a new user query is semantically similar to a previous one to serve cached responses, reducing expensive LLM API calls. This implements a "cache-aside" pattern using vector search.

Royse emphasizes that these tools are **accessible to developers without data science expertise**. He demonstrates the "Meme Twin" project using JavaScript (Node.js) and Hugging Face image embedding models, running locally on standard hardware. While understanding concepts like **Convolutional Neural Networks (CNNs)** is useful for debugging, he cautions against building your own embeddings. Instead, developers should leverage pre-trained models from platforms like Hugging Face, combining them with vector databases to enable semantic search, caching, and **Retrieval Augmented Generation (RAG)** efficiently and cost-effectively.

Royse’s talk highlights that modern AI-powered features no longer require a specialized data science team. By combining **pre-trained embeddings** with vector databases, developers can build semantic search, caching, and intelligent retrieval capabilities into their applications—making previously complex functionality accessible and practical for real-world projects.

---

### Join Us at Devnexus 2026

Insights like these are just a snapshot of what Devnexus offers. Join us next year to discover the latest tools, frameworks, and ideas shaping the future of development—and connect with a vibrant community of peers.  
👉 [Learn more at devnexus.com](https://devnexus.com)

---

📺 **Watch the full session here:**  
[![Watch the video](https://img.youtube.com/vi/INSERT_VIDEO_ID_HERE/0.jpg)](https://www.youtube.com/watch?v=INSERT_VIDEO_ID_HERE)
