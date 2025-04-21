# Notas

## Que es un microservicio?

Conjunto de componentes pequenos y autonomos que colaboran entre si. Cada uno cumple un rol con su propio dominio, su logica de negocio, se pueden conectar a bases de datos distintas; Ejemplo: 

[Microservicio-ejemplo](./images/microservicio-ejemplo.png)

[Caracteristicas](./images/caracteristicas.png)

## Ventajas y desafios

* Nueva tecnologia y adopcion de procesos.
* Reduccion de costo.
* Ciclos de liberacion mas rapidos (despliegues).
* Equipos de desarrollo mas pequenos.

## Explicacion de combinacion de `pom.xml`

Estos archivos son parte de un proyecto Maven, una herramienta de construcción y gestión de proyectos en Java. En este caso, el proyecto parece estar organizado como un **proyecto multimódulo** para desarrollar microservicios con Spring Boot y Kubernetes.

---

### **1. Primer archivo `pom.xml` (msvc-usuarios)**

Este archivo pertenece al **módulo `msvc-usuarios`**, que es uno de los microservicios del proyecto. Analicemos sus partes clave:

#### **a) `<parent>`**
```xml
<parent>
    <groupId>org.asebastian36.springcloud.msvc</groupId>
    <artifactId>curso-kubernetes</artifactId>
    <version>1.0-SNAPSHOT</version>
</parent>
```
- **Qué hace**: Define el proyecto padre (`curso-kubernetes`) del cual hereda configuraciones.
    - El proyecto padre proporciona dependencias comunes, plugins y propiedades que pueden ser reutilizadas por todos los módulos.
    - Aquí, el módulo `msvc-usuarios` hereda del proyecto padre `curso-kubernetes`.

#### **b) `<groupId>`, `<artifactId>`, `<version>`**
```xml
<groupId>org.asebastian36.springcloud.msvc.usuarios</groupId>
<artifactId>msvc-usuarios</artifactId>
<version>0.0.1-SNAPSHOT</version>
```
- **Qué hacen**:
    - **`groupId`**: Identifica el grupo o organización del proyecto (e.g., `org.asebastian36.springcloud.msvc.usuarios`).
    - **`artifactId`**: Es el nombre del módulo (`msvc-usuarios`).
    - **`version`**: Es la versión del módulo (`0.0.1-SNAPSHOT`).

#### **c) `<properties>`**
```xml
<properties>
    <java.version>17</java.version>
    <spring-cloud.version>2024.0.1</spring-cloud.version>
</properties>
```
- **Qué hacen**:
    - **`java.version`**: Especifica la versión de Java utilizada en el proyecto (`Java 17`).
    - **`spring-cloud.version`**: Define la versión de Spring Cloud que se utilizará (`2024.0.1`).

#### **d) `<dependencies>`**
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <!-- Otras dependencias -->
</dependencies>
```
- **Qué hacen**:
    - Declara las dependencias necesarias para este microservicio.
    - Ejemplos:
        - `spring-boot-starter-data-jpa`: Para trabajar con bases de datos usando JPA.
        - `spring-boot-starter-web`: Para crear aplicaciones web RESTful.
        - `mysql-connector-j`: Para conectarse a una base de datos MySQL.
        - `spring-cloud-starter-openfeign`: Para realizar llamadas HTTP a otros servicios.

#### **e) `<dependencyManagement>`**
```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```
- **Qué hace**:
    - Importa las versiones de las dependencias de Spring Cloud desde el BOM (Bill of Materials) `spring-cloud-dependencies`.
    - Esto asegura que todas las dependencias de Spring Cloud sean compatibles entre sí.

#### **f) `<build>`**
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```
- **Qué hace**:
    - Configura el plugin de Maven para Spring Boot.
    - Este plugin permite empaquetar el proyecto como un ejecutable JAR y facilita la ejecución del microservicio.

---

