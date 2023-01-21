# Ableron Spring Boot
[![Build Status](https://github.com/ableron/ableron-spring-boot/actions/workflows/main.yml/badge.svg)](https://github.com/ableron/ableron-spring-boot/actions/workflows/main.yml)
[![License](https://img.shields.io/github/license/ableron/ableron-spring-boot)](https://github.com/ableron/ableron-spring-boot/blob/main/LICENSE)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.ableron/ableron-spring-boot/badge.svg)](https://mvnrepository.com/artifact/io.github.ableron/ableron-spring-boot)
[![GitHub Release](https://img.shields.io/github/v/release/ableron/ableron-spring-boot.svg)](https://github.com/ableron/ableron-spring-boot/releases)
[![Java Version](https://img.shields.io/badge/Java-17+-4EB1BA.svg)](https://docs.oracle.com/en/java/javase/17/)

Spring Boot Support for Ableron Server Side UI Composition

## Installation
Maven:
```xml
<dependency>
  <groupId>io.github.ableron</groupId>
  <artifactId>ableron-spring-boot-starter</artifactId>
  <version>0.0.1</version>
</dependency>
```
Gradle:
```groovy
dependencies {
  implementation 'io.github.ableron:ableron-spring-boot-starter:0.0.1'
}
```

## Usage
Use fragments in response body
```html
<fragment src="https://your-fragment-url" />
```

## Configuration Options
* `ableron.enabled`: Whether to enable Ableron UI composition

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

### Perform Release
1. Create new release branch (`git checkout -b release-x.x.x`)
2. Set release version in `ableron-spring-boot-starter/pom.xml` (remove `-SNAPSHOT`)
3. Set release version in `ableron-spring-boot-autoconfigure/pom.xml` (remove `-SNAPSHOT`)
4. Update version in maven and gradle dependency declaration code snippets in`README.md`
5. Merge release branch into `main`
6. Release and deploy to Maven Central is performed automatically
7. Manually create [GitHub Release](https://github.com/ableron/ableron-spring-boot/releases/new)
   1. Set tag name to the version declared in `ableron-spring-boot-starter/pom.xml`, e.g. `v0.0.1`
   2. Set release title to the version declared in `ableron-spring-boot-starter/pom.xml`, e.g. `0.0.1`
   3. Let GitHub generate the release notes automatically
   4. Publish release
8. Set artifact version in `main` branch to next `-SNAPSHOT` version via new commit
