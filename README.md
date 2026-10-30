# Configuração da IDE para Weka e Java

Configuração das Depêndencias do `pom.xml`

## Configuração do Java

Define a versão do java utilizada no projeto, garantindo compatibilidade com **java 21**

```xml
    <properties>
        <java.version>21</java.version>
    </properties>
```

## Dependência do Weka

Inclui o **Weka** (`weka-stable` na versão **3.8.7**), permitindo que o projeto utilize **Machine Learning**.

```xml
    <dependency>
        <groupId>nz.ac.waikato.cms.weka</groupId>
        <artifactId>weka-stable</artifactId>
        <version>3.8.7</version>
    </dependency>
```

# Configuração do Spring boot


### Depêndencias no `pom.xml`

```xml
    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Hibernate Community Dialects -->
    <dependency>
        <groupId>org.hibernate.orm</groupId>
        <artifactId>hibernate-community-dialects</artifactId>
    </dependency>

    <!-- Validação de Campos -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- Ferramentas de Desenvolvimento -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
    </dependency>

    <!-- Spring Boot Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
```
