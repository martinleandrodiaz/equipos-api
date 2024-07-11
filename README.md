# Equipos API

## Resumen
Equipos API permite gestionar un CRUD de equipos de fútbol.

## Tecnologías
- Java 17
- Spring Boot 3.2.7

## API Doc
Para el uso de la API se utiliza [Swagger](https://swagger.io/tools/swagger-ui/), que también funciona como cliente.

Accede a Swagger: [http://localhost:[port_number]/swagger-ui/index.html](http://localhost:[port_number]/swagger-ui/index.html)

### Usuario para pruebas
- username: test
- password: 12345

## Pasos de ejecución

### Construcción del proyecto
- `mvn clean install`

### Ejecución de la aplicación
- `mvn spring-boot:run`

## Pasos para dockerización

### Construcción de la imagen
- `docker build -t equiposapi:latest .`

### Creación de contenedor y ejecución de la imagen
- `docker run -p 8080:8080 equiposapi:latest`