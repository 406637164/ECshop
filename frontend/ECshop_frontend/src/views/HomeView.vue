<template>
  <div class="container">
    <!-- 左邊商品列表 -->
    <div class="product-section">
      <h2>商品列表</h2>

      <ul class="product-list">
        <li
            v-for="product in products"
            :key="product.productID"
            class="product-item"
        >
          <h3>{{ product.productName }}</h3>
          <p>價格：{{ product.price }} 元</p>
          <p>庫存：{{ product.quantity }}</p>
          <button @click="addToCart(product)">加入購物車</button>
        </li>
      </ul>
    </div>

    <!-- 右下角購物清單按鈕 + 區塊 -->
    <div class="cart-toggle-container">
      <button class="toggle-btn" @click="toggleCart">
        {{ cartOpen ? '關閉購物清單' : `購物清單 (${cart.length})` }}
      </button>

      <transition name="slide-up">
        <aside
            v-if="cartOpen"
            class="cart-section"
        >
          <h2>購物清單</h2>
          <div v-if="cart.length === 0">尚無商品</div>
          <div v-else class="cart-list">
            <div
                v-for="item in cart"
                :key="item.productID"
                class="cart-item"
            >
              <h3>{{ item.productName }}</h3>
              <p>購買數量：{{ item.count }}</p>
              <p>單價：{{ item.price }} 元</p>
            </div>
            <div class="checkout-bar" v-if="cart.length > 0">
              <button class="checkout-btn" @click="checkout">結帳</button>
            </div>
          </div>
        </aside>
      </transition>
    </div>
  </div>
</template>
<script>

import {createOrder,fetchProducts} from '@/API';
export default {
  name: "HomeView",
  data() {
    return {
      products: [],
      cart: [],
      cartOpen: false,
    };
  },
  methods: {
    async fetchProducts() {
      try {
        const token = localStorage.getItem("token");


        const headers = {
          "Content-Type": "application/json"
        };

        if (token) {
          headers.Authorization = `Bearer ${token}`;

        }

        const response = await fetchProducts()


        this.products = response.data;
      } catch (error) {

        if (error.response && error.response.status === 401) {
          this.$router.push("/login");
        }
      }
    },

    async checkout() {
      try {
        const totalPrice = this.cart.reduce(
            (sum, item) => sum + item.price * item.count,
            0
        );

        // 產生訂單編號
        const now = new Date();
        const orderId =
            'Ms' +
            now.getFullYear().toString() +
            String(now.getMonth() + 1).padStart(2, '0') +
            String(now.getDate()).padStart(2, '0') +
            String(now.getHours()).padStart(2, '0') +
            String(now.getMinutes()).padStart(2, '0') +
            String(now.getSeconds()).padStart(2, '0');
        console.error(localStorage.getItem("userID"))
        const orderPayload = {
          order: {
            orderId: orderId,
            memberId: localStorage.getItem("userID"), // 建議改成動態取得登入 userId
            price: totalPrice,
            payStatus: 1
          },
          products: this.cart.map(item => ({
            productID: item.productID,
            productName: item.productName,
            price: item.price,
            quantity: item.count
          }))
        };

          await createOrder(orderPayload);
        alert(`訂單送出成功！訂單編號：${orderId}`);

        this.cart = [];
        this.cartOpen = false;
      } catch (error) {

        alert("訂單送出失敗");
      }
    },

    addToCart(product) {
      if (product.quantity <= 0) {
        alert(`抱歉，${product.productName} 庫存不足，無法再購買。`);
        return;
      }
      const cartItem = this.cart.find(item => item.productID === product.productID);
      if (cartItem) {
        cartItem.count += 1;
        product.quantity -= 1;
      } else {
        this.cart.push({
          productID: product.productID,
          productName: product.productName,
          price: product.price,
          count: 1,
        });
        product.quantity -= 1;
      }

      alert(`${product.productName} 已加入購物車！剩餘庫存：${product.quantity}`);

    },

    toggleCart() {
      this.cartOpen = !this.cartOpen;
    }
  },
  mounted() {
    this.fetchProducts();
  },
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  padding: 20px;
}

.product-list {
  padding: 0;
  margin: 0;
  list-style: none;
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
}

.product-item {
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 15px;
  max-width: 280px;
  width: 100%;
  box-sizing: border-box;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

.product-item button {
  margin-top: 12px;
  padding: 8px 12px;
  border: none;
  background-color: #409eff;
  color: white;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.product-item button:hover {
  background-color: #66b1ff;
}


.cart-toggle-container {
  position: fixed;
  bottom: 20px;
  right: 20px;
  z-index: 2000;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}


.toggle-btn {
  background-color: #409eff;
  border: none;
  color: white;
  padding: 10px 16px;
  border-radius: 25px;
  cursor: pointer;
  box-shadow: 0 2px 6px rgba(64, 158, 255, 0.7);
  margin-bottom: 10px;
  white-space: nowrap;
  font-weight: bold;
  transition: background-color 0.3s ease;
}
.toggle-btn:hover {
  background-color: #66b1ff;
}


.cart-section {
  width: 320px;
  max-height: 50vh;
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0,0,0,0.15);
  padding: 15px;
  overflow-y: auto;
}


.slide-up-enter-active,
.slide-up-leave-active {
  transition: all 0.3s ease;
}
.slide-up-enter-from,
.slide-up-leave-to {
  transform: translateY(20px);
  opacity: 0;
}
.slide-up-enter-to,
.slide-up-leave-from {
  transform: translateY(0);
  opacity: 1;
}


.cart-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.cart-item {
  border: 1px solid #888;
  border-radius: 6px;
  padding: 12px 16px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.15);
}
.checkout-btn {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 8px;
  cursor: pointer;
  font-size: 16px;
}

.checkout-btn:hover {
  background-color: #0056b3;
}
</style>
