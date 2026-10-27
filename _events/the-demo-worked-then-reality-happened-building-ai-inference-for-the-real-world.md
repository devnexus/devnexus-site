---
questionAnswers: []
id: '1334149'
title: 'The Demo Worked. Then Reality Happened: Building AI Inference for the Real
  World'
description: "Getting an AI model to answer a prompt is the easy part. Keeping it
  useful when multiple users, multi-gigabyte models, limited GPUs and real infrastructure
  enter the picture is where the engineering begins.\r\n\r\nThis talk follows the
  evolution of a small three-GPU AI lab from “we have some spare hardware, let’s run
  some models” into a shared inference platform used by a development team, including
  the failures that shaped it along the way.\r\n\r\nWe’ll look at what happened when
  parallel model downloads overwhelmed the network, how fixing one bottleneck exposed
  the next, and why more GPUs didn’t make problems around model distribution, resource
  allocation, observability and recovery disappear. We’ll also explore the guardrails
  needed when an experiment becomes infrastructure that other developers depend on
  for day-to-day inference.\r\n\r\nThen the models got bigger.\r\n\r\nAs the lab moves
  from text models toward multimodal inference, local hardware is no longer always
  enough. We’ll look at the next challenge: extending the same platform onto temporary
  high-end GPU infrastructure without turning startup time, model caching and cloud
  costs into the new bottlenecks.\r\n\r\nUsing OpenShift, Kubernetes, vLLM and the
  surrounding inference ecosystem, we’ll examine what it really takes to move from
  “the demo works” to AI infrastructure people can actually rely on.\r\n\r\nAttendees
  will leave with practical patterns for shared inference, model distribution, observability
  and resource management and a better sense of which AI infrastructure problems more
  GPUs simply won’t solve.\r\n"
startsAt:
endsAt:
isServiceSession: false
isPlenumSession: false
speakers:
- id: d253b88c-7982-4b53-ac3c-3390a7892de2
  name: Paul Power
categories:
- id: 139840
  name: Track
  categoryItems:
  - id: 507382
    name: Production AI Engineering
  sort: 0
roomId:
room:
liveUrl:
recordingUrl:
status: Accepted
isInformed: true
isConfirmed: true
track: Production AI Engineering
slug: the-demo-worked-then-reality-happened-building-ai-inference-for-the-real-world

---
