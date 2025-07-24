                                                                  
此案例示範如何透過Vue.js + Spring boot 進行電商平台開發

# 🛒 電商平台專案

本專案為一個使用 **Vue.js + Spring Boot** 所開發的簡易電商平台，實作會員註冊、登入、商品瀏覽、下單與訂單查詢等功能，採用三層式架構設計，前後端分離，具備良好擴充性與維護性。

---

## 📚 技術架構

### 🔹 前端（Vue.js）

- Vue 3 + Options API
- Vue Router
- Axios
- NPM / Node.js

### 🔸 後端（Spring Boot）

- Spring Boot 3.x
- JDBC Template + DAO 模式
- Java 17+
- Maven
- RESTful API 設計
- JWT 驗證機制
- CORS 支援

---

## 📁 專案結構

### 🔹 前端目錄結構

ECShop_frontend/

├── public/

├

│ └── index.html

├

├── src/

├

│ ├── assets/ # 圖片、樣式資源

├

│ ├── components/ # 可重用 Vue 元件

├

│ ├── router/ # Vue Router 設定

├

│ ├── views/ # 頁面元件（Login、Register、Product）

├

│ ├── API.js # 封裝的 API 請求

├

│ ├── App.vue # 主元件

├

│ └── main.js # Vue 進入點


### 🔸 後端目錄結構

ECShop/

├── controller/                          # 控制器層，接收並處理前端請求

│   ├── UserController.java              # 會員註冊 / 登入

│   ├── ProductController.java           # 商品查詢 / 清單

│   └── OrderController.java             # 下單 / 查詢訂單 / 結帳
│
├── service/                             # 商業邏輯層

│   ├── UserService.java                 # 會員邏輯接口

│   ├── ProductService.java              # 商品邏輯接口

│   ├── OrderService.java                # 訂單邏輯接口

│   └── impl/                            # 服務實作類

│       ├── UserServiceImpl.java

│       ├── ProductServiceImpl.java

│       └── OrderServiceImpl.java

│
├── dao/                                 # DAO 資料庫存取層（含 Impl 實作）

│   ├── UserDao.java

│   ├── ProductDao.java

│   ├── OrderDao.java

│   └── impl/

│       ├── UserDaoImpl.java

│       ├── ProductDaoImpl.java

│       └── OrderDaoImpl.java

│
├── model/                               # Entity 類別（對應資料表）

│   ├── User.java

│   ├── Product.java

│   ├── Order.java

│   ├── OrderDetail.java
│   ├── OrderRequest.java                # 前端送出訂單使用的封裝類

│   └── UserOrder.java                   # 會員訂單查詢結果封裝

│
├── rowmapper/                           # RowMapper 層，映射 SQL 結果為物件

│   ├── ProductRowMapper.java

│   ├── UserRowMapper.java

│   └── OrderRowMapper.java

│

├── config/                              # 設定層（JWT、CORS、Security）

│   ├── JwtFilter.java                   # JWT 驗證過濾器

│   ├── SecurityConfig.java              # Spring Security 設定

│   └── WebConfig.java                   # CORS、靜態資源等設定
│
├── util/                                # 工具類別

│   ├── FuncUtil.java                    # 共用函式

│   └── JwtUtil.java                     # JWT Token 生成與驗證

│

├── resources/

│   └── DB.txt                           # 建表與測試資料 SQL

│

└── Application.java                     # Spring Boot 入口主程式



cd ECShop_frontend
npm install
npm run serve


預設埠口：http://localhost:8081




---

## 🔄 系統架構與資料流

```text
Vue 前端
    ↓ 發送 HTTP Request
Controller（控制器）
    ↓ 呼叫 Service 處理邏輯
Service（商業邏輯層）
    ↓ 呼叫 DAO
DAO（資料存取層）
    ↓ 使用 JdbcTemplate 執行 SQL
DB（MySQL 資料庫）
    ↑ 回傳查詢結果 → 映射為 Java 物件
DAO → Service → Controller → 前端

🔐 認證與安全性
使用 JWT 進行登入驗證與保護路由

後端設有跨網域設定，允許前端請求：

@CrossOrigin(origins = "http://localhost:8081")

🚀 專案啟動方式
🔹 前端啟動
bash

cd ECShop_frontend
npm install
npm run serve
預設埠口：http://localhost:8081

🔸 後端啟動
bash

cd ECShop
./mvnw spring-boot:run
預設埠口：http://localhost:8080

🧪 測試資料與資料表
請使用下列檔案建立資料表與初始資料：

/src/main/resources/DB.txt

可搭配資料庫工具（如 MySQL Workbench）執行。

✅ 實作功能一覽
 會員註冊 / 登入

 商品清單 / 查詢

 購物車模組

 下單 / 訂單明細

 商品庫存更新

 JWT 安全驗證

 前後端分離架構
