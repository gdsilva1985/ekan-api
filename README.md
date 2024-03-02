# Ekan-API

## Motivação
Api responsável por gerenciar os beneficiarios do plano de saúde.

## Instalação
Seguir passos abaixo.

### Configuracao da maquina

Antes de baixar o projeto serão nescessários as seguintes ferramentas instalada em seu computador:

* [Git](https://git-scm.com/ "Git").
* [Java 17](https://www.oracle.com/java/technologies/downloads/#java17)
* [Apache Maven](https://maven.apache.org/download.cgi "Apache Maven").


### Instalação do projeto
- para build da api:
  `mvn clean package`
- para executar a api:
`mvn spring-boot:run`

## Documentação: Swagger
http://localhost:8080/swagger-ui/index.html#/


## Documentação: Passo a passo
1) Autenticar e obter token usando as seguintes credenciais: {
   "login": "usuario",
   "senha": "123456"
   };
2) Clicar em "Authorize" e informar o token obtido no campo "bearer-key";
3) Realizar a consulta desejada usando as informações do campo "Example Value" de cada operação;

## Dúvidas?
- Gilson Silva (gdsilva1985@gmail.com)