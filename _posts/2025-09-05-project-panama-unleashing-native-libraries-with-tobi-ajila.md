---
_schema: default
layout: post-article
title: Project Panama Unleashing Native Libraries with Tobi Ajila
img: /assets/images/tobi-ajila-video-still.jpg
---

For decades, the **Java Native Interface (JNI)** has been the go-to solution for connecting Java with native code. But as **Tobi Ajila**, JVM developer on the OpenJ9 team, explained in his Devnexus session, JNI has always been **complex, verbose, and error-prone**—often introducing performance bottlenecks and unsafe memory handling. Enter **Project Panama**, a modern alternative designed to replace JNI with a safer, faster, and far more developer-friendly approach. With Panama, working with native code feels almost like calling a regular Java API, removing many of the barriers that have frustrated developers for years.  

Ajila described Panama as a **three-part solution** that makes native interop both practical and powerful. The **Foreign Memory Access API** allows Java developers to safely manage off-heap memory with concepts like memory segments and arenas for lifecycle control. The **Foreign Function Support API** automates calling native functions and handling complex types like structs—no more writing layers of fragile JNI bindings. Finally, **JExtract** takes the process a step further by automatically generating Java bindings directly from C header files. With a single command, developers can turn an entire native library into a set of ready-to-use Java classes.  

To drive the point home, Ajila contrasted **boilerplate-heavy JNI code** with the **concise, declarative style of Panama**. By showing side-by-side examples, he highlighted just how much simpler and safer the new approach is, while still delivering better performance. With its inclusion in recent JDKs, Project Panama is no longer experimental—it is quickly becoming the **standard way forward for native interoperability in Java**. Ajila concluded by emphasizing that Panama lowers the barrier for developers to leverage native code, enabling richer integrations and expanding what Java applications can do without sacrificing safety or readability.  

---

## Watch the Session  

[![Watch the talk](https://img.youtube.com/vi/jbrCXRHfnjs/0.jpg)](https://www.youtube.com/watch?v=jbrCXRHfnjs)  

🚀 Watch the full session above, and join us at **Devnexus 2026** to connect with fellow developers and explore the future of software together.  
  

---
