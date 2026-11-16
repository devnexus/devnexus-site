---
questionAnswers: []
id: '1341059'
title: AI Security Engineer Foundations
description: "### 1. OWASP Top 10 for Agentic Applications\r\n\r\nGain a practical
  understanding of the key security risks introduced by agentic AI applications. This
  module explains how risk changes when AI systems can reason, plan, use tools, access
  memory, interact with other agents, and take actions across business workflows.\r\n\r\nBy
  the end of this module you'll be able to:\r\n\r\n- Explain the OWASP Top 10 for
  Agentic Applications vs. the Web and LLM versions\r\n- Describe how agentic AI changes
  security models through goals, tools, memory, identity, permissions, and orchestration\r\n-
  Define ten agentic risks, including Agent Goal Hijacking, Tool Misuse, Identity
  and Privilege Abuse, and Agentic Supply Chain Vulnerabilities\r\n- Map risks to
  controls: least privilege, scoped identities, tool allowlisting, approval gates,
  memory isolation, and audit logging\r\n- Assess an agent's boundaries — its capabilities,
  authority, data access, callable tools, and logging/approval requirements\r\n\r\n###
  2. Addressing Shadow AI\r\n\r\nStep into the shoes of the AI Security Engineer to
  navigate the unexpected things that surface when you look under the hood of your
  AI posture. This module operates at the intersection of platform security, ML engineering,
  and threat intelligence.\r\n\r\nBy the end of this module you'll be able to:\r\n\r\n-
  Define Shadow AI vs. Shadow IT, and cite current prevalence and cost data\r\n- Recognize
  what makes Shadow AI harder to govern than traditional shadow IT\r\n- Identify common
  Shadow AI risk scenarios inside an enterprise\r\n- Define an AI Bill of Materials
  (AIBOM) and its governance functions\r\n- Compare an AIBOM against an SBOM across
  scope, behavior, risk classes, and update cadence\r\n- Generate an AIBOM using the
  Snyk CLI and inspect it in the Evo platform\r\n\r\n### 3. AI Governance & Policy
  as Code\r\n\r\nThis module tackles why most corporate AI policies exist only on
  paper. It opens by diagnosing the common failure patterns, unenforced wiki policies,
  review boards too slow to keep up with development, and outright bans that just
  push AI use underground, before making the case that governance only works when
  it's built directly into the software pipeline rather than handled through traditional
  vendor-style approval processes.\r\n\r\nFrom there, the module walks through the
  practical mechanics of doing this well: identifying the four trust boundaries inside
  an AI system (data ingestion, external data sources, context construction, and downstream
  actions) that any real policy needs to address; turning plain-language governance
  rules into automated checks that run in continuous integration; and stress-testing
  those rules against ambiguous, real-world edge cases where reasonable teams disagree.
  It also covers designing a fast, visible exception process so legitimate exceptions
  don't quietly undermine the policy, and closes with what auditors and regulators
  actually expect to see, an inventory, a risk assessment, and an enforcement log,
  tying this back to frameworks like NIST's AI RMF, the EU AI Act, and Gartner's AI
  TRiSM.\r\n\r\nBy the end, a learner should come away understanding how to convert
  an AI governance intent into an enforceable, auditable control rather than a static
  document, and how to spot the gaps and blind spots that make most current AI policies
  unenforceable in practice.\r\n\r\n### 4. Securing Agents & MCP\r\n\r\n*How SKILL.md
  Introduced Malware.*\r\n\r\nA two-part session covering \"ToxicSkills\" research
  on supply-chain threats targeting agent ecosystems, plus active exploitation techniques
  against Model Context Protocol (MCP) deployments — credential exfiltration via malicious
  tool descriptions, and arbitrary code execution via insecure servers.\r\n\r\nBy
  the end of this module you'll be able to:\r\n\r\n- Explain the Model Context Protocol,
  why it exists, and its server security fundamentals\r\n- Articulate why MCP security
  is urgent in 2026: SDK download scale, agentic IDE adoption, and MCP's placement
  in a developer's most privileged context\r\n- Define tool poisoning and the impact
  of hidden instructions\r\n- Distinguish malicious MCP servers from vulnerable server
  code\r\n- Run MCP-Scan against a local configuration and interpret its findings\r\n-
  Run Snyk Code against MCP server source and interpret command-injection and path-traversal
  findings\r\n\r\n### 5. Securing Vibe Coding\r\n\r\nIn this module, we break down
  the security implications of vibe coding and share actionable strategies to secure
  AI-generated code at scale — including Snyk Studio's approach to securing the AI-powered
  SDLC from code to deployment.\r\n\r\nBy the end of this module you'll be able to:\r\n\r\n-
  Define vibe coding and explain how it changes the development process\r\n- Describe
  the \"Lobster Ecosystem\" — agentic AI tools, agent skill registries, and automation
  frameworks — and its supply-chain attack surface, including agent skill poisoning
  and slopsquatting\r\n- Apply a five-layer Secure-by-Design Playbook to vibe-coded
  projects\r\n- Identify common AI-generated code vulnerability patterns: disabled
  row-level security, hardcoded secrets, missing authentication, and client-side security
  logic\r\n- Map vulnerabilities to the OWASP Top 10 for LLM Applications\r\n- Distinguish
  vibe coding from vibe *hacking*\r\n\r\n### 6. AI Pen Testing\r\n\r\nGain a practical
  understanding of how to test AI-powered and agentic applications for security weaknesses.
  This module explains how AI penetration testing goes beyond traditional web and
  API testing by examining prompts, model behavior, context handling, tool access,
  memory, permissions, and downstream actions.\r\n\r\nBy the end of this module you'll
  be able to:\r\n\r\n- Explain how AI penetration testing differs from traditional
  application, API, and infrastructure testing\r\n- Identify AI-specific attack surfaces:
  prompts, system instructions, RAG pipelines, embeddings, tools, agents, memory,
  and MCP-connected services\r\n- Test for common weaknesses: prompt injection, sensitive
  information disclosure, system prompt leakage, excessive agency, and unsafe output
  handling\r\n- Build safe, repeatable test cases without harmful or production-impacting
  techniques\r\n- Map findings to remediation: input/output validation, tool allowlisting,
  scoped tokens, human approval gates, and audit logging\r\n- Combine AI-specific
  testing with standard API, authentication, authorization, and dependency testing"
startsAt:
endsAt:
isServiceSession: false
isPlenumSession: false
speakers:
- id: e5aad250-139d-4a16-9c6e-d3534bbe1fc6
  name: Micah Silverman
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
isConfirmed: false
track: Workshop
slug: ai-security-engineer-foundations

---
