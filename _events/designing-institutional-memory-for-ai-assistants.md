---
questionAnswers: []
id: '1344604'
title: Designing Institutional Memory for AI Assistants
description: "Most enterprise AI assistants retrieve documents well, but retrieval
  isn't memory. When one assistant serves many people across an organization, it has
  to retain what teams learn, control who can see it, reconcile conflicting facts,
  and retire what's stale.\r\n\r\nWhat makes this different from traditional system
  design is that the core decisions aren't deterministic. A large language model decides
  what's worth remembering, what kind of knowledge it is, whether a new statement
  updates or contradicts an existing one, and how important it is. Embeddings decide
  which memories are semantically related. These components are powerful but probabilistic,
  so the architecture has to be built around the fact that they will sometimes be
  wrong.\r\n\r\nThis session designs an AI-driven institutional memory system from
  the ground up:\r\n\r\nLLM-driven write path: an extraction stage where the model
  turns raw interactions into explicit operations (add, update, invalidate, or no-op),
  grounded in the existing memories in the same scope rather than judged in isolation.\r\n\r\nSemantic
  conflict detection: using embeddings to find related memories across teams, and
  letting the model classify whether they agree, refine, or contradict each other.\r\n\r\nAccess
  model: visibility enforced at the storage layer, outside the model, so a hallucination
  or prompt injection can't leak one team's memory to another.\r\n\r\nConfidence-aware
  human review: routing low-confidence judgments, cross-team conflicts, and promotions
  to shared knowledge through a human approval step instead of trusting a threshold.\r\n\r\nRead
  path: retrieval ranked by semantic relevance, model-assigned importance, and recency
  decay, with every access logged.\r\n\r\nEvaluating the AI itself: why unit tests
  can't verify model judgment, and how scenario-based evaluation catches regressions
  when prompts or models change.\r\n\r\nFor each component, we'll cover the trade-offs,
  including which decisions to delegate to the model and which must stay deterministic.
  Examples come from Noscium, an open-source framework I'm building. It's early-stage,
  so the focus is the architecture and design reasoning, not a finished product."
startsAt:
endsAt:
isServiceSession: false
isPlenumSession: false
speakers:
- id: 788d1e05-48a6-4efc-9ef2-3ed8ac7a6551
  name: Ragesh Raveendran Pillai
categories:
- id: 139840
  name: Track
  categoryItems:
  - id: 507381
    name: System Design
  sort: 0
roomId:
room:
liveUrl:
recordingUrl:
status: Accepted
isInformed: true
isConfirmed: true
track: System Design
slug: designing-institutional-memory-for-ai-assistants

---
