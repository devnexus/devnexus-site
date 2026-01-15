---
questionAnswers: []
id: '1035064'
title: Architecting Microservices for Agentic AI Integration
description: "Agents don’t just “call APIs”—they plan, retry, chain, and orchestrate
  across services. That changes how we design microservices, boundaries, workflows,
  and ops. This talk lays out a practical architecture playbook: move from request/response
  thinking to event-driven flows; use sagas/outbox for correctness; enforce circuit
  breakers/bulkheads for blast-radius control; shape service boundaries around domains
  and agent tasks; and wire in tracing, versioning, and deprecation for long-lived
  agents. You’ll leave with patterns, guardrails, and KPIs to integrate agents without
  breaking prod.\r\n\r\nWhat is Agentic AI in Microservices\r\n\r\nAgents plan, retry,
  chain services → need deterministic, idempotent APIs.\r\nServices must be tool-callable
  (stable operationId, strict schemas).\r\nSystems must survive retry storms + fan-out.\r\nWhy
  Monoliths & Non-Event Systems Fail\r\n\r\nLatency and tight coupling collapse under
  agent retries.\r\nNo event history → agents can’t re-plan.\r\nFailures amplify without
  bulkheads/circuit breakers.\r\nOps teams can’t see human vs agent traffic.\r\nCore
  Patterns for Agent-Friendly Systems\r\n\r\nEvent-driven flows: decouple, replay-safe.\r\nSaga/outbox:
  long workflows with compensations, reliable events.\r\nCircuit breakers/bulkheads:
  contain failure, reduce blast radius.\r\nService mesh/sidecars: centralize retries,
  telemetry, policies.\r\nDesigning Service Boundaries\r\n\r\nBoundaries around tasks/domains
  (Orders, Claims, Appointments).\r\nExpose task APIs (ReserveInventory, ScheduleAppointment).\r\nResponses
  = reason codes + next actions, not just raw data.\r\nAvoid polymorphism; keep contracts
  predictable.\r\nIntegrating Agent Frameworks\r\n\r\nTool calling: operationId as
  tool name; strict inputs/outputs.\r\nSupervisor/planner patterns: policy checks
  between steps.\r\nAsync jobs: job IDs, progress endpoints, webhooks.\r\nSafety:
  least privilege, quotas, payload validation.\r\nInfrastructure & Operations\r\n\r\nObservability:
  OpenTelemetry, x-agent-run-id, dashboards for retries & success rates.\r\nVersioning:
  SemVer, multi-version routing, Deprecation/Sunset headers.\r\nResilience: autoscale
  on retry rate; degrade to read-only mode; run failover drills.\r\nTakeaways\r\n\r\nDesign
  APIs for determinism, idempotency, and tool-callability.\r\nUse event-driven + saga/outbox
  for consistency.\r\nContain failures with circuit breakers, bulkheads, and meshes.\r\nMake
  service boundaries task-focused for agent workflows.\r\nSeparate and monitor agent
  vs human traffic.\r\nBuild lifecycle discipline: versioning, deprecation, multi-version
  gateways."
startsAt: '2026-03-05T15:50:00'
endsAt: '2026-03-05T16:50:00'
isServiceSession: false
isPlenumSession: false
speakers:
- id: 32cb0f88-b1ad-44c2-8860-86a5ac46c59a
  name: Rohit Bhardwaj
categories:
- id: 107979
  name: Track
  categoryItems:
  - id: 389056
    name: Gen AI
  sort: 0
roomId: 70247
room: Gen AI
liveUrl:
recordingUrl:
status: Accepted
isInformed: true
isConfirmed: false
track: Gen AI
slug: architecting-microservices-for-agentic-ai-integration

---
