Tranquil
========

[![Build Status](https://travis-ci.org/glytching/tranquil.svg?branch=master)](https://travis-ci.org/glytching/tranquil)  [![Coverage Status](https://coveralls.io/repos/github/glytching/tranquil/badge.svg?branch=master)](https://coveralls.io/github/glytching/tranquil?branch=master) [![Scrutinizer](https://img.shields.io/scrutinizer/g/glytching/tranquil.svg)](https://scrutinizer-ci.com/g/glytching/junit-extensions/)

> In Progress

A library for reading data from JSON (or a Java Map) using a SQL grammar.

For example:

Given this JSON string:

```
[
  {
    "name": "tap",
    "price": 49.99,
    "quantity": 10,
    "active": true,
    "owner": null,
    "since": "2018-09-07"
  },
  {
    "name": "sink",
    "price": 99.99,
    "quantity": 100,
    "active": false,
    "owner": null,
    "since": "2018-09-02"
  }
]
```

The following code ...

```
Tranquil.parse(json)
  .read(
    "*",
    "quantity = 10 and name like 'ta' and owner is null "
  )
```

... will return:

```
{
  "name": "tap",
  "price": 49.99,
  "quantity": 10,
  "active": true,
  "owner": null,
  "since": "2018-09-07"
}
```

And, using projections with some transformations, the following code ...

```
Tranquil.parse(json)
  .read(
    "name, price, 'bathware' as category, 2 * quantity as twiceTheQuantity",
    "quantity = 10 and name like 'ta' and owner is null "
  )
```

... will return:

```
{
  "name": "tap",
  "price": 49.99,
  "category": "bathware",
  "twiceTheQuantity": 20
}
```

Plenty more to follow including:

* Documentation
* Links to CI, Maven Central etc
* Publishing to Maven Central

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
    limitations under the License.###