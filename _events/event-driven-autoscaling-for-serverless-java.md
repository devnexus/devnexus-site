---
questionAnswers: []
id: '386951'
title: Event-driven autoscaling for Serverless Java
description: "Kubernetes makes it possible to autoscale various business use cases
  from web apps to mobile, IoT edge streaming, and AI/ML in more reliable and stable
  ways. One caveat of the Kubernetes autoscaling is based on hardware resource utilization
  (CPU, memory) through Horizontal Pod Autoscaling. This causes a new challenge to
  build an event-driven serverless Java on Kubernetes because the event metrics from
  multiple event sources (e.g., Apache Kafka, AWS SQS) are more relevant than a pod's
  CPU usage for deciding when applications need to be scaled out and in. \r\n\r\nFortunately,
  KEDA and Knative on Kubernetes are designed to solve this challenge by autoscaling
  both standard apps and serverless by event metrics in a separate way. This session
  will teach you how to redesign your Kubernetes autoscaling architecture by event-driven
  metrics from Apache Kafka over standard resources (CPU, Memory) with Knative and
  KEDA integration for serverless Java using Quarkus.\r\n"
startsAt: '2023-04-06T15:00:00'
endsAt: '2023-04-06T16:00:00'
isServiceSession: false
isPlenumSession: false
speakers:
- id: 4b82436a-947c-45bb-9d5d-3b94c02c8faa
  name: Daniel Oh
categories:
- id: 43783
  name: Track
  categoryItems:
  - id: 166731
    name: Unobtanium
  sort: 0
- id: 43785
  name: Session Format
  categoryItems:
  - id: 143440
    name: session
  sort: 2
roomId: 33056
room: 312 (Unobtanium)
liveUrl: 
recordingUrl: 
slug: event-driven-autoscaling-for-serverless-java

---
