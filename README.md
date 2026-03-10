# 🚀 ForoHub API

Proyecto desarrollado como parte del programa de especialización en Java de Alura. Es una API REST diseñada para la gestión de tópicos en un foro, permitiendo a los usuarios crear, leer, actualizar y eliminar temas de discusión.

## 🛠 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **MySQL**
- **Lombok**
- **Flyway Migration**
- **Spring Security (JWT)**

## 📦 Estructura del Proyecto



## 🚀 Endpoints Principales

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/usuarios` | Registrar un nuevo usuario |
| `POST` | `/login` | Autenticación y obtención de JWT |
| `POST` | `/topicos` | Crear un nuevo tópico |
| `GET` | `/topicos` | Listar todos los tópicos |
| `PUT` | `/topicos/{id}` | Actualizar un tópico existente |
| `DELETE` | `/topicos/{id}` | Eliminar un tópico |

## 🛠 Instalación y Configuración

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/tu-usuario/foro-hub.git](https://github.com/tu-usuario/foro-hub.git)
