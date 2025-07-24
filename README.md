                                                                    歡迎來到電商平台
此案例示範如何透過Vue.js + Spring boot 進行電商平台開發


開發技術
前端
Vue 3
Vue Router
Axios
🖥後端
Spring Boot
JDBC Template / DAO 模式
Maven
Java 17+
RESTful API'
啟動方式


啟動前端

bash

cd ECShop_frontend

npm install

npm run serve

啟動後端

bash

cd ECShop

./mvnw spring-boot:run

預設後端埠口為 http://localhost:8080


預設前端埠口為 http://localhost:8081


 專案結構
前端

ECShop_frontend/
├── public/index

├── src/

│   ├── assets/         → 圖片、樣式等資源

│   ├── components/     → Vue 可重用元件

│   ├── router/         → Vue Router 設定

│   ├── views/          → 頁面元件 (如：Login、Register、Product)

│   ├── API.js          → API 請求設定

│   ├── App.vue         → 主元件

│   └── main.js         → Vue 進入點




後端系統架構說明（Spring Boot 三層式）
專案使用傳統 三層式架構 開發，分為：

1️⃣ Controller 層（控制器）
負責處理前端送來的 HTTP 請求，並將請求委託給 Service 層處理。




前後端連接方式
前端透過 Axios 呼叫後端 RESTful API，包含登入、註冊、商品查詢、購物車操作等功能。

後端需設定 CORS（跨來源資源共享）支援，例如使用：
java
@CrossOrigin(origins = "http://localhost:8081")


📁 controller/

UserController：處理會員註冊、登入等功能。

ProductController：商品瀏覽、查詢。

OrderController：下單、查看訂單、結帳功能。

2️⃣ Service 層（商業邏輯層）
處理主要商業邏輯，調用 DAO 層操作資料庫，並返回結果給 Controller。

📁 service/

各類 *Service 類別會對應使用者、商品、訂單等功能的業務邏輯。

3️⃣ DAO 層（資料存取層）
專責與資料庫互動，使用 Spring 的 JdbcTemplate 實作。

📁 dao/

介面（Interface）：
UserDao, ProductDao, OrderDao ..

實作類別（Impl）：
UserDaoImpl, ProductDaoImpl, OrderDaoImpl
 Model 模型層（資料模型）
對應資料庫表格的 Java 物件（Entity）。
 model/
User, Product, Order, OrderDetail, UserOrder, OrderRequest...
通常會搭配 RowMapper 將資料庫查詢結果轉換成物件。
RowMapper 映射層
負責把資料庫查詢結果的 Row 映射成 Java 物件。

Config 設定層
 config/
JwtFilter：JWT 驗證與過濾器設定
SecurityConfig：Spring Security 設定，用於登入驗證與路由保護
WebConfig：CORS 設定等

整體流程

Frontend (Vue)
     ↓
HTTP Request (e.g., /api/products)
     ↓
Controller（ProductController）
     ↓
Service（ProductService）
     ↓
DAO（ProductDao → ProductDaoImpl）
     ↓
DB（透過 JdbcTemplate 執行 SQL）
     ↓
RowMapper（ProductRowMapper）
     ↓
回傳結果至 Controller → 前端

採用 Spring Boot 快速啟動與整合框架。

分層明確，易於維護與擴充。

後端使用 JWT 驗證機制。

透過 DAO + RowMapper 實現 SQL 操作轉換。

支援 CORS，可與前端 Vue 進行跨網域請求。
