
# Parking API

Este projeto trata-se de uma API REST para controle de estacionamento de um condomínio/prédio.



![preview](https://i.imgur.com/a0m0GKD.gif?noredirect)
## Stack utilizada

* Java 17
* Spring Framework
* PostgreSQL
* Swagger
* Junit 5
* Maven
* Railway (para versionar o banco)
* Postman
* IntelliJ


## Documentação

A documentação da API foi gerada usando SpringDocs Swagger.

Pode ser acessada pelo endpoint:
```
api/parking/docs
```

## Funcionalidades

- CRUD das vagas de estacionamento
- CRUD de usuários
- Consultas paginadas e ordenadas
- Autenticação JWT


## Referência

 - Eu trouxe a ideia do vídeo da Michelli Brito no [Youtube](https://www.youtube.com/@MichelliBrito)

## Melhorias

* Exceções: Uso de Global Exception Handler para capturar as exceções lançadas pela API, além de exceções personalizadas.

* Autenticação: Inclusão da biblioteca e autenticação JWT.

* Uso da lib MapStruct para conversão entre DTO's E Model's

* Spring profiles: Separação dos ambientes de DEV e PROD.

## Feedback

Se você tiver algum feedback, por favor não deixe de dá-lo. 

Me contate através do [github](https://github.com/caiofrz) 
ou [LinkedIn](https://www.linkedin.com/in/caio-ferraz-almeida/) 
