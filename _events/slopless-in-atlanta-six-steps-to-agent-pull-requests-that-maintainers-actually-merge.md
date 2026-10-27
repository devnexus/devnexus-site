---
questionAnswers: []
id: '1347895'
title: 'Slopless in Atlanta: Six Steps to Agent Pull Requests That Maintainers Actually
  Merge'
description: "Any codebase you depend on is now yours to fix, even in a language you
  don't write. The problem is that maintainers are drowning in sloppy pull requests
  blasted at them from other people's clankers. This talk is a method for contributing
  to any software project using coding agents without producing slop, shown on one
  rather nasty real bug. You can run the same steps on the next bug in your own codebase.\r\n\r\nHandy
  is the open-source dictation app I use to talk to my agents, with 31,000 stars on
  GitHub. Its memory grew from 249 MB to 9.5 GB in 36 hours and the kernel killed
  my machine mid-sentence. The backend is 40,000 lines of Rust, a language I don't
  write. Someone else's agent-written fix didn't compile. Mine was merged. The difference
  was the engineering methodology I led the agents with:\r\n\r\n1. **Give the agents
  a way to run the app.** A harness that runs it without me, including a way for an
  agent to speak into the microphone.\r\n2. **Record every layer at once.** No one
  tool could see across the process boundaries the leak crossed, so the agents built
  one.\r\n3. **Measure, don't guess.** Eight possible causes. The measurements cleared
  seven.\r\n4. **Treat each fix as an experiment.** Three candidates, each behind
  a switch. One did nothing and was deleted.\r\n5. **Have agents who know nothing
  review it cold.** They showed the headline metric hid most of the leak. The fix
  was a big reduction, not a cure.\r\n6. **Ship the proof with the patch.** -2 MB
  over four hours against +3,885 MB without it.\r\n\r\nNone of this is specific to
  Rust, memory leaks, or open source. I'll show the whole arc, from the machine dying
  to the merge, and explain what I'd do differently next time. The full write-up is
  at gallon.me. Point your agent at it and it will know how to fix a bug this way."
startsAt:
endsAt:
isServiceSession: false
isPlenumSession: false
speakers:
- id: 459c16dd-b498-4d8f-aeb9-f02c0db046fa
  name: Corey Gallon
categories:
- id: 139840
  name: Track
  categoryItems:
  - id: 507397
    name: AI Developer Tools
  sort: 0
roomId:
room:
liveUrl:
recordingUrl:
status: Accepted
isInformed: true
isConfirmed: false
track: AI Developer Tools
slug: slopless-in-atlanta-six-steps-to-agent-pull-requests-that-maintainers-actually-merge

---
