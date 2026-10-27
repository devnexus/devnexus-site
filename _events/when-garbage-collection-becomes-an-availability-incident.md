---
questionAnswers: []
id: '1342515'
title: When Garbage Collection Becomes an Availability Incident
description: "Abstract: \r\n\r\nGarbage collection is supposed to be invisible, the
  JVM quietly cleaning up while your service does the real work. Until the day it
  isn't.\r\n\r\nWhen our production Java service's dataset grew 10x almost overnight,
  GC stopped being a latency nuisance and became an availability incident. Hosts started
  shedding live write traffic. Heap dumps told a surprising story. And some of the
  worst culprits were hiding in places no one thought to look.\r\n\r\nThis talk is
  the story of how we brought that service back from the edge, from JVM flags all
  the way down to the JDK itself. You'll see how to find what's really driving GC
  pressure, why the metric most teams chase is the wrong one, and which fixes paid
  off far more than expected. Walk away with a practical playbook for the day GC becomes
  your outage.\r\n\r\n\r\nElevator Pitch:\r\n\r\nThis talk is based on a real production
  incident I led the resolution of at LinkedIn. A Java metadata service's dataset
  grew 11x (17K to 200K+ records per region), and GC pressure escalated until host-level
  overload protection began dropping live write traffic. Heap dumps showed 93% of
  the heap was garbage.\r\n\r\nThe talk covers the full diagnosis and fix: JFR and
  heap-dump analysis, G1 humongous objects forcing mixed GCs, a 1GB+ framework header
  cache (over 40% of used heap), per-request object re-materialization fixed with
  identity-based caching, replacing a memory-heavy JSON library, OOM kills traced
  to native memory fragmentation and fixed with an allocator switch, and a major JDK
  upgrade.\r\n\r\nResults: GC overload events and dropped-traffic incidents went to
  zero, peak GC health score rose from ~10 to 90+, and the JDK upgrade alone cut total
  GC pause time ~24% and p99 latency ~42%.\r\n\r\nAttendees leave with a multi step,
  battle-tested playbook for treating GC as an availability concern rather than a
  tuning exercise. The talk is aimed at intermediate-to-advanced Java developers and
  SREs running data-heavy services.\r\n"
startsAt:
endsAt:
isServiceSession: false
isPlenumSession: false
speakers:
- id: 6d2d0bbd-0567-4fd0-9f5d-50ed75655422
  name: Abhishek Bharadwaj
categories:
- id: 139840
  name: Track
  categoryItems:
  - id: 507387
    name: Java & the JVM Ecosystem
  sort: 0
roomId:
room:
liveUrl:
recordingUrl:
status: Accepted
isInformed: true
isConfirmed: true
track: Java & the JVM Ecosystem
slug: when-garbage-collection-becomes-an-availability-incident

---
