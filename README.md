# Ecommerce Project（Backend Only）

## 專案簡介

- 此為 Spring boot 練習專案，使用 Spring Boot、Spring Data JPA 與 H2 Database 開發商品管理後端系統，負責處理產品的 CRUD 功能。
- 以三層式架構（Controller, Service, Repository）設計，便於維護和擴充。
- 前端 React 介面為課程提供，僅用於展示 API 回傳結果。

---

## 技術堆疊

### Backend

- Spring Boot 4
- Spring Web MVC
- Spring Data JPA
- H2 Database
- Lombok

### Tools

- Postman
- Git / GitHub

---

## 功能特色

### 1. RESTful API（CRUD）

商品管理 API：

| Method | Path             | 說明         |
| ------ | ---------------- | ------------ |
| GET    | `/products`      | 取得所有產品 |
| GET    | `/products/{id}` | 取得單一產品 |
| POST   | `/products`      | 新增產品     |
| PUT    | `/products/{id}` | 更新產品     |
| DELETE | `/products/{id}` | 刪除產品     |

---

### 2. 三層式架構（Controller / Service / Repository）

- **Controller**：提供 API 端點
- **Service**：封裝商業邏輯
- **Repository**：繼承 JpaRepository，自動完成資料庫 CRUD

---

### 3. Spring Data JPA 自動化資料模型

- `ProductRepo` 使用 JpaRepository<Product, Long>
- 不用自行撰寫 SQL，即可完成查詢、新增、更新、刪除

---

### 4. H2 Database

- Spring Boot 內建資料庫
- 可使用 `/h2-console` 查看資料表內容

---

## 截圖

<img width="2043" height="1447" alt="Image" src="https://github.com/user-attachments/assets/aec7e3c2-ef4a-49aa-9258-e41c9ae03a31" />
<img width="2045" height="1450" alt="Image" src="https://github.com/user-attachments/assets/0a469003-db06-4f35-89ba-ea4508e9a5e9" />

## 專案架構

```bash
backend/
    ├── controller/
    │     └── ProductController.java
    ├── service/
    │     └── ProductService.java
    ├── repo/
    │     └── ProductRepo.java
    └── model/
          └── Product.java
```
