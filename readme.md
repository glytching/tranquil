Tranquil
========

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
