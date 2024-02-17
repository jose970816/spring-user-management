
## Spring-Boot-Rest-Application

API REST para la gestion de usuarios y sus respectivas publicaciones.

El componente esta desarrollando usando Spring Boot, Spring JPA, Spring Validation, Lombok, Spring Security , JWT y H2 como base de datos en memoria.

Adjunto enlace a mi drive para descargar la coleccion de Postman:

[https://drive.google.com/file/d/1HoOXycg4iGWR1wwB9Y4bb6Czu0WxZEil/view?usp=sharing](https://drive.google.com/file/d/1nfyR7u0OFlNTyhRBsZn-IRoZFBw4mZNM/view?usp=sharing)

## Comandos para ejecutar el DockerFile:
Dentro del repository se encuentra el archivo DockerFile para desplegar el componente en docker desktop el puerto de salida para consumir los servicios es 8585, segun se detalla en los comandos de la tabla siguiente:

| Comando             | Descripcion                                                                |
| ----------------- | ------------------------------------------------------------------ |
| docker build -t my-app:v1 . | Comando para crear la imagen |
| docker run -d --name my-app-container -p 8585:8080 my-app:v1 | Comando para crear el contenedor|

