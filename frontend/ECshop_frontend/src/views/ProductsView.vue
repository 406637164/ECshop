<template>
  <div class="container">
    <div class="product-list">
      <div
          v-for="product in products"
          :key="product.productID"
          class="product-card"
      >
        <div @click="editProduct(product)">
          <p><strong>商品ID:</strong> {{ product.productID }}</p>
          <h3>{{ product.productName }}</h3>
          <p>價格：{{ product.price }}</p>
          <p>數量：{{ product.quantity }}</p>
        </div>
        <button @click.stop="deleteProduct(product.productID)" class="delete-btn">
          刪除
        </button>
      </div>
    </div>

    <!-- 浮動表單 -->
    <div class="floating-form">
      <button class="toggle-btn" @click="formOpen = !formOpen">
        {{ formOpen ? '收起表單' : isEditMode ? '修改商品' : '新增商品' }}
      </button>

      <transition name="fade">
        <div v-if="formOpen" class="form-panel">
          <h3>{{ isEditMode ? '修改商品' : '新增商品' }}</h3>

          <form @submit.prevent="submitForm">
            <div v-if="isEditMode">
              <label>商品ID</label>
              <input v-model="form.productID" readonly />
            </div>
            <div>
              <label>商品名稱</label>
              <input v-model="form.productName" required />
            </div>
            <div>
              <label>價格</label>
              <input type="number" v-model.number="form.price" required />
            </div>
            <div>
              <label>數量</label>
              <input type="number" v-model.number="form.quantity" required />
            </div>

            <div class="form-actions">
              <button type="submit">{{ isEditMode ? '更新' : '新增' }}</button>
              <button type="button" @click="resetForm">清空</button>
            </div>
          </form>
        </div>
      </transition>
    </div>
  </div>
</template>

<script>
import {
  getProducts,
  updateProduct,
  createProduct,
  deleteProductById,
} from "@/API"; // 封裝 API module

export default {
  data() {
    return {
      products: [],
      isEditMode: false,
      formOpen: false,
      form: {
        productID: null,
        productName: "",
        price: 0,
        quantity: 0,
      },
    };
  },
  created() {
    this.fetchProducts();
  },
  methods: {
    async fetchProducts() {
      try {
        const res = await getProducts();
        this.products = res.data;
      } catch (err) {
        console.error("取得商品失敗:", err);
        if (err.response?.status === 401) {
          alert("請先登入");
          this.$router.push("/login");
        }
      }
    },
    editProduct(product) {
      this.isEditMode = true;
      this.formOpen = true;
      this.form = { ...product };
    },
    async submitForm() {
      try {
        if (this.isEditMode) {
          await updateProduct(this.form.productID, this.form);
          alert("更新成功");
        } else {
          const { productName, price, quantity } = this.form;
          await createProduct({ productName, price, quantity });
          alert("新增成功");
        }
        this.fetchProducts();
        this.resetForm();
      } catch (err) {
        alert(this.isEditMode ? "更新失敗" : "新增失敗");
        console.error(err);
      }
    },
    resetForm() {
      this.form = {
        productID: null,
        productName: "",
        price: 0,
        quantity: 0,
      };
      this.isEditMode = false;
    },
    async deleteProduct(productID) {
      if (!confirm("確定要刪除這項商品嗎？")) return;
      try {
        await deleteProductById(productID);
        alert("刪除成功");
        this.fetchProducts();
      } catch (err) {
        console.error("刪除失敗:", err);
        alert("刪除失敗");
      }
    },
  },
};
</script>

<style scoped>
/* --- 你原本的 CSS 已經設計得不錯，保留不變 --- */
.container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  padding-bottom: 120px;
}
.product-list {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
}
.product-card {
  width: 200px;
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 12px;
  background: #fafafa;
  transition: 0.2s;
  position: relative;
}
.product-card:hover {
  background-color: #def;
}
.product-card > div {
  cursor: pointer;
}
.delete-btn {
  margin-top: 8px;
  background-color: #ff4d4f;
  color: white;
  border: none;
  padding: 6px 12px;
  border-radius: 6px;
  cursor: pointer;
  width: 100%;
}
.delete-btn:hover {
  background-color: #d9363e;
}
.floating-form {
  position: fixed;
  bottom: 20px;
  right: 20px;
  text-align: center;
  z-index: 999;
}
.toggle-btn {
  background-color: #409eff;
  border: none;
  color: white;
  padding: 10px 16px;
  border-radius: 20px;
  cursor: pointer;
  font-weight: bold;
  box-shadow: 0 2px 6px rgba(64, 158, 255, 0.6);
  margin-bottom: 8px;
}
form {
  text-align: center;
}
.form-panel {
  width: 300px;
  background: #fff;
  border: 1px solid #ccc;
  border-radius: 10px;
  padding: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
  max-height: 60vh;
  overflow-y: auto;
}
.form-panel h3 {
  margin-top: 0;
  margin-bottom: 12px;
}
.form-panel label {
  font-weight: bold;
  display: block;
  margin-bottom: 4px;
}
.form-panel input {
  width: 100%;
  padding: 6px 8px;
  margin-bottom: 12px;
  box-sizing: border-box;
}
.form-actions {
  display: flex;
  text-align: center;
  gap: 8px;
}
.form-actions button {
  flex: 1;
  padding: 8px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}
.form-actions button[type="submit"] {
  background-color: #409eff;
  color: white;
}
.form-actions button[type="button"] {
  background-color: #aaa;
  color: white;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
