# Sistema de Contas a Pagar

Este projeto é uma API REST para um sistema simples de contas a pagar. Ele permite realizar operações CRUD em contas a pagar, alterar a situação delas quando um pagamento é efetuado, obter informações sobre as contas cadastradas no banco de dados e importar um lote de contas a partir de um arquivo CSV.

## Requisitos

- Java 17 ou superior
- Spring Boot 3.2.7
- PostgreSQL
- Docker e Docker Compose

## Configuração do Ambiente

### Clonando o Repositório

Clone o repositório para sua máquina local usando o comando:

```bash
git clone https://github.com/robdig7x/apipay.git
cd apipay

mvn clean install
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
# Criar User
curl --request POST \
--url http://localhost:8080/api/users \
--header 'Content-Type: application/json' \
--cookie JSESSIONID=E7819DFD9C48D16620B3E62CA5755EEA \
--data '{
"username": "admin",
"password": "admin",
"role": "USER"
}'

# buscar contas a pagar com filtro
curl --request GET \
  --url 'http://localhost:8080/api/contas?page=0&size=10&startDate=2024-06-10&endDate=2024-07-30&descricao=Conta' \
  --header 'Authorization: Basic YWRtaW46YWRtaW4=' \
  --cookie JSESSIONID=E7819DFD9C48D16620B3E62CA5755EEA
```