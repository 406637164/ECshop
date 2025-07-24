<template>
  <div class="register-container">
    <h2>註冊帳號</h2>
    <form @submit.prevent="handleRegister">
      <div>
        <label for="username">帳號：</label>
        <input type="text" v-model="form.username" id="username" required />
      </div>

      <div>
        <label for="password">密碼：</label>
        <input type="password" v-model="form.password" id="password" required />
      </div>

      <div>
        <label for="fullName">姓名：</label>
        <input type="text" v-model="form.fullName" id="fullName" required />
      </div>

      <div>
        <label for="email">信箱：</label>
        <input type="email" v-model="form.email" id="email" required />
      </div>


      <div>
        <label>角色：</label>
        <label>
          <input type="radio" value="1" v-model.number="form.groupId" />
          使用者
        </label>
        <label>
          <input type="radio" value="2" v-model.number="form.groupId" />
          管理者
        </label>
      </div>

      <button type="submit">註冊</button>
    </form>

    <div v-if="message" class="message">{{ message }}</div>
    <router-link to="/">返回</router-link>
  </div>
</template>

<script>

import {
   registerUser
} from "@/API"; // 封裝 API module

export default {
  name: 'RegisterView',
  data() {
    return {
      form: {
        username: '',
        password: '',
        fullName: '',
        email: '',
        groupId: 1
      },
      message: '',
    };
  },
  methods: {
    async handleRegister() {
      try {
        const response = await  registerUser(this.form)

        if (response.data && response.data.status === 200) {
          this.message = '註冊成功';
          this.$router.push('/'); // 跳轉到登入頁
        } else {
          this.message = response.data.message || '註冊失敗';
        }
      } catch (error) {
        console.error(error);
        this.message = '註冊失敗，請稍後再試';
      }
    },
  },
};
</script>

<style scoped>
.register-container {
  max-width: 400px;
  margin: 80px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
}
.register-container div {
  margin-bottom: 15px;
}
.message {
  margin-top: 15px;
  color: red;
}
</style>
