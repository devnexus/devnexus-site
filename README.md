DevNexus Site
=============

This project contains the code of the web application for the **DevNexus** conference in Atlanta, GA. The live-site is at:

http://www.devnexus.com/

Build Status:

[![Build Status](https://travis-ci.org/devnexus/devnexus-site.png?branch=master)](https://travis-ci.org/devnexus/devnexus-site)

#### Running Postgres on Mac

You may run into issues such as the following:

````
FATAL:  could not create shared memory segment: Invalid argument
DETAIL:  Failed system call was shmget(key=5432001, size=5201920, 03600).
HINT:  This error usually means that PostgreSQL's request for a shared memory segment exceeded your kernel's SHMMAX parameter.  You can either reduce the request size or reconfigure the kernel with larger SHMMAX.  To reduce the request size (currently 5201920 bytes), reduce PostgreSQL's shared memory usage, perhaps by reducing shared_buffers or max_connections.
	If the request size is already small, it's possible that it is less than your kernel's SHMMIN parameter, in which case raising the request size or reconfiguring SHMMIN is called for.
	The PostgreSQL documentation contains more information about shared memory configuration.
````

This can be fixed by issuing:

* `sudo sysctl -w kern.sysv.shmmax=12582912`
* `sudo sysctl -w kern.sysv.shmall=12582912`


