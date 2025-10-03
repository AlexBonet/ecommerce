# 🛒 E-commerce Microservice

Este proyecto es un **microservicio backend** desarrollado en **Java + Spring Boot** para gestionar un sistema de
e-commerce minimalista.
Se centra en la administración de **categorías** y **productos con stock**, aplicando principios de **Clean Code** y *
*SOLID**.

---

## 🚀 Características principales

* 📂 **Gestión de Categorías**

    * ➕ Crear
    * 📋 Listar
    * ✏️ Actualizar
    * ❌ Eliminar

* 📦 **Gestión de Productos**

    * 🛠️ CRUD completo de productos asociados a categorías
    * 📉📈 Control de stock

* 💾 **Base de datos persistente en H2 (modo fichero)**

* 📖 **Documentación automática de la API con Swagger** (`/swagger-ui.html`)

* 🏗️ **Arquitectura limpia y desacoplada**

    * 🌐 Controladores (REST Controllers)
    * ⚙️ Servicios (lógica de negocio)
    * 🗄️ Repositorios (persistencia con Spring Data JPA)

* 🧪 **Tests incluidos**

    * 🧩 Unitarios con Mockito
    * 🔗 Integración con `MockMvc`

---

### 🛠️ Tecnologías

![Java 17](https://img.shields.io/badge/Java-17-007396?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?logo=springboot&logoColor=white)
![Spring Web](https://img.shields.io/badge/Spring%20Web-%20-6DB33F?logo=spring&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-%20-6DB33F?logo=spring&logoColor=white)
![Validation](https://img.shields.io/badge/Validation-%20-6DB33F?logo=spring&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2%20Database-%20-0072C6)
![OpenAPI / Swagger](https://img.shields.io/badge/OpenAPI%20%2F%20Swagger-%20-85EA2D?logo=swagger&logoColor=black)
![Maven](https://img.shields.io/badge/Maven-%20-C71A36?logo=apachemaven&logoColor=white)
![JUnit 5](https://img.shields.io/badge/JUnit-5-25A162?logo=junit5&logoColor=white)
![Mockito](https://img.shields.io/badge/Mockito-%20-69D7E2)

<p align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original.svg" height="32" alt="Java" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original.svg" height="32" alt="Spring" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/maven/maven-original.svg" height="32" alt="Maven" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/junit/junit-original.svg" height="32" alt="JUnit" />
  <img src="https://cdn.simpleicons.org/swagger/85EA2D" height="32" alt="Swagger/OpenAPI" />
  <img src="https://cdn.simpleicons.org/h2database/0072C6" height="32" alt="H2" />
</p>

---

## 📂 Estructura del proyecto

```
ecommerce/
 ├── src/main/java/es/spring/ecommerce
 │    ├── controller    → Controladores REST
 │    ├── dto           → Data Transfer Objects
 │    ├── entity        → Entidades JPA
 │    ├── mapper        → Mapeadores DTO ↔ Entidad
 │    ├── repository    → Interfaces JPA
 │    └── service       → Servicios (lógica de negocio)
 ├── src/test/java/es/spring/ecommerce
 │    ├── controller    → Tests de controladores
 │    ├── mapper        → Tests de mapeadores
 │    └── service       → Tests de servicios
 └── application.yml / application.properties
```

---

## ⚙️ Configuración y ejecución

### 1. Requisitos previos

* Java 17+
* Maven 3.9+
* IDE recomendado: **IntelliJ IDEA**

### 2. Clonar repositorio

```bash
git clone https://github.com/AlexBonet/ecommerce
```

### 3. Acceso a la aplicación

* **API base**: `http://localhost:8080/`
* **Swagger UI**: `http://localhost:8080/swagger-ui.html`

---

## 📖 Endpoints principales

### Categorías

* `GET /categories` → Listar todas
* `POST /categories` → Crear nueva
* `GET /categories/{id}` → Obtener por ID
* `PUT /categories/{id}` → Actualizar
* `DELETE /categories/{id}` → Eliminar

### Productos

* `GET /products` → Listar todos
* `POST /products` → Crear nuevo
* `GET /products/{id}` → Obtener por ID
* `PUT /products/{id}` → Actualizar
* `DELETE /products/{id}` → Eliminar

---

## 📌 Próximas mejoras

* Manejo de usuarios y autenticación.
* Validaciones más avanzadas en productos (precio, stock).
* Paginación y búsqueda en endpoints.
* Dockerización para despliegue sencillo.

---

## 👨‍💻 Autor

Proyecto desarrollado por **Alex Bonet** como práctica de **Java Spring Boot + Clean Code & SOLID**.

