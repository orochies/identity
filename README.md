# Prueba Tecnica

Prueba técnica con Spring Security  JWT + MongoDB.

## Ejecutar aplicación

clonar el repositorio github de la aplicación con el siguiente comando

> git clone git@github.com:orochies/identity.git

Ejecutar el siguiente comando de consola de windows para empaqueter la aplicacion en un archivo Jar

> gradlew bootJar

Compilar con docker composer para crear las imagenes y ejecutar los contenedores

> docker-compose up

### Probar los endpoints

Ejecutar una solicitud http con postman a la url para obtener un token de acceso autorizado:

``` json
POST http://localhost:8080/api/login

{
    "username":"user",
    "password": "user"
}
```

Deberá obtener el token de acceso para hacer solicitudes a las siguientes endpoints:

Crear un identity document

```json
POST http://localhost:8080/api/identities

{
    "number": "112380340",
    "expiryDate": "09/24",
    "emissionDate": "2022-05-17T00:00:00.000Z",
    "documentType": {
        "code": "CI",
        "name": "Cédula de identidad."
    }
}
```

Obtener una lista de identity document

```json
GET http://localhost:8080/api/identities
```

Actualizar un identity document

```json
PUT http://localhost:8080/api/identities

{
    "id": "[id obtenido de un documento existente]", 
    "number": "2183176540920",
    "expiryDate": "09/24",
    "emissionDate": "2022-05-17T00:00:00Z",
    "documentType": {
        "code": "DPI",
        "name": "Documento Personal de Identificación."
    }
}
```

Borrar un identity document

```json
PUT http://localhost:8080/api/identities?id=[id obtenido de un documento existente]
```

## Construído con

* [Java JDK 17 (adoptium)](https://adoptium.net/) - Lenguaje de Programación.
* [MongoDB 7.0](https://www.mongodb.com/try/download/community) - Base de Datos
* [Docker Desktop v4.27](https://www.docker.com/products/docker-desktop/) - Containerization Software.
* [Spring Tool Suite v4.19](https://spring.io/tools) - IDE de programación.
* [Visual Studio Code v1.86](https://code.visualstudio.com) - IDE de programación.

## Versionado

Usamos [SemVer](https://semver.org/lang/es/) para versionar.

## Seguimiento de Cambios

Usamos [Mantenga un changelog](https://keepachangelog.com/en/1.0.0/)

## Conventional Commits

Usamos [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/#specification)

## Autores

- **Alvaro De León** - *Developer*

## Licencia

Este proyecto esta bajo la licencia MIT License - ver [Licencia MIT](https://es.wikipedia.org/wiki/Licencia_MIT) para más detalles.

## Agradecimientos

- **Jerson de León** - *Jefe de Proyectos – Desarrollo / FlujosIT*, por la oportunidad.
