---
questionAnswers: []
id: '1331494'
title: 'MCP Killed the Handshake: What Stateless Really Means for Agents'
description: "Older MCP was stateless in spirit and stateful in practice: initialize
  opened a session, and every later message leaned on that memory. The 2026-07-28
  revision removed the handshake outright. There is no first message, no session,
  and a server that needs input cannot send a request — modern clients silently discard
  them.\r\n\r\nThis talk walks the wire. Live in MCP Inspector we will watch discovery
  replace initialize, watch _meta replace the session, and watch a server ask a question
  by answering a tool call. Then: a tool that returns a UI, a task handle instead
  of a blocking result, and why continuation state now has to travel on the wire —
  because the server has nowhere else to keep it.\r\n\r\nIf you have only used an
  MCP SDK, this is the protocol underneath. If you are about to build a server, this
  is why “just add a session” is the wrong instinct."
startsAt:
endsAt:
isServiceSession: false
isPlenumSession: false
speakers:
- id: 7f7eb959-dfdc-4d7f-a6a0-bd64b2710282
  name: David Parry
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
slug: mcp-killed-the-handshake-what-stateless-really-means-for-agents

---