### **2. Segundo archivo `pom.xml` (Proyecto padre: curso-kubernetes)**

Este archivo define el **proyecto padre** (`curso-kubernetes`), que actúa como un contenedor para todos los módulos del proyecto. Analicemos sus partes clave:

#### **a) `<parent>`**
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.4.4</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```
- **Qué hace**:
    - Hereda configuraciones del proyecto padre oficial de Spring Boot (`spring-boot-starter-parent`).
    - Proporciona configuraciones predeterminadas para compilar, probar y ejecutar proyectos Spring Boot.

#### **b) `<groupId>`, `<artifactId>`, `<version>`**
```xml
<groupId>org.asebastian36.springcloud.msvc</groupId>
<artifactId>curso-kubernetes</artifactId>
<version>1.0-SNAPSHOT</version>
```
- **Qué hacen**:
    - **`groupId`**: Identifica el grupo o organización del proyecto.
    - **`artifactId`**: Es el nombre del proyecto padre (`curso-kubernetes`).
    - **`version`**: Es la versión del proyecto padre (`1.0-SNAPSHOT`).

#### **c) `<packaging>`**
```xml
<packaging>pom</packaging>
```
- **Qué hace**:
    - Indica que este proyecto no genera un artefacto ejecutable (como un JAR o WAR).
    - En su lugar, actúa como un contenedor para gestionar múltiples módulos.

#### **d) `<modules>`**
```xml
<modules>
    <module>msvc-usuarios</module>
