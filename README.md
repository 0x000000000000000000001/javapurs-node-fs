# purescript-node-fs

Java port of the PureScript `node-fs` package. See the
[port maintenance guide](../javapurs/docs/port-maintenance.md)
for source layout, local dependency selection and JVM validation.

## JVM tests

`./bin/test` delegates to the [common runner](../javapurs/docs/testing.md#port-particulier) as `node-fs`. The wrapper runs the synchronous filesystem suites, waits for the exported `TestAff.testAff` action, and finishes with the common marker. Java file streams now emit `end` correctly, so the stream check reaches `all good` before completion.
`./bin/test --help` is read-only and `--clean` rebuilds the backend first. The checkout and its outputs are preserved; see the [protocol inventory](../javapurs/docs/port-launchers.md) and [M27](../javapurs/docs/testing.md#validation-m27).

[![Latest release](http://img.shields.io/github/release/purescript-node/purescript-node-fs.svg)](https://github.com/purescript-node/purescript-node-fs/releases)
[![Build status](https://github.com/purescript-node/purescript-node-fs/workflows/CI/badge.svg?branch=master)](https://github.com/purescript-node/purescript-node-fs/actions?query=workflow%3ACI+branch%3Amaster)
[![Pursuit](https://pursuit.purescript.org/packages/purescript-node-fs/badge)](https://pursuit.purescript.org/packages/purescript-node-fs)

PureScript bindings to node's `fs` module.

## Installation

```
spago install node-fs
```

## Documentation

Module documentation is [published on Pursuit](http://pursuit.purescript.org/packages/purescript-node-fs).
