# 🛒 E-commerce Order Flow (Kotlin)

Projeto em Kotlin que simula o **fluxo de criação e validação de pedidos em um e-commerce**, com foco em **regras de negócio no domínio** e aplicação de **boas práticas inspiradas em Domain-Driven Design (DDD)**.

Este projeto está em construção e tem como objetivo estudo, evolução contínua e portfólio.

---

## 🎯 Objetivo do Projeto

- Modelar o fluxo de pedidos de um e-commerce
- Centralizar regras de negócio no domínio
- Evitar estados inválidos desde a criação das entidades
- Praticar Kotlin aplicado a backend / lógica de negócio
- Manter um projeto bem organizado e versionado desde o início

---

## 🧱 Tecnologias Utilizadas

- **Kotlin** (JVM)
- **Gradle** (Kotlin DSL)
- **Git & GitHub**
- Console application (por enquanto)

---

## 🧠 Conceitos Aplicados

- Domain-Driven Design (DDD)
- Entidades de domínio
- Encapsulamento de regras de negócio
- Imutabilidade
- Validações no construtor (`init`)
- Uso de `BigDecimal` para valores monetários
- Identidade com `UUID`

---

## 📦 Estrutura do Projeto

```text
src
└── main
    └── kotlin
        └── com.andersonrocha.ecommerce
            ├── Main.kt
            └── domain
                └── checkout
                    └── entity
                        ├── Order.kt
                        └── OrderItem.kt