</modules>
```
- **Qué hace**:
    - Declara los módulos que forman parte del proyecto.
    - Aquí, el único módulo declarado es `msvc-usuarios`.

#### **e) `<properties>`**
```xml
<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
```
- **Qué hacen**:
    - **`maven.compiler.source` y `maven.compiler.target`**: Especifican la versión de Java para compilar el código (`Java 17`).
    - **`project.build.sourceEncoding`**: Define la codificación de caracteres para los archivos fuente (`UTF-8`).

---

### **3. Relación entre ambos archivos**

#### **a) Proyecto padre (`curso-kubernetes`)**
- Actúa como un **contenedor** para gestionar múltiples módulos.
- Define configuraciones generales que son heredadas por todos los módulos.
- Declara los módulos que forman parte del proyecto (en este caso, solo `msvc-usuarios`).

#### **b) Módulo hijo (`msvc-usuarios`)**
- Es un **microservicio** específico que forma parte del proyecto.
- Hereda configuraciones del proyecto padre (`curso-kubernetes`).
- Define sus propias dependencias y configuraciones específicas.

---

### **4. Flujo general del proyecto**

1. **Compilación del proyecto padre**:
    - Al ejecutar `mvn clean install` en el proyecto padre, Maven compila todos los módulos declarados en `<modules>`.

2. **Compilación del módulo hijo**:
    - El módulo `msvc-usuarios` hereda configuraciones del proyecto padre y utiliza sus propias dependencias y plugins.

3. **Ejecución del microservicio**:
    - Una vez compilado, el módulo `msvc-usuarios` puede ejecutarse como un microservicio independiente.

---

### **5. Resumen**

| Archivo/Carpeta              | Descripción                                                                |
|------------------------------|----------------------------------------------------------------------------|
| **`pom.xml` (padre)**        | Define el proyecto padre que gestiona múltiples módulos.                   |
| **`pom.xml` (hijo)**         | Define un microservicio específico (`msvc-usuarios`) que hereda del padre. |
| **`<parent>`**               | Relaciona el módulo hijo con el proyecto padre.                            |
| **`<modules>`**              | Declara los módulos que forman parte del proyecto padre.                   |
| **`<dependencies>`**         | Declara las dependencias necesarias para cada módulo.                      |
| **`<dependencyManagement>`** | Gestiona versiones de dependencias para garantizar compatibilidad.         |

---

### **6. Conclusión**

Este proyecto está organizado como un **proyecto multimódulo** en Maven. El archivo `pom.xml` del proyecto padre (`curso-kubernetes`) actúa como un contenedor para gestionar múltiples módulos, mientras que el archivo `pom.xml` del módulo hijo (`msvc-usuarios`) define un microservicio específico.

---

## Explicacion del controlador

Este controlador (`UsuarioController`) es parte de un microservicio que maneja operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para entidades `Usuario`. Utiliza Spring Boot y sigue el patrón MVC (Modelo-Vista-Controlador), donde el controlador actúa como intermediario entre las solicitudes HTTP y la lógica de negocio implementada en el servicio.

---

### **1. Anotaciones clave**

#### **`@RestController`**
- **Qué hace**: Marca esta clase como un controlador REST.
    - Combina `@Controller` y `@ResponseBody`, lo que significa que todos los métodos devuelven datos directamente en el cuerpo de la respuesta HTTP (en formato JSON o XML).

---

### **2. Inyección de dependencias**

```java
@Autowired
private UsuarioService service;
```

- **Qué hace**:
    - Inyecta una instancia del servicio `UsuarioService` en el controlador.
    - El servicio contiene la lógica de negocio para interactuar con la entidad `Usuario`.

---

### **3. Métodos del controlador**

El controlador define varios métodos para manejar solicitudes HTTP relacionadas con la entidad `Usuario`. Cada método está asociado a un verbo HTTP específico (`GET`, `POST`, `PUT`, `DELETE`).

---

#### **a) `listar()`**
```java
@GetMapping
public List<Usuario> listar() {
    return service.listar();
}
```

- **Qué hace**:
    - Maneja solicitudes HTTP GET al endpoint `/`.
    - Llama al método `listar()` del servicio para obtener una lista de todos los usuarios.
    - Devuelve la lista de usuarios en el cuerpo de la respuesta HTTP.

- **Ejemplo de respuesta**:
  ```json
  [
    { "id": 1, "nombre": "Angel", "email": "angel@example.com" },
    { "id": 2, "nombre": "Maria", "email": "maria@example.com" }
  ]
  ```

---

#### **b) `detalle(@PathVariable Long id)`**
```java
@GetMapping("/{id}")
public ResponseEntity<?> detalle(@PathVariable Long id) {
    Optional<Usuario> usuarioOptional = service.porId(id);
    if (usuarioOptional.isPresent()) {
        return ResponseEntity.ok(usuarioOptional.get());
    }
    return ResponseEntity.notFound().build();
}
```

- **Qué hace**:
    - Maneja solicitudes HTTP GET al endpoint `/{id}`.
    - Usa el parámetro `id` proporcionado en la URL para buscar un usuario específico.
    - Si el usuario existe, devuelve una respuesta HTTP con estado `200 OK` y el usuario en el cuerpo.
    - Si el usuario no existe, devuelve una respuesta HTTP con estado `404 Not Found`.

- **Ejemplo de respuesta (éxito)**:
  ```json
  { "id": 1, "nombre": "Angel", "email": "angel@example.com" }
  ```

- **Ejemplo de respuesta (error)**:
  ```
  HTTP 404 Not Found
  ```

---

#### **c) `crear(@RequestBody Usuario usuario)`**
```java
@PostMapping
public ResponseEntity<?> crear(@RequestBody Usuario usuario) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));
}
```

- **Qué hace**:
    - Maneja solicitudes HTTP POST al endpoint `/`.
    - Recibe un objeto `Usuario` en el cuerpo de la solicitud (en formato JSON).
    - Llama al método `guardar()` del servicio para almacenar el nuevo usuario.
    - Devuelve una respuesta HTTP con estado `201 Created` y el usuario guardado en el cuerpo.

- **Ejemplo de solicitud**:
  ```json
  { "nombre": "Juan", "email": "juan@example.com", "password": "123456" }
  ```

- **Ejemplo de respuesta**:
  ```json
  { "id": 3, "nombre": "Juan", "email": "juan@example.com" }
  ```

---

#### **d) `editar(@RequestBody Usuario usuario, @PathVariable Long id)`**
```java
@PutMapping("/{id}")
public ResponseEntity<?> editar(@RequestBody Usuario usuario, @PathVariable Long id) {
    Optional<Usuario> o = service.porId(id);
    if (o.isPresent()) {
        Usuario usuarioDb = o.get();
        usuarioDb.setNombre(usuario.getNombre());
        usuarioDb.setEmail(usuario.getEmail());
        usuarioDb.setPassword(usuario.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuarioDb));
    }
    return ResponseEntity.notFound().build();
}
```

- **Qué hace**:
    - Maneja solicitudes HTTP PUT al endpoint `/{id}`.
    - Usa el parámetro `id` proporcionado en la URL para buscar un usuario existente.
    - Si el usuario existe, actualiza sus propiedades (`nombre`, `email`, `password`) con los valores proporcionados en el cuerpo de la solicitud.
    - Guarda el usuario actualizado en la base de datos y devuelve una respuesta HTTP con estado `201 Created` y el usuario actualizado en el cuerpo.
    - Si el usuario no existe, devuelve una respuesta HTTP con estado `404 Not Found`.

- **Ejemplo de solicitud**:
  ```json
  { "nombre": "Angel Updated", "email": "angel.updated@example.com", "password": "newpassword" }
  ```

- **Ejemplo de respuesta (éxito)**:
  ```json
  { "id": 1, "nombre": "Angel Updated", "email": "angel.updated@example.com" }
  ```

- **Ejemplo de respuesta (error)**:
  ```
  HTTP 404 Not Found
  ```

---

#### **e) `eliminar(@PathVariable Long id)`**
```java
@DeleteMapping("/{id}")
public ResponseEntity<?> eliminar(@PathVariable Long id) {
    Optional<Usuario> o = service.porId(id);
    if (o.isPresent()) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
}
```

- **Qué hace**:
    - Maneja solicitudes HTTP DELETE al endpoint `/{id}`.
    - Usa el parámetro `id` proporcionado en la URL para buscar un usuario existente.
    - Si el usuario existe, lo elimina de la base de datos y devuelve una respuesta HTTP con estado `204 No Content`.
    - Si el usuario no existe, devuelve una respuesta HTTP con estado `404 Not Found`.

- **Ejemplo de respuesta (éxito)**:
  ```
  HTTP 204 No Content
  ```

- **Ejemplo de respuesta (error)**:
  ```
  HTTP 404 Not Found
  ```

---

### **4. Flujo general del controlador**

1. **Listar usuarios**:
    - Solicita una lista de todos los usuarios (`GET /`).

2. **Obtener un usuario por ID**:
    - Solicita un usuario específico por su ID (`GET /{id}`).

3. **Crear un usuario**:
    - Envía un nuevo usuario en el cuerpo de la solicitud (`POST /`).

4. **Actualizar un usuario**:
    - Envía los datos actualizados de un usuario existente (`PUT /{id}`).

5. **Eliminar un usuario**:
    - Elimina un usuario existente por su ID (`DELETE /{id}`).

---

### **5. Beneficios de este enfoque**

- **Separación de responsabilidades**:
    - El controlador se enfoca en manejar solicitudes HTTP.
    - La lógica de negocio está encapsulada en el servicio (`UsuarioService`).

- **Manejo de errores**:
    - Devuelve respuestas claras para casos de éxito y error (por ejemplo, `404 Not Found` si un usuario no existe).

- **Respuestas estructuradas**:
    - Las respuestas son consistentes y fáciles de consumir por clientes externos.

---

### **6. Resumen**

Este controlador implementa operaciones CRUD para la entidad `Usuario` utilizando Spring Boot. Cada método maneja un tipo específico de solicitud HTTP (`GET`, `POST`, `PUT`, `DELETE`) y delega la lógica de negocio al servicio `UsuarioService`. Las respuestas son estructuradas y consistentes, lo que facilita la integración con otros sistemas o aplicaciones frontend.

## Explicacion de propiedades del `application.properties` que no conocia

¡Claro! Vamos a analizar qué hacen estas dos propiedades en el archivo `application.properties` de un proyecto Spring Boot. Ambas están relacionadas con la configuración de la base de datos y el logging, específicamente cuando se trabaja con **JPA** (Java Persistence API) y **Hibernate**.

---

### **1. `spring.jpa.generate-ddl=true`**

#### **Qué hace:**
Esta propiedad le indica a Spring Data JPA que genere automáticamente el esquema de la base de datos (tablas, columnas, etc.) basándose en las entidades definidas en tu aplicación.

- **Valores posibles**:
    - `true`: Habilita la generación automática del esquema.
    - `false`: Deshabilita la generación automática del esquema (valor predeterminado).

#### **Cómo funciona:**
Cuando esta propiedad está habilitada (`true`), Spring Boot utiliza Hibernate para generar el DDL (Data Definition Language) necesario para crear las tablas en la base de datos. Esto incluye:
- Crear tablas.
- Definir columnas y sus tipos.
- Configurar claves primarias, índices, relaciones, etc.

#### **Ejemplo práctico:**
Si tienes una entidad como esta:

```java
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;

    // Getters y setters
}
```

Con `spring.jpa.generate-ddl=true`, Hibernate generará automáticamente una tabla como esta en la base de datos:

```sql
CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    email VARCHAR(255)
);
```

#### **Cuándo usarlo:**
- **En desarrollo**: Es útil durante la fase inicial de desarrollo para evitar escribir manualmente el esquema de la base de datos.
- **En producción**: Generalmente no se recomienda usar esta opción en producción porque puede sobrescribir o modificar accidentalmente el esquema existente. En su lugar, se prefiere usar herramientas como **Flyway** o **Liquibase** para gestionar migraciones de bases de datos.

---

### **2. `logging.level.org.hibernate.SQL=debug`**

#### **Qué hace:**
Esta propiedad configura el nivel de logging para las consultas SQL generadas por Hibernate.

- **Valores posibles**:
    - `debug`: Muestra las consultas SQL generadas por Hibernate en los logs.
    - `info`, `warn`, `error`: Niveles más altos que no muestran las consultas SQL.

#### **Cómo funciona:**
Cuando esta propiedad está configurada en `debug`, Hibernate registrará todas las consultas SQL que ejecuta en los logs de la aplicación. Esto incluye:
- Consultas de creación de tablas (si `spring.jpa.generate-ddl=true`).
- Consultas de inserción, actualización, eliminación y selección de datos.

#### **Ejemplo de logs:**
Si Hibernate ejecuta una consulta como esta:

```sql
SELECT * FROM usuario WHERE id = 1;
```

Verás algo como esto en los logs:

```
DEBUG org.hibernate.SQL - select usuario0_.id as id1_0_, usuario0_.nombre as nombre2_0_, usuario0_.email as email3_0_ from usuario usuario0_ where usuario0_.id=1
```

#### **Cuándo usarlo:**
- **En desarrollo**: Es útil para depurar y entender qué consultas SQL está generando Hibernate.
- **En producción**: No se recomienda usar `debug` en producción porque puede generar muchos logs y afectar el rendimiento. En su lugar, se puede usar un nivel más alto como `warn` o `error`.

---

### **3. Resumen**

| Propiedad                               | Descripción                                                                    |
|-----------------------------------------|--------------------------------------------------------------------------------|
| `spring.jpa.generate-ddl=true`          | Genera automáticamente el esquema de la base de datos basado en las entidades. |
| `logging.level.org.hibernate.SQL=debug` | Registra las consultas SQL generadas por Hibernate en los logs.                |

---

### **4. Relación entre ambas propiedades**

Ambas propiedades trabajan juntas para facilitar el desarrollo y la depuración:

1. **Generación del esquema**:
    - Con `spring.jpa.generate-ddl=true`, Hibernate crea automáticamente las tablas necesarias en la base de datos.
    - Esto es útil para probar rápidamente tus entidades sin preocuparte por escribir SQL manualmente.

2. **Logging de consultas SQL**:
    - Con `logging.level.org.hibernate.SQL=debug`, puedes ver exactamente qué consultas SQL está ejecutando Hibernate.
    - Esto es útil para verificar que las consultas sean correctas y optimizadas.

---

### **5. Consideraciones adicionales**

- **Alternativas a `spring.jpa.generate-ddl=true`**:
    - Puedes usar `spring.jpa.hibernate.ddl-auto` para tener más control sobre cómo Hibernate maneja el esquema. Por ejemplo:
        - `create`: Crea el esquema cada vez que se inicia la aplicación (sobrescribe datos existentes).
        - `update`: Actualiza el esquema sin eliminar datos existentes.
        - `validate`: Valida que el esquema coincida con las entidades (no modifica la base de datos).
        - `none`: Deshabilita la generación automática del esquema.

- **Optimización del logging**:
    - Además de `org.hibernate.SQL`, también puedes habilitar el logging de parámetros de las consultas SQL:
      ```properties
      logging.level.org.hibernate.type.descriptor.sql.BasicBinder=trace
      ```
      Esto muestra los valores de los parámetros en los logs.

## Explicacion en las configuraciones del `.properties` de ambos modulos

Para entender por qué algunas propiedades pueden ser omitidas y qué hace la propiedad `spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true`.

---

### **1. ¿Por qué funciona sin la propiedad `spring.jpa.database-platform`?**

#### **a) Propósito de `spring.jpa.database-platform`**
Esta propiedad se utiliza para especificar el **dialecto de Hibernate** que debe usarse para interactuar con la base de datos. El dialecto es un conjunto de reglas y configuraciones específicas para cada tipo de base de datos (por ejemplo, MySQL, PostgreSQL, Oracle, etc.). Hibernate necesita saber cómo traducir las operaciones JPA en consultas SQL específicas para la base de datos.

Por ejemplo:
- Para MySQL 8: `org.hibernate.dialect.MySQL8Dialect`
- Para PostgreSQL: `org.hibernate.dialect.PostgreSQLDialect`

#### **b) Por qué puede funcionar sin esta propiedad**
Spring Boot tiene una característica llamada **"autoconfiguración"**, que intenta detectar automáticamente la base de datos y configurar el dialecto adecuado basándose en la URL de conexión (`spring.datasource.url`) y el controlador JDBC (`spring.datasource.driver-class-name`).

- Si omites `spring.jpa.database-platform`, Spring Boot intentará inferir el dialecto correcto según la base de datos conectada.
    - Por ejemplo:
        - Si la URL comienza con `jdbc:mysql://`, Spring Boot asumirá que estás usando MySQL y aplicará automáticamente el dialecto `MySQL8Dialect`.
        - Si la URL comienza con `jdbc:postgresql://`, Spring Boot asumirá que estás usando PostgreSQL y aplicará automáticamente el dialecto `PostgreSQLDialect`.

