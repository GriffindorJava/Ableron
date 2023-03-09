# Ableron Spring Boot
[![Build Status](https://github.com/ableron/ableron-spring-boot/actions/workflows/main.yml/badge.svg)](https://github.com/ableron/ableron-spring-boot/actions/workflows/main.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.ableron/ableron-spring-boot/badge.svg)](https://mvnrepository.com/artifact/io.github.ableron/ableron-spring-boot)

**Spring Boot Support for Ableron Server Side UI Composition**
1. Spring Boot 3.x project: Please use ableron-spring-boot-starter 3.x (Java 17+, spring-webmvc only)
1. Spring Boot 2.x project: Please use ableron-spring-boot-starter 2.x (Java 11+, spring-webmvc only)

## Installation
Maven:
```xml
<dependency>
  <groupId>io.github.ableron</groupId>
  <artifactId>ableron-spring-boot-starter</artifactId>
  <version>3.0.2</version>
</dependency>
```
Gradle:
```groovy
implementation 'io.github.ableron:ableron-spring-boot-starter:3.0.2'
```

## Usage
Use fragments in response body
```html
<fragment src="https://your-fragment-url" />
```

## Configuration Options
* `ableron.enabled`: Whether UI composition is enabled. Defaults to `true`
* `ableron.requestTimeoutMillis`: Timeout for HTTP requests. Defaults to `5 seconds`
* `ableron.defaultResponseCacheDurationMillis`: Duration to cache HTTP responses in case neither `Cache-Control` nor `Expires` header is present. Defaults to `5 minutes`
* `ableron.maxCacheSizeInBytes`: Maximum size in bytes the response cache may have. Defaults to `10 MB`

## Library Development

### Quick Start
* Install to local `.m2` repository
  ```console
  $ ./mvnw clean install
  ```
* Check for outdated dependencies via [Versions Maven Plugin](https://www.mojohaus.org/versions/versions-maven-plugin/index.html)
  ```console
  $ ./mvnw versions:display-dependency-updates
  ```
* Update maven wrapper to newer version
   ```console
   $ ./mvnw wrapper:wrapper -Dmaven=<version, e.g. 3.9.0>
   ```

### Tooling
* See Artifacts in [nexus repository manager](https://s01.oss.sonatype.org/index.html#nexus-search;gav~io.github.ableron~ableron-spring-boot*~~~)

### Perform Release
1. Create new release branch (`git checkout -b release-x.x.x`)
2. Set release version in `pom.xml`, `ableron-spring-boot-starter/pom.xml` and `ableron-spring-boot-autoconfigure/pom.xml` (remove `-SNAPSHOT`)
3. Update version in maven and gradle dependency declaration code snippets in`README.md`
4. Merge release branch into `main`
5. Release and deploy to Maven Central is performed automatically
6. Manually create [GitHub Release](https://github.com/ableron/ableron-spring-boot/releases/new)
   1. Set tag name to the version declared in `pom.xml`, e.g. `v0.0.1`
   2. Set release title to the version declared in `pom.xml`, e.g. `0.0.1`
   3. Let GitHub generate the release notes automatically
   4. Publish release
7. Set artifact version in `main` branch to next `-SNAPSHOT` version via new commit
8. Apply changes to `2.x` branch as well, if sensible

## Contributing
Contributions are greatly appreciated. To contribute you can either simply open an issue or fork the repository and create a pull request:
1. Fork this repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Added some amazing feature'`)
4. Push to your branch (`git push origin feature/amazing-feature`)
5. Open a pull request
