---
questionAnswers: []
id: '750525'
title: Shadow Vulnerabilities in AI/ML Data Stacks - What You Don’t Know CAN Hurt
  You
description: "The adoption of open-source AI software introduces a new family of vulnerabilities
  to organizations. Some components in AI, like model serving, include Remote Code
  Execution (RCE) by design, like when loading pre-trained models from external sources.\r\n\r\nTraditional
  SCA and SAST approaches are not built for the AI ecosystem leaving a huge & insecure
  attack surface. \r\n\r\nAI models are often downloaded from the public web, from
  untrusted sources in common platforms like HuggingFace using the “trust_remote_code=True”
  flag when loading models.  So how do we better secure our AI stacks?\r\n\r\nIn this
  talk, we’ll examine some of the common security anti-patterns prevalent in AI engineering,
  such as security issues that are not classified as CVEs by design, or patched security
  issues that introduce breaking changes.\r\n\r\nWe’ll review the methods introduced
  for better security hygiene such as new checkpoint formats (model files on disk)
  - like SavedModel and SafeTensors. While SCA, SAST, and traditional approaches don't
  analyze model checkpoints, leaving these silent vulnerabilities in your stacks,
  we’ll demo through real code examples, why the runtime context is crucial to detect
  these security issues."
startsAt: '2025-03-06T11:30:00'
endsAt: '2025-03-06T12:30:00'
isServiceSession: false
isPlenumSession: false
speakers:
- id: dea86363-2e13-4dfa-996a-200e6feb8749
  name: Gal Elbaz
categories:
- id: 81703
  name: Track
  categoryItems:
  - id: 290610
    name: Security
  sort: 0
- id: 81704
  name: Session Format
  categoryItems:
  - id: 290619
    name: session
  sort: 1
roomId: 58705
room: Security
liveUrl:
recordingUrl:
status: Accepted
isInformed: true
isConfirmed: true
track: Security
format: session
slug: shadow-vulnerabilities-in-ai-ml-data-stacks-what-you-don-t-know-can-hurt-you

---
