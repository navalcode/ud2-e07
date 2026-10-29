# Playlist membership: composite identity and ordering

[Español](README.es.md) · [GitHub](https://github.com/navalcode)

## Overview

A JPA model/service exercise with **11 local tests**: the original context, five identity/graph checks and five persistence journeys. There are no REST controllers or app screens in this repository.

AddedTo now uses its actual `AddedToPK` in repository/service signatures. The embedded key has value equality/hashing. A newly added membership receives the next position after the largest remaining position in its own playlist. Repeating the same song/playlist pair keeps its original timestamp and position.

Transient graph helpers avoid repeated references, detach a previous owner and preserve the real owner when asked to remove from a different one. An assigned mapped identity cannot be moved to another song/playlist. Persisted membership deletion uses the typed repository/service; tests flush, clear, find with an equal composite key and delete without deleting either parent.

The two-argument `addSongToPlaylist(song, playlist)` returns the saved link and requires saved parents. The original four-argument call remains compatible and delegates to the same transaction; it reloads managed parents rather than merging detached collections. Tests cover missing saved IDs before writes and per-playlist ordering with gaps. Concurrent ordering is not guaranteed.

Read [identity contracts](e07/src/test/java/com/salesianos/triana/e07/MembershipContractTest.java), [JPA journeys](e07/src/test/java/com/salesianos/triana/e07/MembershipJpaJourneyTest.java) and AddedToService. MainDePrueba retains the original music seed/attribution. The command below runs that persistence example without a web server. Historical Maven/Spring pins and licenses remain; this is not a streaming service, durable storage product or new hosted CI.

## Run

The packaged persistence example was also started without a web server; its original seed completed and the process exited cleanly.

Requires JDK 17. From the repository root:

```sh
cd e07
sh ./mvnw verify
java -jar target/e07-0.0.1-SNAPSHOT.jar --spring.main.web-application-type=none
```

Defaults use memory-only H2, a disabled H2 console and loopback binding. Data disappears on shutdown; the original file DB is not opened.

Local JDK 17/macOS results from 29 October 2026 are recorded in [verification.json](docs/verification.json). They do not establish production readiness. [Design and contract decisions](DESIGN.md) · [Components and attribution](THIRD_PARTY.md).

## Resumen

[La versión española completa](README.es.md) cubre los mismos contratos, comandos y límites.
