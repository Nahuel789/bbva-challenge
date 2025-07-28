# Customer and Bank Products Management API

This project is a RESTful API developed with Spring Boot to manage customers and bank products. The database used is in-memory H2.

---

## 🚀 How to use the app

### 1. Product Initialization

When the application starts, **4 bank products** are automatically inserted into the H2 database.

### 2. Authentication (generate token)

To access the protected endpoints, you first need to generate a JWT token.

Use this `curl` command:

```bash
curl --location --request POST 'http://localhost:8080/auth/token?username=INSERTUSERNAME'
```

the JWT will be available 15 minutes

### 3. Create a product (using your new JWT)

```bash
curl --location 'http://localhost:8080/products' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYWh1ZWxjYXJiYWphbCIsImlhdCI6MTc1MzQ2NzUyOSwiZXhwIjoxNzUzNTAzNTI5fQ.2ixEdy6zxOjF3ik-sBiNnSNUTnuPIWxuFOaQ_k2BUec' \
--data '{
"product":"TJRANDOM"
}
'
```

### 4. See the products (with your JWT)

```bash
curl --location 'http://localhost:8080/products' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYWh1ZWxjYXJiYWphbCIsImlhdCI6MTc1MzQ3MDAwOSwiZXhwIjoxNzUzNTA2MDA5fQ.NC_WFH1zFlp7idfMJ73MsGk_b8Yq98buD9Mtz79okuc'
```
### 5. Create a customer (with JWT)

```bash
curl --location 'http://localhost:8080/customers' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYWh1ZWxjYXJiYWphbCIsImlhdCI6MTc1MzQ2NzUyOSwiZXhwIjoxNzUzNTAzNTI5fQ.2ixEdy6zxOjF3ik-sBiNnSNUTnuPIWxuFOaQ_k2BUec' \
--data '{
  "id": 1234567891,
  "firstName": "Nahuel",
  "lastName": "Carbajal",
  "street": "Calle Falsa",
  "number": 123,
  "postalCode": 1414,
  "phone": "0114567890",
  "mobilePhone": "1134567890",
  "bankProducts": [
    "TJDEBITO"
  ]
}
'
```

### 10. Available Endpoints

#### Authentication
- **POST** `/auth/token`: Generates a JWT token for the specified user.

#### Customers
- **GET** `/customers`: Retrieves all customers.
- **GET** `/customers/{id}`: Retrieves a customer by ID.
- **POST** `/customers`: Creates a new customer.
- **PUT** `/customers/{id}`: Updates an existing customer.
- **DELETE** `/customers/{id}`: Deletes a customer by ID.
- **PATCH** `/customers/{id}/phone`: Updates phone information.

#### Products
- **POST** `/products`: Creates a new product.
- **GET** `/products`: Retrieves all products.
---

## 🛠️ install sonarqube and create the proyect bbva-challenge, then run this command to see the analysis: (modifying the token)
```
mvn clean verify sonar:sonar   -Dsonar.projectKey=bbva-challenge   -Dsonar.projectName='bbva-challenge'   -Dsonar.host.url=http://localhost:9000   -Dsonar.token=sqp_2bf7a64b81a2266df4f7540d0d48797da5f3df3c
```
