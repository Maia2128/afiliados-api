# 🚀 Sistema de Promoções Automáticas (Telegram Bot)

Sistema backend desenvolvido com Java e Spring Boot para envio automático de promoções em um canal do Telegram.

---

## 💡 Funcionalidades

- Cadastro de produtos com preço, link afiliado e categoria
- Envio automático de mensagens para Telegram
- Sistema de fila (ordenação de produtos)
- Desativação automática após múltiplos envios
- Integração com API do Telegram
- API REST para gerenciamento dos produtos

---

## 🛠 Tecnologias utilizadas

- Java
- Spring Boot
- REST APIs
- JPA / Hibernate
- Banco de dados (H2)
- Git / GitHub

---

## ⚙️ Como funciona

1. Os produtos são cadastrados via API
2. O sistema organiza os produtos por ordem
3. Um scheduler envia automaticamente as promoções
4. Após X envios, o produto é desativado automaticamente

---

## 📡 Endpoints principais

### Produtos
- `GET /products`
- `POST /products`
- `PUT /products/{id}`
- `DELETE /products/{id}`

### Telegram
- `GET /telegram/test`
- `GET /telegram/send/all`
- `GET /telegram/send/product/{id}`

---

## 🔗 Projeto

Sistema desenvolvido como prática de backend e automação, com foco em integração com APIs e lógica de negócio.

---

## 👨‍💻 Autor

Erick Maia
