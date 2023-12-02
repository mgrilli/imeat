# IMeat

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

Este projeto é uma API construída em Java 17, Spring Boot, MongoDB como banco de dados e Maven para gerenciamento de dependências e build.

Feito para o Startup One da FIAP, a IMeat API permite manipular registros de produtos a base de proteína sintética. 

## Índice

- [Instalação](#instalação)
- [Uso](#uso)
- [Autenticação](#autenticação)
- [Endpoints da API](#endpoints-da-api)
- [Banco de dados](#banco-de-dados)

## Instalação

1. Clone o repositório:
```bash
git clone https://github.com/mgrilli/imeat.git
```

2. Instale as dependências com Maven


## Uso

1. Inicie a aplicação com Maven
```bash
mvn spring-boot:run
```
2. A API estará disponível em http://localhost:8080


## Autenticação

A IMeat API usa o Spring Security para o controle de autenticação, as seguintes roles estão disponíveis:

```
USER = Usuário padrão para usuários comuns da plataforma
ADMIN = Usuário administrador que pode cadastrar e excluir produtos
```

Para acessar os endpoints protegidos, providenciar as credenciais de autenticação do ADMIN no header requisição

## Endpoints da API

A API possui os seguintes endpoints:

##### Cadastro de Usuário

```
POST /register - Cadastra usuário 
```

```json
{
   "login": "Usuario",
   "email": "usuario@teste.com.br",
   "password": "senha"
}
```

##### Autenticação de Usuário

```markdown
POST /login - Autentica usuário
```

```json
{
   "login": "Usuario",
   "password": "senha"
}
```

##### Catálogo de produtos

```markdown
GET /products - Recupera produtos cadastrados
```

```json
[
   {
     "id": "64ffa630f3823a3a6952ccde",
     "prodName": "Clean Bacon",
     "description": "A tasteful bacon without guilt",
     "amount": 150,
     "price": 8.99,
     "url": "http://pathDaImagem.com"
   },
   {
     "id": "64ffb2be393a4b4c887060ea",
     "prodName": "Clean Nuggets",
     "description": "Lorem Ipsum",
     "amount": 25,
     "price": 12.44,
     "url": "http://pathDaImagem.com"
   },
   {
     "id": "64ffb343393a4b4c887060eb",
     "prodName": "Veggie Sausages",
     "description": "Tastes like meat!",
     "amount": 150,
     "price": 5.78,
     "url": "http://pathDaImagem.com"
   }
]
```

##### Cadastrar Produto

```markdown
POST /products - Cadastra produto (Apenas ADMIN)
```

```json
{
   "prodName": "Clean Chicken Burger",
   "description": "Clean Chicken Burger são Chickens de carne sintética feitas de plantas e proteínas de alta qualidade. Elas são uma ótima opção para quem quer comer carne sem os impactos ambientais e de saúde associados à carne animal",
   "amount": 150,
   "price": 199.99,
   "url": "https://images.unsplash.com/photo-1615297928064-24977384d0da?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1412&q=80"
}
```

##### Consultar produto individualmente

```markdown
GET /products/{id-product} - Recupera produto com id informado
```

```json
{
   "id": "64ffb343393a4b4c887060eb",
   "prodName": "Veggie Sausages",
   "description": "Tastes like meat!",
   "amount": 150,
   "price": 5.78,
   "url": "http://pathDaImagem.com"
}
```

##### Excluir produto

```markdown
DELETE /products/{id-product} - Exclui produto com id informado (Apenas ADMIN)
```


## Banco de Dados

Este projeto utiliza MongoDB como banco de dados