# Proyecto de Importación de Productos desde Excel

Este proyecto es una aplicación Spring Boot que permite importar productos desde un archivo Excel. Cada fila del archivo representa un producto, que se guarda en una base de datos utilizando JPA.

## Características

- Importación de productos desde un archivo Excel (.xlsx).
- Manejo de diferentes tipos de datos en el archivo (String, Double, Integer, Boolean).
- Respuestas adecuadas ante errores durante la importación.

## Dependencias

Asegúrate de incluir las siguientes dependencias en tu archivo `pom.xml`:

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Base de datos H2 (o cualquier otra que desees usar) -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Apache POI para manipulación de archivos Excel -->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.2.3</version> <!-- Asegúrate de usar la última versión -->
    </dependency>

    <!-- Otras dependencias necesarias -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
</dependencies>
