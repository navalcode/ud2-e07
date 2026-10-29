# Playlist membership: composite identity and ordering

[English](README.md) · [GitHub](https://github.com/navalcode)

## Resumen

Ejercicio de modelo/servicio JPA con **11 pruebas locales**: contexto original, cinco comprobaciones de identidad/grafo y cinco recorridos de persistencia. Este repositorio no tiene controladores REST ni pantallas de aplicación.

AddedTo utiliza su `AddedToPK` real en las firmas de repositorio/servicio. La clave embebida tiene igualdad y hash por valor. Una pertenencia nueva recibe la posición posterior al máximo existente en su propia lista. Repetir el par canción/lista conserva fecha y posición originales.

Los helpers de grafo transitorio evitan referencias repetidas, retiran el propietario anterior y conservan al real si se solicita retirar desde otro. Una identidad asignada al mapping no se puede mover a otra canción/lista. Para eliminar pertenencias persistidas se usa repositorio/servicio tipado; las pruebas hacen flush, clear, búsqueda con otra clave equivalente y eliminación sin borrar padres.

La llamada de dos argumentos `addSongToPlaylist(song, playlist)` devuelve el enlace guardado y requiere padres persistidos. La firma original de cuatro argumentos sigue disponible y delega en la misma transacción; recarga padres gestionados en vez de fusionar colecciones separadas de la sesión. Se comprueban IDs ausentes antes de escribir y orden por lista con huecos. No se garantiza orden concurrente.

Lee [contratos de identidad](e07/src/test/java/com/salesianos/triana/e07/MembershipContractTest.java), [recorridos JPA](e07/src/test/java/com/salesianos/triana/e07/MembershipJpaJourneyTest.java) y AddedToService. MainDePrueba conserva los datos musicales y atribución originales. El comando ejecuta ese ejemplo de persistencia sin servidor web. Se mantienen Maven/Spring y licencias históricas; no acredita streaming, almacenamiento duradero ni CI alojada nueva.

## Ejecutar

El ejemplo empaquetado también se ejecuta sin servidor web: completa los datos iniciales originales y termina correctamente.

Requiere JDK 17. Desde la raíz del repositorio:

```sh
cd e07
sh ./mvnw verify
java -jar target/e07-0.0.1-SNAPSHOT.jar --spring.main.web-application-type=none
```

Los valores por defecto usan H2 en memoria, consola H2 desactivada y dirección loopback. La base desaparece al terminar; no se abre el fichero DB original.

Resultados locales con JDK 17/macOS del 29 de octubre de 2026 en [verification.json](docs/verification.json). No equivalen a un servicio de producción. [Decisiones de diseño y contrato](DESIGN.md) · [Componentes y atribución](THIRD_PARTY.md).