Esto explica por qué tu proyecto puede funcionar sin especificar explícitamente `spring.jpa.database-platform`. Sin embargo, **es una buena práctica incluir esta propiedad** para evitar problemas si Spring Boot no infiere correctamente el dialecto o si necesitas un comportamiento específico del dialecto.

---

### **2. Explicación de `spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true`**

#### **a) Propósito de la propiedad**
Esta propiedad está relacionada con la forma en que Hibernate maneja los **LOBs (Large Objects)**, como campos de tipo `BLOB` (binario grande) o `CLOB` (cadena de texto grande). Estos tipos de datos se utilizan para almacenar grandes volúmenes de información, como imágenes, archivos PDF o textos extensos.

La propiedad `hibernate.jdbc.lob.non_contextual_creation` controla cómo Hibernate crea estos objetos LOB en la base de datos.

- **Valor `true`**:
    - Indica que Hibernate debe usar un enfoque **no contextual** para crear los LOBs.
    - Esto significa que Hibernate creará los LOBs directamente sin depender del contexto de la transacción actual.
    - Es útil cuando trabajas con ciertos controladores JDBC que tienen problemas con la creación contextual de LOBs.

- **Valor `false` o ausencia de la propiedad**:
    - Hibernate intentará crear los LOBs dentro del contexto de la transacción actual.
    - Esto puede causar problemas con algunos controladores JDBC o bases de datos que no admiten bien la creación contextual de LOBs.

