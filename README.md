# purescript-node-fs

## JVM tests

`./bin/test` delegates to the [common runner](../javapurs/docs/testing.md#port-particulier) as `node-fs`, but currently exits **1** with an unsupported-completion diagnostic: `Test.Main` combines filesystem callbacks, streams and `launchAff_` without a joined completion action.
`./bin/test --help` is read-only. Even with `--clean`, this unsupported protocol is rejected before build/workspace creation; the checkout and its outputs are preserved. See the [protocol inventory](../javapurs/docs/port-launchers.md).

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
