# Movie Service

## Servidor para listar filmes do imdb8

Desenho técnico: https://app.lucidchart.com/documents/view/4698e7fc-8786-4646-b1c8-6d46f4f4cd70

## Requisitos

1. Java - 1.8.x

2. Lombok plugin - 1.x.x

3. Git

## Configuração 

**1. Clone a aplicação**

```bash
git clone https://github.com/douggass/movie-service-server.git
```

**2. Compilando e rodando a aplicação

```bash
cd movie-service-server
gradlew build
java build/libs/movie-service.jar
```

Alternativa, você pode rodar a aplicação usando Gradle

```bash
gradlew bootRun
```

O servidor irá iniciar no endereço <http://localhost:8080>.
Por padrão a porta será 8080, caso seja necessario alterar, basta informa quando iniciar a aplicação.

## Explorando a aplicação

A aplicação atende na porta que foi inicializada e espera um xml em uma coneção TCP.

Exemplo de xml:
```
1. 

```

## Rodando os testes

O projeto contem uma serie de testes que podem ser rodados utilizando o comando `gradlew test`. 
