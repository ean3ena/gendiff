[![Actions Status](https://github.com/ean3ena/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/ean3ena/java-project-71/actions)
[![Java CI](https://github.com/ean3ena/java-project-71/actions/workflows/main.yml/badge.svg)](https://github.com/ean3ena/java-project-71/actions/workflows/main.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/8f308de161a3695c3700/maintainability)](https://codeclimate.com/github/ean3ena/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/8f308de161a3695c3700/test_coverage)](https://codeclimate.com/github/ean3ena/java-project-71/test_coverage)

## Описание

Вычислитель отличий – программа, определяющая разницу между двумя структурами данных.

Возможности утилиты:

Поддержка разных входных форматов: yaml и json
Генерация отчета в виде plain text, stylish и json

Пример использования:

```
# вывод справки
./build/install/app/bin/app -h

Usage: gendiff [-hV] [-f=format] filepath1 filepath2
Compares two configuration files and shows a difference.
      filepath1         path to first file
      filepath2         path to second file
  -f, --format=format   output format [default: stylish]
  -h, --help            Show this help message and exit.
  -V, --version         Print version information and exit.
```

```
# формат stylish
./build/install/app/bin/app filepath1.json filepath2.json

{
  + follow: false
  + numbers: [1, 2, 3]
    setting1: Value 1
  - setting2: 200
  - setting3: true
  + setting3: {key=value}
  + setting4: blah blah
}
```

```
# формат plain
./build/install/app/bin/app filepath1.yml filepath2.yml -f plain

Property 'follow' was added with value: false
Property 'baz' was updated. From 'bas' to 'bars'
Property 'group2' was removed
```

```
# формат json
./build/install/app/bin/app filepath1.yml filepath2.yml -f json

[ {
  "newValue" : [ "a", "b", "c" ],
  "oldValue" : [ "a", "b", "c" ],
  "key" : "chars1",
  "status" : "unchanged"
}, {
  "newValue" : false,
  "oldValue" : [ "d", "e", "f" ],
  "key" : "chars2",
  "status" : "changed"
}, {
  "newValue" : true,
  "oldValue" : false,
  "key" : "checked",
  "status" : "changed"
} ]
```

---

## Сравнение файлов (демонстрация)

#### Сравнение плоских файлов .json

демонстрация - https://asciinema.org/a/3OJUG64YKHCYifJMu9OKGGbvH

#### Сравнение плоских файлов .yaml

демонстация - https://asciinema.org/a/jnege7tGp5kelZXEcdcccxXHc

#### Сравнение файлов .json и .yml с вложенностью

демонстрация - https://asciinema.org/a/ZoBtIQTCkMrAVz11vIFG5gHJ7

#### Сравнение файлов .json и .yml (плоский формат)

демонстрация - https://asciinema.org/a/AOjFi1FamYHCbZhYN5XUgHFZ7

#### Сравнение файлов .json и .yml (формат json)

демонстрация - https://asciinema.org/a/udDK5hwK0MKrgxdGLeHT1vKsD
