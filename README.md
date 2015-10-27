DevNexus Site
=============

This project contains the code of the web application for the **DevNexus** conference in Atlanta, GA. The live-site is at:

http://www.devnexus.com/

Build Status:

[![Build Status](https://travis-ci.org/devnexus/devnexus-site.png?branch=master)](https://travis-ci.org/devnexus/devnexus-site)

## Requirements

* Git
* Maven

## Quickstart (Demo Mode)

	$ git clone https://github.com/devnexus/devnexus-site.git
	$ cd devnexus-site/
	$ mvn clean package
	$ java -jar target/devnexus.jar

The DevNexus application should successfully startup using demo settings. Open your browser at `http://localhost:8080/`

## Production Mode - Standalone

The DevNexus site uses Postgres as the persistence store in production.

	$ java -jar target/devnexus.jar -DTING_HOME=/opt/ting

### Configuration

```yaml
devnexus:
  twitter:
    enabled: true
    oauth:
      consumerKey:
      consumerSecret:
      accessToken:
      accessTokenSecret:
  mail:
    enabled: true
    authenticationEnabled: false
    debugEnabled: true
    user:
      id:
      password:
      from: info@ajug.org
      cc:
    smtp:
      port: 25
      host: localhost
  recaptcha:
    enabled: true
    publicKey:
    privateKey:
  websocket:
    enabled: true
  server:
    address: http://localhost:8080/ting
    https:
      enabled: false
# registration.state = open | closed | soldout | hide
  registration:
    state: open
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/devnexus
    username:
    password:
    driverClassName: org.postgresql.Driver
database:
  hibernate:
    dialect: org.hibernate.dialect.PostgreSQLDialect
    show_sql: false
    generate_ddl: validate

TING_CLIENT_ID:
TING_CLIENT_SECRET:
TING_PUSH_URL:
TING_PUSH_APP_ID:
TING_PUSH_MASTER_SECRET:
```

## Cloud Foundry Mode

**Demo Deployment**: http://devnexus.cfapps.io/

Mind the Java 8 meta space: https://github.com/cloudfoundry/java-buildpack/issues/174

	$ cf set-env my_app JBP_CONFIG_OPEN_JDK_JRE ‘[memory_heuristics: {metaspace: 128}, memory_sizes: {metaspace: 96m..}]’
	$ cf restage my_app

### Postgres

### Sendgrid

For sending mail notifications, the app uses SendGrid:

http://docs.run.pivotal.io/marketplace/services/sendgrid.html
https://github.com/sendgrid/sendgrid-java



## Running using Docker

TBD

### Resources

* https://spring.io/guides/gs/spring-boot-docker/

## Pittfalls

### Running Postgres on Mac

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


