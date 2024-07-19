# Sistema de Contas a Pagar

Este projeto é uma API REST para um sistema simples de contas a pagar. Ele permite realizar operações CRUD em contas a pagar, alterar a situação delas quando um pagamento é efetuado, obter informações sobre as contas cadastradas no banco de dados e importar um lote de contas a partir de um arquivo CSV.

## Requisitos

- Java 17 ou superior
- Spring Boot
- PostgreSQL
- Docker
- Docker Compose
- Flyway
- JPA
- Autenticação
- Swagger UI

## Estrutura do Projeto

O projeto está organizado de acordo com os princípios do Domain Driven Design (DDD).

### Entidades

- `Conta`: Representa uma conta a pagar.
- `User`: Representa um usuário do sistema.

### APIs

#### Contas

- **POST /api/contas**: Cadastrar conta.
- **PUT /api/contas/{id}**: Atualizar conta.
- **PATCH /api/contas/{id}/situacao**: Alterar a situação da conta.
- **GET /api/contas**: Obter a lista de contas a pagar, com filtro de data de vencimento e descrição.
- **GET /api/contas/{id}**: Obter conta filtrando o id.
- **GET /api/contas/valor-total**: Obter valor total pago por período.

#### Usuários

- **POST /api/users**: Cadastrar usuário.

## Autenticação

A API utiliza autenticação básica HTTP para proteger os endpoints. Para acessar os endpoints protegidos, você deve fornecer um nome de usuário e senha válidos.

## Configuração

### Banco de Dados

O projeto utiliza PostgreSQL como banco de dados. A configuração do banco de dados pode ser encontrada no arquivo `application.yaml`.

### Flyway

O Flyway é utilizado para gerenciar as migrações de banco de dados. As migrações são executadas automaticamente ao iniciar a aplicação.

### Docker

A aplicação e os serviços necessários são orquestrados utilizando Docker Compose. O arquivo `docker-compose.yml` define a configuração dos containers.

### Swagger UI

O Swagger UI está disponível para explorar e testar a API. Para acessar o Swagger UI, 
inicie a aplicação e acesse a URL: http://localhost:8080/swagger-ui.html


## Configuração do Ambiente

### Clonando o Repositório

Clone o repositório para sua máquina local usando o comando:

```bash
git clone https://github.com/robdig7x/apipay.git
cd apipay

#(linux)
./mvnw clean install
#(windows)
mvnw.cmd clean install

docker-compose up --build
```

## Arquivo CSV de Exemplo
```
dataVencimento,valor,descricao,situacao
2024-01-01,100.0,Conta de internet,PENDENTE
2024-01-05,150.0,Conta de luz,PAGA
```



### Exemplo de request da API
```
### Criar User
curl --request POST \
--url http://localhost:8080/api/users \
--header 'Content-Type: application/json' \
--cookie JSESSIONID=E7819DFD9C48D16620B3E62CA5755EEA \
--data '{
"username": "admin",
"password": "admin",
"role": "USER"
}'

### Cadastrar Conta
curl -X POST "http://localhost:8080/api/contas" -H "Content-Type: application/json" -d '{
  "dataVencimento": "2024-12-01",
  "dataPagamento": "2024-12-01",
  "valor": 100.0,
  "descricao": "Conta de luz",
  "situacao": "PENDING"
}'

### Atualizar Conta

curl -X PUT "http://localhost:8080/api/contas/{id}" -H "Content-Type: application/json" -d '{
  "dataVencimento": "2024-12-10",
  "dataPagamento": "2024-12-10",
  "valor": 200.0,
  "descricao": "Conta de água",
  "situacao": "PENDING"
}'

### Alterar Situação da Conta

curl -X PATCH "http://localhost:8080/api/contas/{id}/situacao" -H "Content-Type: application/json" -d '{
  "situacao": "PAID"
}'

### Obter Valor Total Pago por Período

curl -X GET "http://localhost:8080/api/contas/valor-total" -G 
--data-urlencode "start=2024-12-01" --data-urlencode "end=2024-12-31"

### Importar Arquivo CSV

curl -X POST "http://localhost:8080/api/contas/import" -H "Content-Type: multipart/form-data" -F "file=@path/to/your/file.csv"

