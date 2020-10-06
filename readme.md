# Movie Service

## Servidor para listar filmes do imdb8

Desenho técnico: [lucidchart movie-service](https://app.lucidchart.com/documents/view/4698e7fc-8786-4646-b1c8-6d46f4f4cd70)

## Requisitos

1. Java - 1.8.x

## Requisitos para desenvolvimento

1. Lombok plugin - 1.x.x


## Configuração 

**1. Clone o código fonte da aplicação**

```bash
git clone https://github.com/douggass/movie-service-server.git
```

**2. Compilando e rodando a aplicação**

```bash
gradlew build run
```

O servidor irá iniciar no endereço <http://localhost:8080>.
Por padrão a porta será 8080, caso seja necessario alterar, basta alterar as configurações da aplicação.

## Explorando a aplicação

A aplicação atende na porta 8080 e espera um xml em uma conexão TCP.

Exemplo de xml:
```
1. Request=<Input><length>10</length><query>Game of Th</query></Input>

2. Response=<Output><length>275</length><payload>Game of Thrones
                                                  Unaired Game of Thrones Prequel Pilot
                                                  Game of Thrones: The Last Watch
                                                  Game of Thrones: A Telltale Games Series
                                                  Game of Thrones Conquest &amp; Rebellion: An Animated History of the Seven Kingdoms
                                                  Hra o jablko
                                                  Sherlock Holmes: A Game of Shadows
                                                  The Name of the Rose</payload></Output>

```

## Rodando os testes

O projeto contém uma série de testes que podem ser rodados utilizando o comando `gradlew test`. 

Os resultados podem ser visto acesso o html: build/reports/tests/test/index.html
