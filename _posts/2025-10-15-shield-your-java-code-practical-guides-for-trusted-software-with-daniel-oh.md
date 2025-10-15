---
_schema: default
layout: post-article
title: 'Shield your Java Code: Practical Guides for Trusted Software with Daniel Oh'
img: /assets/images/daniel-oh.jpg
---
Security has become one of the defining challenges of modern software engineering — especially in the Java ecosystem. In his Devnexus session, **Daniel Oh** explored the critical importance of protecting code in a cloud-native environment, drawing lessons from major industry breaches such as **Equifax’s Apache Struts vulnerability**, and ecosystem-wide crises like **Log4Shell** and **Spring4Shell**. These incidents revealed a hard truth: while DevSecOps calls for shared responsibility, developers are often pressured to prioritize delivery speed over security. The result is a dangerous imbalance — where a single outdated dependency or unchecked library can expose millions of users and cause devastating financial and reputational losses. Oh’s talk focused on shifting security from reactive patching to an automated, continuous process of verifiable trust across the entire software lifecycle.

The foundation of this transformation is a **Secure Software Supply Chain (SSSC)** built on **Zero Trust Architecture (ZTA)** principles: *never trust, always verify*. Oh outlined a practical three-phase approach that starts in the **developer’s inner loop**, where IDE extensions like **Red Hat Dependency Analytics** proactively detect and flag vulnerabilities before code is even committed. This “shift-left” model keeps risky components from ever reaching source control. From there, automation in the **outer loop** takes over — leveraging tools such as **Tekton** and **Sigstore** to digitally sign builds, enforce policy compliance, and generate a **Software Bill of Materials (SBOM)**. The SBOM provides full transparency into every artifact, making it easy to verify provenance and integrity across environments.

In the deployment phase, continuous verification is handled by **Advanced Cluster Security (ACS)**, which acts as a policy enforcement layer within Kubernetes. ACS performs three ongoing validations: **Image Scanning** (detecting known CVEs), **Image Checking** (enforcing company-specific rules like disallowing unnecessary binaries), and **Deployment Checks** (ensuring manifests include operational safeguards like CPU and memory limits). Together, these measures turn security from a one-time task into an ongoing safety net that operates from development through production. Oh also highlighted the advantages of **GraalVM native compilation**, which reduces runtime dependencies and shrinks the application’s attack surface while improving startup performance — a win for both performance and security.

The key takeaway for developers is clear: the future of secure software lies in automation and transparency. By embedding security checks directly into the pipeline and adopting a Zero Trust mindset, engineering teams can achieve speed and safety simultaneously. Rather than treating security as a blocker, developers can make it a competitive advantage — creating resilient, verifiable systems that stand up to the demands of modern cloud environments.

<div style="margin-top: 1.5em;">
<iframe width="560" height="315" src="https://www.youtube.com/embed/Q3tiD6pZ6cM" title="Devnexus 2025 - Shield Your Java Code: Practical Guides for Trusted Software in the Cloud Native Era" frameborder="0" allowfullscreen=""></iframe>
</div>

---

At **Devnexus**, sessions like Daniel Oh’s remind us that great software isn’t just fast — it’s trusted.<br>
Join us at **Devnexus 2026** to connect with experts, explore real-world Java security strategies, and help shape the future of secure, cloud-native development.

👉 Learn more and register at [**devnexus.com**](https://devnexus.com)