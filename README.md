
![Badge](https://img.shields.io/badge/WIT-JAVA%20CALCULATOR-blue?style=for-the-badge&logo=appveyor)

<h1 align="center">Java Calculator</h1> 

<p align="center">Calculadora desenvolvida em Java com Microserviços e RabbitMQ </p>
<p align="center"> Desafio Técnico Wit Software </p>

* [Sobre](#Sobre)
* [Features](#features)
* [Como usar](#como-usar)
    * [Pre Requisitos](#pre-requisitos)
    * [Como Iniciar](#rodando-o-back-end-servidor)
* [Tecnologias](#tecnologias)

### Sobre

<p>Projeto desenvolvido seguindo os requerimentos enviados. A calculadora conta com as funções básicas de soma, subtração, 
multiplicação e divisão.
<p>A entrada é de apenas dois operandos, ambos com suporte para *arbitraryprecision signed decimal numbers*



### Features

- [x] Cálculo das quatro operações básicas
- [x] Api Rest para as operações
- [x] Envio das mensagens para RabbitMQ
- [x] Recebimento das mensagens pelo consumer
- [x] Log de todas as operações com ID único

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com), [Java](https://www.java.com/pt-BR/), [Maven](https://maven.apache.org/download.cgi) e [Docker](https://www.docker.com)


### 🎲 Rodando o Back End (servidor)

```bash
# Clone este repositório
$ git clone <https://github.com/monialodo/Wit-Java-Calculator.git>

# Abra a sua IDE e instale as dependências com o comando mvn clean install

# No terminal execute docker-compose up -d

# O RabbitMQ está ativo e pode ser acessado com login e senha 'guest' no endereço:
$ <http://localhost:15672/>

# Com o docker funcional rode a aplicação:
  
  # Pelo terminal use o comando mvn -T 2 -pl apirest,calculator clean spring-boot:run

  # Pela IDE shift + F10 em ambos os arquivos - ApirestApplication e CalculatorApplication

# O servidor Apirest se iniciará na porta:8080

# O servidor Calculator se iniciará na porta:8081

# O Swagger pode ser acessado em: 

$ <http://localhost:8080/swagger-ui.html>

# Todas as operações enviadas pelo Swagger serão recebidas pelo RabbitMQ e o retorno será mostrado no console da Apirest 

```

### 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [Java](https://www.java.com/pt-BR/)
- [Maven](https://maven.apache.org/download.cgi)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Docker](https://www.docker.com)
- [RabbitMQ](/www.rabbitmq.com)
- [AMQP](https://www.amqp.org/)
- [Logback Access](https://github.com/akkinoc/logback-access-spring-boot-starter)
- [OpenApi - Swagger](https://swagger.io/)
