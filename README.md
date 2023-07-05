# Ableron Spring Boot
[![Build Status](https://github.com/ableron/ableron-spring-boot/actions/workflows/main.yml/badge.svg)](https://github.com/ableron/ableron-spring-boot/actions/workflows/main.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.ableron/ableron-spring-boot/badge.svg)](https://mvnrepository.com/artifact/io.github.ableron/ableron-spring-boot)

**Spring Boot Support for Ableron Server Side UI Composition**
1. Spring Boot 3.x project: Please use ableron-spring-boot-starter 3.x (Java 17+, spring-webmvc only)
1. Spring Boot 2.x project: Please use ableron-spring-boot-starter 2.x (Java 11+, spring-webmvc only)

## Installation
Add dependency [io.github.ableron:ableron-spring-boot-starter](https://mvnrepository.com/artifact/io.github.ableron/ableron-spring-boot-starter) to your project.

Gradle:
```groovy
implementation 'io.github.ableron:ableron-spring-boot-starter:3.2.0'
```

Maven:
```xml
<dependency>
  <groupId>io.github.ableron</groupId>
  <artifactId>ableron-spring-boot-starter</artifactId>
  <version>3.2.0</version>
</dependency>
```

## Usage
Use includes in response body
```html
<ableron-include src="https://your-fragment-url" />
```

## Configuration Options
* `ableron.enabled`: Whether UI composition is enabled. Defaults to `true`
* `ableron.fragment-request-timeout-millis`: Timeout for requesting fragments. Defaults to `3 seconds`
* `ableron.fragment-request-headers-to-pass`: Request headers that are passed to fragment requests if present. Defaults to:
  * `Accept-Language`
  * `Correlation-ID`
  * `Forwarded`
  * `Referer`
  * `User-Agent`
  * `X-Correlation-ID`
  * `X-Forwarded-For`
  * `X-Forwarded-Proto`
  * `X-Forwarded-Host`
  * `X-Real-IP`
  * `X-Request-ID`
* `ableron.primary-fragment-response-headers-to-pass`: Response headers of primary fragments to pass to the page response if present. Defaults to:
  * `Content-Language`
  * `Location`
  * `Refresh`
* `ableron.cache-max-size-in-bytes`: Maximum size in bytes the fragment cache may have. Defaults to `10 MB`

## Contributing
Contributions are greatly appreciated. To contribute you can either simply open an issue or fork the repository and create a pull request:
1. Fork this repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Added some amazing feature'`)
4. Push to your branch (`git push origin feature/amazing-feature`)
5. Open a pull request

## Library Development
See [DEVELOPMENT.md](./DEVELOPMENT.md) for details regarding development of this library.
