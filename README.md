# oanda-algo-trading
Algorithmic trading testing

Oanda API test curl command:
```bash
curl -H "Authorization: Bearer YOUR_ACCESS_TOKEN" \
"https://api-fxpractice.oanda.com/v3/accounts/{accountID}
```

---

## 1. **Get Candlestick Data**

**Endpoint:**

```
GET /instruments/{instrument}/candles
```

**Description:**  
Fetch candlestick data for a specific instrument, with optional parameters for granularity and count.

```bash
curl --location 'http://localhost:8080/instruments/EUR_USD/candles?granularity=M5&count=15' \
--header 'Content-Type: application/json'
```

**Parameters:**

- `instrument`: The instrument symbol (e.g., `EUR_USD`).
- `granularity` (optional): Candlestick granularity (e.g., `M1`, `M5`, `H1`). Defaults to `M1`.
- `count` (optional): Number of candlesticks to retrieve. Defaults to `10`.

---

## 2. **Get Order Book**

**Endpoint:**

```
GET /instruments/{instrument}/orderBook
```

**Description:**  
Fetch the order book for a specific instrument.

```bash
curl --location 'http://localhost:8080/instruments/EUR_USD/orderBook' \
--header 'Content-Type: application/json'
```

---

## 3. **Get Position Book**

**Endpoint:**

```
GET /instruments/{instrument}/positionBook
```

**Description:**  
Fetch the position book for a specific instrument.

```bash
curl --location 'http://localhost:8080/instruments/EUR_USD/positionBook' \
--header 'Content-Type: application/json'
```

---

## 4. **Get Account Details**

**Endpoint:**

```
GET /account/{accountId}
```

**Description:**  
Retrieve detailed information for a specific account.

```bash
curl --location 'http://localhost:8080/account/101-001-1234567-001' \
--header 'Content-Type: application/json'
```

---

## 5. **Get All Accounts**

**Endpoint:**

```
GET /accounts
```

**Description:**  
Retrieve a list of all accounts associated with your API token.

```bash
curl --location 'http://localhost:8080/accounts' \
--header 'Content-Type: application/json'
```

---

## 6. **Get Account Instruments**

**Endpoint:**

```
GET /account/{accountId}/instruments
```

**Description:**  
Retrieve a list of tradable instruments for a specific account.

```bash
curl --location 'http://localhost:8080/account/101-001-1234567-001/instruments' \
--header 'Content-Type: application/json'
```

---

## 7. **Get Account Summary**

**Endpoint:**

```
GET /account/{accountId}/summary
```

**Description:**  
Retrieve summary information for a specific account.

```bash
curl --location 'http://localhost:8080/account/101-001-1234567-001/summary' \
--header 'Content-Type: application/json'
```

---

## 8. **Create an Order**

**Endpoint:**

```
POST /accounts/{accountId}/orders
```

**Description:**  
Create a new order for a specific account.

```bash
curl --location --request POST 'http://localhost:8080/accounts/101-001-1234567-001/orders' \
--header 'Content-Type: application/json' \
--data-raw '{
    "instrument": "EUR_USD",
    "units": "1000",
    "timeInForce": "FOK",
    "type": "MARKET",
    "positionFill": "DEFAULT"
}'
```

---

## 9. **Get List of Orders**

**Endpoint:**

```
GET /accounts/{accountId}/orders
```

**Description:**  
Retrieve a list of all orders for a specific account.

```bash
curl --location 'http://localhost:8080/accounts/101-001-1234567-001/orders' \
--header 'Content-Type: application/json'
```

---

## 10. **Get Pending Orders**

**Endpoint:**

```
GET /accounts/{accountId}/pendingOrders
```

**Description:**  
List all pending orders in a specific account.

```bash
curl --location 'http://localhost:8080/accounts/101-001-1234567-001/pendingOrders' \
--header 'Content-Type: application/json'
```

---

### **Notes:**

- **Replace `{accountId}`** with your actual account ID (e.g., `101-001-1234567-001`).
- **Replace `{instrument}`** with the desired instrument symbol (e.g., `EUR_USD`).
- Ensure your application is running on `localhost` at port `8080`. Adjust the URL if your server is running on a different host or port.
- For the **Create an Order** endpoint, adjust the JSON payload in `--data-raw` according to the order details you wish to submit.

### **Example Workflow:**

1. **List All Accounts:**

    ```bash
    curl --location 'http://localhost:8080/accounts' \
    --header 'Content-Type: application/json'
    ```

2. **Get Account Summary:**

    ```bash
    curl --location 'http://localhost:8080/account/{accountId}/summary' \
    --header 'Content-Type: application/json'
    ```

3. **List Tradable Instruments for Account:**

    ```bash
    curl --location 'http://localhost:8080/account/{accountId}/instruments' \
    --header 'Content-Type: application/json'
    ```

4. **Fetch Candlestick Data for an Instrument:**

    ```bash
    curl --location 'http://localhost:8080/instruments/{instrument}/candles?granularity=M15&count=20' \
    --header 'Content-Type: application/json'
    ```

5. **Create a Market Order:**

    ```bash
    curl --location --request POST 'http://localhost:8080/accounts/{accountId}/orders' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "instrument": "{instrument}",
        "units": "1000",
        "timeInForce": "FOK",
        "type": "MARKET",
        "positionFill": "DEFAULT"
    }'
    ```

6. **List All Orders for Account:**

    ```bash
    curl --location 'http://localhost:8080/accounts/{accountId}/orders' \
    --header 'Content-Type: application/json'
    ```

7. **List Pending Orders for Account:**

    ```bash
    curl --location 'http://localhost:8080/accounts/{accountId}/pendingOrders' \
    --header 'Content-Type: application/json'
    ```

---