---
questionAnswers: []
id: '1331493'
title: 'No Handshake, No Session: Build a Stateless MCP Server in Java'
description: "The Model Context Protocol just forgot how to start a conversation.
  Revision 2026-07-28 deleted the initialize handshake. There is no session, no ping,
  nothing to resume. Every request now carries its own protocol version and capabilities
  in _meta, and if the server needs something, it asks inside the reply.\r\n\r\nIn
  this instructor-led workshop you will build a Java MCP server from scratch, over
  raw STDIO, with no SDK and no framework. Chapter by chapter you will implement the
  transport, JSON-RPC, server/discover, tools, resources, and prompts, then the pieces
  that only make sense once memory is gone: Multi Round-Trip Requests (ask by answering),
  MCP Apps (a tool that returns a UI), and Tasks (call now, fetch later).\r\n\r\nYou
  will leave with a working server you can point at Cursor, Claude Code, or Windsurf,
  and a wire-level understanding of the protocol that SDKs hide.\r\n\r\nAudience:
  Java developers comfortable with JSON who want to see how agents actually talk to
  tools.\r\nPrerequisites: JDK 21+, Gradle, and a laptop that can run MCP Inspector."
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
  - id: 507372
    name: Workshop
  sort: 0
roomId:
room:
liveUrl:
recordingUrl:
status: Accepted
isInformed: true
isConfirmed: true
track: Workshop
slug: no-handshake-no-session-build-a-stateless-mcp-server-in-java

---
