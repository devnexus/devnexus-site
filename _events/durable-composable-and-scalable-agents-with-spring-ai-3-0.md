---
questionAnswers: []
id: '1349647'
title: Durable, composable and scalable agents with Spring AI 3.0
description: "Getting an LLM to call a few tools is the easy part. Running agents
  in production is harder: a multi-step agent will crash partway through, stop to
  wait for a human, hand work to other agents and stream tokens to thousands of users
  at once. Spring AI 3.0 adds a new spring-ai-agent module for these problems, built
  with the same type safety, extensibility and operational focus as the rest of the
  Spring portfolio.\r\n\r\nThis talk covers its key features:\r\n\r\nComposable: an
  agent is a typed generic Agent transformation. You can build one from a ChatClient
  you already have, or from a graph whose nodes call the model and run tools. Graph
  agents can orchestrate nested agents. Their conversations can be shared, forked
  or isolated. Mismatched contexts are caught when the agent is built, not halfway
  through a run.\r\n\r\nDurable: the durability feature snapshots each completed step,
  so a run that crashed picks up where it stopped instead of calling the LLM again.
  The same mechanism powers human-in-the-loop: interrupt nodes pause a run and resume
  it with the human's answer, even deep inside nested agents. For production-grade
  orchestration, nodes can also run as Temporal activities.\r\n\r\nObservable and
  extensible: logging, conversation persistence and compaction, and durability are
  all pluggable features. Each one is written once on a single set of hooks and sees
  every model call, tool call and node, including those of nested agents.\r\nScalable:
  Spring AI 3.0 provide an innovative unified API with either a blocking call or an
  imperative event stream with backpressure designed for virtual threads and structured
  concurency, with no reactive programming required.\r\n\r\nYou'll leave knowing how
  to design agentic systems that are reliable, testable and ready for production,
  and how the new module fits into the Spring AI programming model."
startsAt:
endsAt:
isServiceSession: false
isPlenumSession: false
speakers:
- id: 23d00872-893d-47d0-ac16-4cc83625d9f2
  name: Soby Chacko
categories:
- id: 139840
  name: Track
  categoryItems:
  - id: 507386
    name: Building AI-Native Applications
  sort: 0
roomId:
room:
liveUrl:
recordingUrl:
status: Accepted
isInformed: true
isConfirmed: false
track: Building AI-Native Applications
slug: durable-composable-and-scalable-agents-with-spring-ai-3-0

---