#### **b) ¿Por qué usar esta propiedad?**
En algunos casos, especialmente con PostgreSQL, se han reportado problemas relacionados con la creación de LOBs en ciertas versiones del controlador JDBC. Configurar `spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true` resuelve estos problemas asegurando que Hibernate use un enfoque más compatible.

---

### **3. Resumen de las respuestas**

1. **¿Por qué funciona sin `spring.jpa.database-platform`?**
    - Spring Boot usa autoconfiguración para detectar el dialecto de Hibernate basándose en la URL de conexión (`spring.datasource.url`) y el controlador JDBC (`spring.datasource.driver-class-name`).
    - Sin embargo, es recomendable especificar explícitamente el dialecto para evitar problemas potenciales.

2. **¿Qué hace `spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true`?**
    - Controla cómo Hibernate maneja la creación de objetos LOB (como `BLOB` y `CLOB`).
    - Cuando está configurada en `true`, Hibernate usa un enfoque no contextual para crear LOBs, lo que puede resolver problemas de compatibilidad con ciertos controladores JDBC o bases de datos.

---

### **4. Mejores prácticas**

- **Incluir `spring.jpa.database-platform`**:
    - Aunque Spring Boot puede inferir el dialecto, es mejor especificarlo explícitamente para garantizar que Hibernate use el dialecto correcto y evitar sorpresas.

