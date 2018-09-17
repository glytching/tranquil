Tranquil
========

[![Build Status](https://travis-ci.org/glytching/tranquil.svg?branch=master)](https://travis-ci.org/glytching/tranquil)  [![Coverage Status](https://coveralls.io/repos/github/glytching/tranquil/badge.svg?branch=master)](https://coveralls.io/github/glytching/tranquil?branch=master) [![Scrutinizer](https://img.shields.io/scrutinizer/g/glytching/tranquil.svg)](https://scrutinizer-ci.com/g/glytching/junit-extensions/) [![Javadoc](https://javadoc-badge.appspot.com/io.github.glytching/tranquil.svg?label=javadoc)](https://www.javadoc.io/doc/io.github.glytching/tranquil/1.0.0) [![Maven Central](https://img.shields.io/maven-central/v/io.github.glytching/tranquil.svg)](http://repo1.maven.org/maven2/io/github/glytching/tranquil/1.0.0/) [![GitHub Release](https://img.shields.io/github/release/glytching/tranquil.svg)](https://github.com/glytching/tranquil/releases)

Tranquil is a Java library which provides a SQL-esque language for querying JSON and `Map`s.

Tranquil wraps a JSON de/serialization library and adds some predicate and projection capabilities. The inputs for these capabilities are expressed in SQL since many developers are likely to be familiar with using SQL to express `select` and `where` clauses.

That's quite a mouthful so have a quick look at the [simple example](https://github.com/glytching/tranquil/wiki/SimpleExample) for clarification.

Plenty more details [in the docs](https://github.com/glytching/tranquil/wiki).


### Examples

See the [docs](https://github.com/glytching/tranquil/wiki), specifically:

* [Simple](https://github.com/glytching/tranquil/wiki/SimpleExample)
* [Nested](https://github.com/glytching/tranquil/wiki/NestedExample)
* [Matching](https://github.com/glytching/tranquil/wiki/MatchingExample)
* [Transformation](https://github.com/glytching/tranquil/wiki/TransformationExample)
* [Bespoke Ouput Types](https://github.com/glytching/tranquil/wiki/BespokeOutputTypesExample)

### Using Tranquil

The `tranquil` library is available on [Maven Central](http://search.maven.org/#artifactdetails%7Cio.github.glytching%7Ctranquil%7C1.0.0%7Cjar). Note: if using Tranquil as a test utility then use `<scope>test</scope>` or `testCompile`.

#### Maven

```
<dependency>
    <groupId>io.github.glytching</groupId>
    <artifactId>tranquil</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### Gradle

```
compile 'io.github.glytching:tranquil:1.0.0'
```

### Building Tranquil

```
$ git clone https://github.com/glytching/tranquil.git
$ cd tranquil
$ mvn clean install
```

This will compile and run all automated tests and install the library in your local Maven repository.

Note: the code is formatted using the [Google Code Formatter](https://github.com/google/google-java-format).

### License

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.

    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.