- **Configurar `hibernate.jdbc.lob.non_contextual_creation`**:
    - Si trabajas con PostgreSQL o bases de datos que tienen problemas conocidos con LOBs, es recomendable incluir esta propiedad y configurarla en `true`.

- **Usar diferentes puertos para microservicios**:
    - Como has hecho (`server.port=8001` y `server.port=8002`), asegúrate de que cada microservicio tenga un puerto único para evitar conflictos.

---

## Relacionando los servicios

[relacionando los servicios](./images/relacionando-servicios.png

### Como relacionarlos

Gracias a una tabla intermedia en el microservicio `Curso` que se llame `CursoUsuario`.

[como relacionar los servicios](./images/como-relacionar-servicios.png)

## Docker

### Introduccion

Docker es una plataforma de contenedores, herramienta que crea y administra contenedores.

> Contenedor: Similar a una caja de herramientas aislada, porcion de un s.o para ejecutar aplicaciones en un host.

Es un empaquetado de codigo y dependencias para ejecutar ese codigo, la aplicacion.

Un mismo contenedor que se ejecuta siempre debe reproducir exactamente el mismo comportamiento de la aplicacion, sin importar donde o quien lo ejecuta.

> Imagen: Plantilla para crear contenedores.

### Ventajas de los contenedores

* Permite tener diferentes ambientes de desarrollo y produccion, que funcione en ambos.
* Permite tener ambientes de desarrollo y versiones en un equipo de desarrollo.

`sudo docker images`: Listar imagenes.
`sudo docker ps`: Listas contenedores.
